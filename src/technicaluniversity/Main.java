package technicaluniversity;

import java.io.IOException;
import java.util.Scanner;
import technicaluniversity.Student.StudentType;

public class Main {
	public static void main(String[] args) {
		int optn;
		int id;
		float grade;
		StudentType type;
		String name;
		String surname;
		int yearOfBirth;
		Scanner sc = new Scanner(System.in);
		Database db = new Database();

		String dbFile = (args.length > 0) ? args[0] : "data/sqlite.db";

		if (!db.loadFromDb(dbFile)) {
			System.out.println("Databázi se nepodařilo načíst, pro pokus o znovunačtení program restartujte");
		}

		do {
			System.out.println("--------------------------------------");
			System.out.println("Databáze studentů technické univerzity");
			System.out.println("--------------------------------------");
			System.out.println("(1) Přidat studenta");
			System.out.println("(2) Zadat známku");
			System.out.println("(3) Propustit studenta");
			System.out.println("(4) Vyhledat studenta");
			System.out.println("(5) Spustit dovednost");
			System.out.println("(6) Vypsat studenty dle příjmení");
			System.out.println("(7) Vypočítat společné průměry");
			System.out.println("(8) Výpis počtu studentů");
			System.out.println("(9) Import studenta ze souboru");
			System.out.println("(10) Export studenta do souboru");
			System.out.println("(11) Uložit a ukončit");
			System.out.println("(12) Ukončit bez uložení");
			System.out.println("--------------------------------------");
			System.out.print("Zvolte možnost: ");

			optn = InputSanitizer.nextInt(sc);

			switch (optn) {
			case 1:
				System.out.println(
						"Zadejte skupinu studenta [CYBERSECURITY/TELECOMMUNICATION], jméno, příjmení a rok narození");
				type = InputSanitizer.nextType(sc);
				name = sc.next();
				surname = sc.next();
				yearOfBirth = InputSanitizer.nextInt(sc);
				System.out.println("ID nového studenta je " + db.addStudent(type, name, surname, yearOfBirth));
				break;
			case 2:
				System.out.println("Zadej ID studenta a známku");
				id = InputSanitizer.nextInt(sc);
				grade = InputSanitizer.nextFloat(sc);
				try {
					db.getStudent(id).addGrade(grade);
				} catch (NullPointerException e) {
					System.out.println("Známka nebyla přidána, protože student nebyl nalezen");
				}
				break;
			case 3:
				System.out.println("Zadej ID studenta ke smazání");
				id = InputSanitizer.nextInt(sc);
				if (db.deleteStudent(id)) {
					System.out.println("Student odstraněn");
				} else {
					System.out.println("Student nebyl odstraněn, protože nebyl nalezen");
				}
				break;
			case 4:
				System.out.println("Zadej ID studenta k vyhledání");
				id = InputSanitizer.nextInt(sc);
				try {
					System.out.println(db.getStudent(id).toString());
				} catch (NullPointerException e) {
					System.out.println("Student nebyl nalezen");
				}
				break;
			case 5:
				System.out.println(
						"Studenti kyberbezpečnosti udělají hash svého jména, studenti telekomunikací řeknou jméno v Morseově abecedě");
				System.out.println("Zadej ID studenta ke spuštění dovednosti");
				id = InputSanitizer.nextInt(sc);
				try {
					System.out.println(db.getStudent(id).specialAbility());
				} catch (NullPointerException e) {
					System.out.println("Student nebyl nalezen");
				}
				break;
			case 6:
				System.out.println("Seznam studentů dle příjmení");
				System.out.print(db.getSortedStudents());
				break;
			case 7:
				System.out.println("Studijní průměry v jednotlivých studijních skupinách");
				if (db.getStudentCounts().get(0) != 0) {
					if (db.getStudentAvgGrade().get(0) != 0.0f) {
						System.out.println("Skupina kyberbezpečnost: " + db.getStudentAvgGrade().get(0));
					} else {
						System.out.println("Studijní skupina kyberbezpečnost nemá žádné známky");
					}
				} else {
					System.out.println("Studijní skupina kyberbezpečnost je prázdná");
				}
				if (db.getStudentCounts().get(1) != 0) {
					if (db.getStudentAvgGrade().get(1) != 0.0f) {
						System.out.println("Skupina telekomunikace: " + db.getStudentAvgGrade().get(1));
					} else {
						System.out.println("Studijní skupina telekomunikace nemá žádné známky");
					}
				} else {
					System.out.println("Studijní skupina telekomunikace je prázdná");
				}
				break;
			case 8:
				System.out.println("Počty zapsaných studentů v jednotlivých studijních skupinách");
				System.out.println("Skupina kyberbezpečnost: " + db.getStudentCounts().get(0));
				System.out.println("Skupina telekomunikace: " + db.getStudentCounts().get(1));
				break;
			case 9:
				System.out.println("Zadejte název souboru pro načtení studenta");
				name = sc.next();
				try {
					db.loadStudentFromFile(name);
				} catch (IOException e) {
					System.out.println("Soubor nelze otevřít");
					System.out.println(e.getMessage());
				} catch (IllegalArgumentException e) {
					System.out.println("Soubor není ve správném formátu");
					System.out.println(e.getMessage());
				}
				break;
			case 10:
				System.out.println("Zadejte ID studenta pro uložení do souboru");
				id = InputSanitizer.nextInt(sc);
				System.out.println("Zadejte název souboru do kterého chcete studenta uložit");
				name = sc.next();
				try {
					db.getStudent(id).saveToFile(name);
				} catch (NullPointerException e) {
					System.out.println("Student nebyl nalezen");
				} catch (IOException e) {
					System.out.println("Soubor nelze uložit");
					System.out.println(e.getMessage());
				}
				break;
			case 11:
				if (db.saveToDb(dbFile)) {
					sc.close();
					System.exit(0);
				} else {
					System.out.println("Zkontrolujte přístupová práva k databázi, nebo program ukončete bez uložení");
				}
				break;
			case 12:
				System.out.println("Opravdu chcete program ukončit bez uložení? [y/n]");
				name = sc.next();
				if (name.equalsIgnoreCase("y")) {
					sc.close();
					System.exit(0);
				}
			default:
				break;
			}
		} while (true);
	}
}
