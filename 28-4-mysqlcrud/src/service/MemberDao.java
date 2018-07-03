package service;
import service.Member;
//service��Ű���� �ִ� MemberŬ������ Ŭ�����̸������� ��밡���ϵ��� import��ŵ�ϴ�.
import java.sql.*;
import java.util.ArrayList;
//java.sql ��Ű������ ����Ǿ��ִ� Ŭ������ Ŭ�����̸������� ��밡���ϵ��� import��ŵ�ϴ�.
//2018.06.26 28�� ������.
public class MemberDao {
	
		//����ó�� �޼����Դϴ�.
		public void deleteMember(int member_no) {
			
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
				
				String deleteQuery = "DELETE FROM member WHERE member_no=?";
				preparedStatement = connection.prepareStatement(deleteQuery);
				preparedStatement.setInt(1, member_no);
				
				preparedStatement.executeUpdate();
				
			}catch(ClassNotFoundException close) {
				close.printStackTrace();
			}catch(SQLException close) {
				close.printStackTrace();
			}finally {
				if(preparedStatement != null) 
					try{
						preparedStatement.close();
					}catch(SQLException close) {
						close.printStackTrace();
					}
				if(connection != null)
					try {
						connection.close();
					}catch(SQLException close) {
						close.printStackTrace();
					}
			}
		}
	
		//����ȭ�鿡 ������ Select���Դϴ�.
		public Member updateMemberSelect(int member_no) {
			
			Member member = new Member();
			PreparedStatement preparedStatement = null;
			Connection connection = null;
			ResultSet resultSet = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//my-sql(DB)�ε� �߽��ϴ�
				String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
				String dbUser = "java";
				String dbPassword = "java0000";
				//DB�����ϱ� ���� ��Ʈ��ȣ ,�����ͺ��̽��� ,ID ,PW���� ������ ������ �߽��ϴ�
				connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
				//my-sql(DB)���� �߽��ϴ�
				
				String selectQuery = "SELECT member_no ,member_name ,member_age FROM member WHERE member_no=?";
				preparedStatement = connection.prepareStatement(selectQuery);
				preparedStatement.setInt(1, member_no);
				
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next()) {
					member.setMemberNo(resultSet.getInt("member_no"));
					member.setMemberName(resultSet.getString("member_name"));
					member.setMemberAge(resultSet.getInt("member_age"));
					
				}
				
			}catch(ClassNotFoundException close) {
				close.printStackTrace();
			}catch(SQLException close) {
				close.printStackTrace();
			}finally {
				if(resultSet != null) 
					try {
						resultSet.close();
					}catch(SQLException close) {
						close.printStackTrace();
					}
				if(preparedStatement != null)
					try {
						preparedStatement.close();
					}catch(SQLException close) {
						close.printStackTrace();
					}
				if(connection != null)
					try {
						connection.close();
					}catch(SQLException close) {
						close.printStackTrace();
					}
			}
			
			return member;
		}
		
		//���� ó���ϴ� �޼����Դϴ�.
		public ArrayList<Member> updateMemberDetail(int member_no ,String member_name ,int member_age) {
			
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
				
				String updateQuery = "UPDATE SET member_name=? ,member_age=? FROM member WHERE member_no=?";
				preparedStatement = connection.prepareStatement(updateQuery);
				preparedStatement.setString(1, member_name);
				preparedStatement.setInt(2, member_age);
				preparedStatement.setInt(3, member_no);
				
				
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
				if(connection != null)
					try {
						connection.close();
					}catch(SQLException close) {
						close.printStackTrace();
					}
			}
			return null;
		}
		
		public int CountMemberList(int pagePerRow) {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			int lastPage = 0;
			int totalPage = 0;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
				String dbUser = "java";
				String dbPassword = "java0000";
				
				connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
				
				String SelectQuery = "select count(student_no) as count from student";
				preparedStatement = connection.prepareStatement(SelectQuery);
				resultSet = preparedStatement.executeQuery();
				
				if (resultSet.next()) {
					totalPage=resultSet.getInt("COUNT(teacher_no)");
				}
				lastPage = (totalPage-1) / pagePerRow;
				if((totalPage-1) % pagePerRow != 0) {
					lastPage++;
				}
				
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			} finally {
				if(resultSet != null)
					try {
						resultSet.close();
					}catch(SQLException close) {
						close.printStackTrace();
					}
				if(preparedStatement != null)
					try {
						preparedStatement.close();
					}catch(SQLException close) {
						close.printStackTrace();
					}
				if(connection != null) 
					try {
						connection.close();
					}catch(SQLException close) {
						close.printStackTrace();
					}
			}
			return lastPage;
		} 
		
		public ArrayList<Member> selectMemberByPage(int currentPage, int pagePerRow){
			
			ArrayList<Member> memberList = new ArrayList<Member>(); 
			Connection connection = null;
			PreparedStatement preparedstatement = null;
			ResultSet resultSet = null;

			String SelectQuery = "SELECT member_no ,member_name ,member_age FROM member ORDER BY member_no LIMIT ?,?";
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String dbUrl = "jdbc:mysql://localhost:3306/284db?useCode=true&characterEncoding=euckr";
				String dbUser = "java";
				String dbPassword = "java0000";
				
				connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				
				int startRow = (currentPage-1)*pagePerRow; 
				
				preparedstatement = connection.prepareStatement(SelectQuery);
				preparedstatement.setInt(1, startRow);
				preparedstatement.setInt(2, pagePerRow);
				
				resultSet = preparedstatement.executeQuery();
				// �ݺ����� ����� �����Ͱ����� Teacher Ŭ���� ��ü�� ������ ��ü���� �޼���(setTeacherNo)�� �̿��� ���������� �����͵��� �����մϴ�.
				Member member = new Member();

				while(resultSet.next()){ 
					
					member.setMemberNo(resultSet.getInt("member_no"));
					member.setMemberName(resultSet.getString("member_name"));
					member.setMemberAge(resultSet.getInt("member_age"));
					
					memberList.add(member); // ��ü���� �ּҰ��� add�޼���� ArrayList Ŭ���� ��ü�� �����մϴ�. 
				}
				
			}catch(ClassNotFoundException close) {
				close.printStackTrace();
			}catch(SQLException close) {
				close.printStackTrace();	
			}finally {
				if(resultSet != null)
					try {
						resultSet.close();
					}catch(SQLException close) {
						close.printStackTrace();
					}
				if(preparedstatement != null) 
					try {
						preparedstatement.close();
					}catch(SQLException close) {
						close.printStackTrace();
					}
				if(connection != null) 
					try {
						connection.close();
					}catch(SQLException close) {
						close.printStackTrace();
					}
			}
		
			return memberList; // ArrayList Ŭ���� ��ü�� �ּҰ��� �����մϴ�.

		}
		
	
	/*
	�޼ҵ� ����	
	 �뵵 : ����̸��� ���̸� �Է��ϴ� �޼ҵ��Դϴ�.
	 �Ű����� : Member Ŭ���� Ÿ������ ����� �� �Ѹ��� ��� �̸��� ���̸� ��� ��ü�� �ּҰ��� ���� ����
	 ���ϰ� : void������ ������ �ؼ� ���ϰ��� �����ϴ�.
	*/		

	public void insertMember(Member member) {
	//�ϳ��� ���� �߰��ϱ� ���ؼ�  �޼ҵ��Դϴ�. 
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		//Ŭ���� �������� ������ ���� �� null������ ���� �߽��ϴ�.
		
		try {	//try���ȿ� �������� �����ϴ��߿� ������ �Ͼ�� catch������ ���� ������ ������ �ϵ��� �����߽��ϴ�.
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)�ε��Դϴ�.
			
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPassword ="java0000";
			//my-sql(DB)�����ϱ� ���� localhost��ȣ & ������ ���̽� & mysql(DB) ID ,PW �Է��Դϴ�. 
			
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)�����ϱ� ���� DriverManager������ �� getConnection�޼ҵ�� ���� dbUrl ,dbUser , dbPassword ������ ����ִ� ���� ��ġ�ϴ� ���� �ּҰ��� connection������ �Ҵ��ŵ�ϴ�.
			System.out.println(connection +"<- connection");
			//my-sql(DB)�����ϱ� ���� ������ ���̽��� & ID &PW �����ϰ� ���� �۾��Դϴ�.
			
			String insertQuery = "INSERT INTO member(member_name ,member_age) VALUES(? ,?)";
			//Query���� StringŸ������ ������ �Ǿ��ִ� insertQuery������ �Ҵ� ��ŵ�ϴ�.
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setString(1, member.getMemberName());
			preparedStatement.setInt(2, member.getMemberAge());
			System.out.println(preparedStatement +"<- preparedStatement");
			//Query���� �غ� ���� connection������ ���� �Ǿ��ִ� �ּҰ��� ã�ư� prepareStatement��ü �������� Query���� ������ ��Ű�� �� �ּҰ��� preparedStatemnet������ �Ҵ� ��ŵ�ϴ�.
			
			preparedStatement.executeUpdate();
			//reparedStatement������ ���ԵǾ��ִ� �ּҰ��� ã�ư� prepareStatement��ü�� ���ԵǾ��ִ� Query���� executeUpdate()�޼ҵ�� ������ ��ŵ�ϴ�.
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage() +"<- connection&preparedStatementŬ���� �ε� ����");
			//������ �Ͼ�� SQLExceptionŬ������������ ����� e������ ���� getMessage()�޼ҵ�� ���� connection&preparedStatementŬ�������� �޼����� ����ϵ��� ���� �߽��ϴ�.
			
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage() +"<- Class.forNameŬ���� �ε� ����");
			//������ �Ͼ�� ClassNotFoundException�������� ����� e������ ���� getMessage()�޼ҵ�� ���� Class.forNameŬ���� ������ �������� �����߽��ϴ�
			
		}finally {
			if(preparedStatement != null)try {
				preparedStatement.close();
			}catch(SQLException e){
				e.printStackTrace();
			//���࿡ preparedStatemnet�������� ���� null���� �ƴϸ� try &catch���� ���� preparedStatemnet������ ���Ḧ �����ݴϴ�.	
			}
			if(connection != null) try {
				connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			//���࿡ connection�������� ���� null���� �ƴϸ� try & catch����  ���� connection������ ���� �����ݴϴ�.
			}
		}
	}
}
