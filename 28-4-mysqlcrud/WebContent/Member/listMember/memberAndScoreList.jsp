<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="memberDAO.MemberScoreDao"%>
<%@ page import="memberDTO.MemberAndScore"%>
<%@ page import="java.util.ArrayList"%>
<!-- 2018.07.09 28�� ������ -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
		<title>���� ����Ʈ</title>
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
				<h3>���� ���</h3>
				<table>
					<thead>
						<tr>
							<th>��ȣ</th>
							<th>�̸�</th>
							<th>����</th>
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
					<a href="./memberList.jsp" class="btn">&nbsp;&nbsp;����Ʈ��������</a>
			</div>
	</body>
</html>