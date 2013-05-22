package CourseList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.Scanner;


import USC.Course;

public class CourseList_Bad {
	public static void main (String[] args) {

		//https://catalogue.usc.edu/schools/engineering/computer-science/courses/
		//https://catalogue.usc.edu/schools/college/math/courses/
		//https://catalogue.usc.edu/schools/architecture/courses/
		//https://catalogue.usc.edu/schools/business/courses/buad-2/



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

		//IF NO INTERNET
		File input = new File("tmp/CSCI.html");
		
		System.out.println("Opening...");

		try {
			//doc = Jsoup.connect(url).get();
			//IF NO INTERNET
			doc = Jsoup.parse(input, "UTF-8", "");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Successfully Opened...");

		System.out.println("Parsing...");

		Elements course  = doc.select("p");

		String dept = "";		// department name (example CSCI)
		String number;			// course number (example 101)
		String name;			// example (Fundamentals of Computer Programming)
		int units;				// number of units
		String description;		// Description for the course
		Boolean spring;			// if offered in spring
		Boolean summer;			// if offered in summer
		Boolean fall;			// if offered in fall
		
		int maxUnits;
		
		//for ab, abc, abcd etc courses;
		
		Boolean ab;
		Boolean abc;
		Boolean abcd;
		Boolean abz;
		Boolean abcz;
		Boolean abcdz;
		
		String numberA;
		int unitsA;
		Boolean springA;
		Boolean summerA;
		Boolean fallA;
		
		String numberB;
		int unitsB;
		Boolean springB;
		Boolean summerB;
		Boolean fallB;
		
		String numberC;
		int unitsC;
		Boolean springC;
		Boolean summerC;
		Boolean fallC;
		
		String numberD;
		int unitsD;
		Boolean springD;
		Boolean summerD;
		Boolean fallD;
		
		String numberZ;
		int unitsZ;
		Boolean springZ;
		Boolean summerZ;
		Boolean fallZ;
		

		ArrayList<Course> deptList = new ArrayList<Course>();
		ArrayList<String> errorList = new ArrayList<String>();

		for(Element src : course) {
			//for (int k = 2; k < course.size(); k++) {
			dept = "";
			number = "";
			name = "";
			units = 0;
			description = "";
			spring = false;
			summer = false;
			fall = false;
			
			maxUnits = 0;

			ab = false;
			abc = false;
			abcd = false;
			abz = false;
			abcz = false;
			abcdz = false;
			
			numberA = "";
			unitsA = 0;
			springA = false;
			summerA = false;
			fallA = false;
			
			numberB = "";
			unitsB = 0;
			springB = false;
			summerB = false;
			fallB = false;
			
			numberC = "";
			unitsC = 0;
			springC = false;
			summerC = false;
			fallC = false;
			
			numberD = "";
			unitsD = 0;
			springD = false;
			summerD = false;
			fallD = false;
			
			numberZ = "";
			unitsZ = 0;
			springZ = false;
			summerZ = false;
			fallZ = false;
			
			//Element src = course.get(k);
			
			ArrayList<String> split = new ArrayList<String>();

			System.out.println(src.text());

			String c = src.text();

			String[] weirdSplit = c.split("Ê"); 	//for weird character;

			for (String s : weirdSplit) {
				String [] spaceSplit = s.split(" ");
				for (String sp :spaceSplit) {
					split.add(sp);
				}
			}
			
			
			//String[] split = c.split(" ");

			try {
				if (split.get(0).toUpperCase().equals(split.get(0))) {
					for (int i = 0; i < split.size(); i++) {
						if (i == 0) {
							dept = split.get(0);
						} else if (i == 1) {
							number = split.get(1);
							
							if (number.contains("abcdz") == true) {
								abcdz = true;
							} else if (number.contains("abcd") == true) {
								abcd = true;
							} else if (number.contains("abcz") == true) {
								abcz = true;
							} else if (number.contains("abc") == true) {
								abc = true;
							} else if (number.contains("abz") == true) {
								abz = true;
							} else if (number.contains("ab") == true) {
								ab = true;
							}
							
							for (int j = 0; j < number.length(); j++) {
								if (number.charAt(j) == 'a') {
									numberA = numberA + number.charAt(j);
								} else if (number.charAt(j) == 'b') {
									numberB = numberB + number.charAt(j);
								} else if (number.charAt(j) == 'c') {
									numberC = numberC + number.charAt(j);
								} else if (number.charAt(j) == 'd') {
									numberD = numberD + number.charAt(j);
								} else if (number.charAt(j) == 'z') {
									numberZ = numberZ + number.charAt(j);
								}else {
									numberA = numberA + number.charAt(j);
									numberB = numberB + number.charAt(j);
									numberC = numberC + number.charAt(j);
									numberD = numberD + number.charAt(j);
									numberZ = numberZ + number.charAt(j);
								}
							}
							
						} else if (i == 2) {
							name = name + split.get(i);
							i++;
							if (split.get(i).equals("(a:") == false) {
								while (split.get(i).charAt(0) != '('
										|| (split.get(i).charAt(1) != '0'
										&& split.get(i).charAt(1) != '1'
										&& split.get(i).charAt(1) != '2'
										&& split.get(i).charAt(1) != '3'
										&& split.get(i).charAt(1) != '4'
										&& split.get(i).charAt(1) != '5'
										&& split.get(i).charAt(1) != '6'
										&& split.get(i).charAt(1) != '7'
										&& split.get(i).charAt(1) != '8'
										&& split.get(i).charAt(1) != '9')) {
									name = name + " " + split.get(i);
									i++;

									if (split.get(i).equals("(a:")) {
										break;
									}
								}
							}

							if (split.get(i).contains("-") && split.get(i).length() == 11 && abcdz == true ) {
								unitsA = Integer.parseInt(""+split.get(i).charAt(1));
								unitsB = Integer.parseInt(""+split.get(i).charAt(3));
								unitsC = Integer.parseInt(""+split.get(i).charAt(5));
								unitsD = Integer.parseInt(""+split.get(i).charAt(7));
								unitsZ = Integer.parseInt(""+split.get(i).charAt(9));
								

								if (split.get(i).charAt(split.get(i).length()-1) == ')') {
									continue;
								}
								i++;
								
								if (split.get(i).contains("Sp") == true) {
									spring = true;
									springA = true;
									springB = true;
									springC = true;
									springD = true;
									springZ = true;
								}
								if (split.get(i).contains("Sm") == true) {
									summer = true;
									summerA = true;
									summerB = true;
									summerC = true;
									summerD = true;
									summerZ = true;
								} 
								if (split.get(i).contains("Fa") == true) {
									fall = true;
									fallA = true;
									fallB = true;
									fallC = true;
									fallD = true;
									fallZ = true;
								}
							} else if (split.get(i).contains("-") && split.get(i).length() == 9 && abcd == true ) {
								unitsA = Integer.parseInt(""+split.get(i).charAt(1));
								unitsB = Integer.parseInt(""+split.get(i).charAt(3));
								unitsC = Integer.parseInt(""+split.get(i).charAt(5));
								unitsD = Integer.parseInt(""+split.get(i).charAt(7));

								if (split.get(i).charAt(split.get(i).length()-1) == ')') {
									continue;
								}
								i++;
								
								if (split.get(i).contains("Sp") == true) {
									spring = true;
									springA = true;
									springB = true;
									springC = true;
									springD = true;
								}
								if (split.get(i).contains("Sm") == true) {
									summer = true;
									summerA = true;
									summerB = true;
									summerC = true;
									summerD = true;
								} 
								if (split.get(i).contains("Fa") == true) {
									fall = true;
									fallA = true;
									fallB = true;
									fallC = true;
									fallD = true;
								}
							} else if (split.get(i).contains("-") && split.get(i).length() == 9 && abcz == true ) {
								unitsA = Integer.parseInt(""+split.get(i).charAt(1));
								unitsB = Integer.parseInt(""+split.get(i).charAt(3));
								unitsC = Integer.parseInt(""+split.get(i).charAt(5));
								unitsZ = Integer.parseInt(""+split.get(i).charAt(7));

								if (split.get(i).charAt(split.get(i).length()-1) == ')') {
									continue;
								}
								i++;
								
								if (split.get(i).contains("Sp") == true) {
									spring = true;
									springA = true;
									springB = true;
									springC = true;
									springZ = true;
								}
								if (split.get(i).contains("Sm") == true) {
									summer = true;
									summerA = true;
									summerB = true;
									summerC = true;
									summerZ = true;
								} 
								if (split.get(i).contains("Fa") == true) {
									fall = true;
									fallA = true;
									fallB = true;
									fallC = true;
									fallZ = true;
								}
							} else if (split.get(i).contains("-") && split.get(i).length() == 7 && abc == true ) {
								unitsA = Integer.parseInt(""+split.get(i).charAt(1));
								unitsB = Integer.parseInt(""+split.get(i).charAt(3));
								unitsC = Integer.parseInt(""+split.get(i).charAt(5));

								if (split.get(i).charAt(split.get(i).length()-1) == ')') {
									continue;
								}
								i++;
								
								if (split.get(i).contains("Sp") == true) {
									spring = true;
									springA = true;
									springB = true;
									springC = true;
								}
								if (split.get(i).contains("Sm") == true) {
									summer = true;
									summerA = true;
									summerB = true;
									summerC = true;
								} 
								if (split.get(i).contains("Fa") == true) {
									fall = true;
									fallA = true;
									fallB = true;
									fallC = true;
								}
							} else if (split.get(i).contains("-") && split.get(i).length() == 7 && abz == true ) {
								unitsA = Integer.parseInt(""+split.get(i).charAt(1));
								unitsB = Integer.parseInt(""+split.get(i).charAt(3));
								unitsZ = Integer.parseInt(""+split.get(i).charAt(5));

								if (split.get(i).charAt(split.get(i).length()-1) == ')') {
									continue;
								}
								i++;
								
								if (split.get(i).contains("Sp") == true) {
									spring = true;
									springA = true;
									springB = true;
									springZ = true;
								}
								if (split.get(i).contains("Sm") == true) {
									summer = true;
									summerA = true;
									summerB = true;
									summerZ = true;
								} 
								if (split.get(i).contains("Fa") == true) {
									fall = true;
									fallA = true;
									fallB = true;
									fallZ = true;
								}
							} else if (split.get(i).contains("-") && split.get(i).length() == 5 && ab == true ) {
								unitsA = Integer.parseInt(""+split.get(i).charAt(1));
								unitsB = Integer.parseInt(""+split.get(i).charAt(3));

								if (split.get(i).charAt(split.get(i).length()-1) == ')') {
									continue;
								}
								i++;
								
								if (split.get(i).contains("Sp") == true) {
									spring = true;
									springA = true;
									springB = true;
								}
								if (split.get(i).contains("Sm") == true) {
									summer = true;
									summerA = true;
									summerB = true;
								} 
								if (split.get(i).contains("Fa") == true) {
									fall = true;
									fallA = true;
									fallB = true;
								}
							} else if (split.get(i).charAt(2) == ')' && split.get(i).length() == 3) {
								units = Integer.parseInt(""+split.get(i).charAt(1));
								//i++;
							} else if (split.get(i).charAt(2) == ',' && split.get(i).length() == 3) {
								units = Integer.parseInt(""+split.get(i).charAt(1));
								if (split.get(i+1).contains(")") != true) {
									units = 0;
									description = description + split.get(i);
									i++;
									while (split.get(i).contains(")") != true && split.get(i).contains(",") != true) {
										description = description + " " + split.get(i);
										i++;
									}
									description = description + " ";
									//to not include the comma. 
									for (int j = 0; j < split.get(i).length() - 1; j++) {
										description = description + split.get(i).charAt(j);
									}
									description = description + ")";
								}
								i++;

								if (split.get(i).contains("Sp") == true) {
									spring = true;
									
									springA = true;
									springB = true;
									springC = true;
									springD = true;
									springZ = true;
								}
								if (split.get(i).contains("Sm") == true) {
									summer = true;
									
									summerA = true;
									summerB = true;
									summerC = true;
									summerD = true;
									summerZ = true;
								} 
								if (split.get(i).contains("Fa") == true) {
									fall = true;
									fallA = true;
									fallB = true;
									fallC = true;
									fallD = true;
									fallZ = true;
								}
								//i++;
							} else if (split.get(i).equals("(a:")) { 
								//units = Integer.parseInt(""+split.get(i+1).charAt(0));
								unitsA = Integer.parseInt("" + split.get(i+1).charAt(0));
								
								if (split.get(i+2).contains("Sp") == true) {
									springA = true;
								} else if (split.get(i+2).contains("Sm") == true) {
									summerA = true;
								} else if (split.get(i+2).contains("Fa") == true) {
									fallA = true;
								}
																
								description = description + split.get(i);
								i++;
								while (split.get(i).contains(")") == false) {
									description = description + " " + split.get(i);
									
									
									if (split.get(i).equals("b:")) {
										unitsB = Integer.parseInt("" + split.get(i+1).charAt(0));
										
										if (split.get(i+2).contains("Sp") == true) {
											springB = true;
										} else if (split.get(i+2).contains("Sm") == true) {
											summerB = true;
										} else if (split.get(i+2).contains("Fa") == true) {
											fallB = true;
										}
									} else if (split.get(i).equals("c:")) {
										unitsC = Integer.parseInt("" + split.get(i+1).charAt(0));
										
										if (split.get(i+2).contains("Sp") == true) {
											springC = true;
										} else if (split.get(i+2).contains("Sm") == true) {
											summerC = true;
										} else if (split.get(i+2).contains("Fa") == true) {
											fallC = true;
										}
									} else if (split.get(i).equals("d:")) {
										unitsD = Integer.parseInt("" + split.get(i+1).charAt(0));
										
										if (split.get(i+2).contains("Sp") == true) {
											springD = true;
										} else if (split.get(i+2).contains("Sm") == true) {
											summerD = true;
										} else if (split.get(i+2).contains("Fa") == true) {
											fallD = true;
										}
									} else if (split.get(i).equals("z:")) {
										unitsZ = Integer.parseInt("" + split.get(i+1).charAt(0));
										
										if (split.get(i+2).contains("Sp") == true) {
											springZ = true;
										} else if (split.get(i+2).contains("Sm") == true) {
											summerZ = true;
										} else if (split.get(i+2).contains("Fa") == true) {
											fallZ = true;
										}
									}
									
									i++;
								}
								description = description + split.get(i);
						
//								if (description.contains("Sp") == true) {
//									spring = true;
//								}
//								if (description.contains("Sm") == true) {
//									summer = true;
//								} 
//								if (description.contains("Fa") == true) {
//									fall = true;
//								}
				
							} else {
								description = description + split.get(i) + " " + split.get(i+1);
								i++;
							}
						} else {
							//Adding the main description. 
							description = description + " " + split.get(i);
						}
					}

					
					if (units == 0) {
						System.err.println(c);
						errorList.add(c);
					}
					
					
					if (abcdz == true) {
						System.out.println("Code: " + dept);
						System.out.println("NumberA: " + numberA);
						System.out.println("NumberB: " + numberB);
						System.out.println("NumberC: " + numberC);
						System.out.println("NumberD: " + numberD);
						System.out.println("NumberZ: " + numberZ);
						System.out.println("Name: " + name);
						System.out.println("UnitsA: " + unitsA);
						System.out.println("UnitsB: " + unitsB);
						System.out.println("UnitsC: " + unitsC);
						System.out.println("UnitsD: " + unitsD);
						System.out.println("UnitsZ: " + unitsZ);
						System.out.println("Max Units: " + maxUnits);
						System.out.println("Description: " + description);
						System.out.println("Offered in SpringA: " + springA);
						System.out.println("Offered in SummerA: " + summerA);
						System.out.println("Offered in FallA: " + fallA);
						System.out.println("Offered in SpringB: " + springB);
						System.out.println("Offered in SummerB: " + summerB);
						System.out.println("Offered in FallB: " + fallB);
						System.out.println("Offered in SpringC: " + springC);
						System.out.println("Offered in SummerC: " + summerC);
						System.out.println("Offered in FallC: " + fallC);
						System.out.println("Offered in SpringD: " + springD);
						System.out.println("Offered in SummerD: " + summerD);
						System.out.println("Offered in FallD: " + fallD);
						System.out.println("Offered in SpringZ: " + springZ);
						System.out.println("Offered in SummerZ: " + summerZ);
						System.out.println("Offered in FallZ: " + fallZ);
						
						deptList.add(new Course(dept, numberA, name, unitsA, maxUnits, maxUnits, description, springA, summerA, fallA));
						deptList.add(new Course(dept, numberB, name, unitsB, maxUnits, maxUnits, description, springB, summerB, fallB));
						deptList.add(new Course(dept, numberC, name, unitsC, maxUnits, maxUnits, description, springC, summerC, fallC));
						deptList.add(new Course(dept, numberD, name, unitsD, maxUnits, maxUnits, description, springD, summerD, fallD));
						deptList.add(new Course(dept, numberZ, name, unitsZ, maxUnits, maxUnits, description, springZ, summerZ, fallZ));
					} else if (abcd == true) {
						System.out.println("Code: " + dept);
						System.out.println("NumberA: " + numberA);
						System.out.println("NumberB: " + numberB);
						System.out.println("NumberC: " + numberC);
						System.out.println("NumberD: " + numberD);
						System.out.println("Name: " + name);
						System.out.println("UnitsA: " + unitsA);
						System.out.println("UnitsB: " + unitsB);
						System.out.println("UnitsC: " + unitsC);
						System.out.println("UnitsD: " + unitsD);
						System.out.println("Max Units: " + maxUnits);
						System.out.println("Description: " + description);
						System.out.println("Offered in SpringA: " + springA);
						System.out.println("Offered in SummerA: " + summerA);
						System.out.println("Offered in FallA: " + fallA);
						System.out.println("Offered in SpringB: " + springB);
						System.out.println("Offered in SummerB: " + summerB);
						System.out.println("Offered in FallB: " + fallB);
						System.out.println("Offered in SpringC: " + springC);
						System.out.println("Offered in SummerC: " + summerC);
						System.out.println("Offered in FallC: " + fallC);
						System.out.println("Offered in SpringD: " + springD);
						System.out.println("Offered in SummerD: " + summerD);
						System.out.println("Offered in FallD: " + fallD);
						
						deptList.add(new Course(dept, numberA, name, unitsA, maxUnits, maxUnits, description, springA, summerA, fallA));
						deptList.add(new Course(dept, numberB, name, unitsB, maxUnits, maxUnits, description, springB, summerB, fallB));
						deptList.add(new Course(dept, numberC, name, unitsC, maxUnits, maxUnits, description, springC, summerC, fallC));
						deptList.add(new Course(dept, numberD, name, unitsD, maxUnits, maxUnits, description, springD, summerD, fallD));
					} else if (abcz == true) {
						System.out.println("Code: " + dept);
						System.out.println("NumberA: " + numberA);
						System.out.println("NumberB: " + numberB);
						System.out.println("NumberC: " + numberC);
						System.out.println("NumberZ: " + numberZ);
						System.out.println("Name: " + name);
						System.out.println("UnitsA: " + unitsA);
						System.out.println("UnitsB: " + unitsB);
						System.out.println("UnitsC: " + unitsC);
						System.out.println("UnitsZ: " + unitsZ);
						System.out.println("Max Units: " + maxUnits);
						System.out.println("Description: " + description);
						System.out.println("Offered in SpringA: " + springA);
						System.out.println("Offered in SummerA: " + summerA);
						System.out.println("Offered in FallA: " + fallA);
						System.out.println("Offered in SpringB: " + springB);
						System.out.println("Offered in SummerB: " + summerB);
						System.out.println("Offered in FallB: " + fallB);
						System.out.println("Offered in SpringC: " + springC);
						System.out.println("Offered in SummerC: " + summerC);
						System.out.println("Offered in FallC: " + fallC);
						System.out.println("Offered in SpringZ: " + springZ);
						System.out.println("Offered in SummerZ: " + summerZ);
						System.out.println("Offered in FallZ: " + fallZ);
						
						deptList.add(new Course(dept, numberA, name, unitsA, maxUnits, maxUnits, description, springA, summerA, fallA));
						deptList.add(new Course(dept, numberB, name, unitsB, maxUnits, maxUnits, description, springB, summerB, fallB));
						deptList.add(new Course(dept, numberC, name, unitsC, maxUnits, maxUnits, description, springC, summerC, fallC));
						deptList.add(new Course(dept, numberZ, name, unitsZ, maxUnits, maxUnits, description, springZ, summerZ, fallZ));
					} else if (abc == true) {
						System.out.println("Code: " + dept);
						System.out.println("NumberA: " + numberA);
						System.out.println("NumberB: " + numberB);
						System.out.println("NumberC: " + numberC);
						System.out.println("Name: " + name);
						System.out.println("UnitsA: " + unitsA);
						System.out.println("UnitsB: " + unitsB);
						System.out.println("UnitsC: " + unitsC);
						System.out.println("Max Units: " + maxUnits);
						System.out.println("Description: " + description);
						System.out.println("Offered in SpringA: " + springA);
						System.out.println("Offered in SummerA: " + summerA);
						System.out.println("Offered in FallA: " + fallA);
						System.out.println("Offered in SpringB: " + springB);
						System.out.println("Offered in SummerB: " + summerB);
						System.out.println("Offered in FallB: " + fallB);
						System.out.println("Offered in SpringC: " + springC);
						System.out.println("Offered in SummerC: " + summerC);
						System.out.println("Offered in FallC: " + fallC);
						
						deptList.add(new Course(dept, numberA, name, unitsA, maxUnits, maxUnits, description, springA, summerA, fallA));
						deptList.add(new Course(dept, numberB, name, unitsB, maxUnits, maxUnits, description, springB, summerB, fallB));
						deptList.add(new Course(dept, numberC, name, unitsC, maxUnits, maxUnits, description, springC, summerC, fallC));
					} else if (abz == true) {
						System.out.println("Code: " + dept);
						System.out.println("NumberA: " + numberA);
						System.out.println("NumberB: " + numberB);
						System.out.println("NumberZ: " + numberZ);
						System.out.println("Name: " + name);
						System.out.println("UnitsA: " + unitsA);
						System.out.println("UnitsB: " + unitsB);
						System.out.println("UnitsZ: " + unitsZ);
						System.out.println("Max Units: " + maxUnits);
						System.out.println("Description: " + description);
						System.out.println("Offered in SpringA: " + springA);
						System.out.println("Offered in SummerA: " + summerA);
						System.out.println("Offered in FallA: " + fallA);
						System.out.println("Offered in SpringB: " + springB);
						System.out.println("Offered in SummerB: " + summerB);
						System.out.println("Offered in FallB: " + fallB);
						System.out.println("Offered in SpringZ: " + springZ);
						System.out.println("Offered in SummerZ: " + summerZ);
						System.out.println("Offered in FallZ: " + fallZ);
						
						deptList.add(new Course(dept, numberA, name, unitsA, maxUnits, maxUnits, description, springA, summerA, fallA));
						deptList.add(new Course(dept, numberB, name, unitsB, maxUnits, maxUnits, description, springB, summerB, fallB));
						deptList.add(new Course(dept, numberZ, name, unitsZ, maxUnits, maxUnits, description, springZ, summerZ, fallZ));
					} else if (ab == true ) {
						System.out.println("Code: " + dept);
						System.out.println("NumberA: " + numberA);
						System.out.println("NumberB: " + numberB);
						System.out.println("Name: " + name);
						System.out.println("UnitsA: " + unitsA);
						System.out.println("UnitsB: " + unitsB);
						System.out.println("Max Units: " + maxUnits);
						System.out.println("Description: " + description);
						System.out.println("Offered in SpringA: " + springA);
						System.out.println("Offered in SummerA: " + summerA);
						System.out.println("Offered in FallA: " + fallA);
						System.out.println("Offered in SpringB: " + springB);
						System.out.println("Offered in SummerB: " + summerB);
						System.out.println("Offered in FallB: " + fallB);

						deptList.add(new Course(dept, numberA, name, unitsA, maxUnits, maxUnits, description, springA, summerA, fallA));
						deptList.add(new Course(dept, numberB, name, unitsB, maxUnits, maxUnits, description, springB, summerB, fallB));
					
					} else {
						System.out.println("Code: " + dept);
						System.out.println("Number: " + number);
						System.out.println("Name: " + name);
						System.out.println("Units: " + units);
						System.out.println("Max Units: " + maxUnits);
						System.out.println("Description: " + description);
						System.out.println("Offered in Spring: " + spring);
						System.out.println("Offered in Summer: " + summer);
						System.out.println("Offered in Fall: " + fall);

						deptList.add(new Course(dept, number, name, units, maxUnits, maxUnits, description, spring, summer, fall));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				
				errorList.add(c);
			}

		}	
		
		try {
			PrintStream out = new PrintStream(new FileOutputStream("output/" + dept + ".txt"));

			for (Course c: deptList) {
				out.println(c.getDept() + "|" + c.getNumber() + "|" + c.getName() + "|" + c.getUnits());
			}

			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if (errorList.size() > 0) {
			try {
				PrintStream out = new PrintStream(new FileOutputStream("output/error_" + dept + ".txt"));

				for (String s : errorList) {
					out.println(s);
					out.println();
				}

				System.out.println("Error(s) outputted to error_" + dept + ".txt");
				
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Finished parsing... File outputted to " + dept + ".txt");
	}
}
