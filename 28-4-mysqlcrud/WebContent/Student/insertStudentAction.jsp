<!-- 28�� �̿��� 2018. 6. 25(��)insertStudentAction.jsp -->
<%@ page language = "java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "service.StudentDao"%> 
<%request.setCharacterEncoding("euc-kr"); %>	<!-- usebean�׼��±׸� ���� ������ ��ü�� �����Ͱ� ��涧 �ѱ��� �� ���ڵ��ǵ��� jsp�׼��±� �տ� ����ؾ���. -->
<jsp:useBean id="stu" class="service.Student"/>	<!-- StudentŬ������ ����Ʈ �� Ŭ������ ���� ��ü�� �����Ͽ��� -->
<jsp:setProperty name="stu" property="*"/>		<!-- jsp:usebean �׼��±׷� ������ ��ü�� ������Ƽ�� ���� *�� ������ ��� ���� �̸��� ���� �Ķ������ ���� ���� -->
<%
	StudentDao sDao = new StudentDao();
	sDao.insertStudent(stu);
	response.sendRedirect(request.getContextPath()+"/Student/studentList.jsp");
%>
