<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="memberDao.MemberDao" %>
<!-- 2018.07.03 28기 전재현 -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>수정 처리 화면</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("EUC-KR");
			
			int getMemberNo = Integer.parseInt(request.getParameter("memberNo"));
			int getMemberAge = Integer.parseInt(request.getParameter("memberAge"));
			String getMemberName =	request.getParameter("memberName");
			System.out.println(getMemberNo +"<- MemberNO");
			System.out.println(getMemberName +"<- MemberName");
			System.out.println(getMemberAge +"<- MemberAge");
			
			MemberDao memberDao = new MemberDao();
			memberDao.updateMember(getMemberNo ,getMemberName ,getMemberAge);
			
			response.sendRedirect("../listMember/memberList.jsp");
		%>
	</body>
</html>