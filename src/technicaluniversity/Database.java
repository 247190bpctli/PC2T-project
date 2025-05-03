package technicaluniversity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import technicaluniversity.Student.StudentType;

public class Database {
	private HashMap<Integer, Student> students;
	
	public Database() {
		students = new HashMap<Integer, Student>();
	}

	private static int LAST_ID = 0;
	
	public int addStudent(StudentType type, String name, String surname, int dateOfBirth) {
		switch(type.toString()) {
			case "CYBERSECURITY": //TODO simplify this
				students.put(LAST_ID + 1, new CybersecurityStudent(name, surname, dateOfBirth));
				LAST_ID++;
				return LAST_ID;
		case "TELECOMMUNICATION":
				students.put(LAST_ID + 1, new TelecommunicationStudent(name, surname, dateOfBirth));
				LAST_ID++;
				return LAST_ID;
			default:
				//TODO throw exception?
				return -1;
		}
	}

	public Student getStudent(int id) throws NullPointerException {
		return students.get(id);
	}

	public boolean deleteStudent(int id) {
		return students.remove(id) != null;
	}

	public String getSortedStudents() {
		String sortedStudents = "";
		ArrayList<Student> list = new ArrayList<Student>(students.values());
		Collections.sort(list, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				// vnitrni anonymni trida
				return o1.getSurname().compareToIgnoreCase(o2.getSurname()); //better to implement compareTo
			}
		});
		for(Student student:list) {
			sortedStudents = sortedStudents.concat(student.toString()+"\n");
		}

		return sortedStudents;
	}

	public List<Integer> getStudentCounts(){
		int CybersecurityStudentCount = 0;
		int TelecommunicationStudentCount = 0;

		ArrayList<Student> list = new ArrayList<Student>(students.values());

		for(Student student:list) {
			if(student instanceof CybersecurityStudent) {
				CybersecurityStudentCount++;
			}else {
				TelecommunicationStudentCount++;
			}
		}

		return Arrays.asList(CybersecurityStudentCount, TelecommunicationStudentCount);
	}

	public List<Float> getStudentAvgGrade() {
		float CybersecurityStudentAvgGrage = 0;
		float TelecommunicationStudentAvgGrage = 0;

		ArrayList<Student> list = new ArrayList<Student>(students.values());

		for(Student student:list) {
			if(student instanceof CybersecurityStudent) {
				CybersecurityStudentAvgGrage += student.getAvgGrade();
			}else {
				TelecommunicationStudentAvgGrage += student.getAvgGrade();
			}
		}

		return Arrays.asList(CybersecurityStudentAvgGrage/getStudentCounts().get(0), TelecommunicationStudentAvgGrage/getStudentCounts().get(1));
	} //TODO: fix returns NaN if empty

	public void loadStudentFromFile(String filename) throws IOException {
		CsvDriver studentFileDriver = new CsvDriver(filename);
		List<List<String>> studentFile = studentFileDriver.load();

		//insert student to db
		StudentType type = InputSanitizer.nextType(new Scanner(studentFile.get(3).get(1))); //TODO workaround, may be done differently
		int id = addStudent(type, studentFile.get(0).get(1), studentFile.get(1).get(1), Integer.valueOf(studentFile.get(2).get(1))); //TODO sanitize inputs

		//insert grades
		List<String> gradesStrings = studentFile.get(4).subList(1, studentFile.get(4).size());
		List<Float> grades = new ArrayList<Float>();

		//convert the types
		for(String gradeString:gradesStrings) grades.add(Float.valueOf(gradeString));

		for(Float grade:grades) {
			getStudent(id).addGrade(grade);
		}
	}

	public void loadFromDb() {
		SqlDriver.connect();
		SqlDriver.createTables(); //must be created in order to suppress error
		SqlDriver.selectStudentsAndGrades(this);
		SqlDriver.disconnect();
	}

	public void saveToDb() {
		SqlDriver.connect();
		SqlDriver.createTables();
		SqlDriver.truncate();
		for(int studentKey:students.keySet()) {
			students.get(studentKey).saveToDb(studentKey);
		}
		SqlDriver.disconnect();
	}
}
