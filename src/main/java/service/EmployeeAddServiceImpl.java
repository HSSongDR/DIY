package service;

import static constants.Constants.searchKey;

import dto.EmployeeInfo;

public class EmployeeAddServiceImpl extends EmployeeService {
	
	@Override
	public String run(String[] tempsplit) {
		// TODO Auto-generated method stub
		ADD(tempsplit);
		return  "";
	}
	
	private void createSearchHM(EmployeeInfo newEmployeeInfo) {

        String sEmployeeNum = newEmployeeInfo.getObj("employeeNum");
        for (String search : searchKey) {
            checkSearchHM(search + newEmployeeInfo.getObj(search), sEmployeeNum);
        }
    }
	
	 private void ADD(String[] tempsplit) {

	        if (tempsplit.length < 10) System.out.println("ADD_FAIL :: Insert Null Data");

	        EmployeeInfo newEmployeeInfo =
	                new EmployeeInfo(tempsplit[4], tempsplit[5], tempsplit[6], tempsplit[7], tempsplit[8], tempsplit[9]);
	        employeeHM.put(tempsplit[4], newEmployeeInfo);

	        createSearchHM(newEmployeeInfo);
	        
	}

}
