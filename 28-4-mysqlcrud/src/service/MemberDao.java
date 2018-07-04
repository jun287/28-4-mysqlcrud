package service;
import service.Member;

import java.sql.*;
import java.util.ArrayList;

//2018.06.26 28기 전재현.
public class MemberDao {
		
		//삭제 처리하기 위한 메서드입니다
		public void deleteMember(int memberNo) {
			
			PreparedStatement preparedStatement = null;
			Connection connection = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//my-sql(DB)드라이브 로딩했습니다
				String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
				String dbUser = "java";
				String dbPassword = "java0000";
				//DB연결하기 위한 포트번호 ,데이터베이스명 ,ID ,PW값 설정했습니다
				connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
				//my-sql(DB)연결 
				
				String deleteQuery = "DELETE FROM member WHERE member_no=?";
				//삭제 처리 Query문을 String형식으로 선언된 deleteQuery변수에 대입했습니다.
				preparedStatement = connection.prepareStatement(deleteQuery);
				preparedStatement.setInt(1, memberNo);
				
				preparedStatement.executeUpdate();
				//Query문 실행 했습니다
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
	
		//수정할 데이터를 가져오기위한 SELECTQUERTY문 입니다.
		public Member updateMemberSelect(int memberNo) {
			
			Member member = new Member();
			PreparedStatement preparedStatement = null;
			Connection connection = null;
			ResultSet resultSet = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//my-sql(DB)로딩 실행 입니다
				String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
				String dbUser = "java";
				String dbPassword = "java0000";
				//my-sql(DB)연결하기 위해 my-sql 포트번호 ,데이터베이스명 ,인코딩 ,ID ,PW 값을 String형식으로 선언된 변수에 대입 입니다.
				connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
				//my-sql(DB)연결 실행입니다
				
				String selectQuery = "SELECT member_no ,member_name ,member_age FROM member WHERE member_no=?";
				//SELECT QUERY문을 String형식으로 선언된 selectQuery변수에 대입했습니다.
				preparedStatement = connection.prepareStatement(selectQuery);
				preparedStatement.setInt(1, memberNo);
				
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
		
		//수정처리에서 수정한값을 처리하는 메서드입니다
		public ArrayList<Member> updateMember(int memberNo ,String memberName ,int memberAge) {
			
			PreparedStatement preparedStatement = null;
			Connection connection = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//my-sql(DB)로딩 입니다
				String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
				String dbUser = "java";
				String dbPassword = "java0000";
				//my-sql(DB)연결하기위한 준비 입니다
				connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
				//my-sql(DB)연결 시도 입니다.
				
				String updateQuery = "UPDATE member SET member_name=? ,member_age=? WHERE member_no=?";
				preparedStatement = connection.prepareStatement(updateQuery);
				preparedStatement.setString(1, memberName);
				preparedStatement.setInt(2, memberAge);
				preparedStatement.setInt(3, memberNo);
				
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
				if(connection != null)
					try {
						connection.close();
					}catch(SQLException close) {
						close.printStackTrace();
					}
			}
			return null;
		}
		//마지막 페이지를 구하기위한 메서드 입니다
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
				
				String SelectQuery = "SELECT COUNT(member_no) AS memberNo FROM member";
				preparedStatement = connection.prepareStatement(SelectQuery);
				resultSet = preparedStatement.executeQuery();
				
				if (resultSet.next()) {
					totalPage=resultSet.getInt("memberNO");
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
		
		//member테이블에 저장되어있는 값들을 전체 출력하는 Select문입니다.
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
				Member member = null;

				while(resultSet.next()){ 
					member = new Member();
					member.setMemberNo(resultSet.getInt("member_no"));
					member.setMemberName(resultSet.getString("member_name"));
					member.setMemberAge(resultSet.getInt("member_age"));
					
					memberList.add(member);
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
		
			return memberList;

		}
		
	//멤버 등록하기 위한 메서드입니다.
	public void insertMember(Member member) {
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPassword ="java0000";
			
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			System.out.println(connection +"<- connection");
			
			String insertQuery = "INSERT INTO member(member_name ,member_age) VALUES(? ,?)";
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setString(1, member.getMemberName());
			preparedStatement.setInt(2, member.getMemberAge());
			System.out.println(preparedStatement +"<- preparedStatement");
			
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage() +"<- connection&preparedStatementŬ���� �ε� ����");
			
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage() +"<- Class.forNameŬ���� �ε� ����");
			
		}finally {
			if(preparedStatement != null)try {
				preparedStatement.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			if(connection != null) try {
				connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
