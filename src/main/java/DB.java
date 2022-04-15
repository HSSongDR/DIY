
import java.util.ArrayList;

public class DB {

	public static void ADD(String[] tempsplit) {
		// TODO Auto-generated method stub
		if(tempsplit.length < 10) System.out.println("ADD_FAIL :: Insert Null Data");
		EmployeeInfo newEmployeeInfo = new EmployeeInfo(tempsplit[4], tempsplit[5], tempsplit[6], tempsplit[7], tempsplit[8], tempsplit[9]);
		Main.employeeHM.put(tempsplit[4], newEmployeeInfo);
	}
	public void DEL() {
	}

	public void SCH() {
	}

	public void MOD() {
	}

}