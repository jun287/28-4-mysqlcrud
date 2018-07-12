<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="memberDto.MemberAndScore"%>
<%@ page import="memberDao.MemberScoreDao"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
		<title>��հ� ���</title>
	</head>
	<body>
		<%
			//���� ������ ���� �Դϴ�.
			int currentPage = 1;
			
			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			//���������� ���� �����Դϴ�.
			int pagePerRow = 5;
			
			int memberNo = 0;
			MemberScoreDao memberScoreDao = new MemberScoreDao();
			
			int average = memberScoreDao.MemberAverage();
			ArrayList<MemberAndScore> totalAverage = memberScoreDao.MemberAverageList(currentPage ,pagePerRow);
			
		%>
		<div align="center">
			<h3>���� ���</h3>
			<table>
				<thead>
					<tr>
						<th>��ȣ</th>
						<th>�̸�</th>
						<th>����</th>
						<th>������� :<%=average%></th>
					</tr>
				</thead>
				<%
					for(int i=0; i<totalAverage.size(); i++) {
						MemberAndScore memberAndScore = totalAverage.get(i);
				%>
						<tbody>
							<tr>
								<td><%=memberAndScore.getMemberNo()%></td>
								<td><a href="./memberAddrList.jsp"><%=memberAndScore.getMemberName()%></a></td>
								<td><%=memberAndScore.getScore()%></td>
							</tr>
						</tbody>
				<%
						memberNo = memberAndScore.getMemberNo();
					}
				%>
			</table>
				<%
					int lastPage = memberScoreDao.selectTotalList(pagePerRow);
					// memberDao������ ����ִ� �ּҰ��� ã�ư� CountMemberList()�޼���ȿ� pagePerRow�������� ������ ������ �ϰ� ������� int�������� ������ lsatPage������ �����߽��ϴ�.
					
					if(currentPage>1){
				%>
					<a href = "./memberListAboveAvg.jsp?currentPage=<%=currentPage-1%>">�� ����</a>
				<%
					}
					//currentPage�� 1���� ũ�� ���� a link�±װ� �������� ���� �߽��ϴ�.
					for(int j=1; j<=lastPage; j++) {
				%>
						<a href = "./memberListAboveAvg.jsp?currentPage=<%=j%>"><%=j%></a>
				<%
					}
					if(currentPage<lastPage){
				%>
						<a href = "./memberListAboveAvg.jsp?currentPage=<%=currentPage+1%>">���� ��</a>
				<%
					}
					//currentPage�� lastPage���� ������ a link�±װ� �������� �����߽��ϴ� 
				%>
				<a href="./memberList.jsp" class="btn">&nbsp;&nbsp;����Ʈ��������</a>
				<a href="../../index.jsp" class="btn">&nbsp;&nbsp;Ȩ��������</a>
		</div>
	</body>
</html>