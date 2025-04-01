package technicaluniversity;

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
		
		db.loadFromDb();
		
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

            optn = sc.nextInt();

            switch (optn) {
	            case 1:
	                System.out.println("Zadejte skupinu studenta [CYBERSECURITY/TELECOMMUNICATION], jméno, příjmení a rok narození");
	                type = StudentType.valueOf(sc.next().toUpperCase());
	                name = sc.next();
	                surname = sc.next();
	                yearOfBirth = sc.nextInt(); //TODO sanitize input
	                System.out.println("ID nového studenta je "+db.addStudent(type, name, surname, yearOfBirth));
	                break;
	            case 2:
	                System.out.println("Zadej ID studenta a známku");
	                id = sc.nextInt();
	                grade = sc.nextFloat();
	                db.getStudent(id).addGrade(grade);
	                break;
	            case 3:
	                System.out.println("Zadej ID studenta ke smazání");
	                id = sc.nextInt();
	                db.deleteStudent(id);
	                break;
	            case 4:
	                System.out.println("Zadej ID studenta k vyhledání");
	                id = sc.nextInt();
	                System.out.println(db.getStudent(id).toString());
	                break;
	            case 5:
	            	System.out.println("Studenti kyberbezpečnosti udělají hash svého jména, studenti telekomunikací řeknou jméno v Morseově abecedě");
	                System.out.println("Zadej ID studenta ke spuštění dovednosti");
	                id = sc.nextInt();
	                System.out.println(db.getStudent(id).specialAbility());
	                break;
	            case 6:
	                System.out.println("todo");
	                break;
	            case 7:
	                System.out.println("todo");
	                break;
                case 8:
                	System.out.println("todo");
                    break;
                case 9:
                    System.out.println("todo");
                    break;
                case 10:
                    System.out.println("todo");
                    break;
                case 11:
                    db.saveToDb();
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }

        } while (optn != 12);

		sc.close();
        System.exit(0);
	}

}
