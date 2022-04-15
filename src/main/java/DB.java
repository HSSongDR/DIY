
import java.util.ArrayList;

import constants.Constants;

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

    public void DEL(String[] tempsplit) {
    	
    	String delCmd = tempsplit[0]; // DEL
    	String opt1 = tempsplit[1]; // opt1
    	String opt2 = tempsplit[2]; // opt2
    	String opt3 = tempsplit[3]; // " "
    	String searchColumn = tempsplit[4]; 
    	String searchkey = tempsplit[5]; // 검색할 String 010-1234-4567
    	String originSearchKey = tempsplit[5]; // 검색할 String 010-1234-4567
    	
   
    	searchkey = FindSearchKey(opt2, searchColumn, searchkey);
    	    	
     	ArrayList<String> empNumList = Main.searchHM.get(searchkey);
    	
     	
     	//searchColumn이 사번인 경우
     	if(searchColumn.equals(Constants.EMP_NUM)) {
     		if(Main.employeeHM.get(originSearchKey) == null) {
     			System.out.println(Constants.CMD_DEL+Constants.DELIMITER_COMMA+Constants.NO_DATA);
        		
     		}else {
     			if(opt1 == "p") {
     				System.out.println(Constants.CMD_DEL+Constants.DELIMITER_COMMA+Main.employeeHM.get(originSearchKey));
     			}else {
     				System.out.println(Constants.CMD_DEL+Constants.DELIMITER_COMMA+"1");
     			}
     		}
     		return;
     	}
     	     	
     		
     	// 검색한 결과 없음
    	if(empNumList == null || empNumList.size() == 0) {
    		System.out.println(Constants.CMD_DEL+Constants.DELIMITER_COMMA+Constants.NO_DATA);
    		return;
    	}
    	
    	/**TO-DO **/
    	// SERACH MAP 정렬 
    	
    	//op1이 없는 경우
    	if(opt1 == " ") {
    		System.out.println(Constants.CMD_DEL+Constants.DELIMITER_COMMA+empNumList.size());
    	}
    	
    	for(int i=0;i<empNumList.size();i++) {
    		String empNo = empNumList.get(i);
    		if(opt1.equals(Constants.OPT1_PRINT) && i < 5) {
    			//출력
    			System.out.println(Constants.CMD_DEL+Constants.DELIMITER_COMMA+Main.employeeHM.get(empNo));
    		}	
    		//main map에서 삭제
    		Main.employeeHM.remove(empNo);
    		
    		//searchHM에서 삭제
        	if(searchColumn.equals(Constants.EMP_NAME)) {
                if(opt2.equals(" ")) {
                	Main.searchHM.remove("name_"+originSearchKey);
                	ArrayList<String> firstNameList = Main.searchHM.get("namef_"+originSearchKey.split(" ")[0]);
                	ArrayList<String> lastNameList = Main.searchHM.get("namel_"+originSearchKey.split(" ")[1]);
                	
                	for(int j=0;j<firstNameList.size();j++) {
                		if(firstNameList.get(j).equals(empNo)) {
                			Main.searchHM.remove("namef_"+originSearchKey.split(" ")[0]);
                		}
                	}
                	
                	for(int j=0;j<lastNameList.size();j++) {
                		if(lastNameList.get(j).equals(empNo)) {
                			Main.searchHM.remove("namel_"+originSearchKey.split(" ")[1]);
                		}
                	}

                }else if (opt2.equals(Constants.OPT2_FIRST_NAME)) {
                	Main.searchHM.remove(searchkey);
                }else if(opt2.equals(Constants.OPT2_LAST_NAME)) {
                	Main.searchHM.remove(searchkey);
                }
             }else if(searchColumn.equals(Constants.EMP_CL)) {
            	 Main.searchHM.remove(searchkey);
             }else if(searchColumn.equals(Constants.EMP_PHONE)) {
            	 if(opt2.equals(" ")) {
                 	Main.searchHM.remove("phoneNum_"+originSearchKey);
                 	ArrayList<String> firstPhoneList = Main.searchHM.get("phoneNumf_"+originSearchKey.split("-")[1]);
                 	ArrayList<String> lastPhoneList = Main.searchHM.get("phoneNuml_"+originSearchKey.split("-")[2]);
                 	
                 	for(int j=0;j<firstPhoneList.size();j++) {
                 		if(firstPhoneList.get(j).equals(empNo)) {
                 			Main.searchHM.remove("phoneNumf_"+originSearchKey.split("-")[1]);
                 		}
                 	}
                 	
                 	for(int j=0;j<lastPhoneList.size();j++) {
                 		if(lastPhoneList.get(j).equals(empNo)) {
                 			Main.searchHM.remove("phoneNuml_"+originSearchKey.split("-")[2]);
                 		}
                 	}
                 }else if (opt2.equals(Constants.OPT2_MID_PHONE)) {
                 	Main.searchHM.remove(searchkey);
                 }else if(opt2.equals(Constants.OPT2_LAST_PHONE)) {
                 	Main.searchHM.remove(searchkey);
                 }
              }else if(searchColumn.equals(Constants.EMP_BIRTH)) {
            	  if(opt2.equals(" ")) {
                   	Main.searchHM.remove("birthday_"+originSearchKey);
                   	ArrayList<String> yearBirthList = Main.searchHM.get("birthdayy_"+originSearchKey.substring(0, 4));
                   	ArrayList<String> monthBirthList = Main.searchHM.get("birthdaym_"+originSearchKey.substring(4, 6));
                   	ArrayList<String> dayBirthList = Main.searchHM.get("birthdayd_"+originSearchKey.substring(6, 8));
                   	
                   	for(int j=0;j<yearBirthList.size();j++) {
                   		if(yearBirthList.get(j).equals(empNo)) {
                   			Main.searchHM.remove("birthdayy_"+originSearchKey.split(" ")[0]);
                   		}
                   	}
                   	
                   	for(int j=0;j<monthBirthList.size();j++) {
                   		if(monthBirthList.get(j).equals(empNo)) {
                   			Main.searchHM.remove("birthdaym_"+originSearchKey.split(" ")[1]);
                   		}
                   	}
                   	
                   	for(int j=0;j<dayBirthList.size();j++) {
                   		if(dayBirthList.get(j).equals(empNo)) {
                   			Main.searchHM.remove("birthdayd_"+originSearchKey.split(" ")[1]);
                   		}
                   	}
                   }else if (opt2.equals(Constants.OPT2_BIRTH_YEAR)) {
                   		Main.searchHM.remove(searchkey);
                   }else if(opt2.equals(Constants.OPT2_BIRTH_MON)) {
                   		Main.searchHM.remove(searchkey);
                   }else if(opt2.equals(Constants.OPT2_BIRTH_DAY)) {
                      	Main.searchHM.remove(searchkey);
                   }
               } else if(searchColumn.equals(Constants.EMP_CERTI)) {
            	   Main.searchHM.remove(searchkey);
               }
    	}
    }

    public void SCH() {
    }

    public void MOD() {
    }
}
