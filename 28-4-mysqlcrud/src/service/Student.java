// 28기 이원상 2018. 6. 25(월) Student.java
package service;

public class Student {
	private int studentNO;
	private String studentName;
	private int studentAge;
	private int studentAddrNo;
	private String studentAddrContent;
	// 프로퍼티 private 접근제한(private, 동일클래스내에서만 접근가능)
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
	public int getStudentAddrNo() {
		return studentAddrNo;
	}
	public void setStudentAddrNo(int studentAddrNo) {
		this.studentAddrNo = studentAddrNo;
		System.out.println(studentAddrNo+"<--setStudentAddrNo service.Student.java");
	}
	public String getStudentAddrContent() {
		return studentAddrContent;
	}
	public void setStudentAddrContent(String studentAddrContent) {
		this.studentAddrContent = studentAddrContent;
		System.out.println(studentAddrContent+"<--setStudentAddrContent service.Student.java");
	}
	@Override
	public String toString() {
		return "Student [studentNO=" + studentNO + ", studentName=" + studentName + ", studentAge=" + studentAge
				+ ", studentAddrNo=" + studentAddrNo + ", studentAddrContent=" + studentAddrContent + "]";
	}	
}
