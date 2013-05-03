package USC;

import java.util.*;

public class Course {
	
	String dept;
	String number;
	String code;
	String name;
	int units;
	
	int maxUnits; 
	//For classes with a range of units and a maximum allowed units. 
	//If there is no range, the max units should be the same as the units. 
	
	String description;
	
	ArrayList<Course> prerequisites;
	ArrayList<Course> corequisites;
	
	Boolean spring;
	Boolean summer;
	Boolean fall;
	
	//int[] offered; 	
	// Spring = 1, Summer = 2, Fall = 3
	
	public Course(String _dept, String _number, String _name, int _units, int max, String desc, Boolean sp, Boolean sm, Boolean fa) {
		dept = _dept;
		number = _number;
		code = dept + " " + number;
		name = _name;
		units = _units;
		description = desc;
		
		maxUnits = max;
		
		prerequisites = new ArrayList<Course>();
		corequisites = new ArrayList<Course>();
		
		spring = sp;
		summer = sm;
		fall = fa;
	}

	public String getDept() {
		return code;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
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
