// 28�� �̿��� 2018. 6. 25(��) Student.java
package StudentDTO;

public class Student {
	private int studentNo;
	private String studentName;
	private int studentAge;
	// ������Ƽ private ��������(private, ����Ŭ������������ ���ٰ���)
	public int getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
		System.out.println(studentNo+"<--setStudentNo service.Student.java");
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
		return "Student [studentNo=" + studentNo + ", studentName=" + studentName + ", studentAge=" + studentAge + "]";
	}
	
}