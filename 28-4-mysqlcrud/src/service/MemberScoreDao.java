package service;
import java.util.ArrayList;

import java.sql.*;
import service.MemberAndScore;
//2018. 07.09. 28�� ������

public class MemberScoreDao {
	
	//��������� ���� ����� ����Ʈ�� ����ϴ� �޼����Դϴ�
	public ArrayList<MemberAndScore> MemberAverageList() {
		 
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		ArrayList<MemberAndScore> averageTotalList = new ArrayList<MemberAndScore>();
		MemberAndScore memberAndScore = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)�ε��� ������ϴ�
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			//������ ���� StringŸ������ ������ �����ȿ� ��Ʈ��ȣ ,�����ͺ��̽��� ,Encoding�� ������ �߽��ϴ�
			String dbUser = "java";
			//my-sql(DB) ID���Դϴ�
			String dbPassword = "java0000";
			//my-sql(DB) Password���Դϴ�
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)DriverManagerŬ������ ���� getConnection�޼��忡 ����ִ� �Ű����������� ������ �����ϰ� �����ּҰ��� ���������� �Ҵ��������ϴ�.
			
			String selectQuery = "SELECT member.member_name ,member.member_age ,member_score.member_no ,member_score.score FROM member_score INNER JOIN member ON member_score.member_no = member.member_no WHERE member_score.score >= (SELECT avg(score) AS score FROM member_score) ORDER BY member_no ASC";
			//INNER JOIN���� ���� ��������� �� ���������� score������ ���ų� �� ���� score���� ��������� list�� ����ϴ� QUERY�� �Դϴ�.
			preparedStatement = connection.prepareStatement(selectQuery);
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
		}catch(ClassNotFoundException check) {
			check.printStackTrace();
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
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)�ε��� ������ϴ�
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			//������ ���� StringŸ������ ������ �����ȿ� ��Ʈ��ȣ ,�����ͺ��̽��� ,Encoding�� ������ �߽��ϴ�
			String dbUser = "java";
			//my-sql(DB) ID���Դϴ�
			String dbPassword = "java0000";
			//my-sql(DB) Password���Դϴ�
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)DriverManagerŬ������ ���� getConnection�޼��忡 ����ִ� �Ű����������� ������ �����ϰ� �����ּҰ��� ���������� �Ҵ��������ϴ�.
			
			String selectQuery = "SELECT avg(Score) AS average FROM member_score";
			preparedStatement = connection.prepareStatement(selectQuery);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				average = resultSet.getInt("average");
			}
		}catch(ClassNotFoundException check) {
			check.printStackTrace();
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
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)�ε��� ������ϴ�
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			//������ ���� StringŸ������ ������ �����ȿ� ��Ʈ��ȣ ,�����ͺ��̽��� ,Encoding�� ������ �߽��ϴ�
			String dbUser = "java";
			//my-sql(DB) ID���Դϴ�
			String dbPassword = "java0000";
			//my-sql(DB) Password���Դϴ�
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)DriverManagerŬ������ ���� getConnection�޼��忡 ����ִ� �Ű����������� ������ �����ϰ� �����ּҰ��� ���������� �Ҵ��������ϴ�.
			
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
		}catch(ClassNotFoundException check) {
			check.printStackTrace();
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
	public ArrayList<MemberAndScore> selectMemberAndScore(int memberNo) {
		ArrayList<MemberAndScore> list = new ArrayList<MemberAndScore>();
		//return���� �����ֱ� ���� ArrayList������ �޼��带 ���ؼ� ���ο� ��ü�� ������ ��Ų�� ������ �ּҰ��� classŸ������ ����� ������ �Ҵ��� ��������ϴ�
		MemberAndScore memberAndScore = null;
		//JOIN���� ���� ��µ� ���� ��� ���� classŸ������ ������ ������ ��Ų�� ���� null�� ������ �߽��ϴ�.
		PreparedStatement memberAndScorePreparedStatement = null;
		PreparedStatement memberPreparedStatement = null;
		Connection  connection = null;
		ResultSet memberResultSet = null;
		ResultSet memberAndScoreResultSet = null;
		String memberName = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)�ε��� ������ϴ�
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			//������ ���� StringŸ������ ������ �����ȿ� ��Ʈ��ȣ ,�����ͺ��̽��� ,Encoding�� ������ �߽��ϴ�
			String dbUser = "java";
			//my-sql(DB) ID���Դϴ�
			String dbPassword = "java0000";
			//my-sql(DB) Password���Դϴ�
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)DriverManagerŬ������ ���� getConnection�޼��忡 ����ִ� �Ű����������� ������ �����ϰ� �����ּҰ��� ���������� �Ҵ��������ϴ�.
			
			String memberSelectQuery = "SELECT member_name FROM member WHERE member_no=?";
			memberPreparedStatement = connection.prepareStatement(memberSelectQuery);
			memberPreparedStatement.setInt(1, memberNo);
			//memberNo������ name���� ã�� ���� SELECT Query���Դϴ�.
			memberResultSet = memberPreparedStatement.executeQuery();
			//memberPreparedStatement���������� ����ִ� �ּҰ��� ã�ư� ������ ������ �ϰ� ������� memberResultSet������ ��ҽ��ϴ�.
			if(memberResultSet.next()) {
				memberName = memberResultSet.getString("member_name");
			}
			System.out.println(memberName +"<-memberName");
			//memberRreusltSet������ ����ִ� ���� memberName������ ��ҽ��ϴ�.
			
			String memberAndScoreSelectQuery = "SELECT member.member_no ,member.member_name ,member_score.member_score_no ,member_score.score FROM member INNER JOIN member_score ON member.member_no = member_score.member_no WHERE member_name=?";
			//member���̺��� �������� member_score���̺��� INNER JOIN�� ������, member���̺�� member_score���̺��� member_no���� ���� ��� ����� �ϴ� �÷����� ����� �˴ϴ�. 
			memberAndScorePreparedStatement = connection.prepareStatement(memberAndScoreSelectQuery);
			//connection���������� ����ִ� �ּҰ��� ã�ư� prepareStatement�޼��带 selectQuery������ ����ִ� query���� �������� ��, ���� ������ �ּҰ��� �Ҵ��ŵ�ϴ�.
			memberAndScorePreparedStatement.setString(1, memberName);
			//preparedStatement���� ������ ����ִ� �ּҰ��� ã�ư� ?���� ������ �߽��ϴ�.
			memberAndScoreResultSet = memberAndScorePreparedStatement.executeQuery();
			//preparedStatement�� ����ִ� �ּҰ��� ������ ��Ų�� ��°��� resultSet������ �����߽��ϴ�.
			if(memberAndScoreResultSet.next()) {
			//��µǴ� ���� �� ���̱� ������ if ������ ������ �߽��ϴ�
				memberAndScore = new MemberAndScore();
				//��µ� ���� �����ϱ� ���� ������ �޼��带 ���� ���ο� ��ü�� ������ ������ �ּҰ��� ���������� �Ҵ��߽��ϴ�.
				memberAndScore.setMemberName(memberAndScoreResultSet.getString("member_name"));
				memberAndScore.setMemberNo(memberAndScoreResultSet.getInt("member_no"));
				memberAndScore.setMemberScoreNo(memberAndScoreResultSet.getInt("member_score_no"));
				memberAndScore.setScore(memberAndScoreResultSet.getInt("score"));
				//���������� ����ִ� �ּҰ��� ��  query�� �������� ���°����� setting�� �մϴ�.
				list.add(memberAndScore);
				//list������ ����Ǿ��ִ� add�޽��带 ���ؼ� memberAndScore������ �ּҰ��� list������ �Ҵ� ��������ϴ�
			}
		}catch(ClassNotFoundException check) {
			check.printStackTrace();
		}catch(SQLException check) {
			check.printStackTrace();
		}finally {
			if(memberAndScoreResultSet != null)
				try {
					memberAndScoreResultSet.close();
				}catch(SQLException close) {
					close.printStackTrace();
				}
			if(memberAndScorePreparedStatement != null)
				try {
					memberAndScorePreparedStatement.close();
				}catch(SQLException close) {
					close.printStackTrace();
				}
			if(memberResultSet != null)
				try {
					memberResultSet.close();
				}catch(SQLException close) {
					close.printStackTrace();
				}
			if(memberPreparedStatement != null)
				try {
					memberPreparedStatement.close();
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
