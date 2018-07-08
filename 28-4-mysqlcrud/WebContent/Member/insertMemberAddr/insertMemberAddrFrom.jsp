<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>주소 입력창</title>
	</head>
	<body>
		<h2>주소 입력</h2>
		<form action="./insertMemberAddrAction.jsp?memberNo=<%=request.getParameter("memberNo")%>" method="post">
			<table>
				<tr>
					<td>주소 :</td>
					<td><input type="text" name="sendContent"></td>
					<td><a href="../listMember/memberList.jsp"></a></td>	<!-- 되돌아가기 -->
					<td><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
	</body>
</html>