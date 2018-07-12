<!-- 2018. 07. 09 28�� ������ -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
    
<%@ page import = "TeacherDAO.TeacherDao" %>
<%@ page import = "TeacherDTO.Teacher" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "TeacherDTO.TeacherScore" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
		<%		
			request.setCharacterEncoding("EUC-KR");
			//����¡�� ���� ù �������� ���� 1�� �����մϴ�.
			int currentPage = 1;
			if(request.getParameter("currentPage")!=null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			
			String searchWord = "";
			System.out.println(searchWord);
			if(request.getParameter("searchWord")!=null) {
				searchWord = request.getParameter("searchWord");
				System.out.println(searchWord);
			}
			
			// �������� 10���� �����͸� �����ݴϴ�.
			int rowPerPage = 10;
			TeacherDao teacherDao = new TeacherDao();
			ArrayList<Teacher> teacherList = teacherDao.selectTeacherByPage(currentPage, rowPerPage, searchWord);
			
		%>
			<div align="center">
				<h2>���� ���</h2><br>
				<table>
					<tr>
						<th>��ȣ</th>
						<th>�̸�</th>
						<th>����</th>
						<th>�ּ��Է�</th><!-- �ټ� -->
						<th>����</th>
						<th>����</th>
						<th>��������</th><!-- Join�� ���� -->
					</tr>
					<%
						//�ݺ����� ����� teacherList��ü���������� �ּҰ��� ã�ư��� size()�޼��带 ȣ���ϰ� index ������ŭ �ݺ��ϰ� �մϴ�.
						for(int i=0;i<teacherList.size();i++){
							Teacher teacher = teacherList.get(i); // Teacher Ŭ����Ÿ���� teacher ��ü���������� teacherList ��ü���������� �ּҰ��� ã�ư��� get()�޼��带 ȣ���ϰ� teacher ��ü���� �ּҰ��� �����մϴ�.
					%>
						<tr>
							<td><%=teacher.getTeacherNo()%></td> <!-- teacher ��ü����  getTeacherNo()�޼��带 ȣ���Ͽ� �����͸� �����ɴϴ�. -->
							<td><a href ="<%= request.getContextPath() %>/Teacher/teacherAddrList.jsp?no=<%=teacher.getTeacherNo()%>"><%=teacher.getTeacherName()%></a></td> <!-- teacher ��ü����  getTeacherName()�޼��带 ȣ���Ͽ� �����͸� �����ɴϴ�. -->
							<td><%=teacher.getTeacherAge()%></td> <!-- teacher ��ü����  getTeacherAge()�޼��带 ȣ���Ͽ� �����͸� �����ɴϴ�. -->
							<td><a href ="<%= request.getContextPath() %>/Teacher/insertTeacherAddrForm.jsp?no=<%=teacher.getTeacherNo()%>">�ּ��Է�</a></td>
							<td><a href ="<%= request.getContextPath() %>/Teacher/deleteTeacherAction.jsp?no=<%=teacher.getTeacherNo()%>">����</a></td>
							<td><a href ="<%= request.getContextPath() %>/Teacher/updateTeacherForm.jsp?no=<%=teacher.getTeacherNo()%>">����</a></td>
							<td><a href ="<%= request.getContextPath() %>/Teacher/teacherAndScoreList.jsp?no=<%=teacher.getTeacherNo()%>">��������</a></td>
							<!-- updateTeacherForm.jsp -> updateTeacherAction.jsp -->
						</tr>
					<%
						}
					%>
				</table>
					<form method="post" action="<%=request.getContextPath()%>/Teacher/teacherList.jsp">
							<input type="text" name="searchWord">
							<button type="submit">�˻�</button>
					</form>
					<%
						// ������ �������� ���ϱ� ���� int �⺻Ÿ������ lastPage ������ �����ϰ� teacherDao Ŭ������ü�� lastPage() �޼��带 ȣ���ϰ� ���ϰ��� �����մϴ�.
						int lastPage = teacherDao.lastPageTeacher(rowPerPage,searchWord);
			
						
						if(currentPage>1){
							%>
							<a href = "./teacherList.jsp?currentPage=<%=currentPage-1%>&searchWord=<%=searchWord%>">�� ����</a>
							<%
						}
			
						if(currentPage<lastPage){
							%>
							<a href = "./teacherList.jsp?currentPage=<%=currentPage+1%>&searchWord=<%=searchWord%>">���� ��</a>
							<%
						}else{
							%>
							<a href = "<%=request.getContextPath()%>/Teacher/teacherList.jsp">�������</a>
							<%
						}
					%>
							<a href = "<%=request.getContextPath()%>/index.jsp">�ε�����</a>
			</div>
	</body>
</html>