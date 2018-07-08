<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.MemberAddrDao" %>
<%@ page import="service.MemberAddr" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>주소 수정 화면</title>
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
		<h2>주소 수정하기</h2>
		<form action="./updateMemberAddrAction.jsp?memberNo=<%=memberAddr.getMemberNo()%>" method="post">
			<table>
				<tr>
					<td>번호 :</td>
					<td><input type="text" name="sendContent" value="<%=memberAddr.getMemberAddrNo()%>"></td>
					<td>주소 :</td>
					<td><input type="text" name="sendContent" value="<%=memberAddr.getMemberAddrContent()%>"></td>
					<td><a href="../listMember/memberAddrList.jsp">목록으로</a></td>	<!-- 되돌아가기 -->
					<td><input type="submit" value="수정"></td>
				</tr>
			</table>
		</form>
	</body>
</html>