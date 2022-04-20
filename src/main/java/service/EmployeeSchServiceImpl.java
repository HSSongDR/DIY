package service;

import java.util.PriorityQueue;

import constants.Constants;
import util.CmpString;

public class EmployeeSchServiceImpl extends EmployeeService {

	@Override
	public String run(String[] lineSplitByComma) {
		return SCH(lineSplitByComma);
	}

	private String SCH(String[] lineSplitByComma) {
		PriorityQueue<CmpString> aResult = schResult(lineSplitByComma);
		return schPrint(Constants.CMD_SCH, aResult, checkPrintType(lineSplitByComma[1]));
	}

}
