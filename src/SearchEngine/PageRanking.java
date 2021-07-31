package SearchEngine;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PageRanking {
     
	private static String regwordexp = "[[ ]*|[,]*|[)]*|[(]*|[\"]*|[;]*|[-]*|[:]*|[']*|[’]*|[\\.]*|[:]*|[/]*|[!]*|[?]*|[+]*]+";
	
	// To calculate the occurence of searched word in a web page and find page ranking. 
	public static int WordOcuurence(String URL, String WORD) throws IOException
		{
		Document webpages = Jsoup.connect(URL).get();
		String content = webpages.body().text();
		Map<String, WordElement> map_content = new HashMap<String, WordElement>();
		BufferedReader brd = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8))));
		String s;
		
		while((s = brd.readLine()) != null)
		{
			String word [] = s.split(regwordexp);
			for (String allwords : word)
			{
				if("".equals(allwords))
				{
					continue;
				}
				
				WordElement woel = map_content.get(allwords);
				
				if(allwords.equalsIgnoreCase(WORD))
				{
					if(woel == null)
					{
						woel = new WordElement();
						woel.word = allwords;
						woel.count = 0;
						map_content.put(allwords,woel);
					}
					woel.count++;
				}
			}
		}
		brd.close();
		SortedSet<WordElement> sort = new TreeSet<WordElement>(map_content.values());
		int p = 0;
		int max = 1000;
		
		LinkedList <String> unusedWords = new LinkedList <>();
		try {
			BufferedReader wordbr = new BufferedReader(new FileReader("stopwords.txt"));
			String w;
			while ((w = wordbr.readLine()) != null)
			{
				unusedWords.add(w);
			}
			wordbr.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Oops!! Sorry..The desired word not found");
		}
		
		for(WordElement words : sort) {
			if(p >= max)
			{
				break;
			}
			if (unusedWords.contains(words.word))
			{
				p++;
				max++;
			}
			else
			{
				p++;
				return words.count;
			}
		}
		return 0;
	}
	
	public static class WordElement implements Comparable<WordElement>
	{
		String word;
		int count;

		@Override
		public int hashCode() {
			return word.hashCode();
		}
		@Override
		public boolean equals(Object object) {
			return word.equals(((WordElement) object).word);
		}
		@Override
		public int compareTo(WordElement we) {
			return we.count - count;
		}	
	}
}

