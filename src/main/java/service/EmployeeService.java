package service;

import static constants.Constants.outputFilePath;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import constants.Constants;
import dto.EmployeeInfo;
import util.CmpString;

public abstract class EmployeeService {

	public static HashMap<String, EmployeeInfo> employeeHM;
	public static HashMap<String, PriorityQueue<CmpString>> searchHM;

	public abstract String run(String[] lineSplitByComma);

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

	protected String checkPrintType(String printType) {
		return printType.equals(" ") ? Constants.NUMBER : Constants.STRING;
	}
	
	private String printformat(String mode, String valueString) {
		return mode + Constants.DELIMITER_COMMA + valueString + Constants.NEW_LINE;
	}

	protected String schPrint(String mode, PriorityQueue<CmpString> resultPQ, String searchOption) {
		String returnString = "";
		if (resultPQ.size() == 0)
			return printformat(mode, Constants.NO_DATA);

		if (searchOption.equals(Constants.STRING)) {
			PriorityQueue<CmpString> tempResultPQ = new PriorityQueue<>();
			int maxresult = 5;

			if (resultPQ.size() < maxresult)
				maxresult = resultPQ.size();

			for (int i = 0; i < maxresult; i++) {
				CmpString tempQueue = resultPQ.poll();
				returnString += printformat(mode, employeeHM.get(tempQueue.getEmployeeNum()).toString());
				tempResultPQ.offer(tempQueue);
			}
			resultPQ.addAll(tempResultPQ);
		} else if (searchOption.equals(Constants.NUMBER)) {
			returnString = printformat(mode, resultPQ.size() + "");
		}
		return returnString;
	}

	protected PriorityQueue<CmpString> schResult(String[] lineSplitByComma) {

		PriorityQueue<CmpString> resultList = new PriorityQueue<>();
		String searchKey = lineSplitByComma[4];

		if (!lineSplitByComma[2].equals(" ")) {
			searchKey += lineSplitByComma[2].substring(1, 2);
		}
		String searchValue = searchKey + lineSplitByComma[5];

		if (searchKey.equals(Constants.EMP_NUM)) {
			if (employeeHM.containsKey(lineSplitByComma[5])) {
				resultList.offer(new CmpString(lineSplitByComma[5]));
			}
		} else if (searchHM.containsKey(searchValue)) {
			ArrayList<CmpString> cacheArr = new ArrayList<>();
			while (!searchHM.get(searchValue).isEmpty()) {
				String searchEmpNum = searchHM.get(searchValue).poll().getEmployeeNum();
				if (!employeeHM.containsKey(searchEmpNum))
					continue;
				if (!employeeHM.get(searchEmpNum).getObj(searchKey).equals(lineSplitByComma[5]))
					continue;
				if (cacheArr.size() == 0) {
					cacheArr.add(new CmpString(searchEmpNum));
					resultList.offer(new CmpString(searchEmpNum));
					continue;
				} else if (cacheArr.size() != 0 && cacheArr.get(cacheArr.size() - 1).getEmployeeNum().equals(searchEmpNum))
					continue;
				cacheArr.add(new CmpString(searchEmpNum));
				resultList.offer(new CmpString(searchEmpNum));
			}
			for (CmpString tmp : cacheArr) {
				searchHM.get(searchValue).offer(tmp);
			}
		}
		return resultList;
	}

	protected void checkSearchHM(String searchkey, String newEmployeeNum) {
		if (searchHM.containsKey(searchkey)) {
			searchHM.get(searchkey).offer(new CmpString(newEmployeeNum));
		} else {
			PriorityQueue<CmpString> pq = new PriorityQueue<>();
			pq.offer(new CmpString(newEmployeeNum));
			searchHM.put(searchkey, pq);
		}
	}

	protected String[] getSubKey(String subKeyName) {
		String[] returnArray = { "" };
		if (subKeyName.equals(Constants.EMP_NAME))
			return Constants.nameSubKey;
		else if (subKeyName.equals(Constants.EMP_PHONE))
			return Constants.phoneSubKey;
		else if (subKeyName.equals(Constants.EMP_BIRTH))
			return Constants.birthSubKey;
		return returnArray;
	}

}
