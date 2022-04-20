package service;

import java.util.Arrays;
import java.util.PriorityQueue;

import constants.Constants;
import util.CmpString;

public class EmployeeModServiceImpl extends EmployeeService {

	@Override
	public String run(String[] lineSplitByComma) {
		return MOD(lineSplitByComma);
	}

	private String MOD(String[] lineSplitByComma) {
		PriorityQueue<CmpString> resultPQ = schResult(lineSplitByComma);
		String printString = schPrint(Constants.CMD_MOD, resultPQ, checkPrintType(lineSplitByComma[1]));
		String modifyKey = lineSplitByComma[6];

		for (CmpString empNum : resultPQ) {

			employeeHM.get(empNum.getEmployeeNum()).setObj(lineSplitByComma[6], lineSplitByComma[7]);

			checkSearchHM(modifyKey + employeeHM.get(empNum.getEmployeeNum()).getObj(modifyKey),
					empNum.getEmployeeNum());
			if (Arrays.asList(Constants.useSubKey).contains(modifyKey)) {
				for (String subkey : getSubKey(modifyKey)) {
					checkSearchHM(subkey + employeeHM.get(empNum.getEmployeeNum()).getObj(subkey),
							empNum.getEmployeeNum());
				}
			}
		}

		return printString;
	}

}
