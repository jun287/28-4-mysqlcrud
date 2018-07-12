<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!-- 2018.06.26. 28기 전재현. -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
		<title>member등록 화면</title>
	</head>
	<body>
		<div align="center">
			<br><br><br>
			<h3>멤버 등록 </h3>
			<form action="./insertMemberAction.jsp" method="post">
				<table>
					<tr>
						<td>이름 </td>
						<td><input type="text" name="member_name"  placeholder="5글자 이내..." maxlength="5"></td>
					</tr>
					<tr>
						<td>나이 </td>
						<td><input type="text" name="member_age"  placeholder="3자릿수이내..." maxlength="3"></td>
					</tr>
				</table>
				<br><input type="submit" value="등록">
				<input type="button" value="취소">
			</form>
		</div>
	</body>
</html>