package service;

public class EmployeeScore {
	
	//employee ��������,����,��ȣ�� int data type���� �����Ѵ�
	private int employee_score_no;
	private int employee_no;
	private int score;

	
	//employee ���� ��ȣ�� ���� �����Ѵ�.
	public int getEmployee_score_no() {
		return employee_score_no;
	}
	public void setEmployee_score_no(int employee_score_no) {
		this.employee_score_no = employee_score_no;
	}
	
	//employee ��ȣ�� ���� �����Ѵ�
	public int getEmployee_no() {
		return employee_no;
	}
	public void setEmployee_no(int employee_no) {
		this.employee_no = employee_no;
	}
	
	//������ ���� �����Ѵ�.
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

}
