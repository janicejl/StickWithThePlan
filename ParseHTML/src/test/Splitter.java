package test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Splitter {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in); 

		String url = "";

		while (!url.equals("~")) {
			System.out.println("Enter Department URL to split, enter '~' to stop:  ");
			url = in.nextLine();

			split(url);
		}
	}
	
	public static void split(String url) {
		Document doc = null;

		//IF NO INTERNET
		File input = new File("tmp/MATH.html");
		
		System.out.println("Opening...");

		try {
			//doc = Jsoup.connect(url).get();
			//IF NO INTERNET
			doc = Jsoup.parse(input, "UTF-8", "");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Successfully Opened...");

		System.out.println("Splitting...");
		
		System.out.println();

		Elements course  = doc.select("p");
		
		for(Element src : course) {
			
			ArrayList<String> split = new ArrayList<String>();
			
			System.out.println(src.text());

			String c = src.text();
			
			String[] weirdSplit = c.split("Ê"); 	//for weird space character;
			
			for (String s : weirdSplit) {
				String [] spaceSplit = s.split(" ");
				for (String sp :spaceSplit) {
					split.add(sp);
				}
			}
			
			//String[] split = c.split("Ê");
			
			for (String s : split) {
				System.out.println("|###|" + s + "|###|");
			}
			
			System.out.println();
		}
		
	}

}

