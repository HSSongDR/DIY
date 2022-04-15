
import constants.Constants;
import java.util.ArrayList;
import java.util.List;

interface DB_SCH{
	public List<String> SCH(String op1,String op2,String op3,String colName, String colValue);
}

public class DB {

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
		checkSearchHM("certi_"+certi, sEmployeeNum);

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

	//String employeeNum, String name, String cl, String phoneNum, String birthday, String certi
	public static void MOD(String cmd,String op1,String op2, String op3, String searchColumn, String searchValue, String chgColumn, String chgValue  ) {
		String SearchKey=FindSearchKey(op2,searchColumn,searchValue);
		String ret="";

		//searchColumn이 사번정보로 들어올 수 없다. (사원 번호는 수정할 수 없다)
		ArrayList<String>empnoList=Main.searchHM.get(SearchKey);

		//ret 출력값 먼저 구하기
		if(op1.equals("-p")){
			ret+=getOp1Print(cmd,empnoList);
		}
		else ret+=getPrint(cmd,empnoList);

		//NONE이면 변경할 필요가 없음
		if(!ret.endsWith("NONE")){
			for(String empno:empnoList){
				//사번을 key값으로 EmployeeInfo 값 변경 or 삭제
				String originValue= getValue(chgColumn, empno);
				ModifyEmpInfo(empno,chgColumn, chgValue);

				//변경할때 우선 searchHM 내의 변경 또는 삭제할 키값으로 현재 사번(empno)과 동일한것들 찾아 모두 삭제 (옵션2가 있을경우 관련 key 예를들어 [생년][월][일][생년월일] 에 조회되는 모든 사번 삭제)
				// MOD옵션의 경우 변경된 chgValue 값으로 hash에 모두 add (옵션 2 고려해서 다른 컬럼에도 추가)
				DelHM(chgColumn, empno, originValue);
				AddHM(chgColumn, chgValue, empno);

			}

		}

	}


	private static void DelHM(String chgColumn, String empno, String originValue) {
		String key=FindSearchKey(" ",chgColumn,originValue);
		removeValue_searchHM(empno, key);

		if(chgColumn.equals(Constants.EMP_NAME)) {
			removeValue_searchHM(empno,FindSearchKey(Constants.OPT2_FIRST_NAME,chgColumn,originValue.split(" ")[0]));
			removeValue_searchHM(empno,FindSearchKey(Constants.OPT2_LAST_NAME,chgColumn,originValue.split(" ")[1]));
		};
		if(chgColumn.equals(Constants.EMP_PHONE)) {
			removeValue_searchHM(empno,FindSearchKey(Constants.OPT2_MID_PHONE,chgColumn,originValue.split("-")[1]));
			removeValue_searchHM(empno,FindSearchKey(Constants.OPT2_LAST_PHONE,chgColumn,originValue.split("-")[2]));
		};
		if(chgColumn.equals(Constants.EMP_BIRTH)) {
			removeValue_searchHM(empno,FindSearchKey(Constants.OPT2_BIRTH_YEAR,chgColumn,originValue.substring(0, 4)));
			removeValue_searchHM(empno,FindSearchKey(Constants.OPT2_BIRTH_MON,chgColumn,originValue.substring(4, 6)));
			removeValue_searchHM(empno,FindSearchKey(Constants.OPT2_BIRTH_DAY,chgColumn,originValue.substring(6, 8)));
		};

	}

	private static void removeValue_searchHM(String empno, String key) {
		int size=Main.searchHM.get(key).size();
		for(int i=0;i<size;i++){
			if(Main.searchHM.get(key).get(i).equals(empno)){
				Main.searchHM.get(key).remove(i);
				return;
			}

		}
	}

	private static void AddHM(String chgColumn, String chgValue, String empno) {
		String key=FindSearchKey(" ",chgColumn,chgValue);
		checkSearchHM(key,empno);

		if(chgColumn.equals(Constants.EMP_NAME)) {
			checkSearchHM(FindSearchKey(Constants.OPT2_FIRST_NAME,chgColumn,chgValue.split(" ")[0]),empno);
			checkSearchHM(FindSearchKey(Constants.OPT2_LAST_NAME,chgColumn,chgValue.split(" ")[1]),empno);
		};
		if(chgColumn.equals(Constants.EMP_PHONE)) {
			checkSearchHM(FindSearchKey(Constants.OPT2_MID_PHONE,chgColumn,chgValue.split("-")[1]),empno);
			checkSearchHM(FindSearchKey(Constants.OPT2_LAST_PHONE,chgColumn,chgValue.split("-")[2]),empno);
		};
		if(chgColumn.equals(Constants.EMP_BIRTH)) {
			checkSearchHM(FindSearchKey(Constants.OPT2_BIRTH_YEAR,chgColumn,chgValue.substring(0, 4)),empno);
			checkSearchHM(FindSearchKey(Constants.OPT2_BIRTH_MON,chgColumn,chgValue.substring(4, 6)),empno);
			checkSearchHM(FindSearchKey(Constants.OPT2_BIRTH_DAY,chgColumn,chgValue.substring(6, 8)),empno);
		};
	}

	private static String getValue(String chgColumn, String empno) {
		if(chgColumn.equals(Constants.EMP_NAME))return Main.employeeHM.get(empno).getName();
		if(chgColumn.equals(Constants.EMP_CL))return Main.employeeHM.get(empno).getCl();
		if(chgColumn.equals(Constants.EMP_PHONE))return Main.employeeHM.get(empno).getPhoneNum();
		if(chgColumn.equals(Constants.EMP_BIRTH))return Main.employeeHM.get(empno).getBirthday();
		if(chgColumn.equals(Constants.EMP_CERTI))return Main.employeeHM.get(empno).getCerti();
		return "";
	}

	private static void ModifyEmpInfo(String chgColumn, String chgValue, String empno) {
		if(chgColumn.equals(Constants.EMP_NAME))Main.employeeHM.get(empno).setName(chgValue);
		if(chgColumn.equals(Constants.EMP_CL))Main.employeeHM.get(empno).setCl(chgValue);
		if(chgColumn.equals(Constants.EMP_PHONE))Main.employeeHM.get(empno).setPhoneNum(chgValue);
		if(chgColumn.equals(Constants.EMP_BIRTH))Main.employeeHM.get(empno).setBirthday(chgValue);
		if(chgColumn.equals(Constants.EMP_CERTI))Main.employeeHM.get(empno).setCerti(chgValue);
	}

	private static String getPrint(String cmd, ArrayList<String> empnoList) {
		if(empnoList.size()==0) return cmd+",NONE";
		return cmd+Constants.DELIMITER_COMMA+empnoList.size();
	}

	private static String getOp1Print(String cmd,ArrayList<String>empnoList) {
		//생년+사번형식으로 정렬 필요
		if(empnoList.size()==0) return cmd+",NONE";
		String ret="";
		for(String empno:empnoList){
			ret+=cmd+ Main.employeeHM.get(empno).toString()+"\n";
		}
		return ret;
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
}
