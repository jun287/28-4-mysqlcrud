package service;
import java.util.ArrayList;
import java.sql.*;
import service.MemberAndScore;
//2018. 07.09. 28�� ������

public class MemberScoreDao {
	
	
	//member���̺�� member_score���̺� JOIN�ϴ� �޼��� �Դϴ�.
	public ArrayList<MemberAndScore> selectMemberAndScore(String memberName) {
		ArrayList<MemberAndScore> list = new ArrayList<MemberAndScore>();
		//return���� �����ֱ� ���� ArrayList������ �޼��带 ���ؼ� ���ο� ��ü�� ������ ��Ų�� ������ �ּҰ��� classŸ������ ����� ������ �Ҵ��� ��������ϴ�
		MemberAndScore memberAndScore = null;
		//JOIN���� ���� ��µ� ���� ��� ���� classŸ������ ������ ������ ��Ų�� ���� null�� ������ �߽��ϴ�.
		PreparedStatement preparedStatement = null;
		Connection  connection = null;
		ResultSet resultSet = null;
		
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
			
			String selectQuery = "SELECT member.member_no ,member.member_name ,member_score.member_score_no ,member_score.score FROM member INNER JOIN member_score ON member.member_no = member_score.member_no WHERE member_name=?";
			//member���̺��� �������� member_score���̺��� INNER JOIN�� ������, member���̺�� member_score���̺��� member_no���� ���� ��� ����� �ϴ� �÷����� ����� �˴ϴ�. 
			preparedStatement = connection.prepareStatement(selectQuery);
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
		}catch(ClassNotFoundException close) {
			close.printStackTrace();
		}catch(SQLException close) {
			close.printStackTrace();
		}
		
		return list;
		//list������ ������ �����ݴϴ�.
	}
}
