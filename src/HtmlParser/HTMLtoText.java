package HtmlParser;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLtoText {

	public static void HTMLtoText(File file1, String FileName) throws IOException {
		
		Document d = Jsoup.parse(file1, "utf-8");
		
		String doc = d.text();
		
		String filepath ="C:\\Users\\admin\\eclipse-workspace\\Search Engine\\W3WebPages\\Text" + FileName + ".txt";
		PrintWriter p = new PrintWriter(filepath);
		
		p.println(doc);
		
		p.close();

	}

}
