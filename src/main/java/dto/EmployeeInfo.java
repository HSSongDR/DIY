package dto;

import java.util.Objects;

import constants.Constants;

public class EmployeeInfo {

    private String employeeNum;
    private String name;
    private String namef;
    private String namel;
    private String cl;
    private String phoneNum;
    private String phoneNumm;
    private String phoneNuml;
    private String birthday;
    private String birthdayy;
    private String birthdaym;
    private String birthdayd;
    private String certi;
    private boolean removeflag;

    public EmployeeInfo(String employeeNum, String name, String cl, String phoneNum, String birthday, String certi) {
        super();
        this.employeeNum = employeeNum;
        this.name = name;
        this.namef = name.split(" ")[0];
        this.namel = name.split(" ")[1];
        this.cl = cl;
        this.phoneNum = phoneNum;
        this.phoneNumm = phoneNum.split("-")[1];
        this.phoneNuml = phoneNum.split("-")[2];
        this.birthday = birthday;
        this.birthdayy = birthday.substring(0, 4);
        this.birthdaym = birthday.substring(4, 6);
        this.birthdayd = birthday.substring(6, 8);
        this.certi = certi;
        this.removeflag = false;
    }

    public String getObj(String sObject) {
        if (sObject.equals("employeeNum")) return employeeNum;
        else if (sObject.equals("name")) return name;
        else if (sObject.equals("namef")) return namef;
        else if (sObject.equals("namel")) return namel;
        else if (sObject.equals("cl")) return cl;
        else if (sObject.equals("phoneNum")) return phoneNum;
        else if (sObject.equals("phoneNumm")) return phoneNumm;
        else if (sObject.equals("phoneNuml")) return phoneNuml;
        else if (sObject.equals("birthday")) return birthday;
        else if (sObject.equals("birthdayy")) return birthdayy;
        else if (sObject.equals("birthdaym")) return birthdaym;
        else if (sObject.equals("birthdayd")) return birthdayd;
        else if (sObject.equals("certi")) return certi;

        return "";
    }

    public void setObj(String sObject, String changeValue) {
        if (sObject.equals("name")) {
            this.name = changeValue;
            this.namef = name.split(" ")[0];
            this.namel = name.split(" ")[1];
        } else if (sObject.equals("namef")) this.namef = changeValue;
        else if (sObject.equals("namel")) this.namel = changeValue;
        else if (sObject.equals("cl")) this.cl = changeValue;
        else if (sObject.equals("phoneNum")) {
            this.phoneNum = changeValue;
            this.phoneNumm = phoneNum.split("-")[1];
            this.phoneNuml = phoneNum.split("-")[2];
        } else if (sObject.equals("phoneNumm")) this.phoneNumm = changeValue;
        else if (sObject.equals("phoneNuml")) this.phoneNuml = changeValue;
        else if (sObject.equals("birthday")) {
            this.birthday = changeValue;
            this.birthdayy = birthday.substring(0, 4);
            this.birthdaym = birthday.substring(4, 6);
            this.birthdayd = birthday.substring(6, 8);
        } else if (sObject.equals("birthdayy")) this.birthdayy = changeValue;
        else if (sObject.equals("birthdaym")) this.birthdaym = changeValue;
        else if (sObject.equals("birthdayd")) this.birthdayd = changeValue;
        else if (sObject.equals("certi")) this.certi = changeValue;
    }
    public static String[] getSubKey(String subKeyName){
        String[] returnArray = {""};
        if(subKeyName.equals("name")) return Constants.nameSubKey;
        else if(subKeyName.equals("phoneNum")) return Constants.phoneSubKey;
        else if(subKeyName.equals("birthday")) return Constants.birthSubKey;
        return returnArray;
    }

    public void setRemoveFlag() {
        removeflag = true;
    }

    public boolean getRemoveFlag() {
        return removeflag;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCerti() {
        return certi;
    }

    public void setCerti(String certi) {
        this.certi = certi;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNum);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmployeeInfo other = (EmployeeInfo) obj;
        return Objects.equals(employeeNum, other.employeeNum);
    }

    @Override
    public String toString() {

        return employeeNum + Constants.DELIMITER_COMMA
                + name + Constants.DELIMITER_COMMA
                + cl + Constants.DELIMITER_COMMA
                + phoneNum + Constants.DELIMITER_COMMA
                + birthday + Constants.DELIMITER_COMMA
                + certi;
    }
}
