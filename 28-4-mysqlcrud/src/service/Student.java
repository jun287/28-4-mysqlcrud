// 28�� �̿��� 2018. 6. 25(��) Student.java
package service;

public class Student {
	private int studentNO;
	private String studentName;
	private int studentAge;
	// ������Ƽ private ��������(private, ����Ŭ������������ ���ٰ���)
	public int getStudentNO() {
		return studentNO;
	}
	public void setStudentNO(int studentNO) {
		this.studentNO = studentNO;
		System.out.println(studentNO+"<--setStudentNo service.Student.java");
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
		System.out.println(studentName+"<--setStudentName service.Student.java");
	}
	public int getStudentAge() {
		return studentAge;
	}
	public void setStudentAge(int studentAge) {
		this.studentAge = studentAge;
		System.out.println(studentAge+"<--setStudentAge service.Student.java");
	}
	@Override
	public String toString() {
		return "Student [studentNO=" + studentNO + ", studentName=" + studentName + ", studentAge=" + studentAge + "]";
	}
	
}