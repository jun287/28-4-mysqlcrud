<!-- 28�� �̿��� 2018. 7. 10(ȭ) insertStudentScoreForm.jsp -->
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
<title>�����Է� ȭ��</title>
</head>
	<body>
<%
	request.setCharacterEncoding("euc-kr");
	int studentNo = Integer.parseInt(request.getParameter("studentNo"));
	StudentDao studentDao = new StudentDao();								// 0. StudentDaoŬ���� �� �޼ҵ� ȣ���� ���� ��ü ����
	Student student = new Student();										// 1. StudentŬ���� ��ü�������� ����,����,�Ҵ�
	student.setStudentNo(studentNo);										// 2. 1������ ������ ��ü�� �ʵ� setting
	student = studentDao.selectStudentDetail(student);						// 3. 1�� ��ü�� �޼ҵ� ������ ����(�л� ������)
	// StudentScoreDao�� �޼ҵ带 Ȱ���Ϸ� ������, ���̺� 2���� inner join ���־� score���̺��� ���� �� ��ȸ ��� ���� ���� �л��� ���� ��ȸ
	
	StudentScoreDao studentScoreDao = new StudentScoreDao();				// 0. StudentScoreDaoŬ���� �� �޼ҵ� ȣ���� ���� ��ü ����
	int count = studentScoreDao.countStudentScore(studentNo);				// 1. Ư�� �л��� �Էµ� ������ ���� ������ �������·� �����ϴ� �޼ҵ�
	System.out.println(count+"<--count");
	
	StudentAndScore studentAndScore = new StudentAndScore();				// �޼ҵ� ȣ�� ���ϰ��� ���� ��ü ����

	ArrayList<StudentAndScore> studentAndScoreList = studentScoreDao.selectStudentAndScore(studentNo);
	// Ư�� �л��� ��ȣ�� �Ű������� ���� ȣ���ϸ�, �� �л��� ����,�̸�,����(2�� ���̺� inner join����� ����Ʈ ���·� �޴� �޼ҵ�)
	if(count >= 1){							// �Էµ� ������ ���� ��
		studentAndScore=studentAndScoreList.get(0);
%>
		<div id="main" align="center">
			<h3>���� ����</h3>
			<form action="<%=request.getContextPath()%>/Student/insertStudentScoreAction.jsp" method="post">
				<ul id="mem_form">
					<li>
						<ul class="cols">
							<li class="col1"><label for="name">�̸� :</label></li>
							<li class="col2">
								<input type="text" id="name" name="studentName" value="<%=studentAndScore.getStudent().getStudentName() %>" readonly>
								<input type="hidden" id="studentNo" name="studentNo" value="<%=studentAndScore.getStudent().getStudentNo() %>">
							</li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1"><label for="score">����</label></li>
							<li class="col2"><input type="text" id="score" name="score" value="<%=studentAndScore.getStudentScore().getScore() %>" required></li>	
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1"></li>
							<li class="col2"><input type="submit" value="����"></li>
						</ul>
					</li>
				</ul>
			</form>
		</div>
<%		
	}else{									// �Էµ� ������ ���� ��
%>	
		<div id="main" align="center">
			<h3>���� �Է�</h3>
			<form action="<%=request.getContextPath()%>/Student/insertStudentScoreAction.jsp" method="post">
				<ul id="mem_form">
					<li>
						<ul class="cols">
							<li class="col1"><label for="name">�̸� :</label></li>
							<li class="col2">
								<input type="text" id="name" name="studentName" value="<%=student.getStudentName() %>" readonly>
								<input type="hidden" id="studentNo" name="studentNo" value="<%=student.getStudentNo()%>">
							</li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1"><label for="score">���� :</label></li>
							<li class="col2"><input type="text" id="score" name="score" required></li>							
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1"></li>
							<li class="col2"><input type="submit" value="�Է�"></li>
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