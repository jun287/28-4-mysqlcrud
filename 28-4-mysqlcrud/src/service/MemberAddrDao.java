package service;
import service.Member;
import java.util.ArrayList;
import java.sql.*;
//java.sql패키지내에 내장되어있는 클래스를 클래스이름만으로 사용가능하도록 import 시킵니다.
//2018.06.26 28기 전재현.
public class MemberAddrDao {
	
	//주소값 넣는 메서드입니다
	public ArrayList<Member> insertMemberAddr(int member_no ,String memberAddr_content) {
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)로딩 했습니다
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPassword = "java0000";
			//DB연결하기 위해 포트번호 ,데이터베이스명 ,ID ,PW값을 변수에 대입을 했습니다
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)연결 했습니다
			
			String insertQuery = "INSERT INTO member_addr(member_no ,memberAddr_name) VALUSE(? ,?)";
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setInt(1, member_no);
			preparedStatement.setString(2, memberAddr_content);
			System.out.println(preparedStatement +"<- preparedStatement");
			
			preparedStatement.executeUpdate();
		}catch(ClassNotFoundException close) {
			close.printStackTrace();
		}catch(SQLException close) {
			close.printStackTrace();
		}finally {
			if(preparedStatement != null)
				try {
					preparedStatement.close();
				}catch(SQLException close) {
					close.printStackTrace();
				}
			if(connection != null) {
				try {
					connection.close();
				}catch(SQLException close) {
					close.printStackTrace();
				}
			}
		}
		return null;
	}
}