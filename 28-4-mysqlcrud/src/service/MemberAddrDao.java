package service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//java.sql패키지내에 내장되어있는 클래스를 클래스이름만으로 사용가능하도록 import 시킵니다.
//2018.06.26 28기 개발자 전재현.
public class MemberAddrDao {
	/*
	메소드 설명	
	 용도 : 멤버의 내용(content)을 저장하는 메소드입니다.
	 매개변수 : MemberAddr 클래스 타입으로 등록이 된 한명의 멤버 내용를 담는 객체의 주소값을 담은 변수
	 리턴값 : void값으로 설정을 해서 리턴값이 없습니다..
	*/		
	public void insertMemberAddr(MemberAddr memberAddr) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		//클래스타입으로 변수를 선언을 시키고 변수의 값을 null로 설정합니다.
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)로딩을 합니다.
			
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPassword = "java0000";
			//my-sql(DB)연결 하기위해 localhost번호 ,데이터 베이스명 ,my-sql(DB)ID ,mysql(DB)PW 값을 변수에 할당시킵니다.
			
			try {
				connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
				/*DriverManager클래스 객체에서 getConnection메소드를 통해 dbUrl ,dbUser ,dbPassword변수에 대입되어있는 값으로 조회를 해 변수에 대입되어있는 값과
				일치하는 Drive의 주소값을 가져와 connection변수에 할당을 시킵니다..*/
				
				String insertQuery = "INSERT INTO member_addr(member_no ,member_addr_content) VALUES(? ,?)";
				//String타입으로 변수를 선언을 하고 변수에 Query문을 할당을 시켰습니다.
				preparedStatement = connection.prepareStatement(insertQuery);
				preparedStatement.setInt(1 ,memberAddr.getMemberAddrNo());
				preparedStatement.setString(2 ,memberAddr.getMemberAddrContent());
				System.out.println(preparedStatement +"<- preparedStatement");
				/*connection변수에 대입되어있는 주소값을 찾아가 prepareStatement객체를 생성을 해
				그객체에 insertQuery변수에 대입되어있는값을 입력 후 그주소값을 preparedStatement변수에 할당시킵니다.*/
				
				preparedStatement.executeUpdate();
				//preparedStatement변수에 대입되어있는 주소값을 찾아가 prepareStatement객체에 대입되어있는 Query문을 executeUpdate()메소드로 통해 실행을 시킵니다.
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage() +"<- preparedStatement&Connection클래스 오류");
			}
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage() +"<- class.forName클래스 오류");
		}finally {
			if(preparedStatement != null) try {
				preparedStatement.close();
				//만약에 preparedStatement변수값이 null값이 아니면 try,catch문을 열어서 preparedStatement변수를 close를 시킵니다.
			}catch(SQLException e) {
				
			}
			if(connection != null) try {
				connection.close();
				//만약에 connection변수값이 null값이 아니면 try,catch문을 열어서 connection변수를 close를 시킵니다.
			}catch(SQLException e) {
				
			}
		}
	}
}
