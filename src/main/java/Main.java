import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {

	protected static HashMap<String, EmployeeInfo> employeeHM;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("./DIY/src/input.txt"));

		int n = Integer.parseInt(br.readLine());
		employeeHM = new HashMap<>();

		for (int i = 0; i < n; i++) {
			String temp = br.readLine();
			System.out.println(temp);
			String[] tempsplit = temp.split(",");

			if (tempsplit[0].equals("ADD")) {
				DB.ADD(tempsplit);
			}else if(tempsplit[0].equals("SCH")) {

			}else if(tempsplit[0].equals("DEL")) {

			}else if(tempsplit[0].equals("MOD")) {

			}
		}

		System.out.println(employeeHM.size());
		System.out.println("employeeHM.get(\"12000001\").name :"+employeeHM.get("12000001").name);
		System.out.println("employeeHM.get(\"12000001\").birthday :"+employeeHM.get("12000001").birthday);
		System.out.println("employeeHM.get(\"12000001\").certi :"+employeeHM.get("12000001").certi);

	}

}