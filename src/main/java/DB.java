import java.util.*;
import java.util.ArrayList;
import constants.Constants;


public class DB {
    static HashMap<String, EmployeeInfo> employeeHM;
    static HashMap<String, ArrayList<String>> searchHM;


    private static void createSearchHM(EmployeeInfo newEmployeeInfo) {
        String sEmployeeNum = newEmployeeInfo.getEmployeeNum();

        String name = newEmployeeInfo.getName();
        checkSearchHM("name_"+name, sEmployeeNum);

        String namef = name.split(" ")[0];
        checkSearchHM("namef_"+namef, sEmployeeNum);

        String lname = name.split(" ")[1];
        checkSearchHM("namel_"+lname, sEmployeeNum);

        String cl = newEmployeeInfo.getCl();
        checkSearchHM("cl"+cl, sEmployeeNum);

        String phoneNum = newEmployeeInfo.getPhoneNum();
        checkSearchHM("phoneNum_"+phoneNum, sEmployeeNum);

        String fphone = phoneNum.split("-")[1];
        checkSearchHM("phoneNumf_"+fphone, sEmployeeNum);

        String lphone = phoneNum.split("-")[2];
        checkSearchHM("phoneNuml_"+lphone, sEmployeeNum);

        String birthday = newEmployeeInfo.getBirthday();
        checkSearchHM("birthday_"+birthday, sEmployeeNum);

        String ybirthday = birthday.substring(0, 4);
        checkSearchHM("birthdayy_"+ybirthday, sEmployeeNum);

        String mbirthday = "M" + birthday.substring(4, 6);
        checkSearchHM("birthdaym_"+mbirthday, sEmployeeNum);

        String dbirthday = "D" + birthday.substring(6, 8);
        checkSearchHM("birthdayd_"+dbirthday, sEmployeeNum);

        String certi = newEmployeeInfo.getCerti();
        checkSearchHM("cl_"+certi, sEmployeeNum);

    }

    private static void checkSearchHM(String searchkey, String newEmployeeNum) {
        if (Main.searchHM.get(searchkey) == null) {
            // serachHM신규 생성.
            ArrayList<String> list = new ArrayList<String>();
            list.add(newEmployeeNum);
            Main.searchHM.put(searchkey, list);
        } else {
            Main.searchHM.get(searchkey).add(newEmployeeNum);
        }
    }

    public static void ADD(String[] tempsplit) {
        if (tempsplit.length < 10) System.out.println("ADD_FAIL :: Insert Null Data");

        EmployeeInfo newEmployeeInfo =
                new EmployeeInfo(tempsplit[4], tempsplit[5], tempsplit[6], tempsplit[7], tempsplit[8], tempsplit[9]);
        Main.employeeHM.put(tempsplit[4], newEmployeeInfo);

        createSearchHM(newEmployeeInfo);
    }

    public void DEL() {
    }

    public void MOD() {
    }

    private static String FindSearchKey(String op2,String searchColumn,String asisKey){
        String ret="";
        if(searchColumn.equals(Constants.EMP_NUM)) {
            ret=asisKey;
        }
        if(searchColumn.equals(Constants.EMP_NAME)) {
            if(op2.equals(" "))ret="name_";
            if(op2.equals(Constants.OPT2_FIRST_NAME))ret="namef_";
            if(op2.equals(Constants.OPT2_LAST_NAME))ret="namel_";
            ret+=asisKey;

        }
        if(searchColumn.equals(Constants.EMP_CL)) {
            ret="cl_"+asisKey;
        }
        if(searchColumn.equals(Constants.EMP_PHONE)) {
            if(op2.equals(" "))ret="phoneNum_";
            if(op2.equals(Constants.OPT2_MID_PHONE))ret="phoneNumf_";
            if(op2.equals(Constants.OPT2_LAST_PHONE))ret="phoneNuml_";
            ret+=asisKey;
        }
        if(searchColumn.equals(Constants.EMP_BIRTH)) {
            if(op2.equals(" "))ret="birthday_";
            if(op2.equals(Constants.OPT2_BIRTH_YEAR))ret="birthdayy_";
            if(op2.equals(Constants.OPT2_BIRTH_MON))ret="birthdaym_";
            if(op2.equals(Constants.OPT2_BIRTH_DAY))ret="birthdayd_";
            ret+=asisKey;
        }
        if(searchColumn.equals(Constants.EMP_CERTI)) {
            ret+="certi_"+asisKey;
        }
        return ret;
    }

	private LinkedHashMap<String, EmployeeInfo> employeeDbLinkedHash;

	public  static List<String> SCH(String opt1, String opt2, String opt3, String colName, String colValue) {
		List<String> resultEmployNumList = new ArrayList<>();


        if(colName.compareTo("employnum")==0){
            if(Main.employeeHM.get(colValue) != null){
                resultEmployNumList.add(Main.employeeHM.get(colValue).getEmployeeNum());
            }
        }
        else{
            String searchkey = FindSearchKey(opt2, colName, colValue);
            if (Main.searchHM.get(searchkey) != null) {
                resultEmployNumList = Main.searchHM.get(searchkey);
            }
        }


		if(resultEmployNumList.size() == 0){
			System.out.print("NONE");
		}
		else {
			System.out.print(resultEmployNumList.size());
		}

		return resultEmployNumList;
	}

}
