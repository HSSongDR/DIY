package service;

import static constants.Constants.birthSubKey;
import static constants.Constants.nameSubKey;
import static constants.Constants.phoneSubKey;

import java.util.PriorityQueue;

public class EmployeeModServiceImpl extends EmployeeService {
	
	@Override
	public String run(String[] tempsplit) {
		return MOD(tempsplit);
	}
	
    private static String MOD(String[] tempsplit) {
        // 대상 조회
        PriorityQueue<CmpString> aResult = schResult(tempsplit);
        // 프린트 옵션
        String printString = schPrint("MOD", aResult, checkPrintType(tempsplit[1]));


        String modifyKey = tempsplit[6];

        for (CmpString empNum : aResult) {

            employeeHM.get(empNum.getEmployeeNum()).setObj(tempsplit[6], tempsplit[7]);

            checkSearchHM(modifyKey + employeeHM.get(empNum.getEmployeeNum()).getObj(modifyKey), empNum.getEmployeeNum());
            if (modifyKey.equals("name")) {
                for (String subkey : nameSubKey) {
                    checkSearchHM(subkey + employeeHM.get(empNum.getEmployeeNum()).getObj(subkey), empNum.getEmployeeNum());
                }
            } else if (modifyKey.equals("phoneNum")) {
                for (String subkey : phoneSubKey) {
                    checkSearchHM(subkey + employeeHM.get(empNum.getEmployeeNum()).getObj(subkey), empNum.getEmployeeNum());
                }
            } else if (modifyKey.equals("birthday")) {
                for (String subkey : birthSubKey) {
                    checkSearchHM(subkey + employeeHM.get(empNum.getEmployeeNum()).getObj(subkey), empNum.getEmployeeNum());
                }
            }
        }

        return printString;
    }


}
