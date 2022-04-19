package service;

import java.util.ArrayList;
import java.util.PriorityQueue;

import constants.Constants;
import util.CmpString;

public class EmployeeSchServiceImpl extends EmployeeService{
	
	@Override
	public String run(String[] tempsplit) {
		// TODO Auto-generated method stub
		return SCH(tempsplit);
	}
	    
    private String SCH(String[] tempsplit) {
        PriorityQueue<CmpString> aResult = schResult(tempsplit);
        return schPrint(Constants.CMD_SCH, aResult, checkPrintType(tempsplit[1]));
    }



}
