// 28기 이원상 2018. 7. 9(월) StudentAndScore.java
package StudentDTO;

public class StudentAndScore {
	private Student student;
	private StudentScore studentScore;
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public StudentScore getStudentScore() {
		return studentScore;
	}
	public void setStudentScore(StudentScore studentScore) {
		this.studentScore = studentScore;
	}
	@Override
	public String toString() {
		return "StudentAndScore [student=" + student + ", studentScore=" + studentScore + "]";
	}
	
}
