// 2018. 07.03(ȭ) 28�� ���μ�
package service;

public class EmployeeAddr {
	//private ���� �����ڷ� String data type ������ �����Ѵ�.
	//�����͸� �����ϰ� �ֱ� ���ؼ�
	private String employee_addr_no;
	private String employee_no;
	private String employee_addr_content;
	
	//������ �����Ͽ��� ������ ���� �����ϰ� get�Ͽ� ������ ����ִ� ���� �����´�.
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