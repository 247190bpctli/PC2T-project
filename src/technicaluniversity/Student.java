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

	public String getGroup() {
		return (this instanceof CybersecurityStudent)?"CYBERSECURITY":"TELECOMMUNICATION";
	}

	public void saveToFile(String filename) {
		CsvDriver studentFileDriver = new CsvDriver(filename);
		List<List<String>> studentFile = new ArrayList<List<String>>();
		for(int i = 0; i < 5; i++) { //make 2d list
			studentFile.add(new ArrayList<String>());
		}

		//insert student params and headers to list
		studentFile.get(0).add("Name");
		studentFile.get(0).add(name);

		studentFile.get(1).add("Surname");
		studentFile.get(1).add(surname);

		studentFile.get(2).add("Year of birth");
		studentFile.get(2).add(Integer.toString(yearOfBirth));

		studentFile.get(3).add("Group");
		studentFile.get(3).add(getGroup());

		studentFile.get(4).add("Grades");
		for(Float grade:grades) {
			studentFile.get(4).add(grade.toString());
		}

		studentFileDriver.save(studentFile);
	}

	public boolean saveToDb(int id) {
		if(SqlDriver.insertStudent(id, name, surname, yearOfBirth, getGroup())) {
			for(float grade:grades) {
				if(!SqlDriver.insertGrade(id, grade)) return false;
			}
			return true;
		}else {
			return false;
		}
	}
}
