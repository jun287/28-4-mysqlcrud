package service;

public class Employee {
	//private 접근 한정자로 String data type 변수를 선언한다.
	//데이터를 저장하고 주기 위해서
	private int employeeNo;
	private String employeeName;
	private int employeeAge;
	
	public int getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}
	//메서드 호출시 멤버변수 employee_name변수에 담겨있는 값 return에 준다
	public String getEmployeeName() {
		return employeeName;
	}
	//회원가입폼에서 넘어온 name변수에 담겨있는 값을 매개변수에 대입하고 값을 멤버변수 employee_name에 할당
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	//메서드 호출시 멤버변수 employee_age변수에 담겨있는 값 return에 준다
	public int getEmployeeAge() {
		return employeeAge;
	}
	//회원가입폼에서 넘어온 age변수에 담겨있는 값을 매개변수에 대입하고 값을 멤버변수 employee_age에 할당
	public void setEmployeeAge(int employeeAge) {
		this.employeeAge = employeeAge;
	}
	
}
