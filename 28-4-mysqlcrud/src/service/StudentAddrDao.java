// 28�� �̿��� StudentAddrDao.java
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentAddrDao {
	/*
	�޼ҵ� ����	
	1. �뵵 : �л��� 1���� �ּҸ� �Է��ϴ� �޼ҵ���(Database�� Student_addr���̺� �� 1������ �Է��ϴ� �޼ҵ�).
	2. �Ű����� : StudentAddr studentAddr(StudentAddrŬ������ ���� ������ studentAddr��ü�� ������)
	3. ���ϰ� : void
	4. StudentAddr Class ������Ƽ
		- ���������ڴ� ��� private��. int studentNO, int studentNO, String studentAddrContent;
	*/	
	public void insertStudentAddress(StudentAddr studentAddr) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		// ���ܸ� ������ ����(try)
		try {
			connection = new DBconnection().getConnection();
			//DBconnectionŬ������ �⺻ �����ڸ� ȣ���Ͽ� ��ü���� �� getConnection�޼ҵ� ȣ���Ͽ� ���ϰ��� connection�� ����.
			preparedStatement = connection.prepareStatement("INSERT INTO student_addr (student_no,student_addr_content)	VALUES (?, ?)");
			preparedStatement.setInt(1, studentAddr.getStudentNO());
			preparedStatement.setString(2, studentAddr.getStudentAddrContent());
			preparedStatement.executeUpdate();
			
		// ����ó��	
		}catch (SQLException e) {
			// SQLException���ܰ� �߻��� �����ų �ڵ�
			e.printStackTrace();
			//printStackTrace �����޼����� �߻� �ٿ��� ã�Ƽ� �ܰ躰 ������ ����Ѵ�.
			
		// �������� �ݵ�� ������Ѿ��� �ڵ�	
		} finally {
			if(preparedStatement != null)	// preparedStatement��ü���������� ���� null�� �ƴ� ��� ��ü �ݳ�(�ݳ������� rs->pstmt->conn)
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// SQLException���ܰ� �߻��� �����ų �ڵ�
				e.printStackTrace();
			}
			if(connection != null)			// connection��ü���������� ���� null�� �ƴ� ��� ��ü �ݳ�(�ݳ������� rs->pstmt->conn)
				try {
					connection.close();
				} catch (SQLException e) {
					// SQLException���ܰ� �߻��� �����ų �ڵ�
					e.printStackTrace();
				}
		}
	}
	
	/*
	�޼ҵ� ����	
	1. �뵵 : �л��� 1���� �ּҵ��� ��ȸ�ϴ� �޼ҵ���(Database�� Student_addr���̺��� Ư�� student_no�÷��� ��ȸ�ϴ� �޼ҵ�).
	2. �Ű����� : int studentNo(list���� get������� �Ѱܹ��� ���� int data�� ��ȯ ���� �� ����)
	3. ���ϰ� : studentAddrList(StudentAddrŬ������ ���� ������ ��ü�� �������� ���� ����Ʈ�� ����)
	4. StudentAddr Class ������Ƽ
		- ���������ڴ� ��� private��. int studentNO, int studentNO, String studentAddrContent;
	*/	
	public ArrayList<StudentAddr> selectStudentAddrList(int studentNo){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<StudentAddr> studentAddrList = new ArrayList<>();
		try {
			connection = new DBconnection().getConnection();
			//DBconnectionŬ������ �⺻ �����ڸ� ȣ���Ͽ� ��ü���� �� getConnection�޼ҵ� ȣ���Ͽ� ���ϰ��� connection�� ����.
			preparedStatement = connection.prepareStatement("select student_addr_no, student_no, student_addr_content from student_addr where student_no=?");
			preparedStatement.setInt(1, studentNo);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				StudentAddr studentAddr = new StudentAddr();
				studentAddr.setStudentAddrNo(resultSet.getInt("student_addr_no"));
				studentAddr.setStudentNO(resultSet.getInt("student_no"));
				studentAddr.setStudentAddrContent(resultSet.getString("student_addr_content"));
				studentAddrList.add(studentAddr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentAddrList;
	}
	
	/*
	�޼ҵ� ����	
	1. �뵵 : �л��� 1���� �ּҵ��� �����ϴ� �޼ҵ���(Database�� Student_addr���̺��� Ư�� student_no�÷��� �����ϴ� �޼ҵ�).
	2. �Ű����� : int studentNo(list���� get������� �Ѱܹ��� ���� int data�� ��ȯ ���� �� ����)
	3. ���ϰ� : void
	4. StudentAddr Class ������Ƽ
		- ���������ڴ� ��� private��. int studentNO, int studentNO, String studentAddrContent;
	*/	
	public void deleteStudentAddr(int studentNo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = new DBconnection().getConnection();
			//DBconnectionŬ������ �⺻ �����ڸ� ȣ���Ͽ� ��ü���� �� getConnection�޼ҵ� ȣ���Ͽ� ���ϰ��� connection�� ����.
			preparedStatement = connection.prepareStatement("delete from student_addr where student_no=?");
			preparedStatement.setInt(1, studentNo);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
