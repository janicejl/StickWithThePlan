package CourseList;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.Scanner;


import USC.Course;

public class CourseList {
	public static void main (String[] args) {

		//"https://catalogue.usc.edu/schools/engineering/computer-science/courses/"
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

		String code = "";
		String number;
		String name;
		int units;
		String description;
		Boolean spring;
		Boolean summer;
		Boolean fall;
		
		//for ab, abc, abcd etc courses;
		
		Boolean ab;
		Boolean abc;
		Boolean abcd;
		Boolean abz;
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
			code = "";
			number = "";
			name = "";
			units = 0;
			description = "";
			spring = false;
			summer = false;
			fall = false;

			ab = false;
			abc = false;
			abcd = false;
			abz = false;
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

			System.out.println(src.text());

			String c = src.text();

			String[] split = c.split(" ");

			try {
				if (split[0].toUpperCase().equals(split[0])) {
					for (int i = 0; i < split.length; i++) {
						if (i == 0) {
							code = split[0];
						} else if (i == 1) {
							number = split [1];
							
							if (number.contains("abcdz") == true) {
								abcdz = true;
							} else if (number.contains("abcd") == true) {
								abcd = true;
							} else if (number.contains("abc") == true) {
								System.err.println("!!##abc not supported!!");
								errorList.add(c);
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
							name = name + split[i];
							i++;
							if (split[i].equals("(a:") == false) {
								while (split[i].charAt(0) != '('
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

									if (split[i].equals("(a:")) {
										break;
									}
								}
							}

							if (split[i].contains("-") && split[i].length() == 11 && abcdz == true ) {
								unitsA = Integer.parseInt(""+split[i].charAt(1));
								unitsB = Integer.parseInt(""+split[i].charAt(3));
								unitsC = Integer.parseInt(""+split[i].charAt(5));
								unitsD = Integer.parseInt(""+split[i].charAt(7));
								unitsZ = Integer.parseInt(""+split[i].charAt(9));
								

								if (split[i].charAt(split[i].length()-1) == ')') {
									continue;
								}
								i++;
								
								if (split[i].contains("Sp") == true) {
									spring = true;
									springA = true;
									springB = true;
									springC = true;
									springD = true;
									springZ = true;
								}
								if (split[i].contains("Sm") == true) {
									summer = true;
									summerA = true;
									summerB = true;
									summerC = true;
									summerD = true;
									summerZ = true;
								} 
								if (split[i].contains("Fa") == true) {
									fall = true;
									fallA = true;
									fallB = true;
									fallC = true;
									fallD = true;
									fallZ = true;
								}
							} else if (split[i].contains("-") && split[i].length() == 9 && abcd == true ) {
								unitsA = Integer.parseInt(""+split[i].charAt(1));
								unitsB = Integer.parseInt(""+split[i].charAt(3));
								unitsC = Integer.parseInt(""+split[i].charAt(5));
								unitsD = Integer.parseInt(""+split[i].charAt(7));

								if (split[i].charAt(split[i].length()-1) == ')') {
									continue;
								}
								i++;
								
								if (split[i].contains("Sp") == true) {
									spring = true;
									springA = true;
									springB = true;
									springC = true;
									springD = true;
								}
								if (split[i].contains("Sm") == true) {
									summer = true;
									summerA = true;
									summerB = true;
									summerC = true;
									summerD = true;
								} 
								if (split[i].contains("Fa") == true) {
									fall = true;
									fallA = true;
									fallB = true;
									fallC = true;
									fallD = true;
								}
							} else if (split[i].contains("-") && split[i].length() == 7 && abz == true ) {
								unitsA = Integer.parseInt(""+split[i].charAt(1));
								unitsB = Integer.parseInt(""+split[i].charAt(3));
								unitsZ = Integer.parseInt(""+split[i].charAt(5));

								if (split[i].charAt(split[i].length()-1) == ')') {
									continue;
								}
								i++;
								
								if (split[i].contains("Sp") == true) {
									spring = true;
									springA = true;
									springB = true;
									springZ = true;
								}
								if (split[i].contains("Sm") == true) {
									summer = true;
									summerA = true;
									summerB = true;
									summerZ = true;
								} 
								if (split[i].contains("Fa") == true) {
									fall = true;
									fallA = true;
									fallB = true;
									fallZ = true;
								}
							} else if (split[i].contains("-") && split[i].length() == 5 && ab == true ) {
								unitsA = Integer.parseInt(""+split[i].charAt(1));
								unitsB = Integer.parseInt(""+split[i].charAt(3));

								if (split[i].charAt(split[i].length()-1) == ')') {
									continue;
								}
								i++;
								
								if (split[i].contains("Sp") == true) {
									spring = true;
									springA = true;
									springB = true;
								}
								if (split[i].contains("Sm") == true) {
									summer = true;
									summerA = true;
									summerB = true;
								} 
								if (split[i].contains("Fa") == true) {
									fall = true;
									fallA = true;
									fallB = true;
								}
							} else if (split[i].charAt(2) == ')' && split[i].length() == 3) {
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
									//to not include the comma. 
									for (int j = 0; j < split[i].length() - 1; j++) {
										description = description + split[i].charAt(j);
									}
									description = description + ")";
								}
								i++;

								if (split[i].contains("Sp") == true) {
									spring = true;
									
									springA = true;
									springB = true;
									springC = true;
									springD = true;
									springZ = true;
								}
								if (split[i].contains("Sm") == true) {
									summer = true;
									
									summerA = true;
									summerB = true;
									summerC = true;
									summerD = true;
									summerZ = true;
								} 
								if (split[i].contains("Fa") == true) {
									fall = true;
									fallA = true;
									fallB = true;
									fallC = true;
									fallD = true;
									fallZ = true;
								}
								//i++;
							} else if (split[i].equals("(a:")) { 
								units = Integer.parseInt(""+split[i+1].charAt(0));
								
								//for the ab courses with irregular course offerings. 
								int semTimes = 0;
								String semParts = split[i];
								
								description = description + split[i];
								i++;
								while (split[i].contains(")") == false) {
									description = description + " " + split[i];
									
									semParts = semParts + " " + split[i];
									
									if (split[i+1].contains(")")) {
										semParts = semParts + " " + split[i+1];
										
										if (semParts.contains("Sp") == true) {
											if (semTimes == 0) {
												springA = true; 
											} else if (semTimes == 1) {
												springB = true;
											} else {
												errorList.add(c);
											}
											
											semTimes++;
											semParts = "";
										}
										if (semParts.contains("Sm") == true) {
											if (semTimes == 0) {
												summerA = true; 
											} else if (semTimes == 1) {
												summerB = true;
											} else {
												errorList.add(c);
											}
											
											semTimes++;
											semParts = "";
										} 
										if (semParts.contains("Fa") == true) {
											if (semTimes == 0) {
												fallA = true; 
											} else if (semTimes == 1) {
												fallB = true;
											} else {
												errorList.add(c);
											}
											
											semTimes++;
											semParts = "";
										}
									} else if (split[i].contains(";") == true) {
										if (semParts.contains("Sp") == true) {
											if (semTimes == 0) {
												springA = true; 
											} else if (semTimes == 1) {
												springB = true;
											} else {
												errorList.add(c);
											}
											
											semTimes++;
											semParts = "";
										}
										if (semParts.contains("Sm") == true) {
											if (semTimes == 0) {
												summerA = true; 
											} else if (semTimes == 1) {
												summerB = true;
											} else {
												errorList.add(c);
											}
											
											semTimes++;
											semParts = "";
										} 
										if (semParts.contains("Fa") == true) {
											if (semTimes == 0) {
												fallA = true; 
											} else if (semTimes == 1) {
												fallB = true;
											} else {
												errorList.add(c);
											}
											
											semTimes++;
											semParts = "";
										}
									}
									
									i++;
								}
								description = description + split[i];
						
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
								description = description + split[i] + " " + split[i+1];
								i++;
							}
						} else {
							//Adding the main description. 
							description = description + " " + split[i];
						}
					}

					if (abcdz == true) {
						System.out.println("Code: " + code);
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
						
						deptList.add(new Course(code, numberA, name, unitsA, description, springA, summerA, fallA));
						deptList.add(new Course(code, numberB, name, unitsB, description, springB, summerB, fallB));
						deptList.add(new Course(code, numberC, name, unitsC, description, springC, summerC, fallC));
						deptList.add(new Course(code, numberD, name, unitsD, description, springD, summerD, fallD));
						deptList.add(new Course(code, numberZ, name, unitsZ, description, springZ, summerZ, fallZ));
					} else if (abcd == true) {
						System.out.println("Code: " + code);
						System.out.println("NumberA: " + numberA);
						System.out.println("NumberB: " + numberB);
						System.out.println("NumberC: " + numberC);
						System.out.println("NumberD: " + numberD);
						System.out.println("Name: " + name);
						System.out.println("UnitsA: " + unitsA);
						System.out.println("UnitsB: " + unitsB);
						System.out.println("UnitsC: " + unitsC);
						System.out.println("UnitsD: " + unitsD);
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
						
						deptList.add(new Course(code, numberA, name, unitsA, description, springA, summerA, fallA));
						deptList.add(new Course(code, numberB, name, unitsB, description, springB, summerB, fallB));
						deptList.add(new Course(code, numberC, name, unitsC, description, springC, summerC, fallC));
						deptList.add(new Course(code, numberD, name, unitsD, description, springD, summerD, fallD));
					} else if (abz == true) {
						System.out.println("Code: " + code);
						System.out.println("NumberA: " + numberA);
						System.out.println("NumberB: " + numberB);
						System.out.println("NumberZ: " + numberZ);
						System.out.println("Name: " + name);
						System.out.println("UnitsA: " + unitsA);
						System.out.println("UnitsB: " + unitsB);
						System.out.println("UnitsZ: " + unitsZ);
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
						
						deptList.add(new Course(code, numberA, name, unitsA, description, springA, summerA, fallA));
						deptList.add(new Course(code, numberB, name, unitsB, description, springB, summerB, fallB));
						deptList.add(new Course(code, numberZ, name, unitsZ, description, springZ, summerZ, fallZ));
					} else if (ab == true ) {
						System.out.println("Code: " + code);
						System.out.println("NumberA: " + numberA);
						System.out.println("NumberB: " + numberB);
						System.out.println("Name: " + name);
						System.out.println("UnitsA: " + unitsA);
						System.out.println("UnitsB: " + unitsB);
						System.out.println("Description: " + description);
						System.out.println("Offered in SpringA: " + springA);
						System.out.println("Offered in SummerA: " + summerA);
						System.out.println("Offered in FallA: " + fallA);
						System.out.println("Offered in SpringB: " + springB);
						System.out.println("Offered in SummerB: " + summerB);
						System.out.println("Offered in FallB: " + fallB);

						deptList.add(new Course(code, numberA, name, unitsA, description, springA, summerA, fallA));
						deptList.add(new Course(code, numberB, name, unitsB, description, springB, summerB, fallB));
					
					} else {
						System.out.println("Code: " + code);
						System.out.println("Number: " + number);
						System.out.println("Name: " + name);
						System.out.println("Units: " + units);
						System.out.println("Description: " + description);
						System.out.println("Offered in Spring: " + spring);
						System.out.println("Offered in Summer: " + summer);
						System.out.println("Offered in Fall: " + fall);

						deptList.add(new Course(code, number, name, units, description, spring, summer, fall));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				
				errorList.add(c);
			}

		}	
		
		try {
			PrintStream out = new PrintStream(new FileOutputStream(code + ".txt"));

			for (Course c: deptList) {
				out.println(c.getCode() + "|" + c.getNumber() + "|" + c.getName() + "|" + c.getUnits());
			}

			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if (errorList.size() > 0) {
			try {
				PrintStream out = new PrintStream(new FileOutputStream("error_" + code + ".txt"));

				for (String s : errorList) {
					out.println(s);
					out.println();
				}

				System.out.println("Error(s) outputted to error_" + code + ".txt");
				
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Finished parsing... File outputted to " + code + ".txt");
	}
}
