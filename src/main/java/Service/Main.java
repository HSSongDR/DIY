package Service;

import static constants.Constants.CMD_ADD;
import static constants.Constants.CMD_DEL;
import static constants.Constants.CMD_MOD;
import static constants.Constants.CMD_SCH;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.PriorityQueue;

import Service.DB;
import VO.EmployeeInfo;

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

    private static String inputFilePath = "./src/input.txt";
    private static String outputFilePath = "./src/output.txt";
    private static String answerFilePath = "./src/answer.txt";

    private static void printOut(String printString) {
        try { // 파일 객체 생성
            System.out.print(printString);
            File file = new File(outputFilePath);
            FileWriter fw = new FileWriter(file, true);
            fw.write(printString);
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        employeeHM = new HashMap<>();
        searchHM = new HashMap<>();

        systemTest();
    }

    private static void systemTest() throws IOException {




        BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
        try {
            Files.delete(Paths.get(outputFilePath));
        }catch (Exception e){

        }

        while (true) {
            String temp = br.readLine();
            if (temp == null) break;
            System.out.println(temp);
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


        if(compareTwoFiles(answerFilePath, outputFilePath)){
            System.out.println("Pass");
        }
        else{
            System.out.println("Fail");
        }


    }

    private static boolean compareTwoFiles(String filePath1, String filePath2) throws IOException {

        BufferedReader answer = new BufferedReader(new FileReader(filePath1));
        BufferedReader output = new BufferedReader(new FileReader(filePath2));

        String line1 = "", line2 = "";
        int linenumber =0;
        while ((line1 = output.readLine()) != null) {
            line2 = answer.readLine();
            if (line2 == null || !line1.equals(line2)) {
                System.out.println("not matched at line " + linenumber);
                System.out.println("expected : " + line2);
                System.out.println("your out : " + line1);
                return false;
            }
            linenumber++;
        }
        return true;
    }
}
