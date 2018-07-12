// 28기 이원상 StudentAddr.java
package StudentDTO;

public class StudentAddr {
	private int studentNO;
	private int studentAddrNo;
	private String studentAddrContent;
	
	
	public int getStudentNO() {
		return studentNO;
	}
	public void setStudentNO(int studentNO) {
		this.studentNO = studentNO;
		System.out.println(studentNO+"<--setStudentNO service.StudentAddr.java");
	}
	public int getStudentAddrNo() {
		return studentAddrNo;
	}
	public void setStudentAddrNo(int studentAddrNo) {
		this.studentAddrNo = studentAddrNo;
		System.out.println(studentAddrNo+"<--setStudentAddrNo service.StudentAddr.java");
	}
	public String getStudentAddrContent() {
		return studentAddrContent;
	}
	public void setStudentAddrContent(String studentAddrContent) {
		this.studentAddrContent = studentAddrContent;
		System.out.println(studentAddrContent+"<--setStudentAddrContent service.StudentAddr.java");
	}
	@Override
	public String toString() {
		return "StudentAddr [studentNO=" + studentNO + ", studentAddrNo=" + studentAddrNo + ", studentAddrContent="
				+ studentAddrContent + "]";
	}
	
}
