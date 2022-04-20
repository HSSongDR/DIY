package service;

import java.util.PriorityQueue;

import constants.Constants;
import util.CmpString;

public class EmployeeDelServiceImpl extends EmployeeService {

	@Override
	public String run(String[] lineSplitByComma) {
		return DEL(lineSplitByComma);
	}

	private String DEL(String[] lineSplitByComma) {

		PriorityQueue<CmpString> aResult = schResult(lineSplitByComma);
		String printString = schPrint(Constants.CMD_DEL, aResult, checkPrintType(lineSplitByComma[1]));

		for (CmpString empNum : aResult) {
			employeeHM.remove(empNum.getEmployeeNum());
		}
		return printString;
	}
}
