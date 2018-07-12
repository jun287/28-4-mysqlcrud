package memberDAO;
import java.util.ArrayList;

import DBConnectionPool.DBconnection;
import memberDTO.MemberAndScore;

import java.sql.*;

public class MemberScoreDao {
	
	
	//평균점보다 높은 사람의 수를 구하는 메서드입니다.
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
			//서브쿼리를 사용하여 평균값보다 높은사람의 수를 구하는 Query입니다.
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
	
	//평균점보다 높은 사람의 리스트를 출력하는 메서드입니다
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
			//INNER JOIN문을 통해 평균점수를 낸 서브쿼리의 score값보다 같거나 더 높은 score값을 가진사람의 list를 출력하는 QUERY문 입니다.
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
			//while문을 통해 resultSet변수에 들어있는 값이 false가 나올때까지 memberAndScore변수에 대입시켜줬습니다.
		
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
	
	//점수의 평균점을 내기 위한 메서드입니다.
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
	
	//점수 등록하는 메서드 입니다
	public int insertMember(int memberNo ,int memberScore) {
		
		PreparedStatement selectPreparedStatement = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		//Query문을 실행하기 위해 Class타입으로 변수를 선언을 시켜주고 null값으로 셋팅을 했습니다
		int insertCheck = 0;
		//return값을 보내기 위해 변수 설정을 했습니다
		int totalCount = 0;
		//SELECT QUERY문 실행 후 결과값을 담기 위해 설정했습니다.
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String selectQuery = "SELECT COUNT(member_no) AS member_no FROM member_score WHERE member_no=?";
			selectPreparedStatement = connection.prepareStatement(selectQuery);
			selectPreparedStatement.setInt(1, memberNo);
			
			resultSet = selectPreparedStatement.executeQuery();
			////memberNo변수에 들어있는 값이 member_score테이블에 저장되어 있는지 확인을 위한 SELECT문 입니다.
			if(resultSet.next()) {
				 totalCount = resultSet.getInt("member_no");
				System.out.println(totalCount +"<- totalCount");
			}
			//실행후 결과값을 toatalCount변수에 대입을 했습니다
			if(totalCount == 0) {
				String insertQuery = "INSERT INTO member_score(member_no ,score) VALUES(? ,?)";
				preparedStatement = connection.prepareStatement(insertQuery);
				preparedStatement.setInt(1, memberNo);
				preparedStatement.setInt(2, memberScore);
				//member_score테이블에 저장되어있는 값이 없다면 INSERT QUERY문으로 실행이 되도록 만들었습니다
				insertCheck = 1;
				//INSERT QUERY문이 실행이 되면  1로 리턴이 됩니다.
				
			}else {
				String updateQuery = "UPDATE member_score SET score=? WHERE member_no=?";
				preparedStatement = connection.prepareStatement(updateQuery);
				preparedStatement.setInt(1, memberScore);
				preparedStatement.setInt(2, memberNo);
				//member_score테이블에 저장되어있는 값이 있으면 UPDATE문으로 저장되어있는 값을 변경이 되도록 만들었습니다
				insertCheck = 0;
				//UPDATE QUERY문 실행이 되면 0으로 리턴이 됩니다. 
			}
			preparedStatement.executeUpdate();
			//preparedStatement변수에 들어있는 주소값을 실행을 했습니다.
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
	
	//member테이블과 member_score테이블 JOIN하는 메서드 입니다.
	public ArrayList<MemberAndScore> selectMemberAndScore(String memberName) {
		ArrayList<MemberAndScore> list = new ArrayList<MemberAndScore>();
		//return값을 보내주기 위해 ArrayList생성자 메서드를 통해서 새로운 객체를 생성을 시킨후 생성된 주소값을 class타입으로 선언된 변수에 할당을 시켜줬습니다
		MemberAndScore memberAndScore = null;
		//JOIN문을 통해 출력된 값을 담기 위해 class타입으로 변수를 선언을 시킨후 값을 null로 설정을 했습니다.
		PreparedStatement preparedStatement = null;
		Connection  connection = null;
		ResultSet resultSet = null;
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String memberAndScoreSelectQuery = "SELECT member.member_no ,member.member_name ,member_score.member_score_no ,member_score.score FROM member INNER JOIN member_score ON member.member_no = member_score.member_no WHERE member_name=?";
			//member테이블을 기준으로 member_score테이블을 INNER JOIN을 했으며, member테이블과 member_score테이블의 member_no값이 같을 경우 얻고자 하는 컬럼값이 출력이 됩니다. 
			preparedStatement = connection.prepareStatement(memberAndScoreSelectQuery);
			//connection참조변수에 들어있는 주소값을 찾아가 prepareStatement메서드를 selectQuery변수에 들어있는 query문을 셋팅을한 후, 참조 변수에 주소값을 할당시킵니다.
			preparedStatement.setString(1, memberName);
			//preparedStatement참조 변수에 들어있는 주소값을 찾아가 ?값을 셋팅을 했습니다.
			resultSet = preparedStatement.executeQuery();
			//preparedStatement에 들어있는 주소값을 실행을 시킨후 출력값을 resultSet변수에 대입했습니다.
			if(resultSet.next()) {
			//출력되는 값이 한 행이기 때문에 if 문으로 실행을 했습니다
				memberAndScore = new MemberAndScore();
				//출력된 값을 셋팅하기 위해 생성자 메서드를 통해 새로운 객체를 생성후 생성된 주소값을 참조변수에 할당했습니다.
				memberAndScore.setMemberName(resultSet.getString("member_name"));
				memberAndScore.setMemberNo(resultSet.getInt("member_no"));
				memberAndScore.setMemberScoreNo(resultSet.getInt("member_score_no"));
				memberAndScore.setScore(resultSet.getInt("score"));
				//참조변수에 들어있는 주소값을 들어가  query문 실행으로 나온값들을 setting을 합니다.
				list.add(memberAndScore);
				//list변수에 내장되어있는 add메스드를 통해서 memberAndScore변수의 주소값을 list변수에 할당 시켜줬습니다
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
		//list변수를 리턴을 시켜줍니다.
	}
}
