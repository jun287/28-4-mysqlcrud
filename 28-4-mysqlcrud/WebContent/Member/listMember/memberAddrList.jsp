<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="memberDao.MemberAddrDao" %>
<%@ page import="memberDto.MemberAddr" %>
<%@ page import="memberDto.Member" %>
<%@ page import="java.util.ArrayList" %>
<!-- 2018.07.08 28�� ������ -->
<!DOCTYPE html>
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
		<title>�ּ� ��� ����Ʈ</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("EUC-KR");
			//ȭ�鿡�� ����ִ��ѱ۰� ���� ���� �Դϴ�.
			int memberNo = Integer.parseInt(request.getParameter("memberNo"));
			System.out.println(memberNo +"<- memberNo");
			MemberAddrDao memberAddrDao = new MemberAddrDao();
			
			String memberName = memberAddrDao.selectMemberName(memberNo);
			
			ArrayList<MemberAddr> totalMemberAddrList = memberAddrDao.listMemberSelect(memberName);

			
				
		%>
		<div align="center">
			<h2><%=memberName%>�� �ּ� ����Ʈ</h2>
			<table>
				<tr>
					<td>��ȣ</td>
					<td>�ּ�</td>
					<td>��� �Ⱓ</td>
					<td>����</td>
					<td>����</td>
				</tr> 
				<%
					for(int i=0; i<totalMemberAddrList.size(); i++) {
						MemberAddr memberAddr = totalMemberAddrList.get(i);
				%>
						<tr>
							<td><%=memberAddr.getMemberAddrNo()%></td>
							<td><%=memberAddr.getMemberAddrContent()%></td>
							<td><%=memberAddr.getMemberAddrDate()%></td>
							<td><a href="../updateMember/updateMemberAddrForm.jsp?memberAddrNo=<%=memberAddr.getMemberAddrNo()%>">����</a></td>
							<td><a href="../deleteMember/deleteMemberAddrAction.jsp?memberAddrNo=<%=memberAddr.getMemberAddrNo()%>">����</a></td>
						</tr>
				<%
					}
				%>
			</table>
				<a href="./memberList.jsp">�������</a>
		</div>
	</body>
</html>