<!-- 2018. 7. 9(��) 28�� �̿��� insertStudentScoreAction.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="StudentDAO.StudentScoreDao"%>
<%
	request.setCharacterEncoding("euc-kr");
	int studentNo = Integer.parseInt(request.getParameter("studentNo"));
	int score = Integer.parseInt(request.getParameter("score"));
	
	StudentScoreDao studentScoreDao = new StudentScoreDao();
	int count = studentScoreDao.countStudentScore(studentNo);
	// DB student_score���̺��� Ư�� studentNo�� ��ȸ�Ͽ� ���� ���� ������ ���Ϲ޴� �޼ҵ� 
	if(count >= 1){
		// ��ȸ�� ����� DB student_score���̺� ��ȸ�� studentNo�� 1�� �� �̻� ���� ���
		studentScoreDao.updateStudentScore(studentNo, score);
	}else{
		// ��ȸ�� ����� DB student_score���̺� ��ȸ�� studentNo�� ���� ���
		studentScoreDao.insertStudentScore(studentNo, score);
	}
	response.sendRedirect(request.getContextPath()+"/Student/studentList.jsp");
%>
