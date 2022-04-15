
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import constants.Constants;

public class Main {

	protected static HashMap<String, EmployeeInfo> employeeHM;
	protected static HashMap<String, ArrayList<String>> searchHM;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		int n = Integer.parseInt(br.readLine());
		employeeHM = new HashMap<>();
		searchHM = new HashMap<>();
		
		DB database = new DB();
		
		for (int i = 0; i < n; i++) {
			String temp = br.readLine();
			System.out.println(temp);
			String[] tempsplit = temp.split(Constants.DELIMITER_COMMA);

			if (tempsplit[0].equals(Constants.CMD_ADD)) {
				if(tempsplit[4].equals("08117441")) {
					int a = 1;
				}
				DB.ADD(tempsplit);
			} else if (tempsplit[0].equals(Constants.CMD_SCH)) {
			} else if (tempsplit[0].equals(Constants.CMD_DEL)) {
				database.DEL(tempsplit);
			} else if (tempsplit[0].equals(Constants.CMD_MOD)) {

			}
		}
	}
}


/*
		System.out.println(employeeHM.size());
		System.out.println("employeeHM.get(\"12000001\").name :" + employeeHM.get("15123099").getName());
		System.out.println("employeeHM.get(\"12000001\").birthday :" + employeeHM.get("15123099").getBirthday());
		System.out.println("employeeHM.get(\"12000001\").certi :" + employeeHM.get("15123099").getCerti());
		if (searchHM.get("phoneNumf_3112").size() > 0) {
			for (String getNumber : searchHM.get("phoneNumf_3112")) {
				System.out.println("searchHM.get(\"1234\") :" + getNumber);
			}
		}
	}
	
 */


