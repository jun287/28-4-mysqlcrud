<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!-- 2018.07.09. 28�� ������ -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="../../css/member/insertMemberForm.css">
		<title>Score�Է� ȭ��</title>
	</head>
	<body>
		<%
			int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		%>
		<div id="wrap">
			<br><br><br>
			<h3>���� ��� </h3>
			<form action="./insertMemberScoreAction.jsp?memberNo=<%=memberNo%>" method="post">
				<table>
					<tr>
						<td id="title">���� </td>
						<td><input type="text" name="memberScore"  placeholder="score..." maxlength="5"></td>
					</tr>
				</table>
				<br><input type="submit" value="���"> &nbsp;<input type="button" value="���">
			</form>
		</div>
	</body>
</html>