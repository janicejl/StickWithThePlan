package CourseList;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import USC.Course;

public class CourseList {
	public static void main (String[] args) {
		
		//"https://catalogue.usc.edu/schools/engineering/computer-science/courses/"

		//CSCI200, CSCI511, CSCI 477ab
		
		
		Scanner in = new Scanner(System.in); 
		
		String url = "";
		
		while (!url.equals("~")) {
			System.out.println("Enter Department URL to parse, enter '~' to stop:  ");
			url = in.nextLine();
			
			parseDepartment(url);
		}
		
	}
	
	public static void parseDepartment(String url) {
		Document doc = null;
		
		System.out.println("Opening...");
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Successfully Opened...");
		
		System.out.println("Parsing...");
		
		Elements course  = doc.select("p");
		Elements courseID = doc.select("b");
		
		String code;
		String number;
		String name;
		int units;
		String description;
		Boolean spring;
		Boolean summer;
		Boolean fall;
		
		for (int k = 2; k < course.size(); k++) {
			code = "";
			number = "";
			name = "";
			units = 0;
			description = "";
			spring = false;
			summer = false;
			fall = false;
			
			Element src = course.get(k);
			
			System.out.println(src.text());
			
			String c = src.text();
			
			String[] split = c.split(" ");
			
			for (int i = 0; i < split.length; i++) {
				if (i == 0) {
					code = split[0];
				} else if (i == 1) {
					number = split [1];
				} else if (i == 2) {
					while (split[i].startsWith("(") == false 
							|| (split[i].charAt(1) != '0'
							&& split[i].charAt(1) != '1'
							&& split[i].charAt(1) != '2'
							&& split[i].charAt(1) != '3'
							&& split[i].charAt(1) != '4'
							&& split[i].charAt(1) != '5'
							&& split[i].charAt(1) != '6'
							&& split[i].charAt(1) != '7'
							&& split[i].charAt(1) != '8'
							&& split[i].charAt(1) != '9')) {
						name = name + " " + split[i];
						i++;
					}
					
					if (split[i].charAt(2) == ')' && split[i].length() == 3) {
						units = Integer.parseInt(""+split[i].charAt(1));
						//i++;
					} else if (split[i].charAt(2) == ',' && split[i].length() == 3) {
						units = Integer.parseInt(""+split[i].charAt(1));
						if (split[i+1].contains(")") != true) {
							units = 0;
							description = description + split[i];
							i++;
							while (split[i].contains(")") != true && split[i].contains(",") != true) {
								description = description + " " + split[i];
								i++;
							}
							description = description + " ";
							for (int j = 0; j < split[i].length() - 1; j++) {
								description = description + split[i].charAt(j);
							}
							description = description + ")";
						}
						i++;
						
						if (split[i].contains("Sp") == true) {
							spring = true;
						}
						if (split[i].contains("Sm") == true) {
							summer = true;
						} 
						if (split[i].contains("Fa") == true) {
							fall = true;
						}
						//i++;
					} else {
						description = description + split[i] + " " + split[i+1];
						i++;
					}
				} else {
					description = description + " " + split[i];
				}
			}
			
			System.out.println("Code: " + code);
			System.out.println("Number: " + number);
			System.out.println("Name: " + name);
			System.out.println("Units: " + units);
			System.out.println("Description: " + description);
			System.out.println("Offered in Spring: " + spring);
			System.out.println("Offered in Summer: " + summer);
			System.out.println("Offered in Fall: " + fall);
		}
		
		System.out.println("Finished parsing... File outputted to ###NONEXISTENT ATM##");
	}
}
