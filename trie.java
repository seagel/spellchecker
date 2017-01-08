import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

class trie
{
	private node root;
	trie()
	{
		root=new node();
	}
	public void insert(String s)
	{
		node p=root;
		for(int i=0;i<s.length();i++)
		{
			char c=s.charAt(i);
			if(p.arr[c]!=null)
			{
				p=p.arr[c];
			}
			else
			{
				node temp=new node();
				//System.out.println(temp);
				p.arr[c]=temp;
				//System.out.println(p.arr[c-'a']);
				p=temp;
			}
			if(i==s.length()-1)
			{
				p.isleaf=true;
			}
		}
	}
	public boolean  search(String s)
	{
		node temp=searchnode(s);
		if(temp!=null&&temp.isleaf==true)
		{
			return true;
		}
		else
		{
			//System.out.println("hi");
			return false;
		}
	}
	public node searchnode(String s)
	{
		node p=root;
		for(int i=0;i<s.length();i++)
		{
			char c=s.charAt(i);
			//System.out.println(c);
			//System.out.println(p.arr[c-'a']);
			if(p.arr[c]!=null)
			{
				p=p.arr[c];
			}
			else
			{
				return null;
			}
		}
		return p;
	}
	public void searchprefix(String s)
	{
		node temp=searchnode(s);
		if(temp!=null)
		{
			printwordwithprefix(temp,s);
		}
		else
		{
			System.out.println("NO words exist in dictionary");
		}
	}
	public  void printwordwithprefix(node temp,String s)
	{
		if(temp.isleaf==true)
		{
			System.out.println(s);
		}
		for(int i=0;i<136;i++)
		{
			if(temp.arr[i]!=null)
			{
				char c=(char)i;
				printwordwithprefix(temp.arr[i],s+c);
			}
		}
	}
	public Map<String,Integer> suggestions(String word)
	{
		Map<String,Integer> suggestedword=new HashMap<String,Integer>();
		suggestedword.put(word,1);
		Map<String,Integer> edit1=suggestwords(suggestedword);
		Map<String,Integer > edit2=suggestwords(edit1);
		Map<String,Integer> edit3=new HashMap<String,Integer>();
		edit3.putAll(edit1);
		edit3.putAll(edit2);
		Iterator<Entry<String,Integer>> it=edit1.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<String,Integer> qe=it.next();
			
				System.out.println(qe.getKey());
		}
		return edit3;
	}
	public Map<String,Integer> suggestwords(Map<String,Integer> suggestedword)
	{
		Map<String,Integer> temp=new HashMap<String,Integer >();
		//deletion
		Iterator<Entry<String, Integer>> it=suggestedword.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<String,Integer> current_entry=it.next();
			for(int i=0;i<current_entry.getKey().length();i++)
			{
				String word=current_entry.getKey().substring(0,i)+current_entry.getKey().substring(i+1);
				if(this.search(word)==true)
				{
					//System.out.println(word);
					temp.put(word,1);
				}
			}
		}
		//transpose
		it=suggestedword.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<String,Integer> current_entry=it.next();
			for(int i=0;i<current_entry.getKey().length()-1;i++)
			{
				String word=current_entry.getKey().substring(0,i)+current_entry.getKey().substring(i+1,i+2)+current_entry.getKey().substring(i,i+1)+current_entry.getKey().substring(i+2);
				if(this.search(word)==true)
				{
					//System.out.println(word);
					temp.put(word,1);
				}
			}
		}
		//insertion
		it=suggestedword.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<String,Integer> current_entry=it.next();
			for(int i=0;i<current_entry.getKey().length();i++)
			{
				for(char j='a';j<='z';j++)
				{
					String word=current_entry.getKey().substring(0,i)+j+current_entry.getKey().substring(i);
					if(this.search(word)==true)
					{
						//System.out.println(word);
						temp.put(word,1);
					}
				}
			}
		}
		//substitution
				it=suggestedword.entrySet().iterator();
				while(it.hasNext())
				{
					Entry<String,Integer> current_entry=it.next();
					for(int i=0;i<current_entry.getKey().length();i++)
					{
						for(char j='a';j<='z';j++)
						{
							String word=current_entry.getKey().substring(0,i)+j+current_entry.getKey().substring(i+1);
							//System.out.println(word);
							if(this.search(word)==true)
							{
								temp.put(word,1);
							}
						}
					}
				}
				return temp;
				
	}
}























