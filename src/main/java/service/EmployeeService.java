package service;

import static constants.Constants.outputFilePath;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import dto.EmployeeInfo;

public abstract class EmployeeService {
	
	public static HashMap<String, EmployeeInfo> employeeHM;
	public static HashMap<String, PriorityQueue<CmpString>> searchHM;
    
	
	public abstract String run(String[] tempsplit);
	
	public static void INIT() {
		
	    employeeHM = new HashMap<>();
	    searchHM = new HashMap<>();
	    try {
	        Files.delete(Paths.get(outputFilePath));
	        File file = new File(outputFilePath);
	    } catch (Exception e) {
	        System.out.println("output File Delete Fail");
	    }
	 }
	
    protected static String checkPrintType(String printType){
        return printType.equals(" ") ? "NUMBER" : "STRING";
    }
    
    protected static String printformat(String mode, String valueString) {
        return mode + "," + valueString + "\n";
    }

    protected static String schPrint(String mode, PriorityQueue<CmpString> aResult, String sOption) {
        String returnString = "";
        if (aResult.size() == 0) return printformat(mode, "NONE");

        if (sOption.equals("STRING")) {
            PriorityQueue<CmpString> tempResult = new PriorityQueue<>();
            int maxresult = 5;

            if (aResult.size() < maxresult) maxresult = aResult.size();

            for (int i = 0; i < maxresult; i++) {
            	CmpString tempQueue = aResult.poll();
                returnString += printformat(mode, employeeHM.get(tempQueue.getEmployeeNum()).toString());
                tempResult.offer(tempQueue);
            }
            aResult.addAll(tempResult);
        } else if (sOption.equals("NUMBER")) {
            returnString = printformat(mode, aResult.size() + "");
        }
        return returnString;
    }
    
    protected static PriorityQueue<CmpString> schResult(String[] tempsplit) {

//      employPQ = new PriorityQueue<>();
      PriorityQueue<CmpString> resultList = new PriorityQueue<>();
      String searchKey = tempsplit[4];

      if (!tempsplit[2].equals(" ")) {
          searchKey += tempsplit[2].substring(1, 2);
      }
      String searchValue = searchKey + tempsplit[5];

      if (searchKey.equals("employeeNum")) {
          if (employeeHM.containsKey(tempsplit[5]) && !employeeHM.get(tempsplit[5]).getRemoveFlag()) {
              resultList.offer(new CmpString(tempsplit[5]));
          }
      } else if (searchHM.containsKey(searchValue)) {
          ArrayList<CmpString> cache = new ArrayList<>();
          while (!searchHM.get(searchValue).isEmpty()) {
              String sEmpNum = searchHM.get(searchValue).poll().getEmployeeNum();
              if (employeeHM.get(sEmpNum).getRemoveFlag()) continue;
              if (!employeeHM.get(sEmpNum).getObj(searchKey).equals(tempsplit[5])) continue;
              //refactoring 필요할듯합니다
              if (cache.size() == 0) {
                  cache.add(new CmpString(sEmpNum));
                  resultList.offer(new CmpString(sEmpNum));
                  continue;
              } else if (cache.size() != 0 && cache.get(cache.size() - 1).getEmployeeNum().equals(sEmpNum)) continue;
              cache.add(new CmpString(sEmpNum));
              resultList.offer(new CmpString(sEmpNum));
          }
          for (CmpString tmp : cache) {
              searchHM.get(searchValue).offer(tmp);
          }
      }
      return resultList;
  }
  
    
    protected static void checkSearchHM(String searchkey, String newEmployeeNum) {
        if (searchHM.containsKey(searchkey)) {
            searchHM.get(searchkey).offer(new CmpString(newEmployeeNum));
        } else {
            PriorityQueue<CmpString> pq = new PriorityQueue<>();
            pq.offer(new CmpString(newEmployeeNum));
            searchHM.put(searchkey, pq);
        }
    }
	
}
