<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="memberDAO.MemberScoreDao"%>
<%@ page import="memberDTO.MemberAndScore"%>
<%@ page import="java.util.ArrayList"%>
<!-- 2018.07.09 28기 전재현 -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
		<title>점수 리스트</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("EUC-KR");
			String memberName = request.getParameter("memberName");
			System.out.println(memberName +"<-memberName");
			MemberScoreDao memberScoreDao = new MemberScoreDao();
			ArrayList<MemberAndScore> totalList = memberScoreDao.selectMemberAndScore(memberName);
		%>
			<div id="main" align="center">
				<h3>점수 목록</h3>
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>이름</th>
							<th>점수</th>
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
					<a href="./memberList.jsp" class="btn">&nbsp;&nbsp;리스트페이지로</a>
			</div>
	</body>
</html>