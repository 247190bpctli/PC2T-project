package technicaluniversity;

import java.util.Scanner;

public class InputSanitizer {
	public static int nextInt(Scanner sc) {
		if(sc.hasNextInt()) {
			return sc.nextInt();
		}else{
			System.out.println("Zadej celé číslo");
			sc.next();
			return nextInt(sc);
		}
	}
	
	public static float nextFloat(Scanner sc) {
		if(sc.hasNextFloat()) {
			return sc.nextFloat();
		}else{
			System.out.println("Zadej desetinné číslo");
			sc.next();
			return nextFloat(sc);
		}
	}
}
