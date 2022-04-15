import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	protected static HashMap<String, EmployeeInfo> employeeHM;
	protected static HashMap<String, ArrayList<String>> searchHM;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("./DIY/src/input.txt"));

		int n = Integer.parseInt(br.readLine());
		employeeHM = new HashMap<>();
		searchHM = new HashMap<>();

		for (int i = 0; i < n; i++) {
			String temp = br.readLine();
			System.out.println(temp);
			String[] tempsplit = temp.split(",");

			if (tempsplit[0].equals("ADD")) {
				DB.ADD(tempsplit);
			} else if (tempsplit[0].equals("SCH")) {

			} else if (tempsplit[0].equals("DEL")) {

			} else if (tempsplit[0].equals("MOD")) {

			}
		}

	}

}
