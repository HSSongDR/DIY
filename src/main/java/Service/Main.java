package Service;

import static constants.Constants.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.PriorityQueue;

import Service.DB;
import VO.EmployeeInfo;



public class Main {

    public static void main(String[] args) throws IOException {
        systemTest();
    }

    private static void systemTest() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
        DB.INIT();
        while (true) {
            String temp = br.readLine();
            if (temp == null) break;
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

        if (compareTwoFiles(answerFilePath, outputFilePath)) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
    }

    private static void printOut(String printString) {

        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(outputFilePath, true));
            fw.write(printString);
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean compareTwoFiles(String filePath1, String filePath2) throws IOException {

        BufferedReader answer = new BufferedReader(new FileReader(filePath1));
        BufferedReader output = new BufferedReader(new FileReader(filePath2));

        String line1 = "", line2 = "";
        int linenumber = 0;
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
