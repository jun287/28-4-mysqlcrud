package service;
import service.Member;
//service��Ű���� �ִ� MemberŬ������ Ŭ�����̸������� ��밡���ϵ��� import��ŵ�ϴ�.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
//java.sql ��Ű������ ����Ǿ��ִ� Ŭ������ Ŭ�����̸������� ��밡���ϵ��� import��ŵ�ϴ�.
//2018.06.26 28�� ������ ������.
public class MemberDao {
	/*
	�޼ҵ� ����	
	 �뵵 : ����̸��� ���̸� �Է��ϴ� �޼ҵ��Դϴ�.
	 �Ű����� : Member Ŭ���� Ÿ������ ����� �� �Ѹ��� ��� �̸��� ���̸� ��� ��ü�� �ּҰ��� ���� ����
	 ���ϰ� : void������ ������ �ؼ� ���ϰ��� �����ϴ�.
	*/		

	public void insertMember(Member member) {
	//�ϳ��� ���� �߰��ϱ� ���ؼ�  �޼ҵ��Դϴ�. 
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		//Ŭ���� �������� ������ ���� �� null������ ���� �߽��ϴ�.
		
		try {	//try���ȿ� �������� �����ϴ��߿� ������ �Ͼ�� catch������ ���� ������ ������ �ϵ��� �����߽��ϴ�.
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)�ε��Դϴ�.
			
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPassword ="java0000";
			//my-sql(DB)�����ϱ� ���� localhost��ȣ & ������ ���̽� & mysql(DB) ID ,PW �Է��Դϴ�. 
			
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)�����ϱ� ���� DriverManager������ �� getConnection�޼ҵ�� ���� dbUrl ,dbUser , dbPassword ������ ����ִ� ���� ��ġ�ϴ� ���� �ּҰ��� connection������ �Ҵ��ŵ�ϴ�.
			System.out.println(connection +"<- connection");
			//my-sql(DB)�����ϱ� ���� ������ ���̽��� & ID &PW �����ϰ� ���� �۾��Դϴ�.
			
			String insertQuery = "INSERT INTO member(member_name ,member_age) VALUES(? ,?)";
			//Query���� StringŸ������ ������ �Ǿ��ִ� insertQuery������ �Ҵ� ��ŵ�ϴ�.
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setString(1, member.getMemberName());
			preparedStatement.setInt(2, member.getMemberAge());
			System.out.println(preparedStatement +"<- preparedStatement");
			//Query���� �غ� ���� connection������ ���� �Ǿ��ִ� �ּҰ��� ã�ư� prepareStatement��ü �������� Query���� ������ ��Ű�� �� �ּҰ��� preparedStatemnet������ �Ҵ� ��ŵ�ϴ�.
			
			preparedStatement.executeUpdate();
			//reparedStatement������ ���ԵǾ��ִ� �ּҰ��� ã�ư� prepareStatement��ü�� ���ԵǾ��ִ� Query���� executeUpdate()�޼ҵ�� ������ ��ŵ�ϴ�.
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage() +"<- connection&preparedStatementŬ���� �ε� ����");
			//������ �Ͼ�� SQLExceptionŬ������������ ����� e������ ���� getMessage()�޼ҵ�� ���� connection&preparedStatementŬ�������� �޼����� ����ϵ��� ���� �߽��ϴ�.
			
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage() +"<- Class.forNameŬ���� �ε� ����");
			//������ �Ͼ�� ClassNotFoundException�������� ����� e������ ���� getMessage()�޼ҵ�� ���� Class.forNameŬ���� ������ �������� �����߽��ϴ�
			
		}finally {
			if(preparedStatement != null)try {
				preparedStatement.close();
			}catch(SQLException e){
				e.printStackTrace();
			//���࿡ preparedStatemnet�������� ���� null���� �ƴϸ� try &catch���� ���� preparedStatemnet������ ���Ḧ �����ݴϴ�.	
			}
			if(connection != null) try {
				connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			//���࿡ connection�������� ���� null���� �ƴϸ� try & catch����  ���� connection������ ���� �����ݴϴ�.
			}
		}
	}
}
