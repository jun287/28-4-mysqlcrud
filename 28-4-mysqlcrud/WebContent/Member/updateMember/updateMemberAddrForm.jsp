<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="memberDao.MemberAddrDao" %>
<%@ page import="memberDto.MemberAddr" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="../../css/member/insertMemberForm.css">
		<title>�ּ� ���� ȭ��</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("EUC-KR");
			
			int memberAddrNo = Integer.parseInt(request.getParameter("memberAddrNo"));
			System.out.println(memberAddrNo +"<-memberAddrNo");
			MemberAddrDao memberAddrDao = new MemberAddrDao();
			MemberAddr memberAddr = new MemberAddr();
			
			memberAddr = memberAddrDao.selectMemberAddrList(memberAddrNo);
			
		%>
			<div id="wrap">
				<br><br><br>
				<h3>��� ���� </h3>
				<form action="./updateMemberAddrAction.jsp?memberNo=<%=memberAddr.getMemberNo()%>" method="post">
					<table>
						<tr>
							<td id="title">��ȣ </td>
							<td><input type="text" name="sendAddrNo" value="<%=memberAddr.getMemberAddrNo()%>" readonly></td>
						</tr>
						<tr>
							<td id="title">�ּ� </td>
							<td><input type="text" name="sendAddrContent" value="<%=memberAddr.getMemberAddrContent()%>" maxlength="50"></td>
						</tr>
					</table>
					<br><input type="submit" value="����"> &nbsp;<input type="button" value="���">
				</form>
			</div>
	</body>
</html>