package service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//java.sql��Ű������ ����Ǿ��ִ� Ŭ������ Ŭ�����̸������� ��밡���ϵ��� import ��ŵ�ϴ�.
//2018.06.26 28�� ������ ������.
public class MemberAddrDao {
	/*
	�޼ҵ� ����	
	 �뵵 : ����� ����(content)�� �����ϴ� �޼ҵ��Դϴ�.
	 �Ű����� : MemberAddr Ŭ���� Ÿ������ ����� �� �Ѹ��� ��� ���븦 ��� ��ü�� �ּҰ��� ���� ����
	 ���ϰ� : void������ ������ �ؼ� ���ϰ��� �����ϴ�..
	*/		
	public void insertMemberAddr(MemberAddr memberAddr) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		//Ŭ����Ÿ������ ������ ������ ��Ű�� ������ ���� null�� �����մϴ�.
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)�ε��� �մϴ�.
			
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPassword = "java0000";
			//my-sql(DB)���� �ϱ����� localhost��ȣ ,������ ���̽��� ,my-sql(DB)ID ,mysql(DB)PW ���� ������ �Ҵ��ŵ�ϴ�.
			
			try {
				connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
				/*DriverManagerŬ���� ��ü���� getConnection�޼ҵ带 ���� dbUrl ,dbUser ,dbPassword������ ���ԵǾ��ִ� ������ ��ȸ�� �� ������ ���ԵǾ��ִ� ����
				��ġ�ϴ� Drive�� �ּҰ��� ������ connection������ �Ҵ��� ��ŵ�ϴ�..*/
				
				String insertQuery = "INSERT INTO member_addr(member_no ,member_addr_content) VALUES(? ,?)";
				//StringŸ������ ������ ������ �ϰ� ������ Query���� �Ҵ��� ���׽��ϴ�.
				preparedStatement = connection.prepareStatement(insertQuery);
				preparedStatement.setInt(1 ,memberAddr.getMemberAddrNo());
				preparedStatement.setString(2 ,memberAddr.getMemberAddrContent());
				System.out.println(preparedStatement +"<- preparedStatement");
				/*connection������ ���ԵǾ��ִ� �ּҰ��� ã�ư� prepareStatement��ü�� ������ ��
				�װ�ü�� insertQuery������ ���ԵǾ��ִ°��� �Է� �� ���ּҰ��� preparedStatement������ �Ҵ��ŵ�ϴ�.*/
				
				preparedStatement.executeUpdate();
				//preparedStatement������ ���ԵǾ��ִ� �ּҰ��� ã�ư� prepareStatement��ü�� ���ԵǾ��ִ� Query���� executeUpdate()�޼ҵ�� ���� ������ ��ŵ�ϴ�.
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage() +"<- preparedStatement&ConnectionŬ���� ����");
			}
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage() +"<- class.forNameŬ���� ����");
		}finally {
			if(preparedStatement != null) try {
				preparedStatement.close();
				//���࿡ preparedStatement�������� null���� �ƴϸ� try,catch���� ��� preparedStatement������ close�� ��ŵ�ϴ�.
			}catch(SQLException e) {
				
			}
			if(connection != null) try {
				connection.close();
				//���࿡ connection�������� null���� �ƴϸ� try,catch���� ��� connection������ close�� ��ŵ�ϴ�.
			}catch(SQLException e) {
				
			}
		}
	}
}
