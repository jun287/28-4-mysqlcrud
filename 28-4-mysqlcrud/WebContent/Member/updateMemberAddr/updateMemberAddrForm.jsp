<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.MemberAddrDao" %>
<%@ page import="service.MemberAddr" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
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
		<h2>�ּ� �����ϱ�</h2>
		<form action="./updateMemberAddrAction.jsp?memberNo=<%=memberAddr.getMemberNo()%>" method="post">
			<table>
				<tr>
					<td>��ȣ :</td>
					<td><input type="text" name="sendContent" value="<%=memberAddr.getMemberAddrNo()%>"></td>
					<td>�ּ� :</td>
					<td><input type="text" name="sendContent" value="<%=memberAddr.getMemberAddrContent()%>"></td>
					<td><a href="../listMember/memberAddrList.jsp">�������</a></td>	<!-- �ǵ��ư��� -->
					<td><input type="submit" value="����"></td>
				</tr>
			</table>
		</form>
	</body>
</html>