package CourseRequirements;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CourseRequirement {
	
	public static void main (String[] args) {
		
		Document doc = null;
		
		try {
			doc = Jsoup.connect("https://catalogue.usc.edu/schools/engineering/computer-science/undergraduate/").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements course  = doc.select("td");
		
		for (Element src: course) {
			if (src.attr("width").equals("100"))
			System.out.println(src.text());
		}
		
	}

}
