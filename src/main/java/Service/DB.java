package Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.PriorityQueue;
import static constants.Constants.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

import VO.EmployeeInfo;


class cmpString implements Comparable<cmpString> {
    String employeeNum;

    public cmpString(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }


    @Override
    public int compareTo(cmpString o) {
        int originBirth = Integer.parseInt(transformbirth(this.employeeNum.substring(0, 2)));
        String originEmpno = this.employeeNum.substring(2, 8);
        int targetBirth = Integer.parseInt(transformbirth(o.employeeNum.substring(0, 2)));
        String targetEmpno = o.employeeNum.substring(2, 8);
        if (targetBirth != originBirth) return (targetBirth - originBirth) > 0 ? -1 : 1;
        else {
            return originEmpno.compareTo(targetEmpno);
        }
    }

    private String transformbirth(String origin) {
        if ('0' <= origin.charAt(0) && origin.charAt(0) <= '2') {
            return "1" + origin;
        }
        return origin;
    }
}

public class DB extends Main {

    public static HashMap<String, EmployeeInfo> employeeHM;
    public static HashMap<String, PriorityQueue<cmpString>> searchHM;

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


    private static String printformat(String mode, String valueString) {
        return mode + "," + valueString + "\n";
    }


    private static void createSearchHM(EmployeeInfo newEmployeeInfo) {

        String sEmployeeNum = newEmployeeInfo.getObj("employeeNum");
        for (String search : searchKey) {
            checkSearchHM(search + newEmployeeInfo.getObj(search), sEmployeeNum);
        }
    }


    private static void checkSearchHM(String searchkey, String newEmployeeNum) {
        if (searchHM.containsKey(searchkey)) {
            searchHM.get(searchkey).offer(new cmpString(newEmployeeNum));
        } else {
            PriorityQueue<cmpString> pq = new PriorityQueue<>();
            pq.offer(new cmpString(newEmployeeNum));
            searchHM.put(searchkey, pq);
        }
    }

    public static void ADD(String[] tempsplit) {

        if (tempsplit.length < 10) System.out.println("ADD_FAIL :: Insert Null Data");

        EmployeeInfo newEmployeeInfo =
                new EmployeeInfo(tempsplit[4], tempsplit[5], tempsplit[6], tempsplit[7], tempsplit[8], tempsplit[9]);
        employeeHM.put(tempsplit[4], newEmployeeInfo);

        createSearchHM(newEmployeeInfo);
    }

    public static String SCH(String[] tempsplit) {
        PriorityQueue<cmpString> aResult = schResult(tempsplit);
        return schPrint("SCH", aResult, checkPrintTyep(tempsplit[1]));
    }

    private static String checkPrintTyep(String printType){
        return printType.equals(" ") ? "NUMBER" : "STRING";
    }

    private static String schPrint(String mode, PriorityQueue<cmpString> aResult, String sOption) {
        String returnString = "";
        if (aResult.size() == 0) return printformat(mode, "NONE");

        if (sOption.equals("STRING")) {
            PriorityQueue<cmpString> tempResult = new PriorityQueue<>();
            int maxresult = 5;

            if (aResult.size() < maxresult) maxresult = aResult.size();

            for (int i = 0; i < maxresult; i++) {
                cmpString tempQueue = aResult.poll();
                returnString += printformat(mode, employeeHM.get(tempQueue.getEmployeeNum()).toString());
                tempResult.offer(tempQueue);
            }
            aResult.addAll(tempResult);
        } else if (sOption.equals("NUMBER")) {
            returnString = printformat(mode, aResult.size() + "");
        }
        return returnString;
    }

    private static PriorityQueue<cmpString> schResult(String[] tempsplit) {

//        employPQ = new PriorityQueue<>();
        PriorityQueue<cmpString> resultList = new PriorityQueue<>();
        String searchKey = tempsplit[4];

        if (!tempsplit[2].equals(" ")) {
            searchKey += tempsplit[2].substring(1, 2);
        }
        String searchValue = searchKey + tempsplit[5];

        if (searchKey.equals("employeeNum")) {
            if (employeeHM.containsKey(tempsplit[5]) && !employeeHM.get(tempsplit[5]).getRemoveFlag()) {
                resultList.offer(new cmpString(tempsplit[5]));
            }
        } else if (searchHM.containsKey(searchValue)) {
            ArrayList<cmpString> cache = new ArrayList<>();
            while (!searchHM.get(searchValue).isEmpty()) {
                String sEmpNum = searchHM.get(searchValue).poll().getEmployeeNum();
                if (employeeHM.get(sEmpNum).getRemoveFlag()) continue;
                if (!employeeHM.get(sEmpNum).getObj(searchKey).equals(tempsplit[5])) continue;
                //refactoring 필요할듯합니다
                if (cache.size() == 0) {
                    cache.add(new cmpString(sEmpNum));
                    resultList.offer(new cmpString(sEmpNum));
                    continue;
                } else if (cache.size() != 0 && cache.get(cache.size() - 1).getEmployeeNum().equals(sEmpNum)) continue;
                cache.add(new cmpString(sEmpNum));
                resultList.offer(new cmpString(sEmpNum));
            }
            for (cmpString tmp : cache) {
                searchHM.get(searchValue).offer(tmp);
            }
        }
        return resultList;
    }


    public static String DEL(String[] tempsplit) {
        // 대상 조회
        PriorityQueue<cmpString> aResult = schResult(tempsplit);
        // 프린트 옵션
        String printString = schPrint("DEL", aResult, checkPrintTyep(tempsplit[1]));

        for (cmpString empNum : aResult) {
            employeeHM.get(empNum.getEmployeeNum()).setRemoveFlag();
        }
        return printString;
    }


    public static String MOD(String[] tempsplit) {
        // 대상 조회
        PriorityQueue<cmpString> aResult = schResult(tempsplit);
        // 프린트 옵션
        String printString = schPrint("MOD", aResult, checkPrintTyep(tempsplit[1]));


        String modifyKey = tempsplit[6];
        if (!tempsplit[2].equals(" ")) {
            modifyKey += tempsplit[2].substring(1, 2);
        }

        for (cmpString empNum : aResult) {

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
