<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.Member" %>
<%@ page import="service.MemberDao" %>
<!-- 2018.07.02 28기 전재현 -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>회원 정보 List출력창</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("euc-kr");
			
			int currentPage = 1; //현재 페이지를 1로 설정했습니다.
			System.out.println(currentPage +"<- currentPage");
			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			
			
			int pagePerRow = 5; //한 페이지에 나오는 갯수를 설정입니다.
			System.out.println(pagePerRow +"<- pagePerRow");
			MemberDao memberDao = new MemberDao();
			ArrayList<Member> get_totalList = memberDao.selectMemberByPage(currentPage ,pagePerRow);
			System.out.println(get_totalList +"<- totalList");
			//memberDao변수에 들어있는 주소값을 찾아가 selectMemberByPage메서드안에 들어있는 currentPage변수와 pagePerRow변수에 값을 대입후 실행을 합니다
			//실행후 return값을 Member클래스 타입의 ArrayList으로 선언을 한 get_list변수에 할당을 했습니다. 
		%>
		<h2>멤버 리스트</h2>
		<table>
			<tr>
				<th>번호 </th>
				<th>이름 </th>
				<th>나이 </th>
				<th>삭제하기</th>
				<th>수정하기</th>
			</tr> 
		<%
			for(int i=0; i<get_totalList.size(); i++){
				Member member = get_totalList.get(i);
		%>
				<tr>
					<th><%=member.getMemberNo()%></th>
					<th><a href="./memberAddrList.jsp"><%=member.getMemberName()%></a></th>
					<th><%=member.getMemberAge()%></th>
					<th><a href="../delete/deleteMemberAction.jsp?member_no=<%=member.getMemberNo()%>">삭제</a></th>
					<th><a href="../update/updateMemberForm.jsp?member_no=<%=member.getMemberNo()%>">수정</a></th>
				</tr>
				<!-- member변수에 들어있는 주소값을 찾아가 getMemberXx()메서드를 실행을해 값을 가져와 대입을 시켰습니다. -->
		<%
			}
		%>
		</table>
		<form>
			<!-- 이름 :  -->
			<input type="text" name="searchWord">
			<button type="submit">검색</button>
		</form>
		<%
			int lastPage = memberDao.CountMemberList(pagePerRow);

			if(currentPage>1){
				%>
				<a href = "./MemberList.jsp?currentPage=<%=currentPage-1%>">◀ 이전</a>
				<%
			}

			if(currentPage<lastPage){
				%>
				<a href = "./MemberList.jsp?currentPage=<%=currentPage+1%>">다음 ▶</a>
				<%
			}
		%>
	</body>
</html>