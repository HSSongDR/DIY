package service;

import java.util.PriorityQueue;

public class EmployeeDelServiceImpl extends EmployeeService {
	
	@Override
	public String run(String[] tempsplit) {
		// TODO Auto-generated method stub
		return DEL(tempsplit);
	}
	
    private static String DEL(String[] tempsplit) {
        // 대상 조회
        PriorityQueue<CmpString> aResult = schResult(tempsplit);
        // 프린트 옵션
        String printString = schPrint("DEL", aResult, checkPrintType(tempsplit[1]));

        for (CmpString empNum : aResult) {
            employeeHM.get(empNum.getEmployeeNum()).setRemoveFlag();
        }
        return printString;
    }
}
