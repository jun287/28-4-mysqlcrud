<!-- 2018. 07. 03 28�� ������ -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "TeacherDAO.TeacherAddrDao" %>
<%@ page import = "TeacherDTO.TeacherAddr" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>teacherAddrList</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
	<%	
		/*  
			��ȣ�� �ּҰ��� �޾ƿͼ� ȭ�鿡 ����ϱ�����
			teacher_no(no)���� ����ȯ�� int teacherNo ������ �����մϴ�.
			TeacherAddrDao Ŭ���� ��ü ������ 
			teacherAddrDao Ŭ���� ��ü���� selectTeacherAddr �޼��忡 teacherNo�� ������ ȣ���ϸ�
			TeacherAddr Ŭ���� ��ü Ÿ������ teacherAddr ��ü���������� 
			���ǿ� �´� ��ȸ����� ��� ��ü�� �ּҰ� ���ϵǰ�
			
			�� �ּҰ��� ã�ư��� �޼��带 ȣ���ϰ� ���ǹ��� ����� �ּҰ� ������(null) �ּ��Է������� �̵���Ű��
			�ּҰ��� ������ ȭ�鿡 ����մϴ�.
		*/
		
		int teacherNo = Integer.parseInt(request.getParameter("no"));
		TeacherAddrDao teacherAddrDao = new TeacherAddrDao();
		TeacherAddr teacherAddr = teacherAddrDao.selectTeacherAddr(teacherNo);
		String teacherAddress= null;
		
		if(teacherAddr.getTeacherAddrContent() == null){
			response.sendRedirect(request.getContextPath()+"/Teacher/insertTeacherAddrForm.jsp?no="+teacherNo);
		}else{
			teacherAddress = teacherAddr.getTeacherAddrContent();
		}
	%>
		<div id="main" align="center" >
			<h2>�ּ� ���</h2><br>
			<table>
				<tr>
					<th>��ȣ</th>
					<th>�ּ�</th>
				</tr>
				<tr>
					<td><%=teacherAddr.getTeacherNo()%></td>
					<td><%=teacherAddress%></td>
				</tr>
			</table>
			<a href="<%=request.getContextPath()%>/Teacher/teacherList.jsp">�������</a>
		</div>
	</body>
</html>