<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.MemberScore" %>
<%@ page import="service.MemberScoreDao" %>
<!-- 2018.07.09 28기 전재현 -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>득롱 화면 처리</title>
	</head>
	<body>
		<%
			int memberScore = Integer.parseInt(request.getParameter("memberScore"));
			int memberNo = Integer.parseInt(request.getParameter("memberNo"));
			System.out.println(memberNo +"<- memberNO");
			System.out.println(memberScore +"<- memberScore");
		
			MemberScoreDao memberScoreDao = new MemberScoreDao();
			int insertCheck = memberScoreDao.insertMember(memberNo ,memberScore);
			System.out.println(insertCheck +"<- insertCheck");
			if(insertCheck != 0) {
				System.out.println(insertCheck +"<- 저장 완료 되었습니다");
				
			}else {
				System.out.println(insertCheck +"<- 수정 완료 되었습니다");
				
			}
			response.sendRedirect("../listMember/memberList.jsp");
		%>
	</body>
</html>