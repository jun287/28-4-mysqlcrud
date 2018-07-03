<!-- 2018. 07. 02 28�� ������ -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
    
<%@ page import = "service.TeacherDao" %>
<%@ page import = "service.Teacher" %>
<%@ page import = "java.util.ArrayList" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<%	
			//����¡�� ���� ù �������� ���� 1�� �����մϴ�.
			int currentPage = 1;
			if(request.getParameter("currentPage")!=null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			// �������� 10���� �����͸� �����ݴϴ�.
			int rowPerPage = 10;
			TeacherDao teacherDao = new TeacherDao();
			ArrayList<Teacher> teacherList = teacherDao.selectTeacherByPage(currentPage, rowPerPage);
		%>
			<h2>���� ���</h2>
			<table border="1">
				<tr>
					<th>��ȣ</th>
					<th>�̸�</th>
					<th>����</th>
					<th>�ּ��Է�</th>
					<th>����</th>
					<th>����</th>
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
					<!-- updateTeacherForm.jsp -> updateTeacherAction.jsp -->
				</tr>
		<%
				}
		%>
			</table>
			
			<form>
				<div>
					�̸� :
					<input type="text" name="searchWord">
					<button type="button">�˻�</button>
				</div>
			</form>
		<%
			// ������ �������� ���ϱ� ���� int �⺻Ÿ������ lastPage ������ �����ϰ� teacherDao Ŭ������ü�� lastPage() �޼��带 ȣ���ϰ� ���ϰ��� �����մϴ�.
			int lastPage = teacherDao.lastPage(rowPerPage);

			
			if(currentPage>1){
				%>
				<a href = "./teacherList.jsp?currentPage=<%=currentPage-1%>">�� ����</a>
				<%
			}

			if(currentPage<lastPage){
				%>
				<a href = "./teacherList.jsp?currentPage=<%=currentPage+1%>">���� ��</a>
				<%
			}
		%>
	</body>
</html>