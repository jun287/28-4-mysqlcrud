package memberDAO;

import java.sql.*;
import java.util.ArrayList;

import DBConnectionPool.DBconnection;
import memberDTO.MemberAddr;

//java.sql��Ű������ ����Ǿ��ִ� Ŭ������ Ŭ�����̸������� ��밡���ϵ��� import ��ŵ�ϴ�.
//2018.06.26 28�� ������.
public class MemberAddrDao {
	
	//�ּҰ� ������ ���� �޼����Դϴ�
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
	
	//member_addr���̺� ��ȸ �޼����Դϴ�.
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
	
	//�ּҸ�� ����Ʈ�� ���ϱ� ���� �޼����Դϴ�.
	public ArrayList<MemberAddr> listMemberSelect(String memberName) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//Query���� �����ϱ� ���� classŸ������ ������ ������ �����ְ� ���� null������ ������ ������ϴ�
		ArrayList<MemberAddr> totalMemberAddr = null;
		MemberAddr memberAddr = null;
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String SelectQuery = "SELECT member.member_no ,member_addr.memberAddr_no ,member_addr.memberAddr_content ,left(memberAddr_date ,10) AS memberAddr_date FROM member JOIN member_addr ON member.member_no = member_addr.member_no WHERE member_name=? ORDER BY memberAddr_no DESC";
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
				memberAddr.setMemberAddrDate(resultSet.getString("memberAddr_date"));
				totalMemberAddr.add(memberAddr);
			}
			//while������ resultSet��������false�� ���ö����� ������ ���� memberAddr���������� ������ �Ѵ��� totalMEmberAddr���� ������ add�޼��忡 memberADdr���� ���� ���� ��� �Ҵ� ��������ϴ�.
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
	
	//���� ó���� �ϱ����� �޼����Դϴ�.
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
	
	//�ּҰ� �ִ� �޼����Դϴ�
	public int insertMemberAddr(int memberNo ,String memberAddrContent) {
		
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		//Query�� ������ ���� classŸ���� ���������� ������ �ϰ� �ּҰ��� null�� �����߽��ϴ�.
		int execution = 0;
		//ó�� ����� �ޱ� ���� �����Դϴ�
		
		DBconnection dbConnection = new DBconnection();
		connection = dbConnection.getConnection();
		
		try {
			String insertQuery = "INSERT INTO member_addr(member_no ,memberAddr_content ,memberAddr_date) VALUES(? ,? ,now())";
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setInt(1, memberNo);
			preparedStatement.setString(2, memberAddrContent);
			System.out.println(preparedStatement +"<- preparedStatement");
			
			execution = preparedStatement.executeUpdate();
			//memberAddrContent������ ���� ������ query���� ������ �ϰ� ���ϰ����� ������ ���� ���ప�� execution������ �����մϴ�
			
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