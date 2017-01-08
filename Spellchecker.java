import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Spellchecker
{
	public trie dictionary;
	Spellchecker()
	{
		 dictionary=new trie();
		try {
			FileReader reader=new FileReader("src/words.txt");
			@SuppressWarnings("resource")
			BufferedReader r=new BufferedReader(reader);
			String line;
			while((line=r.readLine())!=null)
			{
				dictionary.insert(line);
			}
			
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}