<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="memberDAO.MemberAddrDao"%>
<!-- 2018.07.08. 28기 전재현 -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>삭제 처리</title>
	</head>
	<body>
		<%
			int memberAddrNo = Integer.parseInt(request.getParameter("memberAddrNo"));
		
			MemberAddrDao memberAddrDao = new MemberAddrDao();
			memberAddrDao.deleteMemberAddr(memberAddrNo);
			
			response.sendRedirect("../listMember/memberList.jsp");
			
		%>
	</body>
</html>