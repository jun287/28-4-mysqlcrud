package memberDAO;
import java.sql.*;
import java.util.ArrayList;

import DBConnection.DBconnection;
import memberDTO.Member;
import memberDTO.MemberAndScore;
//2018.06.26 28�� ������.
public class MemberDao {
	
	//���� ó�� �ϱ����� �޼����Դϴ�.
	public void deleteMember(int memberNo) {
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String deleteQuery = "DELETE FROM member WHERE member_no=?";
			//query���� String�������� ����� ������ �����߽��ϴ�
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setInt(1, memberNo);
			//query���� 1��° ? ����  memberNo�Ű������� ����ִ� ���� ���� �߽��ϴ�.
			
			preparedStatement.executeUpdate();
			//preparedStatement���������� ����ִ� �ּҰ��� ã�ư� ������ �մϴ�.
			
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
	
	//����ó���� ���� ����ϴ� �޼����Դϴ�.
	public Member updateMemberSelect(int memberNo) {
		
		Member member = new Member();
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String selectQuery = "SELECT member_no ,member_name ,member_age FROM member WHERE member_no=?";
			//SELECTQUERY���� String���� ������ ������ ������ ��������ϴ�.
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
	
	//����ó���� �ϱ����� �޼����Դϴ�.
	public ArrayList<Member> updateMember(int memberNo ,String memberName ,int memberAge) {
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String updateQuery = "UPDATE member SET member_name=? ,member_age=? WHERE member_no=?";
			//member_no���� �ִ� ���� ����ó�� �մϴ�
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
	//member���̺� ����ִ� ������ �Ѱ����� ���ϴ� �޼����Դϴ�.
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
	
	//��ϸ���Ʈ�� �����ִ� �޼����Դϴ�.
	public ArrayList<MemberAndScore> selectMemberByPage(int currentPage, int pagePerRow ,String searchWord){
		
		ArrayList<MemberAndScore> memberJoinList = new ArrayList<MemberAndScore>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			int startRow = (currentPage-1)*pagePerRow; 
			//������ ����
			if(searchWord.equals("")) {
				String joinQuery = "SELECT member.member_no ,member.member_name ,member.member_age ,member_score.score FROM member LEFT JOIN member_score ON member.member_no = member_score.member_no ORDER BY member.member_no DESC LIMIT ? ,?";
				preparedStatement = connection.prepareStatement(joinQuery);
				preparedStatement.setInt(1, startRow);
				preparedStatement.setInt(2, pagePerRow);
				
			}else {
				//������ ���θ� �˱� ���� LEFT JOIN QEURY�Դϴ�
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
	
	//ȭ�鿡�� �Է��� ���� ����ϱ� ���� �޼����Դϴ�
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
			//preparedStatement�� ����ִ� �ּҰ��� ã�ư� member���������� setting�Ǿ� �ִ� ���� ������ ?�� ������ ��������ϴ�.
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
