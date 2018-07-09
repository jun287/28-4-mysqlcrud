// 28기 이원상 2018. 7. 9(월) StudentScore.java
package service;

public class StudentScore {
	private int studentScoreNumber = 0;
	private int studentNumber = 0;
	private int score = 0;
	public int getStudentScoreNumber() {
		return studentScoreNumber;
	}
	public void setStudentScoreNumber(int studentScoreNumber) {
		this.studentScoreNumber = studentScoreNumber;
		System.out.println(studentScoreNumber+"<--setStudentScoreNumber");
	}
	public int getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
		System.out.println(studentNumber+"<--setStudentNumber");
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
		System.out.println(score+"<--setScore");
	}
	@Override
	public String toString() {
		return "StudentScore [studentScoreNumber=" + studentScoreNumber + ", studentNumber=" + studentNumber
				+ ", score=" + score + "]";
	}
	
}
