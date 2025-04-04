package technicaluniversity;

import java.util.ArrayList;
import java.util.List;

public abstract class Student {
	private String name;
	private String surname;
	private int yearOfBirth;
	private List<Float> grades;
	
	public static enum StudentType{
		CYBERSECURITY,
		TELECOMMUNICATION
	}

	public Student(String name, String surname, int yearOfBirth) {
		this.name = name;
		this.surname = surname;
		this.yearOfBirth = yearOfBirth;
		grades = new ArrayList<Float>();
	}

	public void addGrade(float Grade) {
		//TODO: check if grade in range 1-5, otherwise throw exception
		grades.add(Grade);
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	//public void setDateOfBirth(String dateOfBirth) {
	//	DateOfBirth = dateOfBirth;
	//}

	public String getSurname() {
		return surname;
	}

	//public void setSurname(String surname) {
	//	Surname = surname;
	//}

	public String getName() {
		return name;
	}

	public abstract String specialAbility();

	//public void setName(String name) {
	//	Name = name;
	//}

	@Override
	public String toString() {
		return "Jméno: "+name+", příjmení: "+surname+", rok narození: "+yearOfBirth+", průměr: "+getAvgGrade();
	}

	public float getAvgGrade() {
		//sum all grades and divide them
		Float gradeSum = 0f;
		if(!grades.isEmpty()){
			for(Float grade : grades) {
				gradeSum += grade;
			}
			return gradeSum/grades.size();
		}else {
			return gradeSum;
		}
	}
}
