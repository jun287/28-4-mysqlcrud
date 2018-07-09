// 28�� 2018. 7. 9(��) �̿��� StudentScoreDao.java
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import service.Student;
import service.StudentScore;
import service.StudentAndScore;

public class StudentScoreDao {
	/*
	�޼ҵ� ����	
	1. �뵵 : �л��� ��ü�� ��ȣ, �̸�, ����, ������ȣ, ������ ��ȸ�ϴ� �޼ҵ�(Database�� Student���̺�,Student���̺� innet ���� ��� ��ȸ).
	2. �Ű����� : ����
	3. ���ϰ� : StudentAndScoreŬ������ ������ ��ü�������� �������� ���� ����Ʈ.
		- StudentAndScoreŬ���� ������Ƽ : private Student student, private StudentScore studentScore;
	*/
	public ArrayList<StudentAndScore> selectStudentAndScore(){
		ArrayList<StudentAndScore> studentAndScoreList = new ArrayList<StudentAndScore>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			connection = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			System.out.println(connection + "<-- connection");
			preparedStatement = connection.prepareStatement("select student.student_no, student.student_name, student.student_age, studentscore.student_score_no, studentscore.score from student student inner join student_score studentscore on student.student_no = studentscore.student_no");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Student student = new Student();
				StudentScore studentScore = new StudentScore();
				StudentAndScore studentAndScore = new StudentAndScore();
				student.setStudentNo(resultSet.getInt("student_no"));
				student.setStudentName(resultSet.getString("student_name"));
				student.setStudentAge(resultSet.getInt("student_age"));
				studentScore.setStudentNumber(resultSet.getInt("student_no"));
				studentScore.setStudentScoreNumber(resultSet.getInt("student_score_no"));
				studentScore.setScore(resultSet.getInt("score"));
				studentAndScore.setStudent(student);
				studentAndScore.setStudentScore(studentScore);
				studentAndScoreList.add(studentAndScore);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}if(preparedStatement != null) {
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

		return studentAndScoreList;
		
	}
	
	/*
	�޼ҵ� ����	
	1. �뵵 : �л� 1���� ������ �Է��ϴ� �޼ҵ���(Database�� Student_score���̺��� 1�� ���� �Է��ϴ� �޼ҵ�).
	2. �Ű����� : studentNo(Student���̺� studentNo�÷��� �����޾� Student_score���̺� ����ϴ� �ܷ�Ű ��), score(Student_score���̺� �÷��� ��� ��)
	3. ���ϰ� : ����.
	4. StudentScore Class ������Ƽ
		- ���������ڴ� ��� private��. int studentScoreNumber,int studentNumber,int score
	*/	
	public void insertStudentScore(int studentNo, int score) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			connection = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			System.out.println(connection + "<-- connection");
			preparedStatement = connection.prepareStatement("INSERT INTO student_score (student_no, score) VALUES (?, ?)");
			preparedStatement.setInt(1, studentNo);
			preparedStatement.setInt(2, score);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	/*
	�޼ҵ� ����	
	1. �뵵 : �л� 1���� ������ ��� �ԷµǾ��ִ��� üũ�ϴ� �޼ҵ���(Database�� Student_score���̺��� Ư�� student_no�� ���� ���� ��ȸ�ϴ� �޼ҵ�).
	2. �Ű����� : studentNo(Student���̺� studentNo�÷��� �����޾� Student_score���̺� ����ϴ� �ܷ�Ű ��)
	3. ���ϰ� : int(Student_score���̺��� Ư�� student_no�� ���� ���� ���� ���� �������·� ����)
	4. StudentScore Class ������Ƽ
		- ���������ڴ� ��� private��. int studentScoreNumber,int studentNumber,int score
	*/	
	public int countStudentScore(int studentNo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			connection = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			System.out.println(connection + "<-- connection");
			preparedStatement = connection.prepareStatement("select count(student_no) as count from student_score where student_no = ?");
			preparedStatement.setInt(1, studentNo);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				count=resultSet.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}if(preparedStatement != null) {
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
		return count;
	}
	
	/*
	�޼ҵ� ����	
	1. �뵵 : �л� 1���� ������ �����ϴ� �޼ҵ���(Database�� Student_score���̺��� 1�� ���� �����ϴ� �޼ҵ�).
	2. �Ű����� : studentNo(Student���̺� studentNo�÷��� �����޾� Student_score���̺� ����ϴ� �ܷ�Ű ��), score(Student_score���̺� �÷��� ��� ��)
	3. ���ϰ� : ����.
	4. StudentScore Class ������Ƽ
		- ���������ڴ� ��� private��. int studentScoreNumber,int studentNumber,int score
	*/		
	public void updateStudentScore(int studentNo, int score) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			connection = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			System.out.println(connection + "<-- connection");
			preparedStatement = connection.prepareStatement("UPDATE student_score SET score=? WHERE student_no=?");
			preparedStatement.setInt(1, score);
			preparedStatement.setInt(2, studentNo);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
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
