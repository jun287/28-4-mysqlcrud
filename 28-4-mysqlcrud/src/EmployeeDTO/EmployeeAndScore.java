package EmployeeDTO;

public class EmployeeAndScore {
	
	//EmployeeScore,Employee class date type���� ��ü������������
	private EmployeeScore employeescore;
	private Employee employee;
	
	//EmployeeScore�ּҰ��� �����ͼ� ���� �����Ѵ�
	public EmployeeScore getEmployeescore() {
		return employeescore;
	}
	public void setEmployeescore(EmployeeScore employeescore) {
		this.employeescore = employeescore;
	}
	
	//Employee �ּҰ��� �����ͼ� ���� �����Ѵ�.
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
