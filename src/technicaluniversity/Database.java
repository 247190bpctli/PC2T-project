package technicaluniversity;

import java.util.HashMap;

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
