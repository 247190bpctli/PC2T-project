package technicaluniversity;

import java.util.HashSet;

public abstract class Student {
	private int Id;
	private String Name;
	private String Surname;
	private String DateOfBirth; //TODO: make a special variable or use special one
	private HashSet<Float> Grades;
	
	public Student() {
		Grades = new HashSet<Float>();
	}
}
