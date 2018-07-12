// 28�� �̿��� 2018. 6. 26(ȭ) StudentDao.java
package service;
import java.sql.*;			// java.sql��Ű���� Ŭ���� ����Ʈ(Connection,PreparedStatement,SQLException,DriverManager)
import java.util.ArrayList;
import service.Student;
public class StudentDao {
	/*
	�޼ҵ� ����	
	1. �뵵 : �л� 1���� �Է��ϴ� �޼ҵ���(Database�� Student���̺��� �Ѱ��� ���� �Է��ϴ� �޼ҵ�).
	2. �Ű������� Student class data type�̰� �Ű��������� Student class�� ���� ������� ��ü���������� ��������.
	  - ���������ڴ� ��� private��. int studentNO,String studentName,int studentAge
	3. ���ϰ� : ����.
	*/		
	public void insertStudent(Student stu) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		// ���ܸ� ������ ����(try)
		try {
			// DB Connection
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			connection = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			System.out.println(connection + "<-- connection");
			// DB Connection ��
			
			// preparedStatement������ �غ� �� ����
			preparedStatement = connection.prepareStatement("INSERT INTO student (student_name, student_age)	VALUES (?, ?)");
			// ����1. �л� �̸��� ���̸� ����ϴ� ����(no�� auto_increment�� �ڵ��ο���)
			preparedStatement.setString(1, stu.getStudentName());
			preparedStatement.setInt(2, stu.getStudentAge());
			preparedStatement.executeUpdate();
			// preparedStatement������ �غ� �� ���� ��

		// ����ó��	
		}catch (ClassNotFoundException e) {
			// ClassNotFoundException���ܰ� �߻��� �����ų �ڵ�
			e.printStackTrace();
			//printStackTrace �����޼����� �߻� �ٿ��� ã�Ƽ� �ܰ躰 ������ ����Ѵ�.
		} catch (SQLException e) {
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
	1. �뵵 : �л� pagePerRow(?)���� ��ȸ�ϴ� �޼ҵ���(Database�� Student���̺��� Ư�� ���� ���� ��ȸ�ϴ� �޼ҵ�).
	2. �Ű������� currentPage(������ ������), pagePerRow(�������� ȭ�鿡 ����� ���� ��), searchWord(�˻���), ageSelect(�������Ĺ��) ��.
	3. ���ϰ� : ArrayList<Student>��. <--Arraylist�� ���� ������ �迭����Ʈ�̸� ������ �ε����� StudentŬ������ ���� ������ ��ü�� �������� ����Ų��.
	4. Student Class ������Ƽ
		- ���������ڴ� ��� private��. int studentNO,String studentName,int studentAge
	*/		
	public ArrayList<Student> selectStudentByPage(int currentPage, int pagePerRow, String searchWord, String ageSelect){
		ArrayList<Student> studentList = new ArrayList<>();
		Student student = new Student();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			connection = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			int startRow = (currentPage-1)*pagePerRow;
			if(searchWord.equals("") && ageSelect.equals("")) {
				System.out.println("01����. �˻�� ����, ���̼� ������ ����.");
				String sql = "select student_no,student_name,student_age from student order by student_no asc limit ?,?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, startRow);
				preparedStatement.setInt(2, pagePerRow);	
			}else if(!searchWord.equals("") && ageSelect.equals("")) {
				System.out.println("02����. �˻�� �ְ�, ���̼� ������ ����.");
				String sql = "select student_no,student_name,student_age from student where student_name like ? order by student_no asc limit ?,?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, "%"+searchWord+"%");
				preparedStatement.setInt(2, startRow);
				preparedStatement.setInt(3, pagePerRow);
			}else if(searchWord.equals("") && ageSelect.equals("youngAge")) {
				System.out.println("03����. �˻�� ����, ���̼� ������ �������̼��̴�.");
				String sql = "select student_no,student_name,student_age from student order by student_age asc limit ?,?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, startRow);
				preparedStatement.setInt(2, pagePerRow);		
			}else if(searchWord.equals("") && ageSelect.equals("oldAge")) {
				System.out.println("04����. �˻�� ����, ���̼� ������ �������̼��̴�.");
				String sql = "select student_no,student_name,student_age from student order by student_age desc limit ?,?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, startRow);
				preparedStatement.setInt(2, pagePerRow);
			}else if(!searchWord.equals("") && ageSelect.equals("youngAge")) {
				System.out.println("05����. �˻�� �ְ�, ���̼� ������ �������̼��̴�.");
				String sql = "select student_no,student_name,student_age from student where student_name like ? order by student_age asc limit ?,?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, "%"+searchWord+"%");
				preparedStatement.setInt(2, startRow);
				preparedStatement.setInt(3, pagePerRow);
			}else if(!searchWord.equals("") && ageSelect.equals("oldAge")) {
				System.out.println("06����. �˻�� �ְ�, ���̼� ������ �������̼��̴�.");
				String sql = "select student_no,student_name,student_age from student where student_name like ? order by student_age desc limit ?,?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, "%"+searchWord+"%");
				preparedStatement.setInt(2, startRow);
				preparedStatement.setInt(3, pagePerRow);
			}
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				student = new Student();
				student.setStudentNo(resultSet.getInt("student_no"));
				student.setStudentName(resultSet.getString("student_name"));
				student.setStudentAge(resultSet.getInt("student_age"));
				studentList.add(student);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return studentList;
	}
	
	/*
	�޼ҵ� ����	
	1. �뵵 : �л��� �� ����� ��ȸ�ϴ� �޼ҵ���(Database�� Student���̺��� ��ü ���� ������ ��ȸ�ϴ� �޼ҵ�).
	2. �Ű�����, �Ű��������� ����.
	3. ���ϰ� : int datatype���� �������� ������ student table�� �� ���� ������.
	4. Student Class ������Ƽ
		- ���������ڴ� ��� private��. int studentNO,String studentName,int studentAge
	*/		
	public int countStudent(int pagePerRow) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int lastPage = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			connection = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			preparedStatement = connection.prepareStatement("select count(student_no) as count from student");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int totalRow = resultSet.getInt("count");
			System.out.println(totalRow+"<--DB�� student ���̺� �� ���� ����");
			lastPage = totalRow/pagePerRow;
				if(totalRow%pagePerRow!=0){
					lastPage++;
				}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	1. �뵵 : �л��� 1���� �����ϴ� �޼ҵ���(Database�� Student���̺��� 1������ �����ϴ� �޼ҵ�).
	2. �Ű����� : int studentNo(list���� get������� �Ѱܹ��� ���� int data�� ��ȯ ���� �� ����)
	3. ���ϰ� : void
	4. Student Class ������Ƽ
		- ���������ڴ� ��� private��. int studentNO,String studentName,int studentAge
	*/		
	public void deleteStudent(int studentNo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			connection = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			preparedStatement = connection.prepareStatement("delete from student where student_no=?");
			preparedStatement.setInt(1, studentNo);
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
	1. �뵵 : �л��� 1���� �����ϴ� �޼ҵ���(Database�� Student���̺��� 1������ �����ϴ� �޼ҵ�).
	2. �Ű����� : Student student(updateForm���� ������ parameter���� StudentŬ������ ���� ������ student��ü�� ����)
	3. ���ϰ� : void
	4. Student Class ������Ƽ
		- ���������ڴ� ��� private��. int studentNO,String studentName,int studentAge
	*/	
	public void updateStudent(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			connection = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			preparedStatement = connection.prepareStatement("UPDATE student SET student_name=?, student_age=? WHERE student_no=?");
			preparedStatement.setString(1, student.getStudentName());
			preparedStatement.setInt(2, student.getStudentAge());
			preparedStatement.setInt(3, student.getStudentNo());
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
	1. �뵵 : �л��� 1���� ��ȸ�ϴ� �޼ҵ���(Database�� Student���̺��� 1������ ��ȸ�ϴ� �޼ҵ�).
	2. �Ű����� : Student student(StudentŬ������ ���� ������ student��ü�� ������)
	3. ���ϰ� : Student student(StudentŬ������ ���� ������ student��ü�� ������)
	4. Student Class ������Ƽ
		- ���������ڴ� ��� private��. int studentNO,String studentName,int studentAge
	*/	
	public Student selectStudentDetail(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			connection = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			preparedStatement = connection.prepareStatement("select student_no,student_name,student_age from student where student_no=?");
			preparedStatement.setInt(1, student.getStudentNo());
			System.out.println(preparedStatement+"<--preparedStatement");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			student.setStudentNo(resultSet.getInt("student_no"));
			student.setStudentName(resultSet.getString("student_name"));
			student.setStudentAge(resultSet.getInt("student_age"));
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
		return student;
		
	}
}
