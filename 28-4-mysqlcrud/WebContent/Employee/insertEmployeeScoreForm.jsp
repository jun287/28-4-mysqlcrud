<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			int no=Integer.parseInt(request.getParameter("no"));
			System.out.println(no+"<--no");
		%>
		<form action="./insertEmployeeScoreAction.jsp?no=<%=no %>" method="post">
			<div>
				<lable>����</lable>
				<input type="text" name="score">
				<input type="submit" value="�����Է�">
			</div>
		</form>
	</body>
</html>