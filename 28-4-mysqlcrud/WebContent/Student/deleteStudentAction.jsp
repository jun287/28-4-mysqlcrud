<!-- 2018. 7. 3(ȭ) �̿��� deleteStudentAction.jsp -->
<%@ page language = "java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "StudentDAO.StudentDao" %>
<%@ page import = "StudentDAO.StudentAddrDao" %>
<%@ page import = "StudentDAO.StudentScoreDao" %>
<%
	request.setCharacterEncoding("euc-kr");
	int studentNo = Integer.parseInt(request.getParameter("studentNo"));
	
	StudentAddrDao studentAddrDao = new StudentAddrDao();		// StudentAddrDaoŬ������ �޼ҵ� ȣ�� ���� ��ü������������,����,�Ҵ�
	StudentScoreDao studentScoreDao = new StudentScoreDao();	// StudentScoreDaoŬ���� �޼ҵ� ȣ�� ���� ��ü������������,����,�Ҵ�
	StudentDao studentDao = new StudentDao();					// StudentDaoŬ���� �޼ҵ� ȣ�� ���� ��ü������������,����,�Ҵ�
																// DB���̺� �� Student table�� student_no�÷��� ������ �ڽ�Ű�� ���� �����ϱ� ���Ͽ�
																// studentAddrDao,studentScoreDao��ü���������� �����Ͽ� �����޼ҵ带 ����.
	studentAddrDao.deleteStudentAddr(studentNo);				// �ڽ����̺��� ������ ���� ����
	studentScoreDao.deleteStudentScore(studentNo);				// �ڽ����̺��� ������ ���� ����
	studentDao.deleteStudent(studentNo);						// �θ����̺��� ������ ������ ����
	response.sendRedirect(request.getContextPath()+"/Student/studentList.jsp");
%>