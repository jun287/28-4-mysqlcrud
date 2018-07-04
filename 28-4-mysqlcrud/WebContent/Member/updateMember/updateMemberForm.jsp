<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.MemberDao" %>
<%@ page import="service.Member" %>
<!-- 2018.07.03 28기 전재현 -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>수정 화면</title>
	</head>
	<body>
		<%	
			int getMember_no = Integer.parseInt(request.getParameter("member_no"));
			System.out.println (getMember_no +"<- getMember_no");
			MemberDao memberDao = new MemberDao();
			Member member = memberDao.updateMemberSelect(getMember_no);
		%>
		<h3>멤버 등록 </h3>
		<form action="./updateMemberAction.jsp" method="post">
			<ul class="member_list">
				<li>
					<ul class="cols">
						<li><input type="hidden" name="memberNo" value="<%=member.getMemberNo()%>"></li>
						<li class="col1">이름: </li>
						<li class="col2"><input type="text" name="memberName" value="<%=member.getMemberName()%>"></li>
					</ul>
				</li>
				<li>
					<ul class="cols">
						<li class="col1">나이: </li>
						<li class="col2"><input type="text" name="memberAge" value="<%=member.getMemberAge()%>"></li>
						<li><input type="submit" value="수정"></li>
					</ul>
				</li>
			</ul>
		</form>
	</body>
</html>