package technicaluniversity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvDriver {
	private File csvFile;
	private List<List<String>> content;

	public CsvDriver(String path){
		csvFile = new File(path);
	}

	public List<List<String>> load() throws IOException {
		FileReader s = null; //null surpasses not initialized exception
		BufferedReader br = null;

		s = new FileReader(csvFile);
		br = new BufferedReader(s);

		content = br.lines()
					.map(k -> Arrays.asList(k.split(",")))
					.collect(Collectors.toCollection(LinkedList::new));

		try {
			br.close();
			s.close();
		} catch (IOException e) {
			System.out.println("Soubor nelze bezpečně uzavřít");
		} catch (NullPointerException e) {}

		return content;
	}
	
	public void save(List<List<String>> data) throws IOException {
		FileWriter s = null; //null surpasses not initialized exception
		BufferedWriter br = null;

		s = new FileWriter(csvFile);
		br = new BufferedWriter(s);

		for(List<String> line:data) {
			//do not append comma ate of line
			for(int i = 0; i < line.size(); i++) {
				if(i+1 == line.size()) {
					br.write(line.get(i));
				}else {
					br.write(line.get(i)+",");
				}
			}
			br.write("\n");
		}

		try {
			br.close();
			s.close();
		} catch (IOException e) {
			System.out.println("Soubor nelze bezpečně uzavřít");
		} catch (NullPointerException e) {}
	}
}
