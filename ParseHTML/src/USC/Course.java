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
	Boolean summer;
	Boolean fall;
	
	//int[] offered; 	
	// Spring = 1, Summer = 2, Fall = 3
	
	public Course(String _code, String _number, String _name, int _units, String desc, Boolean sp, Boolean sm, Boolean fa) {
		code = _code;
		number = _number;
		name = _name;
		units = _units;
		description = desc;
		
		prerequisites = new ArrayList<Course>();
		corequisites = new ArrayList<Course>();
		
		spring = sp;
		summer = sm;
		fall = fa;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
