import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;


public class mainui 
{
	private JFrame mainframe;
	private JLabel header;
	private JPanel toolspanel,filepanel;
	private Spellchecker dictionary;
	private JTextArea textarea;
	private ArrayList<String> wrongword;
	private DefaultListModel<String> model;
	mainui()
	{
		dictionary=new Spellchecker();
		mainframe=new JFrame("Spellchecker");
		mainframe.setSize(1500, 1500);
		mainframe.setLayout(new GridLayout(3,1));
		mainframe.setVisible(true);
		wrongword=new ArrayList<String>();
		init_header();
		init_filepanel();
		init_toolspanel();
	}
	public void init_header()
	{
		header=new JLabel("Get It Right-Spellchecker",JLabel.CENTER);
		header.setFont(new Font("Serif",Font.PLAIN,23));
		header.setSize(250,100);
		mainframe.add(header);
	}
	public void init_filepanel()
	{
		filepanel=new JPanel();
		filepanel.setLayout(new FlowLayout());
		filepanel.setSize(700,700);
		textarea=new JTextArea(15,50);
		JScrollPane scrollpane=new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		filepanel.add(scrollpane);
		mainframe.add(filepanel);
	}
	public void init_toolspanel()
	{
		toolspanel=new JPanel();
		toolspanel.setLayout(new GridLayout(1,3));
		init_panel_1();
	}
	public void suggestions_ui(JTextField text_1)
	{
		String text=textarea.getText();
		Highlighter highlighter = textarea.getHighlighter();
	      HighlightPainter painter = 
	             new DefaultHighlighter.DefaultHighlightPainter(Color.red);
	      String t=wrongword.get(0);
	      int p0 = text.indexOf(t);
	      int p1 = p0 + t.length();
	      try {
			highlighter.addHighlight(p0, p1, painter );
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     text_1.setText(t);
	     model.clear();
	     Iterator<Entry<String,Integer>> it=dictionary.dictionary.suggestions(wrongword.get(0)).entrySet().iterator();
	     while(it.hasNext())
	     {
	    	 model.addElement(it.next().getKey());
	     }
	}
	public void init_panel_1()
	{
		JPanel panel_1=new JPanel();
		panel_1.setLayout(new BorderLayout());
		JLabel label_1=new JLabel("Suggestions");
		label_1.setFont(new Font("Serif",Font.BOLD,14));
		panel_1.add(label_1,BorderLayout.NORTH);
		model=new DefaultListModel<String>();
		final JList<String> list=new JList<String>(model);
		JScrollPane scroll=new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_1.add(scroll,BorderLayout.CENTER);
		toolspanel.add(panel_1);
		JPanel panel_2=new JPanel();
		panel_2.setLayout(new GridLayout(3,1,4,0));
		JPanel spellpanel=new JPanel();
		spellpanel.setLayout(new GridLayout(2,1));
		JLabel label_2=new JLabel("Misspelled word");
		label_2.setFont(new Font("Serif",Font.BOLD,14));
		final JTextField text_1=new JTextField();
		spellpanel.add(label_2);
		spellpanel.add(text_1);
		panel_2.add(spellpanel);
		JPanel correctionpanel=new JPanel();
		correctionpanel.setLayout(new GridLayout(2,1));
		JLabel label_3=new JLabel("Replace with");
		label_3.setFont(new Font("Serif",Font.BOLD,14));
		final JTextField text_2=new JTextField();
		correctionpanel.add(label_3);
		correctionpanel.add(text_2);
		panel_2.add(correctionpanel);
		list.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) 
			{
				text_2.setText(list.getSelectedValue());
			}
			
		});
		JButton button_1=new JButton("Start Checking");
		button_1.addActionListener(new ActionListener(){
		
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String text=textarea.getText();
					for(String t:text.split(" "))
					{
						if(dictionary.dictionary.search(t)==false)
							wrongword.add(t);
					}
					suggestions_ui(text_1);
			}
		});
		button_1.setSize(50, 50);
		panel_2.add(button_1);
		toolspanel.add(panel_2);
		JPanel panel_3=new JPanel();
		panel_3.setLayout(new GridBagLayout());
		JButton button_2=new JButton("Replace");
		button_2.setSize(10, 10);
		button_2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String text=textarea.getText();
				int start=text.indexOf(wrongword.get(0));
				String newword=text_2.getText();
				textarea.replaceRange(newword, start, start+wrongword.get(0).length());
				wrongword.remove(0);
				suggestions_ui(text_1);
			}
			
		});
		JButton button_3=new JButton("Add to Dictionary");
		button_3.setSize(10, 10);
		panel_3.add(button_2);
		panel_3.add(button_3);
		toolspanel.add(panel_3);
		mainframe.add(toolspanel);
	}
}
