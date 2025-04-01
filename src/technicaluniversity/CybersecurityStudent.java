package technicaluniversity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CybersecurityStudent extends Student {
	public CybersecurityStudent(String Name, String Surname, int yearOfBirth) {
		super(Name, Surname, yearOfBirth);
	}

	@Override
	public String specialAbility() {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-512");
			messageDigest.update(super.getName().getBytes());
			String name = messageDigest.digest().toString();
			messageDigest.update(super.getSurname().getBytes());
			String surname = messageDigest.digest().toString();
			return "Jméno: "+name+", příjmení: "+surname;
		} catch (NoSuchAlgorithmException e) {
			return "SHA-512 není podporován, nelze provést hashování";
		}
	}
}
