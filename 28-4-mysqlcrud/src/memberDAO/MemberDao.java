package memberDAO;
import java.sql.*;
import java.util.ArrayList;

import DBConnection.DBconnection;
import memberDTO.Member;
import memberDTO.MemberAndScore;
//2018.06.26 28기 전재현.
public class MemberDao {
	
	//삭제 처리 하기위한 메서드입니다.
	public void deleteMember(int memberNo) {
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String deleteQuery = "DELETE FROM member WHERE member_no=?";
			//query문을 String형식으로 선언된 변수에 대입했습니다
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setInt(1, memberNo);
			//query문의 1번째 ? 값을  memberNo매개변수에 들어있는 값을 대입 했습니다.
			
			preparedStatement.executeUpdate();
			//preparedStatement참조변수안 들어있는 주소값을 찾아가 실행을 합니다.
			
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
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
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
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String updateQuery = "UPDATE member SET member_name=? ,member_age=? WHERE member_no=?";
			//member_no값에 있는 행을 수정처리 합니다
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, memberName);
			preparedStatement.setInt(2, memberAge);
			preparedStatement.setInt(3, memberNo);
			
			preparedStatement.executeUpdate();
			
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
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		int totalPage = 0;
		int lastPage = 0;
		try {
			if(searchWord.equals("")) {
				String SelectQuery = "SELECT COUNT(member_no) AS memberNo FROM member ORDER BY member_no DESC";
				preparedStatement = connection.prepareStatement(SelectQuery);
				System.out.println(SelectQuery +"<- SelectQuery");
			}else {
				String SelectQuery = "SELECT COUNT(member_no) AS memberNo FROM member WHERE member_name LIKE ? ORDER BY member_no DESC";
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
	
	//목록리스트를 보여주는 메서드입니다.
	public ArrayList<MemberAndScore> selectMemberByPage(int currentPage, int pagePerRow ,String searchWord){
		
		ArrayList<MemberAndScore> memberJoinList = new ArrayList<MemberAndScore>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			int startRow = (currentPage-1)*pagePerRow; 
			//시작행 기준
			if(searchWord.equals("")) {
				String joinQuery = "SELECT member.member_no ,member.member_name ,member.member_age ,member_score.score FROM member LEFT JOIN member_score ON member.member_no = member_score.member_no ORDER BY member.member_no DESC LIMIT ? ,?";
				preparedStatement = connection.prepareStatement(joinQuery);
				preparedStatement.setInt(1, startRow);
				preparedStatement.setInt(2, pagePerRow);
				
			}else {
				//점수값 여부를 알기 위한 LEFT JOIN QEURY입니다
				String joinQuery = "SELECT member.member_no ,member.member_name ,member.member_age ,member_score.score FROM member LEFT JOIN member_score ON member.member_no = member_score.member_no WHERE member.member_name LIKE ? ORDER BY member.member_no  LIMIT ? ,?";
				preparedStatement = connection.prepareStatement(joinQuery);
				preparedStatement.setString(1, "%"+searchWord+"%");
				preparedStatement.setInt(2, startRow);
				preparedStatement.setInt(3, pagePerRow);
			}
			
			resultSet = preparedStatement.executeQuery();
			MemberAndScore memberAndScore = null;

			while(resultSet.next()){ 
				memberAndScore = new MemberAndScore();
				memberAndScore.setScore(resultSet.getInt("score"));
				memberAndScore.setMemberNo(resultSet.getInt("member_no"));
				memberAndScore.setMemberName(resultSet.getString("member_name"));
				memberAndScore.setMemberAge(resultSet.getInt("member_age"));
				
				memberJoinList.add(memberAndScore);
			}
			
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
	
		return memberJoinList;

	}
	
	//화면에서 입력한 값을 등록하기 위한 메서드입니다
	public void insertMember(Member member) {
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
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
