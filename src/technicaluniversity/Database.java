package technicaluniversity;

import java.util.HashSet;

public class Database {
	private HashSet<Student> Students;
	
	private static int LAST_ID = 0;
		
	public void addCybersecurityStudent(String Name, String Surname, String DateOfBirth) {
		Students.add(new CybersecurityStudent(Name, Surname, DateOfBirth)); // LAST_ID + 1, 
		LAST_ID++;
	}
}
