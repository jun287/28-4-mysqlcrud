package service;
import java.util.ArrayList;
import java.sql.*;
import service.MemberAndScore;
//2018. 07.09. 28기 전재현

public class MemberScoreDao {
	
	
	//member테이블과 member_score테이블 JOIN하는 메서드 입니다.
	public ArrayList<MemberAndScore> selectMemberAndScore(String memberName) {
		ArrayList<MemberAndScore> list = new ArrayList<MemberAndScore>();
		//return값을 보내주기 위해 ArrayList생성자 메서드를 통해서 새로운 객체를 생성을 시킨후 생성된 주소값을 class타입으로 선언된 변수에 할당을 시켜줬습니다
		MemberAndScore memberAndScore = null;
		//JOIN문을 통해 출력된 값을 담기 위해 class타입으로 변수를 선언을 시킨후 값을 null로 설정을 했습니다.
		PreparedStatement preparedStatement = null;
		Connection  connection = null;
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
			
			String selectQuery = "SELECT member.member_no ,member.member_name ,member_score.member_score_no ,member_score.score FROM member INNER JOIN member_score ON member.member_no = member_score.member_no WHERE member_name=?";
			//member테이블을 기준으로 member_score테이블을 INNER JOIN을 했으며, member테이블과 member_score테이블의 member_no값이 같을 경우 얻고자 하는 컬럼값이 출력이 됩니다. 
			preparedStatement = connection.prepareStatement(selectQuery);
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
		}catch(ClassNotFoundException close) {
			close.printStackTrace();
		}catch(SQLException close) {
			close.printStackTrace();
		}
		
		return list;
		//list변수를 리턴을 시켜줍니다.
	}
}
