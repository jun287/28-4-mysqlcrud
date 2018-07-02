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
				</tr>
		<%
				//�ݺ����� ����� teacherList��ü���������� �ּҰ��� ã�ư��� size()�޼��带 ȣ���ϰ� index ������ŭ �ݺ��ϰ� �մϴ�.
				for(int i=0;i<teacherList.size();i++){
					Teacher teacher = teacherList.get(i); // Teacher Ŭ����Ÿ���� teacher ��ü���������� teacherList ��ü���������� �ּҰ��� ã�ư��� get()�޼��带 ȣ���ϰ� teacher ��ü���� �ּҰ��� �����մϴ�.
		%>
				<tr>
					<td><%=teacher.getTeacherNo()%></td> <!-- teacher ��ü����  getTeacherNo()�޼��带 ȣ���Ͽ� �����͸� �����ɴϴ�. -->
					<td><%=teacher.getTeacherName()%></td> <!-- teacher ��ü����  getTeacherName()�޼��带 ȣ���Ͽ� �����͸� �����ɴϴ�. -->
					<td><%=teacher.getTeacherAge()%></td> <!-- teacher ��ü����  getTeacherAge()�޼��带 ȣ���Ͽ� �����͸� �����ɴϴ�. -->
				</tr>
		<%
				}
		%>
			</table>
		<%
			// ������ �������� ���ϱ� ���� int �⺻Ÿ������ totalRow ������ �����ϰ� teacherDao Ŭ������ü�� count() �޼��帣 ȣ���ϰ� ���ϰ�(COUNT(teacher_no))�� �����մϴ�.
			int totalRow = teacherDao.count();
			
			// ������ ���� ��� totalRow�� rowPerPage�� ������ ������(lastPage)�� ���ϰ� �����մϴ�.
			int lastPage = (totalRow-1) / rowPerPage;
			// if ���ǹ��� ����� �� �����Ͱ���(COUNT(teacher_no))-1 �� rowPerPage�� �������� 0�� �ƴҶ� ������ �������� 1�� ���� ��ŵ�ϴ�.
			if((totalRow-1) % rowPerPage != 0) {
				lastPage++;
			}
			
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