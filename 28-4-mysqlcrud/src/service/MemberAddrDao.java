package service;

import java.sql.*;
import java.util.ArrayList;
import service.MemberAddr;

//java.sql��Ű������ ����Ǿ��ִ� Ŭ������ Ŭ�����̸������� ��밡���ϵ��� import ��ŵ�ϴ�.
//2018.06.26 28�� ������.
public class MemberAddrDao {
	
	//
	
	//member_addrŬ���� ��ȸ �޼����Դϴ�.
	public MemberAddr selectMemberAddrList(int memberAddrNo) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		MemberAddr memberAddr = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)�ε� �Դϴ�
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPassword = "java0000";
			//my-sql(DB)�����ϱ� ���� ��Ʈ��ȣ ,�����ͺ��̽��� ,ID ,PW���� ������ ���� �Դϴ�
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)���� �Դϴ�
			
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
			
		}catch(ClassNotFoundException close) {
			close.printStackTrace();
		}catch(SQLException close) {
			close.printStackTrace();
		}
		
		return memberAddr;
	}
	
	//�ּҸ�� ����Ʈ�� ���ϱ� ���� �޼����Դϴ�.
	public ArrayList<MemberAddr> listMemberSelect(String memberName) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//Query���� �����ϱ� ���� classŸ������ ������ ������ �����ְ� ���� null������ ������ ������ϴ�
		ArrayList<MemberAddr> totalMemberAddr = null;
		MemberAddr memberAddr = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)�ε� �Դϴ�
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPassword = "java0000";
			//my-sql(DB)�����ϱ� ���� ��Ʈ��ȣ ,�����ͺ��̽��� ,ID ,PW���� ������ ���� �Դϴ�
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)���� �Դϴ�
			
			String SelectQuery = "SELECT member.member_no ,member_addr.memberAddr_no ,member_addr.memberAddr_content FROM member JOIN member_addr ON member.member_no = member_addr.member_no WHERE member_name=? ORDER BY memberAddr_no DESC";
			//LEFT JOIN�� ���� member���̺� �������� member_name�÷��� ���� ������ member���̺��� member_no ���� member_addr���̺��� member_no ���� ��ġ�ϴ� �ุ ����� �ǰ� member_addr���̺� ���� ����� �ǵ��� �߽��ϴ�. 
			preparedStatement = connection.prepareStatement(SelectQuery);
			preparedStatement.setString(1, memberName);
			//�ŰԺ��� memberNo������ selectQuery�� �ۼ��� ������ϴ�.
			resultSet = preparedStatement.executeQuery();
			totalMemberAddr = new ArrayList<MemberAddr>();
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				memberAddr = new MemberAddr();
				memberAddr.setMemberNo(resultSet.getInt("member_no"));
				memberAddr.setMemberAddrNo(resultSet.getInt("memberAddr_no"));
				memberAddr.setMemberAddrContent(resultSet.getString("memberAddr_content"));
				totalMemberAddr.add(memberAddr);
			}
			//while������ resultSet��������false�� ���ö����� ������ ���� memberAddr���������� ������ �Ѵ��� totalMEmberAddr���� ������ add�޼��忡 memberADdr���� ���� ���� ��� �Ҵ� ��������ϴ�.
		}catch(ClassNotFoundException close) {
			close.printStackTrace();
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
	
	//memberName�� ��� ���� �޼��� �Դϴ�.
	public String selectMemberName(int memberNo) {
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		String memberName = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)�ε� �Դϴ�
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPassword = "java0000";
			//my-sql(DB)�����ϱ� ���� ��Ʈ��ȣ ,�����ͺ��̽��� ,ID ,PW���� ������ ���� �Դϴ�
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)���� �Դϴ�
			String selectQuery = "SELECT member_name FROM member WHERE member_no=?";
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, memberNo);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				memberName = resultSet.getString("member_name");
			}
			
		}catch(ClassNotFoundException close) {
			close.printStackTrace();
		}catch(Exception close) {
			close.printStackTrace();
		}
		
		return memberName;
	}
	
	//���� ó���� �ϱ����� �޼����Դϴ�.
	public void deleteMemberAddr(int memberAddrNo) {
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)�ε� �Դϴ�
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPassword = "java0000";
			//my-sql(DB)�����ϱ� ���� ��Ʈ��ȣ ,�����ͺ��̽��� ,ID ,PW���� ������ ���� �Դϴ�
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)���� �Դϴ�
			
			String deleteQuery = "DELETE FROM member_addr WHERE memberAddr_no=?";
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setInt(1, memberAddrNo);
			
			preparedStatement.executeUpdate();
		
		}catch(ClassNotFoundException close) {
			close.printStackTrace();
		}catch(SQLException close) {
			close.printStackTrace();
		}
	}
	
	//�ּҰ� �ִ� �޼����Դϴ�
	public int insertMemberAddr(int memberNo ,String memberAddrContent) {
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		//Query�� ������ ���� classŸ���� ���������� ������ �ϰ� �ּҰ��� null�� �����߽��ϴ�.
		int execution = 0;
		//ó�� ����� �ޱ� ���� �����Դϴ�
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//my-sql(DB)�ε� �Դϴ�
			String dbUrl = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPassword = "java0000";
			//my-sql(DB)�����ϱ� ���� ��Ʈ��ȣ ,�����ͺ��̽��� ,ID ,PW���� ������ ���� �Դϴ�
			connection = DriverManager.getConnection(dbUrl ,dbUser ,dbPassword);
			//my-sql(DB)���� �Դϴ�
			if(memberAddrContent.equals("")) {
				System.out.println("�Է°��� �����ϴ�");
				//memberAddrConten������ ����ִ� ���� �����϶� �ֿܼ� �Է� �˴ϴ�
			}else {
				String insertQuery = "INSERT INTO member_addr(member_no ,memberAddr_content) VALUES(? ,?)";
				preparedStatement = connection.prepareStatement(insertQuery);
				preparedStatement.setInt(1, memberNo);
				preparedStatement.setString(2, memberAddrContent);
				System.out.println(preparedStatement +"<- preparedStatement");
				
				execution = preparedStatement.executeUpdate();
				//memberAddrContent������ ���� ������ query���� ������ �ϰ� ���ϰ����� ������ ���� ���ప�� execution������ �����մϴ�
				
			}
			
		}catch(ClassNotFoundException close) {
			close.printStackTrace();
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
		//execution������ ����ִ� ���� ���� ��ŵ�ϴ�.
	}
}