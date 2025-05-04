package technicaluniversity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CybersecurityStudent extends Student {
	public CybersecurityStudent(String name, String surname, int yearOfBirth) {
		super(name, surname, yearOfBirth);
	}

	private String getHash(String word) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-512");
			messageDigest.update(word.getBytes());
			return messageDigest.digest().toString();
		} catch (NoSuchAlgorithmException e) {
			return "SHA-512 není podporován, nelze provést hashování";
		}
	}

	@Override
	public String specialAbility() {
		return "Jméno: " + getHash(getName()) + ", příjmení: " + getHash(getSurname());
	}

	@Override
	public String toString() {
		return super.toString() + ", skupina: kyberbezpečnost";
	}
}
