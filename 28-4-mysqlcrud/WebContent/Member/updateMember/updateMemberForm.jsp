<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="memberDao.MemberDao" %>
<%@ page import="memberDto.Member" %>
<!-- 2018.07.03 28기 전재현 -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="../../css/member/insertMemberForm.css">
		<title>수정 화면</title>
	</head>
	<body>
		<%	
			int getMemberNo = Integer.parseInt(request.getParameter("memberNo"));
			System.out.println (getMemberNo +"<- getMemberNo");
			MemberDao memberDao = new MemberDao();
			Member member = memberDao.updateMemberSelect(getMemberNo);
		%>
		<div id="wrap">
			<br><br><br>
			<h3>멤버 수정 </h3>
			<form action="./updateMemberAction.jsp?memberNo=<%=getMemberNo%>" method="post">
				<table>
					<tr>
						<td id="title">이름 </td>
						<td><input type="text" name="memberName" value="<%=member.getMemberName()%>" maxlength="10" readonly></td>
					</tr>
					<tr>
						<td id="title">나이 </td>
						<td><input type="text" name="memberAge" value="<%=member.getMemberAge()%>" maxlength="3"></td>
					</tr>
				</table>
				<br><input type="submit" value="수정"> &nbsp;<input type="button" value="취소">
			</form>
		</div>
	</body>
</html>