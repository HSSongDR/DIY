package service;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class EmployeeSchServiceImpl extends EmployeeService{
	
	@Override
	public String run(String[] tempsplit) {
		// TODO Auto-generated method stub
		return SCH(tempsplit);
	}
	    
    private static String SCH(String[] tempsplit) {
        PriorityQueue<CmpString> aResult = schResult(tempsplit);
        return schPrint("SCH", aResult, checkPrintType(tempsplit[1]));
    }



}
