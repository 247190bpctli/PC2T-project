package technicaluniversity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

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

	public Student getStudent(int id) {
		return students.get(id); //TODO throw exception if null
	}

	public void deleteStudent(int id) {
		students.remove(id);
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

	public void loadStudentFromFile(int id) {
		//TODO: load from file
	}

	public void saveStudentToFile(int id) {
		//TODO: save to file
	}

	public void loadFromDb() {
		//TODO: load from db
	}

	public void saveToDb() {
		//TODO: save to db
	}
}
