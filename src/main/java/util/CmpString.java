package util;

import constants.Constants;

public class CmpString implements Comparable<CmpString> {
    private String employeeNum;

    public CmpString(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }


    @Override
    public int compareTo(CmpString o) {
        int originBirth = Integer.parseInt(transformbirth(this.employeeNum.substring(0, 2)));
        String originEmpno = this.employeeNum.substring(2, 8);
        int targetBirth = Integer.parseInt(transformbirth(o.employeeNum.substring(0, 2)));
        String targetEmpno = o.employeeNum.substring(2, 8);
        if (targetBirth != originBirth) return (targetBirth - originBirth) > 0 ? -1 : 1;
        else {
            return originEmpno.compareTo(targetEmpno);
        }
    }

    private String transformbirth(String origin) {
        if (Constants.CHAR_ZERO <= origin.charAt(0) && origin.charAt(0) <= Constants.CHAR_TWO) {
            return Constants.CHAR_ONE + origin;
        }
        return origin;
    }
}
