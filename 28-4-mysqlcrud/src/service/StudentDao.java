// 28기 이원상 2018. 6. 25(월) StudentDao.java
package service;
import service.DriverDb;
import java.sql.*;
public class StudentDao {
	Connection conn=null;
	PreparedStatement pstmt=null;
	PreparedStatement pstmt2=null;
	PreparedStatement pstmt3=null;
	ResultSet rs = null;
	//studentInsert(db연결 포함)
	public Student studentInsert(Student stu) throws ClassNotFoundException, SQLException {
		DriverDb db = new DriverDb();
		conn = db.driverDbcon();
		pstmt = conn.prepareStatement("INSERT INTO student (student_name, student_age)	VALUES (?, ?)");
		pstmt.setString(1, stu.getStudentName());
		pstmt.setInt(2, stu.getStudentAge());
		int updateRs = pstmt.executeUpdate();
		int maxStudentNo=0;
		if(updateRs==1) {
			pstmt2 = conn.prepareStatement("select max(student_no) as max from student");
			rs=pstmt2.executeQuery();
			if(rs.next()) {
				maxStudentNo = rs.getInt(1)+1;
				stu.setStudentNO(maxStudentNo);
				System.out.println(rs.getInt(1)+"<--rs.index1"+" "+maxStudentNo+"<--maxStudentNo");
			}else {
				maxStudentNo = 1;
				stu.setStudentNO(maxStudentNo);
				System.out.println(maxStudentNo);
			}
		}else {
			System.out.println("form입력결과 db입력되지 안았음.");
		}
		return stu;
	}
/*	public void studentAddressInsert(Student stu) throws ClassNotFoundException, SQLException {
		DriverDb db = new DriverDb();
		conn = db.driverDbcon();
		pstmt = conn.prepareStatement("INSERT INTO student_addr (student_no, student_addr_content)	VALUES (?, ?)");
		pstmt.setInt(1, stu.getStudentNO());
		System.out.println(stu.getStudentNO()+"<--stu.getStudentNO()");
		pstmt.setString(2, stu.getStudentAddrContent());
		System.out.println(stu.getStudentAddrContent()+"<--stu.getStudentAddrContent()");
		pstmt.executeUpdate();
	}*/
}
