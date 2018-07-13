<!-- 2018.07.02 28�� ������ -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="memberDTO.MemberAndScore" %>
<%@ page import="memberDAO.MemberDao" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
		<title>ȸ�� ���� ����Ʈ ���â</title>
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
				System.out.println(searchWord +"<- (memberList)searchWord");
			}
			
			int pagePerRow = 5;
			System.out.println(pagePerRow +"<- pagePerRow");
			MemberDao memberDao = new MemberDao();
			
			/*
				�� �������� ���� ���������� �˻��� �������� ó���ϱ� ���� �޼���ȿ� ������ ��� ������ ���׽��ϴ�.
			*/
			ArrayList<MemberAndScore> getTotalList = memberDao.selectMemberByPage(currentPage ,pagePerRow ,searchWord);
			System.out.println(getTotalList +"<- getTotalList");
			
		%>
		<div align="center">
			<h2>��� ����Ʈ</h2><br>
			<form action="./memberList.jsp" method="post">
				<!-- placeholder=>text �ڽ��� �۾��� ���̵��� ���� -->
				<input type="text" name="searchWord" placeholder="�̸��˻�...">
				
				<button type="submit">�˻�</button>
			</form>
			<table>
				<tr>
					<th>��ȣ </th>
					<th>�̸� </th>
					<th>���� </th>
					<th>�ּ��Է�</th>
					<th>���� �Է�</th>
					<th>���� ����</th>
					<th>�����ϱ�</th>
					<th>�����ϱ�</th>
				</tr> 
				<%
					for(int i=0; i<getTotalList.size(); i++){
						MemberAndScore memberJoinList = getTotalList.get(i);
					
				%>
						<tr>
							<td><%=memberJoinList.getMemberNo()%></td>
							<td><a href="./memberAddrList.jsp?memberNo=<%=memberJoinList.getMemberNo()%>"><%=memberJoinList.getMemberName()%></a></td>
							<td><%=memberJoinList.getMemberAge()%></td>
							<td><a href="../insertMember/insertMemberAddrForm.jsp?memberNo=<%=memberJoinList.getMemberNo()%>">�ּ��Է�</a></td>
							<td><a href="../insertMember/insertMemberScoreForm.jsp?memberNo=<%=memberJoinList.getMemberNo()%>">���� ���</a></td>
							<%
								if(memberJoinList.getScore() != 0) {
									%>
										<td><a href="../listMember/memberAndScoreList.jsp?memberName=<%=memberJoinList.getMemberName()%>">���� ����</a></td>
									<%
								}else {
									%>
										<td><a href="../insertMember/insertMemberScoreForm.jsp?memberNo=<%=memberJoinList.getMemberNo()%>">���� �Է�</a></td>
									<%
								}
							%>
							<td><a href="../deleteMember/deleteMember.jsp?memberNo=<%=memberJoinList.getMemberNo()%>">�����ϱ�</a></td>
							<td><a href="../updateMember/updateMemberForm.jsp?memberNo=<%=memberJoinList.getMemberNo()%>">�����ϱ�</a></td>
						</tr>
				<%
					}
					
				%>
			</table>
			
				<%
					int totalList = memberDao.countMemberList(pagePerRow ,searchWord);
					System.out.println(totalList +"<- totalList");
					if(currentPage>1){
				%>
						<a href = "./memberList.jsp?currentPage=<%=currentPage-1%>&searchWord=<%=searchWord%>">�� ����</a>
				<%
					}
					for(int j=1; j<=totalList; j++) {
				%>
						<a href = "./memberList.jsp?currentPage=<%=j%>&searchWord=<%=searchWord%>"><%=j%></a>
				<%
					}
					if(currentPage<totalList){
				%>
						<a href = "./memberList.jsp?currentPage=<%=currentPage+1%>&searchWord=<%=searchWord%>">���� ��</a>
				<%
					}
				%>
				<a href="../../index.jsp" class="btn btn-default">&nbsp;&nbsp;Ȩ��������</a>
		</div>
	</body>
</html>