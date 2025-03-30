package technicaluniversity;

import java.util.List;

public abstract class Student {
	private String Name;
	private String Surname;
	private String DateOfBirth; //TODO: make a special variable or use special one
	private List<Float> Grades;

	public Student(String Name, String Surname, String DateOfBirth) {
		this.Name = Name;
		this.Surname = Surname;
		this.DateOfBirth = DateOfBirth;
	}

	public void addGrade(int Id, float Grade) {
		//TODO: check if grade in range 1-5, otherwise throw exception
		Grades.add(Grade);
	}

	public String getDateOfBirth() {
		return DateOfBirth;
	}

	//public void setDateOfBirth(String dateOfBirth) {
	//	DateOfBirth = dateOfBirth;
	//}

	public String getSurname() {
		return Surname;
	}

	//public void setSurname(String surname) {
	//	Surname = surname;
	//}

	public String getName() {
		return Name;
	}

	//public void setName(String name) {
	//	Name = name;
	//}
}
