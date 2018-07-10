<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.MemberAndScore"%>
<%@ page import="service.MemberScoreDao"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="../../css/member/css/bootstrap.css">
		<title>��հ� ���</title>
	</head>
	<body>
		<%
			int currentPage = 1;
			//���� ������ ���� �Դϴ�.
			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			int pagePerRow = 5;
			//���������� ���� �����Դϴ�.
			
			MemberScoreDao memberScoreDao = new MemberScoreDao();
			int average = memberScoreDao.MemberAverage();
			ArrayList<MemberAndScore> totalAverage = memberScoreDao.MemberAverageList(currentPage ,pagePerRow);
			
		%>
		<div class="container">
			<h3>���� ���</h3>
			<table class="table table-hover">
				<thead>
					<tr>
						<td>��ȣ</td>
						<td>�̸�</td>
						<td>����</td>
						<td>������� :<%=average%></td>
					</tr>
				</thead>
				<%
					for(int i=0; i<totalAverage.size(); i++) {
						MemberAndScore memberAndScore = totalAverage.get(i);
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
				<%
					int lastPage = memberScoreDao.selectTotalList(pagePerRow);
					// memberDao������ ����ִ� �ּҰ��� ã�ư� CountMemberList()�޼���ȿ� pagePerRow�������� ������ ������ �ϰ� ������� int�������� ������ lsatPage������ �����߽��ϴ�.
					
					if(currentPage>1){
				%>
					<a href = "./memberListAboveAvg.jsp?currentPage=<%=currentPage-1%>">�� ����</a>
				<%
					}
					//currentPage�� 1���� ũ�� ���� a link�±װ� �������� ���� �߽��ϴ�.
		
					if(currentPage<lastPage){
				%>
						<a href = "./memberListAboveAvg.jsp?currentPage=<%=currentPage+1%>">���� ��</a>
				<%
					}
					//currentPage�� lastPage���� ������ a link�±װ� �������� �����߽��ϴ� 
				%>
				<a href="./memberList.jsp" class="btn">&nbsp;&nbsp;����Ʈ��������</a>
			</div>
		</div>
	</body>
</html>