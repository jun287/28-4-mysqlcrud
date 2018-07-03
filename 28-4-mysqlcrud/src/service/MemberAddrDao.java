package service;
import service.Member;
import java.util.ArrayList;
import java.sql.*;
//java.sql��Ű������ ����Ǿ��ִ� Ŭ������ Ŭ�����̸������� ��밡���ϵ��� import ��ŵ�ϴ�.
//2018.06.26 28�� ������.
public class MemberAddrDao {
	
	//�ּҰ� �ִ� �޼����Դϴ�
	public ArrayList<Member> insertMemberAddr(int member_no ,String memberAddr_content) {
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)�ε� �߽��ϴ�
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPassword = "java0000";
			//DB�����ϱ� ���� ��Ʈ��ȣ ,�����ͺ��̽��� ,ID ,PW���� ������ ������ �߽��ϴ�
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)���� �߽��ϴ�
			
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