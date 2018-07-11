package service;
import service.Member;

import java.sql.*;
import java.util.ArrayList;

//2018.06.26 28기 전재현.
public class MemberDao {
	
	//삭제 처리 하기위한 메서드입니다.
	public void deleteMember(int memberNo) {
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)로딩을 해줬습니다
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			//연결을 위해 String타입으로 선언한 변수안에 포트번호 ,데이터베이스명 ,Encoding을 대입을 했습니다
			String dbUser = "java";
			//my-sql(DB) ID값입니다
			String dbPassword = "java0000";
			//my-sql(DB) Password값입니다
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)DriverManager클래스를 통해 getConnection메서드에 들어있는 매개변수값으로 연결을 실행하고 실행주소값을 참조변수에 할당시켜줬습니다.
			
			String deleteQuery = "DELETE FROM member WHERE member_no=?";
			//query문을 String형식으로 선언된 변수에 대입했습니다
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setInt(1, memberNo);
			//query문의 1번째 ? 값을  memberNo매개변수에 들어있는 값을 대입 했습니다.
			
			preparedStatement.executeUpdate();
			//preparedStatement참조변수안 들어있는 주소값을 찾아가 실행을 합니다.
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
	
	//수정처리할 행을 출력하는 메서드입니다.
	public Member updateMemberSelect(int memberNo) {
		
		Member member = new Member();
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)로딩을 해줬습니다
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			//연결을 위해 String타입으로 선언한 변수안에 포트번호 ,데이터베이스명 ,Encoding을 대입을 했습니다
			String dbUser = "java";
			//my-sql(DB) ID값입니다
			String dbPassword = "java0000";
			//my-sql(DB) Password값입니다
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)DriverManager클래스를 통해 getConnection메서드에 들어있는 매개변수값으로 연결을 실행하고 실행주소값을 참조변수에 할당시켜줬습니다.
			
			String selectQuery = "SELECT member_no ,member_name ,member_age FROM member WHERE member_no=?";
			//SELECTQUERY문을 String으로 선언한 변수에 대입을 시켜줬습니다.
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
	
	//수정처리를 하기위한 메서드입니다.
	public ArrayList<Member> updateMember(int memberNo ,String memberName ,int memberAge) {
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)로딩을 해줬습니다
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			//연결을 위해 String타입으로 선언한 변수안에 포트번호 ,데이터베이스명 ,Encoding을 대입을 했습니다
			String dbUser = "java";
			//my-sql(DB) ID값입니다
			String dbPassword = "java0000";
			//my-sql(DB) Password값입니다
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)DriverManager클래스를 통해 getConnection메서드에 들어있는 매개변수값으로 연결을 실행하고 실행주소값을 참조변수에 할당시켜줬습니다.
			
			String updateQuery = "UPDATE member SET member_name=? ,member_age=? WHERE member_no=?";
			//member_no값에 있는 행을 수정처리 합니다
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
	//member테이블에 들어있는 데이터 총갯수를 구하는 메서드입니다.
	public int countMemberList(int pagePerRow ,String searchWord) {
		System.out.println(searchWord +"<- searchWord");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int totalPage = 0;
		int lastPage = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPassword = "java0000";
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			
			if(searchWord.equals("")) {
				String SelectQuery = "SELECT COUNT(member_no) AS memberNo FROM member";
				preparedStatement = connection.prepareStatement(SelectQuery);
				System.out.println(SelectQuery +"<- SelectQuery");
			}else {
				String SelectQuery = "SELECT COUNT(member_no) AS memberNo FROM member WHERE member_name LIKE ?";
				preparedStatement = connection.prepareStatement(SelectQuery);
				preparedStatement.setString(1, "%"+searchWord+"%");
			}
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				totalPage = resultSet.getInt("memberNo");
			}
			
			lastPage = totalPage / pagePerRow;
			
			if(totalPage % pagePerRow != 0) {
				lastPage++;
			}
			System.out.println(totalPage +"<- totalPage");
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
	
	//member테이블에 들어있는 데이터를 출력하는 메서드입니다.
	public ArrayList<Member> selectMemberByPage(int currentPage, int pagePerRow ,String searchWord){
		
		ArrayList<Member> memberList = new ArrayList<Member>(); 
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		System.out.println(searchWord +"<- searchWord");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)로딩을 해줬습니다
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			//연결을 위해 String타입으로 선언한 변수안에 포트번호 ,데이터베이스명 ,Encoding을 대입을 했습니다
			String dbUser = "java";
			//my-sql(DB) ID값입니다
			String dbPassword = "java0000";
			//my-sql(DB) Password값입니다
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)DriverManager클래스를 통해 getConnection메서드에 들어있는 매개변수값으로 연결을 실행하고 실행주소값을 참조변수에 할당시켜줬습니다.
			
			int startRow = (currentPage-1)*pagePerRow; 
			//시작행 기준
			if(searchWord.equals("")) {
				String SelectQuery = "SELECT member_no ,member_name ,member_age FROM member ORDER BY member_no LIMIT ?,?";
				preparedStatement = connection.prepareStatement(SelectQuery);
				preparedStatement.setInt(1, startRow);
				preparedStatement.setInt(2, pagePerRow);
			}else {
				String selectQuery = "SELECT member_no ,member_name ,member_age FROM member WHERE member_name LIKE ? ORDER BY member_no LIMIT ? ,?";
				preparedStatement = connection.prepareStatement(selectQuery);
				preparedStatement.setString(1, "%"+searchWord+"%");
				preparedStatement.setInt(2, startRow);
				preparedStatement.setInt(3, pagePerRow);
			}
			
			resultSet = preparedStatement.executeQuery();
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
	
		return memberList;

	}
	
	//화면에서 입력한 값을 등록하기 위한 메서드입니다
	public void insertMember(Member member) {
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)로딩을 해줬습니다
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			//연결을 위해 String타입으로 선언한 변수안에 포트번호 ,데이터베이스명 ,Encoding을 대입을 했습니다
			String dbUser = "java";
			//my-sql(DB) ID값입니다
			String dbPassword = "java0000";
			//my-sql(DB) Password값입니다
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)DriverManager클래스를 통해 getConnection메서드에 들어있는 매개변수값으로 연결을 실행하고 실행주소값을 참조변수에 할당시켜줬습니다.
			
			String insertQuery = "INSERT INTO member(member_name ,member_age) VALUES(? ,?)";
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setString(1, member.getMemberName());
			preparedStatement.setInt(2, member.getMemberAge());
			System.out.println(preparedStatement +"<- preparedStatement");
			//preparedStatement에 들어있는 주소값을 찾아가 member참조변수에 setting되어 있는 값을 가져와 ?에 대입을 시켜줬습니다.
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage());
			
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage());
			
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
