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
	1. �뵵 : �л��� ��ü�� ��ȣ, �̸�, ����, ������ȣ, ������ ��ȸ�ϴ� �޼ҵ�(Database�� Student���̺�,Student_score���̺� inner ���� ��� ��ȸ).
	2. �Ű����� : ����
	3. ���ϰ� : StudentAndScoreŬ������ ������ ��ü�������� �������� ���� ����Ʈ.
		- StudentAndScoreŬ���� ������Ƽ : private Student student, private StudentScore studentScore;
	*/
	public ArrayList<StudentAndScore> selectStudentAndScore(int studentNo){
		ArrayList<StudentAndScore> studentAndScoreList = new ArrayList<StudentAndScore>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = new DBconnection().getConnection();
			//DBconnectionŬ������ �⺻ �����ڸ� ȣ���Ͽ� ��ü���� �� getConnection�޼ҵ� ȣ���Ͽ� ���ϰ��� connection�� ����.
			preparedStatement = connection.prepareStatement("select student.student_no, student.student_name, student.student_age, studentscore.student_score_no, studentscore.score from student student inner join student_score studentscore on student.student_no = studentscore.student_no where student.student_no=?");
			preparedStatement.setInt(1, studentNo);
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
			connection = new DBconnection().getConnection();
			//DBconnectionŬ������ �⺻ �����ڸ� ȣ���Ͽ� ��ü���� �� getConnection�޼ҵ� ȣ���Ͽ� ���ϰ��� connection�� ����.
			preparedStatement = connection.prepareStatement("INSERT INTO student_score (student_no, score) VALUES (?, ?)");
			preparedStatement.setInt(1, studentNo);
			preparedStatement.setInt(2, score);
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
			connection = new DBconnection().getConnection();
			//DBconnectionŬ������ �⺻ �����ڸ� ȣ���Ͽ� ��ü���� �� getConnection�޼ҵ� ȣ���Ͽ� ���ϰ��� connection�� ����.
			preparedStatement = connection.prepareStatement("select count(student_no) as count from student_score where student_no = ?");
			preparedStatement.setInt(1, studentNo);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				count=resultSet.getInt(1);
			}
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
			connection = new DBconnection().getConnection();
			//DBconnectionŬ������ �⺻ �����ڸ� ȣ���Ͽ� ��ü���� �� getConnection�޼ҵ� ȣ���Ͽ� ���ϰ��� connection�� ����.
			preparedStatement = connection.prepareStatement("UPDATE student_score SET score=? WHERE student_no=?");
			preparedStatement.setInt(1, score);
			preparedStatement.setInt(2, studentNo);
			preparedStatement.executeUpdate();
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
	
	/*
	�޼ҵ� ����	
	1. �뵵 : �л� ��ü�� ���� ����� ���ϴ� �޼ҵ���(Database�� Student_score���̺��� score �÷��� ������ ���� ��հ��� ���ϴ� �޼ҵ�).
	2. �Ű����� : ����.
	3. ���ϰ� : Student_score���̺��� score �÷��� ������ ���� ��հ�
	4. StudentScore Class ������Ƽ
		- ���������ڴ� ��� private��. int studentScoreNumber,int studentNumber,int score
	*/		
	public double selectStudentScoreAverage() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		double studentScoreAverage=0;
		try {
			connection = new DBconnection().getConnection();
			//DBconnectionŬ������ �⺻ �����ڸ� ȣ���Ͽ� ��ü���� �� getConnection�޼ҵ� ȣ���Ͽ� ���ϰ��� connection�� ����.
			preparedStatement = connection.prepareStatement("select avg(score) as averageStudentScore from student_score");
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				studentScoreAverage = resultSet.getDouble("averageStudentScore");
			}
		} catch (SQLException e) {

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
		return studentScoreAverage;
	}
	
	/*
	�޼ҵ� ����	
	1. �뵵 : ������� �̻��� �л��� ��ü�� ��ȣ, �̸�, ����, ������ȣ, ������ ������������ ��ȸ�ϴ� �޼ҵ�(Database�� Student���̺�,Student_score���̺� inner ���� ��� ��ȸ).
	2. �Ű����� : int currentPage(����������), int pagePerRow(�������� �� ���� ����)
	3. ���ϰ� : StudentAndScoreŬ������ ������ ��ü�������� �������� ���� ����Ʈ.(������� �̻��� �л�����Ʈ)
	*/
	public ArrayList<StudentAndScore> selectStudentAndScoreAboveAverage(int currentPage, int pagePerRow){
		ArrayList<StudentAndScore> StudentAndScoreAboveAverageList = new ArrayList<StudentAndScore>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = new DBconnection().getConnection();
			//DBconnectionŬ������ �⺻ �����ڸ� ȣ���Ͽ� ��ü���� �� getConnection�޼ҵ� ȣ���Ͽ� ���ϰ��� connection�� ����.
			preparedStatement = connection.prepareStatement("select student.student_no, student.student_name, student.student_age, studentscore.student_score_no, studentscore.score "
					+ "from student student inner join student_score studentscore on student.student_no = studentscore.student_no "
					+ "where score >= (select avg(score) from student_score) order by score desc limit ?,?");
			int startRow = (currentPage-1)*pagePerRow;
			preparedStatement.setInt(1, startRow);
			preparedStatement.setInt(2, pagePerRow);
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
				StudentAndScoreAboveAverageList.add(studentAndScore);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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

		return StudentAndScoreAboveAverageList;
		
	}
	
	/*
	�޼ҵ� ����	
	1. �뵵 : ��������̻��� �л��� �� ����� ��ȸ�ϴ� �޼ҵ���
	(Database�� Student�� student_score���̺�(Inner ����)�Ͽ� student_score�÷��� ��հ� �̻��� �����͸� ���� ���� ������ ��ȸ�ϴ� �޼ҵ�).
	2. �Ű����� : pagePerRow = jsp�������� ����� �� db���̺� ���� ��
	3. ���ϰ� : lastPage = 1������ ������ ���̺� ��ȸ���(��ü���� ����)�� 2�� �Ű�����(�������� �� ���� ��)�� ���� %���� 0�� �ƴҰ�� 1����, 0�ϰ�� ���� �� ���
	*/		
	public int countStudentAndScoreAboveAverage(int pagePerRow) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int lastPage = 0;
		try {
			connection = new DBconnection().getConnection();
			//DBconnectionŬ������ �⺻ �����ڸ� ȣ���Ͽ� ��ü���� �� getConnection�޼ҵ� ȣ���Ͽ� ���ϰ��� connection�� ����.
			preparedStatement = connection.prepareStatement("select count(student.student_no) as count from student student "
					+ "inner join student_score studentscore on student.student_no = studentscore.student_no "
					+ "where score >= (select avg(score) from student_score)");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int totalRow = resultSet.getInt("count");
			System.out.println(totalRow+"<--totalRow");
			lastPage = totalRow/pagePerRow;
				if(totalRow%pagePerRow!=0){
					lastPage++;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
		return lastPage;
		
	}
	
	/*
	�޼ҵ� ����	
	1. �뵵 : �л��� 1���� ������ �����ϴ� �޼ҵ���(Database�� Student_Score���̺��� Ư�� student_no�÷��� �����ϴ� �޼ҵ�).
	2. �Ű����� : int studentNo(list���� get������� �Ѱܹ��� ���� int data�� ��ȯ ���� �� ����)
	3. ���ϰ� : void
	4. StudentScore Class ������Ƽ
		- ���������ڴ� ��� private��. int studentScoreNumber,int studentNumber,int score
	*/			
	public void deleteStudentScore(int studentNo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = new DBconnection().getConnection();
			//DBconnectionŬ������ �⺻ �����ڸ� ȣ���Ͽ� ��ü���� �� getConnection�޼ҵ� ȣ���Ͽ� ���ϰ��� connection�� ����.
			preparedStatement = connection.prepareStatement("DELETE FROM student_score WHERE student_no=?");
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
