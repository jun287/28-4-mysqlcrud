<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.MemberDao" %>
<%@ page import="service.Member" %>
<!-- 2018.07.03 28�� ������ -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>���� ȭ��</title>
	</head>
	<body>
		<%	
			int getMember_no = Integer.parseInt(request.getParameter("member_no"));
			System.out.println (getMember_no +"<- getMember_no");
			MemberDao memberDao = new MemberDao();
			Member member = memberDao.updateMemberSelect(getMember_no);
		%>
		<h3>��� ��� </h3>
		<form action="./updateMemberAction.jsp" method="post">
			<ul class="member_list">
				<li>
					<ul class="cols">
						<li><input type="hidden" name="memberNo" value="<%=member.getMemberNo()%>"></li>
						<li class="col1">�̸�: </li>
						<li class="col2"><input type="text" name="memberName" value="<%=member.getMemberName()%>"></li>
					</ul>
				</li>
				<li>
					<ul class="cols">
						<li class="col1">����: </li>
						<li class="col2"><input type="text" name="memberAge" value="<%=member.getMemberAge()%>"></li>
						<li><input type="submit" value="����"></li>
					</ul>
				</li>
			</ul>
		</form>
	</body>
</html>