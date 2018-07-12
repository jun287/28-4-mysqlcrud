<!-- 2018.07.02 28기 전재현 -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="memberDTO.Member" %>
<%@ page import="memberDAO.MemberDao" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
		<title>회원 정보 리스트 출력창</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("euc-kr");
		
			
			int currentPage = 1;
			System.out.println(currentPage +"<- currentPage");
			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			
			String searchWord = "";
			System.out.println(searchWord +"<- (String)searchWord");
			if(request.getParameter("searchWord") != null) {
				searchWord = request.getParameter("searchWord");
				System.out.println(searchWord +"<- (if)searchWord");
			}
			
			int pagePerRow = 5;
			System.out.println(pagePerRow +"<- pagePerRow");
			MemberDao memberDao = new MemberDao();
			
			/*
				몇 페이지당 나올 갯수설정과 검색시 실행결과를 처리하기 위해 메서드안에 변수를 담아 실행을 시켰습니다.
			*/
			
			ArrayList<Member> getTotalList = memberDao.selectMemberByPage(currentPage ,pagePerRow ,searchWord);
			System.out.println(getTotalList +"<- getTotalList");
			
		%>
		<div align="center">
			<h2>멤버 리스트</h2><br>
			<table>
				<tr>
					<th>번호 </th>
					<th>이름 </th>
					<th>나이 </th>
					<th>주소입력</th>
					<th>점수 입력</th>
					<th>점수 보기</th>
					<th>삭제하기</th>
					<th>수정하기</th>
				</tr> 
				<%
					for(int i=0; i<getTotalList.size(); i++){
						Member member = getTotalList.get(i);
					
				%>
						<tr>
							<td><%=member.getMemberNo()%></td>
							<td><a href="./memberAddrList.jsp?memberNo=<%=member.getMemberNo()%>"><%=member.getMemberName()%></a></td>
							<td><%=member.getMemberAge()%></td>
							<td><a href="../insertMember/insertMemberAddrForm.jsp?memberNo=<%=member.getMemberNo()%>">주소입력</a></td>
							<td><a href="../insertMember/insertMemberScoreForm.jsp?memberNo=<%=member.getMemberNo()%>">점수 등록</a></td>
							<td><a href="../listMember/memberAndScoreList.jsp?memberName=<%=member.getMemberName()%>">점수 보기</a></td>
							<td><a href="../deleteMember/deleteMember.jsp?memberNo=<%=member.getMemberNo()%>">삭제하기</a></td>
							<td><a href="../updateMember/updateMemberForm.jsp?memberNo=<%=member.getMemberNo()%>">수정하기</a></td>
						</tr>
				<%
					}
					
				%>
			</table>
			<form action="./memberList.jsp" method="post">
				<!-- placeholder=>text 박스안 글씨가 보이도록 설정 -->
				<input type="text" name="searchWord" placeholder="Search...">
				
				<button type="submit">검색</button>
			</form>
				<%
					int totalList = memberDao.countMemberList(pagePerRow ,searchWord);
					System.out.println(totalList +"<- totalList");
					if(currentPage>1){
				%>
						<a href = "./memberList.jsp?currentPage=<%=currentPage-1%>&searchWord=<%=searchWord%>" class="btn">◀ 이전</a>
				<%
					}
					for(int j=1; j<=totalList; j++) {
				%>
						<a href = "./memberList.jsp?currentPage=<%=j%>&searchWord=<%=searchWord%>" class="btn"><%=j%></a>
				<%
					}
					if(currentPage<totalList){
				%>
						<a href = "./memberList.jsp?currentPage=<%=currentPage+1%>&searchWord=<%=searchWord%>" class="btn">다음 ▶</a>
				<%
					}
				%>
				<a href="../../index.jsp" class="btn btn-default">&nbsp;&nbsp;홈페이지로</a>
		</div>
	</body>
</html>