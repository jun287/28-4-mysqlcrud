package EmployeeDTO;

public class EmployeeAndScore {
	
	//EmployeeScore,Employee class date type으로 객체참조변수선언
	private EmployeeScore employeescore;
	private Employee employee;
	
	//EmployeeScore주소값을 가져와서 셋팅 겟팅한다
	public EmployeeScore getEmployeescore() {
		return employeescore;
	}
	public void setEmployeescore(EmployeeScore employeescore) {
		this.employeescore = employeescore;
	}
	
	//Employee 주소값을 가져와서 겟팅 셋팅한다.
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
