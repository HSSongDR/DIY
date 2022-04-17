import java.util.Objects;

import constants.Constants;

public class EmployeeInfo {
	
	private String employeeNum;
	private String name;
	private String cl;
	private String phoneNum;
	private String birthday;
	private String certi;

	public EmployeeInfo(String employeeNum, String name, String cl, String phoneNum, String birthday, String certi) {
		super();

		this.employeeNum = employeeNum;
		this.name = name;
		this.cl = cl;
		this.phoneNum = phoneNum;
		this.birthday = birthday;
		this.certi = certi;
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
				+ cl+Constants.DELIMITER_COMMA 
				+ phoneNum+Constants.DELIMITER_COMMA 
				+ birthday+Constants.DELIMITER_COMMA 
				+ certi;
	}
	
	
	
	

}
