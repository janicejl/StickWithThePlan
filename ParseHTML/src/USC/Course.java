package USC;

import java.util.*;

public class Course {
	
	String code;
	String number;
	String name;
	int units;
	
	String description;
	
	ArrayList<Course> prerequisites;
	ArrayList<Course> corequisites;
	
	Boolean spring;
	Boolean suumer;
	Boolean fall;
	
	//int[] offered; 	
	// Spring = 1, Summer = 2, Fall = 3
	
	Course(String _code, String _number, String _name, int _units, String desc) {
		code = _code;
		number = _number;
		name = _name;
		units = _units;
		description = desc;
		
		prerequisites = new ArrayList<Course>();
		corequisites = new ArrayList<Course>();
	}
	
}
