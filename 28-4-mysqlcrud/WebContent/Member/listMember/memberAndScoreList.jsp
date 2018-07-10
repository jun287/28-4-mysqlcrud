<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.MemberScoreDao"%>
<%@ page import="service.MemberAndScore"%>
<%@ page import="java.util.ArrayList"%>
<!-- 2018.07.09 28기 전재현 -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link  rel="stylesheet" type="text/css" href="../../css/member/css/bootstrap.css">
		<title>점수 리스트</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("EUC-KR");
			int memberNo = Integer.parseInt(request.getParameter("memberNo"));
			System.out.println(memberNo +"<-memberNo");
			MemberScoreDao memberScoreDao = new MemberScoreDao();
			ArrayList<MemberAndScore> totalList = memberScoreDao.selectMemberAndScore(memberNo);
		%>
			<div class="container">
				<h3>점수 목록</h3>
				<table class="table table-hover">
					<thead>
						<tr>
							<td>번호</td>
							<td>이름</td>
							<td>점수</td>
						</tr>
					</thead>
					<%
						for(int i=0; i<totalList.size(); i++) {
							MemberAndScore memberAndScore = totalList.get(i);
						
					%>
							<tbody>
								<tr>
									<td><%=memberAndScore.getMemberNo()%></td>
									<td><%=memberAndScore.getMemberName()%></td>
									<td><%=memberAndScore.getScore()%></td>
								</tr>
							</tbody>
					<%
						}
					%>
				</table>
				<div class="text-center">
					<a href="./memberList.jsp" class="btn">&nbsp;&nbsp;리스트페이지로</a>
				</div>
			</div>
	</body>
</html>