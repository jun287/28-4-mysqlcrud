<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="../../css/member/insertMemberForm.css">
		<title>주소 입력창</title>
	</head>
	<body>
		<%
			int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		%>
		<div id="wrap">
			<br><br><br>
			<h3>주소 등록 </h3>
			<form action="./insertMemberAddrAction.jsp?memberNo=<%=memberNo%>" method="post">
				<table>
					<tr>
						<td id="title">주소 </td>
						<td><input type="text" name="sendContent" maxlength="50"></td>
					</tr>
				</table>
				<br><input type="submit" value="등록"> &nbsp;<input type="button" value="취소">
			</form>
		</div>
	</body>
</html>