package USC;

import java.util.*;

public class Course {
	
	String dept;	//Department only (example: CSCI)
	String number;	//Number only (example: 101)
	String code;	//Department and Number (example: CSCI 101)
	String name;	//Full name of course (example: Fundamentals of Computer Programming)
	
	double units;		//Number of Units
	double maxUnits; 	//Max you can take per semester
	double overallMax;	//Max number of units of a course you can take as part of degree. 
	//For classes with a range of units and a maximum allowed units. 
	//If there is no range, the max units should be the same as the units. 
	
	String description;
	
	ArrayList<String> prerequisites;
	ArrayList<String> corequisites;
	String crosslist;
	
	Boolean spring;
	Boolean summer;
	Boolean fall;
	
	//int[] offered; 	
	// Spring = 1, Summer = 2, Fall = 3
	
	public Course(String _dept, String _number, String _name, double _units, double max, double overall, String desc, Boolean sp, Boolean sm, Boolean fa) {
		dept = _dept;
		number = _number;
		code = dept + " " + number;
		name = _name;
		units = _units;
		description = desc;
		
		maxUnits = max;
		overallMax = overall;
		//prerequisites = new ArrayList<String>();
		//corequisites = new ArrayList<String>();
		
		spring = sp;
		summer = sm;
		fall = fa;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
		
		this.code = dept + " " + this.number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
		
		this.code = this.dept + " " + number;
	}

	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnits() {
		return units;
	}

	public void setUnits(double units) {
		this.units = units;
	}

	public double getMaxUnits() {
		return maxUnits;
	}
	
	public Boolean getSpring() {
		return spring;
	}

	public void setSpring(Boolean spring) {
		this.spring = spring;
	}

	public Boolean getSummer() {
		return summer;
	}

	public void setSuumer(Boolean summer) {
		this.summer = summer;
	}

	public Boolean getFall() {
		return fall;
	}

	public void setFall(Boolean fall) {
		this.fall = fall;
	}
	
}
