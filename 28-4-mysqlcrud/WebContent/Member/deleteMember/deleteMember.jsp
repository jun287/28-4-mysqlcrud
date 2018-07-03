<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.MemberDao" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>삭제 처리 jap</title>
	</head>
	<body>
		<%
			int getMember_no = Integer.parseInt(request.getParameter("member_no"));
			MemberDao memberDao = new MemberDao();
			memberDao.deleteMember(getMember_no);
		%>
	</body>
</html>