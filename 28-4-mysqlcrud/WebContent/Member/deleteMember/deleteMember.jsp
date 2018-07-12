<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="memberDAO.MemberDao" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>삭제 처리</title>
	</head>
	<body>
		<%
			int getMemberNo = Integer.parseInt(request.getParameter("memberNo"));
			MemberDao memberDao = new MemberDao();
			memberDao.deleteMember(getMemberNo);
			
			response.sendRedirect("../listMember/memberList.jsp");
		%>
	</body>
</html>