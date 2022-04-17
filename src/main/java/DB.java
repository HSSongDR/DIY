
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
//        String sEmployeeNum = newEmployeeInfo.getEmployeeNum();
//
//
//        String name = newEmployeeInfo.name;
//        checkSearchHM("name" + name, sEmployeeNum);
//
//        String namef = name.split(" ")[0];
//        checkSearchHM("namef" + namef, sEmployeeNum);
//
//        String lname = name.split(" ")[1];
//        checkSearchHM("namel" + lname, sEmployeeNum);
//
//        String cl = newEmployeeInfo.cl;
//        checkSearchHM("cl" + cl, sEmployeeNum);
//
//        String phoneNum = newEmployeeInfo.phoneNum;
//        checkSearchHM("phoneNum" + phoneNum, sEmployeeNum);
//
//        String phoneNumf = phoneNum.split("-")[1];
//        checkSearchHM("phoneNumf" + phoneNumf, sEmployeeNum);
//
//        String phoneNuml = phoneNum.split("-")[2];
//        checkSearchHM("phoneNuml" + phoneNuml, sEmployeeNum);
//
//        String birthday = newEmployeeInfo.birthday;
//        checkSearchHM("birthday" + birthday, sEmployeeNum);
//
//        String birthdayy = birthday.substring(0, 4);
//        checkSearchHM("birthdayy" + birthdayy, sEmployeeNum);
//
//        String birthdaym = birthday.substring(4, 6);
//        checkSearchHM("birthdaym" + birthdaym, sEmployeeNum);
//
//        String birthdayd = birthday.substring(6, 8);
//        checkSearchHM("birthdayd" + birthdayd, sEmployeeNum);
//
//        String certi = newEmployeeInfo.getCerti();
//        String certi = newEmployeeInfo.certi;
//        checkSearchHM("certi" + certi, sEmployeeNum);
    }

    private static void checkSearchHM(String searchkey, String newEmployeeNum) {
        if (searchHM.containsKey(searchkey)) {
            searchHM.get(searchkey).add(newEmployeeNum);
        } else {
            ArrayList<String> list = new ArrayList<String>();
            list.add(newEmployeeNum);
            searchHM.put(searchkey, list);
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
        ArrayList<String> aResult = schResult(tempsplit);

        //print option
        return schPrint("SCH", aResult, (tempsplit[1].equals(" ") ? "NUMBER" : "STRING"));
    }

    private static String schPrint(String mod, ArrayList<String> aResult, String sOption) {
        String returnString = "";
        if (aResult.size() == 0) return mod + "," + "NONE"+ "\n";
        if (sOption.equals("STRING")) {

            // 전체 LIST의 EMPLOYEE NO 기준으로 정렬하는 기능 추가 필요함.
            int maxresult = 5;
            if (aResult.size() < 5) maxresult = aResult.size();
            for (int i = 0; i < maxresult; i++) {
                returnString += mod + "," + employeeHM.get(aResult.get(i)).toString() + "\n";
            }
        } else if (sOption.equals("NUMBER")) {
            returnString = mod + "," + aResult.size() + "\n";
        }

        return returnString;
    }

    //PriorityQueue를 이용해서
    //1. 사번 순으로 정렬해서 저장.
    //2. result에 동일 사번이 적재되지 않도록 개선.
    private static ArrayList<String> schResult(String[] tempsplit) {

//        employPQ = new PriorityQueue<>();
        String searchKey = tempsplit[4];

        if (!tempsplit[2].equals(" ")) {
            searchKey += tempsplit[2].substring(1, 2);
        }
        String searchValue = searchKey + tempsplit[5];

        ArrayList<String> resultList = new ArrayList<String>();

        if (searchKey.equals("employeeNum")) {
            if (employeeHM.containsKey(tempsplit[5]) && !employeeHM.get(tempsplit[5]).getRemoveFlag()) {
                resultList.add(tempsplit[5]);
            }
        } else if (searchHM.containsKey(searchValue)) {
            for (int i = 0; i < searchHM.get(searchValue).size(); i++) {
                String sEmpNum = searchHM.get(searchValue).get(i);
                if (employeeHM.get(sEmpNum).getRemoveFlag()) continue;
                if (!employeeHM.get(sEmpNum).getObj(searchKey).equals(tempsplit[5])) continue;

                resultList.add(sEmpNum);
            }
        }
        return resultList;
    }

    public static String DEL(String[] tempsplit) {
        // 대상 조회
        ArrayList<String> aResult = schResult(tempsplit);
        // 프린트 옵션
        String sSearchResult = schPrint("DEL", aResult, (tempsplit[1].equals(" ") ? "NUMBER" : "STRING"));

        for (String empNum : aResult) {
            employeeHM.get(empNum).setRemoveFlag();
        }

        return sSearchResult;
    }


    public static String MOD(String[] tempsplit) {
        // 대상 조회
        ArrayList<String> aResult = schResult(tempsplit);
        // 프린트 옵션
        String sSearchResult = schPrint("MOD", aResult, (tempsplit[1].equals(" ") ? "NUMBER" : "STRING"));


        String modifyKey = tempsplit[6];
        if (!tempsplit[2].equals(" ")) {
            modifyKey += tempsplit[2].substring(1, 2);
        }


        //MOD,-p, , ,name,FB NTAWR,cl,CL3
        for (String empNum : aResult) {
            //원본 변경
            employeeHM.get(empNum).setObj(tempsplit[6], tempsplit[7]);

            checkSearchHM(modifyKey + employeeHM.get(empNum).getObj(modifyKey), empNum);
            if (modifyKey.equals("name")) {
                for (String subkey : nameSubKey) {
                    checkSearchHM(subkey + employeeHM.get(empNum).getObj(subkey), empNum);
                }
            } else if (modifyKey.equals("phoneNum")) {
                for (String subkey : phoneSubKey) {
                    checkSearchHM(subkey + employeeHM.get(empNum).getObj(subkey), empNum);
                }
            } else if (modifyKey.equals("birthday")) {
                for (String subkey : birthSubKey) {
                    checkSearchHM(subkey + employeeHM.get(empNum).getObj(subkey), empNum);
                }
            }
        }

        return sSearchResult;
    }


}
