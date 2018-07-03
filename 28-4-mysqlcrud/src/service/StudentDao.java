// 28기 이원상 2018. 6. 26(화) StudentDao.java
package service;
import java.sql.*;			// java.sql패키지내 클래스 임포트(Connection,PreparedStatement,SQLException,DriverManager)
import java.util.ArrayList;
import service.Student;
public class StudentDao {
	/*
	메소드 설명	
	1. 용도 : 학생 1명을 입력하는 메소드임(Database내 Student테이블의 한개의 행을 입력하는 메소드).
	2. 매개변수는 Student class data type이고 매개변수명은 Student class를 통해 만들어진 객체참조변수의 참조값임.
	  - 접근지정자는 모두 private임. int studentNO,String studentName,int studentAge
	3. 리턴값 : 없음.
	*/		
	public void insertStudent(Student stu) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		// 예외를 조사할 문장(try)
		try {
			// DB Connection
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcDriver = "jdbc:mysql://localhost:3306/284db?useUnicode=true&characterEncoding=euckr";
			String dbUser = "java";
			String dbPass = "java0000";
			connection = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
			System.out.println(connection + "<-- connection");
			// DB Connection 끝
			
			// preparedStatement쿼리문 준비 및 실행
			preparedStatement = connection.prepareStatement("INSERT INTO student (student_name, student_age)	VALUES (?, ?)");
			// 쿼리1. 학생 이름과 나이를 등록하는 쿼리(no는 auto_increment로 자동부여됨)
			preparedStatement.setString(1, stu.getStudentName());
			preparedStatement.setInt(2, stu.getStudentAge());
			preparedStatement.executeUpdate();
			// preparedStatement쿼리문 준비 및 실행 끝

		// 예외처리	
		}catch (ClassNotFoundException e) {
			// ClassNotFoundException예외가 발생시 실행시킬 코드
			e.printStackTrace();
			//printStackTrace 에러메세지의 발생 근원츨 찾아서 단계별 에러를 출력한다.
		} catch (SQLException e) {
			// SQLException예외가 발생시 실행시킬 코드
			e.printStackTrace();
			//printStackTrace 에러메세지의 발생 근원츨 찾아서 단계별 에러를 출력한다.
			
		// 마지막에 반드시 실행시켜야할 코드	
		} finally {
			if(preparedStatement != null)	// preparedStatement객체참조변수의 값이 null이 아닐 경우 객체 반납(반납순서는 rs->pstmt->conn)
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// SQLException예외가 발생시 실행시킬 코드
				e.printStackTrace();
			}
			if(connection != null)			// connection객체참조변수의 값이 null이 아닐 경우 객체 반납(반납순서는 rs->pstmt->conn)
				try {
					connection.close();
				} catch (SQLException e) {
					// SQLException예외가 발생시 실행시킬 코드
					e.printStackTrace();
				}
		}
	}
	
	/*
	메소드 설명	
	1. 용도 : 학생 pagePerRow(?)명을 조회하는 메소드임(Database내 Student테이블의 특정 개의 행을 조회하는 메소드).
	2. 매개변수는 int data type이고 매개변수명은 currentPage(시작할 페이지), pagePerRow(페이지당 화면에 출력할 행의 수)임.
	3. 리턴값 : ArrayList<Student>임. <--Arraylist를 통해 생성된 배열리스트이며 각각의 인덱스는 Student클래스를 통해 생성된 객체의 참조값을 가르킨다.
	4. Student Class 프로퍼티
		- 접근지정자는 모두 private임. int studentNO,String studentName,int studentAge
	*/		
	public ArrayList<Student> selectStudentByPage(int currentPage, int pagePerRow){
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
			String sql = "select student_no,student_name,student_age from student order by student_no asc limit ?,?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, startRow);
			preparedStatement.setInt(2, pagePerRow);	
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
	메소드 설명	
	1. 용도 : 학생의 총 명수를 조회하는 메소드임(Database내 Student테이블의 전체 행의 갯수를 조회하는 메소드).
	2. 매개변수, 매개변수명은 없음.
	3. 리턴값 : int datatype으로 쿼리문의 실행결과 student table의 총 행의 갯수임.
	4. Student Class 프로퍼티
		- 접근지정자는 모두 private임. int studentNO,String studentName,int studentAge
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
			System.out.println(totalRow+"<--DB내 student 테이블 총 행의 갯수");
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
	메소드 설명	
	1. 용도 : 학생의 1명을 삭제하는 메소드임(Database내 Student테이블의 1개행을 삭제하는 메소드).
	2. 매개변수 : int studentNo(list에서 get방식으로 넘겨받은 값을 int data로 변환 받은 후 대입)
	3. 리턴값 : void
	4. Student Class 프로퍼티
		- 접근지정자는 모두 private임. int studentNO,String studentName,int studentAge
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
	메소드 설명	
	1. 용도 : 학생의 1명을 수정하는 메소드임(Database내 Student테이블의 1개행을 수정하는 메소드).
	2. 매개변수 : Student student(updateForm에서 수정된 parameter값을 Student클래스를 통해 생성된 student객체에 셋팅)
	3. 리턴값 : void
	4. Student Class 프로퍼티
		- 접근지정자는 모두 private임. int studentNO,String studentName,int studentAge
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
	메소드 설명	
	1. 용도 : 학생의 1명을 조회하는 메소드임(Database내 Student테이블의 1개행을 조회하는 메소드).
	2. 매개변수 : Student student(Student클래스를 통해 생성된 student객체의 참조값)
	3. 리턴값 : Student student(Student클래스를 통해 생성된 student객체의 참조값)
	4. Student Class 프로퍼티
		- 접근지정자는 모두 private임. int studentNO,String studentName,int studentAge
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
