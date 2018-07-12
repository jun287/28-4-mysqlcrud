package memberDAO;

import java.sql.*;
import java.util.ArrayList;

import DBConnectionPool.DBconnection;
import memberDTO.MemberAddr;

//java.sql패키지내에 내장되어있는 클래스를 클래스이름만으로 사용가능하도록 import 시킵니다.
//2018.06.26 28기 전재현.
public class MemberAddrDao {
	
	//주소값 수정을 위한 메서드입니다
	public int updateMemberAddr(MemberAddr memberAddr) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int updateResult = 0;
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String updateQuery = "UPDATE member_addr SET memberAddr_content=? WHERE memberAddr_no=?";
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, memberAddr.getMemberAddrContent());
			preparedStatement.setInt(2, memberAddr.getMemberAddrNo());
			
			System.out.println(preparedStatement +"<-preparedStatement");
			updateResult = preparedStatement.executeUpdate();
			System.out.println(updateResult +"<-updateResult");
		}catch(SQLException close) {
			close.printStackTrace();
		}finally {
			if(preparedStatement != null)
				try {
					preparedStatement.close();
				}catch(SQLException close) {
					close.printStackTrace();
				}
			if(connection != null) {
				try {
					connection.close();
				}catch(SQLException close) {
					close.printStackTrace();
				}
			}
		}
		
		return updateResult;
	}
	
	//member_addr테이블 조회 메서드입니다.
	public MemberAddr selectMemberAddrList(int memberAddrNo) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		MemberAddr memberAddr = null;
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String selectQeury = "SELECT memberAddr_no ,memberAddr_content ,member_no FROM member_addr WHERE member_no=?";
			preparedStatement = connection.prepareStatement(selectQeury);
			preparedStatement.setInt(1, memberAddrNo);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				memberAddr =  new MemberAddr();
				memberAddr.setMemberAddrNo(resultSet.getInt("memberAddr_no"));
				memberAddr.setMemberNo(resultSet.getInt("member_no"));
				memberAddr.setMemberAddrContent(resultSet.getString("memberAddr_content"));
				
			}
			
		}catch(SQLException close) {
			close.printStackTrace();
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
			if(connection != null) {
				try {
					connection.close();
				}catch(SQLException close) {
					close.printStackTrace();
				}
			}
		}
		return memberAddr;
	}
	
	//주소목록 리스트값 구하기 위한 메서드입니다.
	public ArrayList<MemberAddr> listMemberSelect(String memberName) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//Query문을 실행하기 위해 class타입으로 변수를 선언을 시켜주고 값은 null값으로 셋팅을 해줬습니다
		ArrayList<MemberAddr> totalMemberAddr = null;
		MemberAddr memberAddr = null;
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String SelectQuery = "SELECT member.member_no ,member_addr.memberAddr_no ,member_addr.memberAddr_content ,left(memberAddr_date ,10) AS memberAddr_date FROM member JOIN member_addr ON member.member_no = member_addr.member_no WHERE member_name=? ORDER BY memberAddr_no DESC";
			//LEFT JOIN을 통해 member테이블 기준으로 member_name컬럼의 값이 있을시 member테이블의 member_no 값과 member_addr테이블의 member_no 값이 일치하는 행만 출력이 되고 member_addr테이블에 값이 출력이 되도록 했습니다. 
			preparedStatement = connection.prepareStatement(SelectQuery);
			preparedStatement.setString(1, memberName);
			//매게변수 memberNo값으로 selectQuery문 작성을 해줬습니다.
			resultSet = preparedStatement.executeQuery();
			totalMemberAddr = new ArrayList<MemberAddr>();
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				memberAddr = new MemberAddr();
				memberAddr.setMemberNo(resultSet.getInt("member_no"));
				memberAddr.setMemberAddrNo(resultSet.getInt("memberAddr_no"));
				memberAddr.setMemberAddrContent(resultSet.getString("memberAddr_content"));
				memberAddr.setMemberAddrDate(resultSet.getString("memberAddr_date"));
				totalMemberAddr.add(memberAddr);
			}
			//while문으로 resultSet변수값이false가 나올때까지 실행을 시켜 memberAddr참조변수에 셋팅을 한다음 totalMEmberAddr참조 변수에 add메서드에 memberADdr참조 변구 값을 담아 할당 시켜줬습니다.
		}catch(SQLException close) {
			close.printStackTrace();
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
			if(connection != null) {
				try {
					connection.close();
				}catch(SQLException close) {
					close.printStackTrace();
				}
			}
		}
		return totalMemberAddr;
	}
	
	//memberName값 얻기 위한 메서드 입니다.
	public String selectMemberName(int memberNo) {
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		String memberName = null;
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String selectQuery = "SELECT member_name FROM member WHERE member_no=?";
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, memberNo);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				memberName = resultSet.getString("member_name");
			}
			
		}catch(Exception close) {
			close.printStackTrace();
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
			if(connection != null) {
				try {
					connection.close();
				}catch(SQLException close) {
					close.printStackTrace();
				}
			}
		}
		
		return memberName;
	}
	
	//삭제 처리를 하기위한 메서드입니다.
	public void deleteMemberAddr(int memberAddrNo) {
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String deleteQuery = "DELETE FROM member_addr WHERE memberAddr_no=?";
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setInt(1, memberAddrNo);
			
			preparedStatement.executeUpdate();
		
		}catch(SQLException close) {
			close.printStackTrace();
		}finally {
			if(preparedStatement != null)
				try {
					preparedStatement.close();
				}catch(SQLException close) {
					close.printStackTrace();
				}
			if(connection != null) {
				try {
					connection.close();
				}catch(SQLException close) {
					close.printStackTrace();
				}
			}
		}
	}
	
	//주소값 넣는 메서드입니다
	public int insertMemberAddr(int memberNo ,String memberAddrContent) {
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		//Query문 실행을 위해 class타입의 참조변수를 선언을 하고 주소값을 null로 지정했습니다.
		int execution = 0;
		//처리 결과값 받기 위한 변수입니다
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String insertQuery = "INSERT INTO member_addr(member_no ,memberAddr_content ,memberAddr_date) VALUES(? ,? ,now())";
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setInt(1, memberNo);
			preparedStatement.setString(2, memberAddrContent);
			System.out.println(preparedStatement +"<- preparedStatement");
			
			execution = preparedStatement.executeUpdate();
			//memberAddrContent변수에 값이 있으면 query문을 실행을 하고 리턴값으로 보내기 위해 실행값을 execution변수에 대입합니다
			
		}catch(SQLException close) {
			close.printStackTrace();
		}finally {
			if(preparedStatement != null)
				try {
					preparedStatement.close();
				}catch(SQLException close) {
					close.printStackTrace();
				}
			if(connection != null) {
				try {
					connection.close();
				}catch(SQLException close) {
					close.printStackTrace();
				}
			}
		}
		return execution;
		//execution변수에 들어있는 값을 리턴 시킵니다.
	}
}