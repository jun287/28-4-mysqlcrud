package service;
import service.Member;
//service패키지에 있는 Member클래스를 클래스이름만으로 사용가능하도록 import시킵니다.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
//java.sql 패키지내에 저장되어있는 클래스를 클래스이름만으로 사용가능하도록 import시킵니다.
//2018.06.26 28기 개발자 전재현.
public class MemberDao {
	/*
	메소드 설명	
	 용도 : 멤버이름과 나이를 입력하는 메소드입니다.
	 매개변수 : Member 클래스 타입으로 등록이 된 한명의 멤버 이름과 나이를 담는 객체의 주소값을 담은 변수
	 리턴값 : void값으로 설정을 해서 리턴값이 없습니다.
	*/		

	public void insertMember(Member member) {
	//하나의 행을 추가하기 위해서  메소드입니다. 
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		//클래스 형식으로 변수를 설정 및 null값으로 설정 했습니다.
		
		try {	//try문안에 변수들이 실행하는중에 에러가 일어나면 catch문으로 가서 오류를 나오게 하도록 설정했습니다.
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)로딩입니다.
			
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPassword ="java0000";
			//my-sql(DB)연결하기 위해 localhost번호 & 데이터 베이스 & mysql(DB) ID ,PW 입력입니다. 
			
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)연결하기 위해 DriverManager변수에 들어가 getConnection메소드로 통해 dbUrl ,dbUser , dbPassword 변수에 들어있는 값과 일치하는 곳의 주소값을 connection변수에 할당시킵니다.
			System.out.println(connection +"<- connection");
			//my-sql(DB)연결하기 위해 데이터 베이스명 & ID &PW 설정하고 연결 작업입니다.
			
			String insertQuery = "INSERT INTO member(member_name ,member_age) VALUES(? ,?)";
			//Query문을 String타입으로 선언이 되어있는 insertQuery변수에 할당 시킵니다.
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setString(1, member.getMemberName());
			preparedStatement.setInt(2, member.getMemberAge());
			System.out.println(preparedStatement +"<- preparedStatement");
			//Query실행 준비를 위해 connection변수에 대입 되어있는 주소값을 찾아가 prepareStatement객체 생성을해 Query문을 대입을 시키고 그 주소값을 preparedStatemnet변수에 할당 시킵니다.
			
			preparedStatement.executeUpdate();
			//reparedStatement변수에 대입되어있는 주소값을 찾아가 prepareStatement객체에 대입되어있는 Query문을 executeUpdate()메소드로 실행을 시킵니다.
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage() +"<- connection&preparedStatement클래스 로딩 실패");
			//에러가 일어나면 SQLException클래스형식으로 선언된 e변수를 통해 getMessage()메소드롤 통해 connection&preparedStatement클래스오류 메세지를 출력하도록 설정 했습니다.
			
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print(e.getMessage() +"<- Class.forName클래스 로딩 실패");
			//에러가 일어나면 ClassNotFoundException형식으로 선언된 e변수를 통해 getMessage()메소드로 통해 Class.forName클래스 오류를 나오도록 설정했습니다
			
		}finally {
			if(preparedStatement != null)try {
				preparedStatement.close();
			}catch(SQLException e){
				e.printStackTrace();
			//만약에 preparedStatemnet변수안의 값이 null값이 아니면 try &catch문을 열어 preparedStatemnet변수를 종료를 시켜줍니다.	
			}
			if(connection != null) try {
				connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			//만약에 connection변수안의 값이 null값이 아니면 try & catch문을  열어 connection변수를 종료 시켜줍니다.
			}
		}
	}
}
