package service;

public class EmployeeScore {
	
	//employee 점수버놓,전수,번호를 int data type으로 선언한다
	private int employee_score_no;
	private int employee_no;
	private int score;

	
	//employee 점수 번호를 셋팅 겟팅한다.
	public int getEmployee_score_no() {
		return employee_score_no;
	}
	public void setEmployee_score_no(int employee_score_no) {
		this.employee_score_no = employee_score_no;
	}
	
	//employee 번호를 셋팅 겟팅한다
	public int getEmployee_no() {
		return employee_no;
	}
	public void setEmployee_no(int employee_no) {
		this.employee_no = employee_no;
	}
	
	//점수를 겟팅 셋팅한다.
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

}
