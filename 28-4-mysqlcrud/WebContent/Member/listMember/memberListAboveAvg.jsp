<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="memberDto.MemberAndScore"%>
<%@ page import="memberDao.MemberScoreDao"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
		<title>평균값 출력</title>
	</head>
	<body>
		<%
			//현재 페이지 설정 입니다.
			int currentPage = 1;
			
			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			//한페이지에 나올 갯수입니다.
			int pagePerRow = 5;
			
			int memberNo = 0;
			MemberScoreDao memberScoreDao = new MemberScoreDao();
			
			int average = memberScoreDao.MemberAverage();
			ArrayList<MemberAndScore> totalAverage = memberScoreDao.MemberAverageList(currentPage ,pagePerRow);
			
		%>
		<div align="center">
			<h3>점수 목록</h3>
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>이름</th>
						<th>점수</th>
						<th>평균점수 :<%=average%></th>
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
					// memberDao변수에 들어있는 주소값을 찾아가 CountMemberList()메서드안에 pagePerRow변수값을 가지고 실행을 하고 결과값을 int형식으로 선언한 lsatPage변수에 대입했습니다.
					
					if(currentPage>1){
				%>
					<a href = "./memberListAboveAvg.jsp?currentPage=<%=currentPage-1%>">◀ 이전</a>
				<%
					}
					//currentPage가 1보다 크면 이전 a link태그가 나오도록 설정 했습니다.
					for(int j=1; j<=lastPage; j++) {
				%>
						<a href = "./memberListAboveAvg.jsp?currentPage=<%=j%>"><%=j%></a>
				<%
					}
					if(currentPage<lastPage){
				%>
						<a href = "./memberListAboveAvg.jsp?currentPage=<%=currentPage+1%>">다음 ▶</a>
				<%
					}
					//currentPage가 lastPage보다 작으면 a link태그가 나오도록 설정했습니다 
				%>
				<a href="./memberList.jsp" class="btn">&nbsp;&nbsp;리스트페이지로</a>
				<a href="../../index.jsp" class="btn">&nbsp;&nbsp;홈페이지로</a>
		</div>
	</body>
</html>