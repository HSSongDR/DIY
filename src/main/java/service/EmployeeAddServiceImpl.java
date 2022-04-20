package service;

import static constants.Constants.searchKey;

import constants.Constants;
import dto.EmployeeInfo;

public class EmployeeAddServiceImpl extends EmployeeService {

	@Override
	public String run(String[] lineSplitByComma) {
		ADD(lineSplitByComma);
		return "";
	}

	private void createSearchHM(EmployeeInfo newEmployeeInfo) {

		String sEmployeeNum = newEmployeeInfo.getObj(Constants.EMP_NUM);
		for (String search : searchKey) {
			checkSearchHM(search + newEmployeeInfo.getObj(search), sEmployeeNum);
		}
	}

	private void ADD(String[] lineSplitByComma) {

		if (lineSplitByComma.length < 10)
			System.out.println("ADD_FAIL :: Insert Null Data");

		EmployeeInfo newEmployeeInfo = new EmployeeInfo(lineSplitByComma[4], lineSplitByComma[5], lineSplitByComma[6],
				lineSplitByComma[7], lineSplitByComma[8], lineSplitByComma[9]);
		employeeHM.put(lineSplitByComma[4], newEmployeeInfo);

		createSearchHM(newEmployeeInfo);

	}

}
