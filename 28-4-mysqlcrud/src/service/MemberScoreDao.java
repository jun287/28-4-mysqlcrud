package service;
import java.util.ArrayList;
import java.sql.*;
import service.MemberAndScore;
//2018. 07.09. 28기 전재현

public class MemberScoreDao {
	
	
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
			if(totalCount == 1) {
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
	
	//member테이블과 member_score테이블 JOIN하는 메서드 입니다.
	public ArrayList<MemberAndScore> selectMemberAndScore(int memberNo) {
		ArrayList<MemberAndScore> list = new ArrayList<MemberAndScore>();
		//return값을 보내주기 위해 ArrayList생성자 메서드를 통해서 새로운 객체를 생성을 시킨후 생성된 주소값을 class타입으로 선언된 변수에 할당을 시켜줬습니다
		MemberAndScore memberAndScore = null;
		//JOIN문을 통해 출력된 값을 담기 위해 class타입으로 변수를 선언을 시킨후 값을 null로 설정을 했습니다.
		PreparedStatement memberAndScorePreparedStatement = null;
		PreparedStatement memberPreparedStatement = null;
		Connection  connection = null;
		ResultSet memberResultSet = null;
		ResultSet memberAndScoreResultSet = null;
		String memberName = null;
		
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
			
			String memberSelectQuery = "SELECT member_name FROM member WHERE member_no=?";
			memberPreparedStatement = connection.prepareStatement(memberSelectQuery);
			memberPreparedStatement.setInt(1, memberNo);
			//memberNo값으로 name값을 찾기 위한 SELECT Query문입니다.
			memberResultSet = memberPreparedStatement.executeQuery();
			//memberPreparedStatement참조변수에 들어있는 주소값을 찾아가 쿼리를 실행을 하고 결과값을 memberResultSet변수에 담았습니다.
			if(memberResultSet.next()) {
				memberName = memberResultSet.getString("member_name");
			}
			System.out.println(memberName +"<-memberName");
			//memberRreusltSet변수에 들어있는 값을 memberName변수에 담았습니다.
			
			String memberAndScoreSelectQuery = "SELECT member.member_no ,member.member_name ,member_score.member_score_no ,member_score.score FROM member INNER JOIN member_score ON member.member_no = member_score.member_no WHERE member_name=?";
			//member테이블을 기준으로 member_score테이블을 INNER JOIN을 했으며, member테이블과 member_score테이블의 member_no값이 같을 경우 얻고자 하는 컬럼값이 출력이 됩니다. 
			memberAndScorePreparedStatement = connection.prepareStatement(memberAndScoreSelectQuery);
			//connection참조변수에 들어있는 주소값을 찾아가 prepareStatement메서드를 selectQuery변수에 들어있는 query문을 셋팅을한 후, 참조 변수에 주소값을 할당시킵니다.
			memberAndScorePreparedStatement.setString(1, memberName);
			//preparedStatement참조 변수에 들어있는 주소값을 찾아가 ?값을 셋팅을 했습니다.
			memberAndScoreResultSet = memberAndScorePreparedStatement.executeQuery();
			//preparedStatement에 들어있는 주소값을 실행을 시킨후 출력값을 resultSet변수에 대입했습니다.
			if(memberAndScoreResultSet.next()) {
			//출력되는 값이 한 행이기 때문에 if 문으로 실행을 했습니다
				memberAndScore = new MemberAndScore();
				//출력된 값을 셋팅하기 위해 생성자 메서드를 통해 새로운 객체를 생성후 생성된 주소값을 참조변수에 할당했습니다.
				memberAndScore.setMemberName(memberAndScoreResultSet.getString("member_name"));
				memberAndScore.setMemberNo(memberAndScoreResultSet.getInt("member_no"));
				memberAndScore.setMemberScoreNo(memberAndScoreResultSet.getInt("member_score_no"));
				memberAndScore.setScore(memberAndScoreResultSet.getInt("score"));
				//참조변수에 들어있는 주소값을 들어가  query문 실행으로 나온값들을 setting을 합니다.
				list.add(memberAndScore);
				//list변수에 내장되어있는 add메스드를 통해서 memberAndScore변수의 주소값을 list변수에 할당 시켜줬습니다
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
		//list변수를 리턴을 시켜줍니다.
	}
}
