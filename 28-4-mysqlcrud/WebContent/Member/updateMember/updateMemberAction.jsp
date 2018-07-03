<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.MemberDao" %>
<!-- 2018.07.03 28기 전재현 -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>수정 처리 화면</title>
	</head>
	<body>
		<%
			String getMember_name =	request.getParameter("member_name");
			int getMember_age = Integer.parseInt(request.getParameter("member_age"));
			int getMember_no = Integer.parseInt(request.getParameter("member_no"));
			System.out.println(getMember_no +"<- Member_no");
			System.out.println(getMember_name +"<- Member_name");
			System.out.println(getMember_age +"<- Member_age");
			
			MemberDao memberDao = new MemberDao();
			memberDao.updateMemberDetail(getMember_no ,getMember_name ,getMember_age);
			
			response.sendRedirect("../ListMember/memberList.jsp");
		%>
	</body>
</html>