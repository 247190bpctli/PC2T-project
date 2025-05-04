package technicaluniversity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Student {
	private String name;
	private String surname;
	private int yearOfBirth;
	private List<Float> grades;

	public static enum StudentType {
		CYBERSECURITY,
		TELECOMMUNICATION
	}

	public Student(String name, String surname, int yearOfBirth) {
		this.name = name;
		this.surname = surname;
		this.yearOfBirth = yearOfBirth;
		grades = new ArrayList<Float>();
	}

	public void addGrade(float grade) {
		if (grade >= 1 && grade <= 5) {
			grades.add(grade);
		} else {
			System.out.println("Známka nebyla přidána, protože není v povoleném rozsahu 1-5");
		}
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public String getSurname() {
		return surname;
	}

	public String getName() {
		return name;
	}

	public abstract String specialAbility();

	@Override
	public String toString() {
		return "Jméno: " + name + ", příjmení: " + surname + ", rok narození: " + yearOfBirth + ", průměr: "
				+ getAvgGrade();
	}

	public float getAvgGrade() {
		Float gradeSum = 0f;
		if (!grades.isEmpty()) {
			for (Float grade : grades) {
				gradeSum += grade;
			}
			return gradeSum / grades.size();
		} else {
			return gradeSum;
		}
	}

	public String getGroup() {
		return (this instanceof CybersecurityStudent) ? "CYBERSECURITY" : "TELECOMMUNICATION";
	}

	public void saveToFile(String filename) throws IOException {
		CsvDriver studentFileDriver = new CsvDriver(filename);
		List<List<String>> studentFile = new ArrayList<List<String>>();
		for (int i = 0; i < 5; i++) {
			studentFile.add(new ArrayList<String>());
		}

		studentFile.get(0).add("Name");
		studentFile.get(0).add(name);

		studentFile.get(1).add("Surname");
		studentFile.get(1).add(surname);

		studentFile.get(2).add("Year of birth");
		studentFile.get(2).add(Integer.toString(yearOfBirth));

		studentFile.get(3).add("Group");
		studentFile.get(3).add(getGroup());

		studentFile.get(4).add("Grades");
		for (Float grade : grades) {
			studentFile.get(4).add(grade.toString());
		}

		studentFileDriver.save(studentFile);
	}

	public boolean saveToDb(int id) {
		if (SqlDriver.insertStudent(id, name, surname, yearOfBirth, getGroup())) {
			for (float grade : grades) {
				if (!SqlDriver.insertGrade(id, grade))
					return false;
			}
			return true;
		} else {
			return false;
		}
	}
}
