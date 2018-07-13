<!-- 28기 이원상 2018. 7. 10(화) insertStudentScoreForm.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="StudentDAO.StudentDao" %>
<%@ page import="StudentDAO.StudentScoreDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="StudentDTO.Student" %>
<%@ page import="StudentDTO.StudentAndScore" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/main.css">
<title>점수입력 화면</title>
</head>
	<body>
<%
	request.setCharacterEncoding("euc-kr");
	int studentNo = Integer.parseInt(request.getParameter("studentNo"));
	StudentDao studentDao = new StudentDao();								// 0. StudentDao클래스 내 메소드 호출을 위한 객체 생성
	Student student = new Student();										// 1. Student클래스 객체참조변수 선언,대입,할당
	student.setStudentNo(studentNo);										// 2. 1번에서 생성된 객체의 필드 setting
	student = studentDao.selectStudentDetail(student);						// 3. 1번 객체의 메소드 실행결과 대입(학생 상세정보)
	// StudentScoreDao의 메소드를 활용하려 했으나, 테이블 2개가 inner join 되있어 score테이블이 없을 시 조회 결과 값이 없어 학생만 따로 조회
	
	StudentScoreDao studentScoreDao = new StudentScoreDao();				// 0. StudentScoreDao클래스 내 메소드 호출을 위한 객체 생성
	int count = studentScoreDao.countStudentScore(studentNo);				// 1. 특정 학생의 입력된 점수의 행을 개수를 정수형태로 리턴하는 메소드
	System.out.println(count+"<--count");
	
	StudentAndScore studentAndScore = new StudentAndScore();				// 메소드 호출 리턴값을 받을 객체 생성

	ArrayList<StudentAndScore> studentAndScoreList = studentScoreDao.selectStudentAndScore(studentNo);
	// 특정 학생의 번호를 매개변수로 대입 호출하면, 그 학생의 나이,이름,점수(2개 테이블 inner join결과를 리스트 형태로 받는 메소드)
	if(count >= 1){							// 입력된 점수가 있을 시
		studentAndScore=studentAndScoreList.get(0);
%>
		<div id="main" align="center">
			<h3>점수 수정</h3>
			<form action="<%=request.getContextPath()%>/Student/insertStudentScoreAction.jsp" method="post">
				<ul id="mem_form">
					<li>
						<ul class="cols">
							<li class="col1"><label for="name">이름 :</label></li>
							<li class="col2">
								<input type="text" id="name" name="studentName" value="<%=studentAndScore.getStudent().getStudentName() %>" readonly>
								<input type="hidden" id="studentNo" name="studentNo" value="<%=studentAndScore.getStudent().getStudentNo() %>">
							</li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1"><label for="score">점수</label></li>
							<li class="col2"><input type="text" id="score" name="score" value="<%=studentAndScore.getStudentScore().getScore() %>" required></li>	
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1"></li>
							<li class="col2"><input type="submit" value="수정"></li>
						</ul>
					</li>
				</ul>
			</form>
		</div>
<%		
	}else{									// 입력된 점수가 없을 시
%>	
		<div id="main" align="center">
			<h3>점수 입력</h3>
			<form action="<%=request.getContextPath()%>/Student/insertStudentScoreAction.jsp" method="post">
				<ul id="mem_form">
					<li>
						<ul class="cols">
							<li class="col1"><label for="name">이름 :</label></li>
							<li class="col2">
								<input type="text" id="name" name="studentName" value="<%=student.getStudentName() %>" readonly>
								<input type="hidden" id="studentNo" name="studentNo" value="<%=student.getStudentNo()%>">
							</li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1"><label for="score">점수 :</label></li>
							<li class="col2"><input type="text" id="score" name="score" required></li>							
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1"></li>
							<li class="col2"><input type="submit" value="입력"></li>
						</ul>
					</li>
				</ul>
			</form>
		</div>

<%		
	}
%>		
	</body>
</html>