package memberDAO;
import java.util.ArrayList;

import DBConnectionPool.DBconnection;
import memberDTO.MemberAndScore;

import java.sql.*;

public class MemberScoreDao {
	
	
	//��������� ���� ����� ���� ���ϴ� �޼����Դϴ�.
	public int selectTotalList(int rowPerPage) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int lastPage = 0;
		int totalList = 0;
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String SelectQuery = "SELECT count(score>=(SELECT avg(score) AS score FROM member_score)) AS totalList FROM member_score";
			//���������� ����Ͽ� ��հ����� ��������� ���� ���ϴ� Query�Դϴ�.
			preparedStatement = connection.prepareStatement(SelectQuery);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				totalList=resultSet.getInt("totalList");
			}
			lastPage = (totalList) / rowPerPage;
			if((totalList) % rowPerPage != 0) {
				lastPage++;
			}
			
		}catch(SQLException check) {
			check.printStackTrace();
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
	
	//��������� ���� ����� ����Ʈ�� ����ϴ� �޼����Դϴ�
	public ArrayList<MemberAndScore> MemberAverageList(int currentPage ,int pagePerRow) {
		 
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		ArrayList<MemberAndScore> averageTotalList = new ArrayList<MemberAndScore>();
		MemberAndScore memberAndScore = null;
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			int startRow = (currentPage-1)*pagePerRow;
			String selectQuery = "SELECT member.member_name ,member.member_age ,member_score.member_no ,member_score.score FROM member_score INNER JOIN member ON member_score.member_no = member.member_no WHERE member_score.score >= (SELECT avg(score) AS score FROM member_score) ORDER BY member_score.score DESC LIMIT ? ,?";
			//INNER JOIN���� ���� ��������� �� ���������� score������ ���ų� �� ���� score���� ��������� list�� ����ϴ� QUERY�� �Դϴ�.
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, startRow);
			preparedStatement.setInt(2, pagePerRow);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				memberAndScore = new MemberAndScore();
				memberAndScore.setMemberNo(resultSet.getInt("member_no"));
				memberAndScore.setMemberName(resultSet.getString("member_name"));
				memberAndScore.setMemberAge(resultSet.getInt("member_age"));
				memberAndScore.setScore(resultSet.getInt("score"));
				averageTotalList.add(memberAndScore);
			}
			//while���� ���� resultSet������ ����ִ� ���� false�� ���ö����� memberAndScore������ ���Խ�������ϴ�.
		
		}catch(Exception check) {
			check.printStackTrace();
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
				}catch(SQLException clsoe) {
					clsoe.printStackTrace();
				}
			if(connection != null)
				try {
					connection.close();
				}catch(SQLException close) {
					close.printStackTrace();
			}
		}
		return averageTotalList;
	}
	
	//������ ������� ���� ���� �޼����Դϴ�.
	public int MemberAverage() {
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		int average = 0;
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String selectQuery = "SELECT avg(Score) AS average FROM member_score";
			preparedStatement = connection.prepareStatement(selectQuery);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				average = resultSet.getInt("average");
			}
		
		}catch(Exception check) {
			check.printStackTrace();
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
				}catch(SQLException clsoe) {
					clsoe.printStackTrace();
				}
			if(connection != null)
				try {
					connection.close();
				}catch(SQLException close) {
					close.printStackTrace();
			}
		}
		
		return average;
	}
	
	//���� ����ϴ� �޼��� �Դϴ�
	public int insertMember(int memberNo ,int memberScore) {
		
		PreparedStatement selectPreparedStatement = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		//Query���� �����ϱ� ���� ClassŸ������ ������ ������ �����ְ� null������ ������ �߽��ϴ�
		int insertCheck = 0;
		//return���� ������ ���� ���� ������ �߽��ϴ�
		int totalCount = 0;
		//SELECT QUERY�� ���� �� ������� ��� ���� �����߽��ϴ�.
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String selectQuery = "SELECT COUNT(member_no) AS member_no FROM member_score WHERE member_no=?";
			selectPreparedStatement = connection.prepareStatement(selectQuery);
			selectPreparedStatement.setInt(1, memberNo);
			
			resultSet = selectPreparedStatement.executeQuery();
			////memberNo������ ����ִ� ���� member_score���̺� ����Ǿ� �ִ��� Ȯ���� ���� SELECT�� �Դϴ�.
			if(resultSet.next()) {
				 totalCount = resultSet.getInt("member_no");
				System.out.println(totalCount +"<- totalCount");
			}
			//������ ������� toatalCount������ ������ �߽��ϴ�
			if(totalCount == 0) {
				String insertQuery = "INSERT INTO member_score(member_no ,score) VALUES(? ,?)";
				preparedStatement = connection.prepareStatement(insertQuery);
				preparedStatement.setInt(1, memberNo);
				preparedStatement.setInt(2, memberScore);
				//member_score���̺� ����Ǿ��ִ� ���� ���ٸ� INSERT QUERY������ ������ �ǵ��� ��������ϴ�
				insertCheck = 1;
				//INSERT QUERY���� ������ �Ǹ�  1�� ������ �˴ϴ�.
				
			}else {
				String updateQuery = "UPDATE member_score SET score=? WHERE member_no=?";
				preparedStatement = connection.prepareStatement(updateQuery);
				preparedStatement.setInt(1, memberScore);
				preparedStatement.setInt(2, memberNo);
				//member_score���̺� ����Ǿ��ִ� ���� ������ UPDATE������ ����Ǿ��ִ� ���� ������ �ǵ��� ��������ϴ�
				insertCheck = 0;
				//UPDATE QUERY�� ������ �Ǹ� 0���� ������ �˴ϴ�. 
			}
			preparedStatement.executeUpdate();
			//preparedStatement������ ����ִ� �ּҰ��� ������ �߽��ϴ�.
			System.out.println(totalCount +"<- totalCount");

		}catch(SQLException check) {
			check.printStackTrace();
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
				}catch(SQLException clsoe) {
					clsoe.printStackTrace();
				}
			if(selectPreparedStatement != null)
				try {
					selectPreparedStatement.close();
				}catch(SQLException clsoe) {
					clsoe.printStackTrace();
				}
			if(connection != null)
				try {
					connection.close();
				}catch(SQLException close) {
					close.printStackTrace();
			}
		}
		return insertCheck;
		
	}
	
	//member���̺�� member_score���̺� JOIN�ϴ� �޼��� �Դϴ�.
	public ArrayList<MemberAndScore> selectMemberAndScore(String memberName) {
		ArrayList<MemberAndScore> list = new ArrayList<MemberAndScore>();
		//return���� �����ֱ� ���� ArrayList������ �޼��带 ���ؼ� ���ο� ��ü�� ������ ��Ų�� ������ �ּҰ��� classŸ������ ����� ������ �Ҵ��� ��������ϴ�
		MemberAndScore memberAndScore = null;
		//JOIN���� ���� ��µ� ���� ��� ���� classŸ������ ������ ������ ��Ų�� ���� null�� ������ �߽��ϴ�.
		PreparedStatement preparedStatement = null;
		Connection  connection = null;
		ResultSet resultSet = null;
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String memberAndScoreSelectQuery = "SELECT member.member_no ,member.member_name ,member_score.member_score_no ,member_score.score FROM member INNER JOIN member_score ON member.member_no = member_score.member_no WHERE member_name=?";
			//member���̺��� �������� member_score���̺��� INNER JOIN�� ������, member���̺�� member_score���̺��� member_no���� ���� ��� ����� �ϴ� �÷����� ����� �˴ϴ�. 
			preparedStatement = connection.prepareStatement(memberAndScoreSelectQuery);
			//connection���������� ����ִ� �ּҰ��� ã�ư� prepareStatement�޼��带 selectQuery������ ����ִ� query���� �������� ��, ���� ������ �ּҰ��� �Ҵ��ŵ�ϴ�.
			preparedStatement.setString(1, memberName);
			//preparedStatement���� ������ ����ִ� �ּҰ��� ã�ư� ?���� ������ �߽��ϴ�.
			resultSet = preparedStatement.executeQuery();
			//preparedStatement�� ����ִ� �ּҰ��� ������ ��Ų�� ��°��� resultSet������ �����߽��ϴ�.
			if(resultSet.next()) {
			//��µǴ� ���� �� ���̱� ������ if ������ ������ �߽��ϴ�
				memberAndScore = new MemberAndScore();
				//��µ� ���� �����ϱ� ���� ������ �޼��带 ���� ���ο� ��ü�� ������ ������ �ּҰ��� ���������� �Ҵ��߽��ϴ�.
				memberAndScore.setMemberName(resultSet.getString("member_name"));
				memberAndScore.setMemberNo(resultSet.getInt("member_no"));
				memberAndScore.setMemberScoreNo(resultSet.getInt("member_score_no"));
				memberAndScore.setScore(resultSet.getInt("score"));
				//���������� ����ִ� �ּҰ��� ��  query�� �������� ���°����� setting�� �մϴ�.
				list.add(memberAndScore);
				//list������ ����Ǿ��ִ� add�޽��带 ���ؼ� memberAndScore������ �ּҰ��� list������ �Ҵ� ��������ϴ�
			}
			
		}catch(SQLException check) {
			check.printStackTrace();
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
		
		return list;
		//list������ ������ �����ݴϴ�.
	}
}
