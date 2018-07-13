<!-- 2018. 7. 3(화) 이원상 insertStudentAddrAction.jsp -->
<%@ page language = "java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "StudentDAO.StudentAddrDao" %>
<%@ page import = "StudentDTO.StudentAddr" %>
<%
	request.setCharacterEncoding("euc-kr");
	StudentAddr studentAddr = new StudentAddr();
	studentAddr.setStudentNO(Integer.parseInt(request.getParameter("studentNo")));
	studentAddr.setStudentAddrContent(request.getParameter("studentAddrContent"));
	// studentAddr 객체참조변수에 Form에서 받아온 parameter Setting
	
	StudentAddrDao studentAddrDao = new StudentAddrDao();	//StudentAddrDao클래스 메소드 사용을 위한 객체참조변수 선언,생성,할당
	studentAddrDao.insertStudentAddress(studentAddr);
	response.sendRedirect(request.getContextPath()+"/Student/studentAddrList.jsp?studentNo="+studentAddr.getStudentNO());
%>