<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>�ּ� �Է�â</title>
	</head>
	<body>
		<h2>�ּ� �Է�</h2>
		<form action="./insertMemberAddrAction.jsp?memberNo=<%=request.getParameter("memberNo")%>" method="post">
			<table>
				<tr>
					<td>�ּ� :</td>
					<td><input type="text" name="sendContent"></td>
					<td><a href="../listMember/memberList.jsp"></a></td>	<!-- �ǵ��ư��� -->
					<td><input type="submit" value="���"></td>
				</tr>
			</table>
		</form>
	</body>
</html>