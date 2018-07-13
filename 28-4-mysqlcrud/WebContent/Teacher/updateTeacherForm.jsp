<!-- 2018. 07. 03 28�� ������ -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "TeacherDAO.TeacherDao" %>
<%@ page import = "TeacherDAO.TeacherAddrDao" %>
<%@ page import = "TeacherDTO.Teacher" %>
<%@ page import = "TeacherDTO.TeacherAddr" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertTeacherForm</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
		<%
			
			int teacherNo = Integer.parseInt(request.getParameter("no"));
			TeacherAddrDao teacherAddrDao = new TeacherAddrDao();
			TeacherDao teacherDao = new TeacherDao();
			Teacher teacher = teacherDao.updateSelectTeacher(teacherNo);
			TeacherAddr teacherAddr = teacherAddrDao.updateSelectTeacherAddr(teacherNo);
			String teacherAddress = null;
			
			if(teacherAddr.getTeacherAddrContent() == null){
				teacherAddress = "�ּҸ� �Է����ּ���.";
			}else{
				teacherAddress = teacherAddr.getTeacherAddrContent();
			}
		
		%>
			<div id="main" align="center">
				<h3>���� ����</h3>
				<form action="<%= request.getContextPath()%>/Teacher/updateTeacherAction.jsp" method="post">
					<ul id="mem_form">
						<li>
							<ul class="cols">
								<li class="col1">�̸� :</li>
								<li class="col2"><input type="text" name="teacherName" maxlength="5" autocomplete="off" value="<%=teacher.getTeacherName()%>" required></li>							
							</ul>
						</li>
						<li>
							<ul class="cols">
								<li class="col1">���� :</li>
								<li class="col2"><input type="number" name="teacherAge" min="1" max="100" maxlength="3" autocomplete="off" value="<%=teacher.getTeacherAge()%>" required></li>
							</ul>
						</li>
						<li>
							<ul class="cols">
								<li class="col1">�ּ� :</li>
								<li class="col2"><input type="text" name="teacherAddrContent" autocomplete="off" value="<%=teacherAddress%>" required></li>
							</ul>
						</li>
						<li>
							<ul class="cols">
								<li class="col1"><input type="hidden" name="no" value="<%=teacherNo%>"></li>
								<li class="col2"><input type="submit" value="����"></li>
							</ul>
						</li>
					</ul>
				</form>
			</div>
	</body>
</html>