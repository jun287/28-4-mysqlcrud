<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!-- 2018.07.09. 28기 전재현 -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="../../css/member/insertMemberForm.css">
		<title>Score입력 화면</title>
	</head>
	<body>
		<%
			int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		%>
		<div id="wrap">
			<br><br><br>
			<h3>점수 등록 </h3>
			<form action="./insertMemberScoreAction.jsp?memberNo=<%=memberNo%>" method="post">
				<table>
					<tr>
						<td id="title">점수 </td>
						<td><input type="text" name="memberScore"  placeholder="score..." maxlength="5"></td>
					</tr>
				</table>
				<br><input type="submit" value="등록"> &nbsp;<input type="button" value="취소">
			</form>
		</div>
	</body>
</html>