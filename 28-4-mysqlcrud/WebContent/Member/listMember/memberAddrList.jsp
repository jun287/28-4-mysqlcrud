<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.MemberAddrDao" %>
<%@ page import="service.MemberAddr" %>
<%@ page import="service.Member" %>
<%@ page import="java.util.ArrayList" %>
<!-- 2018.07.08 28기 전재현 -->
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>주소 목록 리스트</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("EUC-KR");
			//화면에서 념겨주는한글값 깨짐 방지 입니다.
			int memberNo = Integer.parseInt(request.getParameter("memberNo"));
			System.out.println(memberNo +"<- memberNo");
			MemberAddrDao memberAddrDao = new MemberAddrDao();
			
			String memberName = memberAddrDao.selectMemberName(memberNo);
			
			ArrayList<MemberAddr> totalMemberAddrList = memberAddrDao.listMemberSelect(memberName);

			
				
		%>
		<h2><%=memberName%>님 주소 리스트</h2>
		<table>
			<tr>
				<td>번호</td>
				<td>주소</td>
				<td>수정</td>
				<td>삭제</td>
			</tr> 
		<%
			for(int i=0; i<totalMemberAddrList.size(); i++) {
				MemberAddr memberAddr = totalMemberAddrList.get(i);
		%>
				<tr>
					<td><%=memberAddr.getMemberAddrNo()%></td>
					<td><%=memberAddr.getMemberAddrContent()%></td>
					<td><a href="../updateMemberAddr/updateMemberAddrForm.jsp?memberAddrNo=<%=memberAddr.getMemberAddrNo()%>">수정</a></td>
					<td><a href="../deleteMemberAddr/deleteMemberAddrAction.jsp?memberAddrNo=<%=memberAddr.getMemberAddrNo()%>">삭제</a></td>
				</tr>
		<%
			}
		%>
			<tr>
				<td><a href="./memberList.jsp">목록으로</a></td>
			</tr>
		</table>
	</body>
</html>