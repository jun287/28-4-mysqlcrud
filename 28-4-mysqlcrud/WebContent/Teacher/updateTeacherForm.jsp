<!-- 2018. 07. 03 28기 공세준 -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "service.TeacherDao" %>
<%@ page import = "service.Teacher" %>
<%@ page import = "service.TeacherAddr" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertTeacherForm</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
		<div align="center">
		<%
			
			int teacherNo = Integer.parseInt(request.getParameter("no"));
			TeacherDao teacherDao = new TeacherDao();
			Teacher teacher = teacherDao.updateSelectTeacher(teacherNo);
			TeacherAddr teacherAddr = teacherDao.updateSelectTeacherAddr(teacherNo);
			String teacherAddress = null;
			
			if(teacherAddr.getTeacherAddrContent() == null){
				teacherAddress = "주소를 입력해주세요.";
			}else{
				teacherAddress = teacherAddr.getTeacherAddrContent();
			}
		
		%>
			<h3>정보 수정</h3>
			<form action="<%= request.getContextPath()%>/Teacher/updateTeacherAction.jsp" method="post">
				<ul id="mem_form">
					<li>
						<ul class="cols">
							<li class="col1">이름 :</li>
							<li class="col2"><input type="text" name="teacherName" maxlength="5" value="<%=teacher.getTeacherName()%>" required></li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1">나이 :</li>
							<li class="col2"><input type="number" name="teacherAge" min="1" max="100" maxlength="3" value="<%=teacher.getTeacherAge()%>" required></li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1">주소 :</li>
							<li class="col2"><input type="text" name="teacherAddrContent" value="<%=teacherAddress%>" required></li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1"></li>
							<li class="col2">
								<input type="hidden" name="no" value="<%=teacherNo%>">
								<input type="submit" value="등록">	
							</li>
						</ul>
					</li>
				</ul>
			</form>
		</div>
	</body>
</html>