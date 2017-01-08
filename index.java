import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
public class index 
{
	Spellchecker spellchecker;
	JFrame mainframe;
	JLabel headerpanel;
	JPanel controlpanel2,controlpanel1;
	index()
	{
		mainframe=new JFrame();
		mainframe.setSize(400, 400);
		mainframe.setVisible(true);
		mainframe.setLayout(new GridLayout(3,1));
		//mainframe.setBackground(Color.blue);
		init_header();
		try {
			init_controlpanel1();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init_controlpanel2();
		
	}
	public void init_controlpanel1() throws IOException
	{
		JLabel icon=new JLabel();
		icon.setIcon(new ImageIcon("src/image.jpg"));
		controlpanel1=new JPanel();
		controlpanel1.setSize(200, 100);
		controlpanel1.add(icon);
		mainframe.add(controlpanel1);
	}
	public void init_header()
	{
		headerpanel=new JLabel("Welcome to SpellChecker",JLabel.CENTER);
		headerpanel.setSize(250, 100);
		headerpanel.setVisible(true);
		mainframe.add(headerpanel);
	}
	public void init_controlpanel2()
	{
		JButton about=new JButton("About");
		about.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		about.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JFrame info=new JFrame();
				info.setSize(400, 400);
				info.setVisible(true);
				JLabel label=new JLabel("It scans the text and extracts the words contained in it");
				info.add(label);
			}
			
		});
		JButton proceed=new JButton("Continue");
		proceed.setAlignmentX(JButton.LEFT_ALIGNMENT);
		proceed.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				mainframe.setVisible(false);
				new mainui();
			}
			
		});
		controlpanel2=new JPanel();
		controlpanel2.setLayout(new FlowLayout());
		controlpanel2.setSize(250, 100);
		controlpanel2.add(proceed);
		controlpanel2.add(about);
		mainframe.add(controlpanel2);

	}
	public static void main(String args[])
	{
		new index();
	}
}
