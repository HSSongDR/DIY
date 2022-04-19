package service;



import java.util.Arrays;
import java.util.PriorityQueue;

import constants.Constants;
import util.CmpString;

public class EmployeeModServiceImpl extends EmployeeService {
	
	@Override
	public String run(String[] tempsplit) {
		return MOD(tempsplit);
	}
	
    private String MOD(String[] tempsplit) {
        // 대상 조회
        PriorityQueue<CmpString> aResult = schResult(tempsplit);
        // 프린트 옵션
        String printString = schPrint(Constants.CMD_MOD, aResult, checkPrintType(tempsplit[1]));
        String modifyKey = tempsplit[6];

        for (CmpString empNum : aResult) {

            employeeHM.get(empNum.getEmployeeNum()).setObj(tempsplit[6], tempsplit[7]);

            checkSearchHM(modifyKey + employeeHM.get(empNum.getEmployeeNum()).getObj(modifyKey), empNum.getEmployeeNum());
            if(Arrays.asList(Constants.useSubKey).contains(modifyKey)){
                for (String subkey : getSubKey(modifyKey)) {
                    checkSearchHM(subkey + employeeHM.get(empNum.getEmployeeNum()).getObj(subkey), empNum.getEmployeeNum());
                }
            }
        }

        return printString;
    }


}
