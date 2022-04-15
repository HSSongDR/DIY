
import java.util.ArrayList;

public class DB {

    private static void createSearchHM(EmployeeInfo newEmployeeInfo) {
        String sEmployeeNum = newEmployeeInfo.getEmployeeNum();
        String name = newEmployeeInfo.getName();
        checkSearchHM(name, sEmployeeNum);

        String fname = "FNAME"+name.split(" ")[0];
        checkSearchHM(fname, sEmployeeNum);

        String lname = "LNAME"+name.split(" ")[1];
        checkSearchHM(lname, sEmployeeNum);

        String cl = newEmployeeInfo.getCl();
        checkSearchHM(cl, sEmployeeNum);

        String phoneNum = newEmployeeInfo.getPhoneNum();
        checkSearchHM(phoneNum, sEmployeeNum);

        String fphone = "FPHONE"+phoneNum.split("-")[1];
        checkSearchHM(fphone, sEmployeeNum);

        String lphone = "LPHONE"+phoneNum.split("-")[2];
        checkSearchHM(lphone, sEmployeeNum);

        String birthday = "BIRTH" + newEmployeeInfo.getBirthday();
        checkSearchHM(birthday, sEmployeeNum);

        String ybirthday = "Y" + birthday.substring(0, 4);
        checkSearchHM(ybirthday, sEmployeeNum);

        String mbirthday = "M" + birthday.substring(4, 6);
        checkSearchHM(mbirthday, sEmployeeNum);

        String dbirthday = "D" + birthday.substring(6, 8);
        checkSearchHM(dbirthday, sEmployeeNum);

        String certi = newEmployeeInfo.getCerti();
        checkSearchHM(certi, sEmployeeNum);

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

    public void SCH() {
    }

    public void MOD() {
    }
}
