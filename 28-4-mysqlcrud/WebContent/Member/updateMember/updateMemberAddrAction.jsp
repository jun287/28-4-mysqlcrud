<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="memberDTO.MemberAddr"%>
<%@ page import="memberDAO.MemberAddrDao" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>���� ó�� ȭ��</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("EUC-KR");
			
			int getMemberAddrNo = Integer.parseInt(request.getParameter("sendAddrNo"));
			String getMemberAddrContent = request.getParameter("sendAddrContent");
			
			MemberAddr memberAddr = new MemberAddr();
			
			memberAddr.setMemberAddrContent(getMemberAddrContent);
			memberAddr.setMemberAddrNo(getMemberAddrNo);
			System.out.println(memberAddr +"<-memberAddr");
			
			MemberAddrDao memberAddrDao = new MemberAddrDao();
			int updateResult = memberAddrDao.updateMemberAddr(memberAddr);
			
			if(updateResult == 0) {
				System.out.println("���� ����");
				response.sendRedirect("../listMember/memberList.jsp");
			}else {
				System.out.println("���� ����");
				response.sendRedirect("../listMember/memberList.jsp");
			}
			
			
		%>
	</body>
</html>