
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import static constants.Constants.*;

public class DB extends Main {

    private static PriorityQueue<String> employPQ;

    private static String[] searchKey = {"name", "namef", "namel", "cl", "phoneNum", "phoneNumm", "phoneNuml", "birthday", "birthdayy", "birthdaym", "birthdayd", "certi"};
    private static String[] makeSubKey = {"name", "phoneNum", "birthday"};
    private static String[] nameSubKey = {"namef", "namel"};
    private static String[] phoneSubKey = {"phoneNumm", "phoneNuml"};
    private static String[] birthSubKey = {"birthdayy", "birthdaym", "birthdayd"};

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
//        SCH,-p,-d, ,birthday,04
        PriorityQueue<cmpString> aResult = schResult(tempsplit);

        //print option
        return schPrint("SCH", aResult, (tempsplit[1].equals(" ") ? "NUMBER" : "STRING"));
    }

    private static String schPrint(String mod, PriorityQueue<cmpString> aResult, String sOption) {
        String returnString = "";
        if (aResult.size() == 0) return mod + "," + "NONE"+ "\n";
        if (sOption.equals("STRING")) {

            // 전체 LIST의 EMPLOYEE NO 기준으로 정렬하는 기능 추가 필요함.
            int maxresult = 5;
            if (aResult.size() < 5) maxresult = aResult.size();
            for (int i = 0; i < maxresult; i++) {
                returnString += mod + "," + employeeHM.get(aResult.poll().getEmployeeNum()).toString() + "\n";
            }
        } else if (sOption.equals("NUMBER")) {
            returnString = mod + "," + aResult.size() + "\n";
        }

        return returnString;
    }

    private static PriorityQueue<cmpString> schResult(String[] tempsplit) {

//        employPQ = new PriorityQueue<>();
        PriorityQueue<cmpString> resultList=new PriorityQueue<>();
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
            ArrayList<cmpString> cache=new ArrayList<>();
            while(!searchHM.get(searchValue).isEmpty()){
                String sEmpNum = searchHM.get(searchValue).poll().getEmployeeNum();
                if (employeeHM.get(sEmpNum).getRemoveFlag()) continue;
                if (!employeeHM.get(sEmpNum).getObj(searchKey).equals(tempsplit[5])) continue;
                //refactoring 필요할듯합니다
                if(cache.size()==0){
                    cache.add(new cmpString(sEmpNum));
                    resultList.offer(new cmpString(sEmpNum));
                    continue;
                }
                else if(cache.size()!=0&&cache.get(cache.size()-1).getEmployeeNum().equals(sEmpNum))continue;
                cache.add(new cmpString(sEmpNum));
                resultList.offer(new cmpString(sEmpNum));
            }
            for(cmpString tmp:cache){
                searchHM.get(searchValue).offer(tmp);
            }
        }
        return resultList;
    }


    public static String DEL(String[] tempsplit) {
        // 대상 조회
        PriorityQueue<cmpString> aResult = schResult(tempsplit);
        // 프린트 옵션
        String sSearchResult = schPrint("DEL", aResult, (tempsplit[1].equals(" ") ? "NUMBER" : "STRING"));

        for (cmpString empNum : aResult) {
            employeeHM.get(empNum.getEmployeeNum()).setRemoveFlag();
        }

        return sSearchResult;
    }


    public static String MOD(String[] tempsplit) {
        // 대상 조회
        PriorityQueue<cmpString> aResult = schResult(tempsplit);
        // 프린트 옵션
        String sSearchResult = schPrint("MOD", aResult, (tempsplit[1].equals(" ") ? "NUMBER" : "STRING"));


        String modifyKey = tempsplit[6];
        if (!tempsplit[2].equals(" ")) {
            modifyKey += tempsplit[2].substring(1, 2);
        }


        //MOD,-p, , ,name,FB NTAWR,cl,CL3
        for (cmpString empNum : aResult) {
            //원본 변경
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

        return sSearchResult;
    }

}
