package Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import VO.EmployeeInfo;

import static constants.Constants.*;

class cmpString implements Comparable<cmpString>{
    String employeeNum;

    public cmpString(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }


    @Override
    public int compareTo(cmpString o) {
        int originBirth=Integer.parseInt(transformbirth(this.employeeNum.substring(0,2)));
        String originEmpno=this.employeeNum.substring(2,8);
        int targetBirth=Integer.parseInt(transformbirth(o.employeeNum.substring(0,2)));
        String targetEmpno=o.employeeNum.substring(2,8);
        if(targetBirth!=originBirth)return (targetBirth-originBirth)>0?-1:1;
        else{
            return originEmpno.compareTo(targetEmpno);
        }
    }

    private String transformbirth(String origin) {
        if('0'<= origin.charAt(0)&& origin.charAt(0)<='2'){
            return "1"+ origin;
        }
        return origin;
    }
}

public class Main {
    public static HashMap<String, EmployeeInfo> employeeHM;
    public static HashMap<String, PriorityQueue<cmpString>> searchHM;



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

        //BufferedReader br = new BufferedReader(new FileReader("./DIY/src/input.txt"));
        employeeHM = new HashMap<>();
        searchHM = new HashMap<>();
//        //pq order 확인
//        System.out.println("012345".substring(0,2));
//        PriorityQueue <cmpString> pq= new PriorityQueue<>();
//        pq.offer(new cmpString("90123456"));
//        pq.offer(new cmpString("72123456"));
//        pq.offer(new cmpString("00123456"));
//        pq.offer(new cmpString("00123456"));
//        pq.offer(new cmpString("00323456"));
//        pq.offer(new cmpString("00023456"));
//        while (!pq.isEmpty()){
//            System.out.println(pq.poll().getEmployeeNum());
//        }

//        while (true) {
//            String temp = br.readLine();
//            if (temp == null) break;
////            System.out.println(temp);
//            String[] tempsplit = temp.split(",");
//
//            if (tempsplit[0].equals(CMD_ADD)) {
//                DB.ADD(tempsplit);
//            } else if (tempsplit[0].equals(CMD_SCH)) {
//                printOut(DB.SCH(tempsplit));
//            } else if (tempsplit[0].equals(CMD_DEL)) {
//                printOut(DB.DEL(tempsplit));
//            } else if (tempsplit[0].equals(CMD_MOD)) {
//                printOut(DB.MOD(tempsplit));
//            }
//        }
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
