// 2018. 06. 25 28�� ���μ�
package service;

public class Employee {
	//private ���� �����ڷ� String data type ������ �����Ѵ�.
	//�����͸� �����ϰ� �ֱ� ���ؼ�
	private int employeeNo;
	private String employeeName;
	private int employeeAge;
	
	
	public int getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}
	//�޼��� ȣ��� ������� employee_name������ ����ִ� �� return�� �ش�
	public String getEmployeeName() {
		return employeeName;
	}
	//ȸ������������ �Ѿ�� name������ ����ִ� ���� �Ű������� �����ϰ� ���� ������� employee_name�� �Ҵ�
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	//�޼��� ȣ��� ������� employee_age������ ����ִ� �� return�� �ش�
	public int getEmployeeAge() {
		return employeeAge;
	}
	//ȸ������������ �Ѿ�� age������ ����ִ� ���� �Ű������� �����ϰ� ���� ������� employee_age�� �Ҵ�
	public void setEmployeeAge(int employeeAge) {
		this.employeeAge = employeeAge;
	}
	
}