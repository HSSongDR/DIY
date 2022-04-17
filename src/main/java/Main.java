
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import static constants.Constants.*;

public class Main {
    protected static HashMap<String, EmployeeInfo> employeeHM;
    protected static HashMap<String, ArrayList<String>> searchHM;

    private static void printOut(String printString) {

        try { // 파일 객체 생성
            System.out.print(printString);
            File file = new File("./DIY/src/output.txt");
            FileWriter fw = new FileWriter(file, true);
            fw.write(printString);
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("./DIY/src/input.txt"));
        employeeHM = new HashMap<>();
        searchHM = new HashMap<>();

        while (true) {
            String temp = br.readLine();
            if (temp == null) break;
//            System.out.println(temp);
            String[] tempsplit = temp.split(",");

            if (tempsplit[0].equals(CMD_ADD)) {
                DB.ADD(tempsplit);
            } else if (tempsplit[0].equals(CMD_SCH)) {
                printOut(DB.SCH(tempsplit));
            } else if (tempsplit[0].equals(CMD_DEL)) {
                printOut(DB.DEL(tempsplit));
            } else if (tempsplit[0].equals(CMD_MOD)) {
                printOut(DB.MOD(tempsplit));
            }
        }
//
//        System.out.println(employeeHM.size());
//        System.out.println("employeeHM.get(\"12000001\").name :" + employeeHM.get("15123099").getName());
//        System.out.println("employeeHM.get(\"12000001\").birthday :" + employeeHM.get("15123099").getBirthday());
//        System.out.println("employeeHM.get(\"12000001\").certi :" + employeeHM.get("15123099").getCerti());
//        if (searchHM.get("phoneNumf_3112").size() > 0) {
//            for (String getNumber : searchHM.get("phoneNumf_3112")) {
//                System.out.println("searchHM.get(\"1234\") :" + getNumber);
//                printOut(getNumber);
//            }
//        }

    }
}
