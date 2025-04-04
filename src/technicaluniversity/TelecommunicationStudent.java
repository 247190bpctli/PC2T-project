package technicaluniversity;

import java.util.Map;
import static java.util.Map.entry;

public class TelecommunicationStudent extends Student{
	public TelecommunicationStudent(String name, String surname, int yearOfBirth) {
		super(name, surname, yearOfBirth);
	}

	//Java 9+ TODO check if OK
	Map<Character, String> MorseCharMap = Map.ofEntries(
	    entry('A', ".-"),
	    entry('B', "-..."),
	    entry('C', "-.-."),
	    entry('D', "-.."),
	    entry('E', "."),
	    entry('F', "..-."),
	    entry('G', "--."),
	    entry('H', "...."),
	    entry('I', ".."),
	    entry('J', ".---"),
	    entry('K', "-.-"),
	    entry('L', ".-.."),
	    entry('M', "--"),
	    entry('N', "-."),
	    entry('O', "---"),
	    entry('P', ".--."),
	    entry('Q', "--.-"),
	    entry('R', ".-."),
	    entry('S', "..."),
	    entry('T', "-"),
	    entry('U', "..-"),
	    entry('V', "...-"),
	    entry('W', ".--"),
	    entry('X', "-..-"),
	    entry('Y', "-.--"),
	    entry('Z', "--.."),
	    entry('1', ".----"),
	    entry('2', "..---"),
	    entry('3', "...--"),
	    entry('4', "....-"),
	    entry('5', "....."),
	    entry('6', "-...."),
	    entry('7', "--..."),
	    entry('8', "---.."),
	    entry('9', "----."),
	    entry('0', "-----")
	);

	private String morseEncode(String word) {
	    char[] wordCharArray = word.toUpperCase().toCharArray();
	    String[] morseChars = new String[wordCharArray.length];
	    String morseChar;

	    for (int i = 0; i < wordCharArray.length; i++) {
	        morseChar = MorseCharMap.get(wordCharArray[i]);
	        morseChars[i] = morseChar;
	    }
	    return String.join(" ", morseChars);
	}

	@Override
	public String specialAbility() {
		return "Jméno: "+morseEncode(getName())+", příjmení: "+morseEncode(getSurname());
	}

	@Override
	public String toString() {
		return super.toString()+", skupina: telekomunikace";
	}
}
