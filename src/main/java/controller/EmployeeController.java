package controller;

import static constants.Constants.CMD_ADD;
import static constants.Constants.CMD_DEL;
import static constants.Constants.CMD_MOD;
import static constants.Constants.CMD_SCH;

import static constants.Constants.inputFilePath;
import static constants.Constants.outputFilePath;
import static constants.Constants.answerFilePath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import service.EmployeeAddServiceImpl;
import service.EmployeeDelServiceImpl;
import service.EmployeeModServiceImpl;
import service.EmployeeSchServiceImpl;
import service.EmployeeService;



public class EmployeeController {

    private static final String INPUT_OUTPUT_FILE_NAME_MATCHER = "^[a-z](?:_?[a-z0-9]+)*(\\.)(txt)$";

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Unexpected argument");
            System.out.println("Use : java -jar EmployeeManagement.jar [input file path] [output file path] ");
            System.out.println("Exiting...");
            return;
        }

        inputFilePath = args[0];
        outputFilePath = args[1];

        if (!fileNameCheck(inputFilePath) || !fileNameCheck(outputFilePath)) {
            System.out.println("Illegal file name");
            System.out.println("allowed pattern : " + INPUT_OUTPUT_FILE_NAME_MATCHER);
            System.out.println("Exiting...");
            return;
        }


        runExmployeeService();
        //checkAnswer(answerFilePath, outputFilePath);
    }

    private static boolean fileNameCheck(String fileName) {
        Pattern pattern = Pattern.compile(INPUT_OUTPUT_FILE_NAME_MATCHER, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(fileName);
        return matcher.find();
    }

    private static void runExmployeeService() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
        EmployeeService.INIT();

        EmployeeService addService = new EmployeeAddServiceImpl();
        EmployeeService modService = new EmployeeModServiceImpl();
        EmployeeService delService = new EmployeeDelServiceImpl();
        EmployeeService schService = new EmployeeSchServiceImpl();

        while (true) {
            String temp = br.readLine();
            if (temp == null) break;
            String[] tempsplit = temp.split(",");

            if (tempsplit[0].equals(CMD_ADD)) {
                addService.run(tempsplit);
            } else if (tempsplit[0].equals(CMD_SCH)) {
                printOut(schService.run(tempsplit));
            } else if (tempsplit[0].equals(CMD_DEL)) {
                printOut(delService.run(tempsplit));
            } else if (tempsplit[0].equals(CMD_MOD)) {
                printOut(modService.run(tempsplit));
            }
        }

    }

    private static void checkAnswer(String ANSWER_FILE_PATH, String OUTPUT_FILE_PATH) throws IOException {
        if (compareTwoFiles(ANSWER_FILE_PATH, OUTPUT_FILE_PATH)) {
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
