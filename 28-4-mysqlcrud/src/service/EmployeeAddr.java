// 2018. 07.03(화) 28기 정민수
package service;

public class EmployeeAddr {
	//private 접근 한정자로 String data type 변수를 선언한다.
	//데이터를 저장하고 주기 위해서
	private String employee_addr_no;
	private String employee_no;
	private String employee_addr_content;
	
	//변수를 셋팅하여서 변수에 값을 저장하고 get하여 변수에 담겨있는 값을 가져온다.
	public String getEmployee_addr_no() {
		return employee_addr_no;
	}
	public void setEmployee_addr_no(String employee_addr_no) {
		this.employee_addr_no = employee_addr_no;
	}
	public String getEmployee_no() {
		return employee_no;
	}
	public void setEmployee_no(String employee_no) {
		this.employee_no = employee_no;
	}
	public String getEmployee_addr_content() {
		return employee_addr_content;
	}
	public void setEmployee_addr_content(String employee_addr_content) {
		this.employee_addr_content = employee_addr_content;
	}
	
}