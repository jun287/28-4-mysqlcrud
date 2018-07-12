<!-- 2018.07.02 28�� ������ -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="memberDto.Member" %>
<%@ page import="memberDao.MemberDao" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<link rel="stylesheet" type="text/css" href="../../css/member/css/bootstrap.css">
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
				System.out.println(searchWord +"<- (if)searchWord");
			}
			
			int pagePerRow = 5;
			System.out.println(pagePerRow +"<- pagePerRow");
			MemberDao memberDao = new MemberDao();
			
			/*
				�� �������� ���� ���������� �˻��� �������� ó���ϱ� ���� �޼���ȿ� ������ ��� ������ ���׽��ϴ�.
			*/
			
			ArrayList<Member> getTotalList = memberDao.selectMemberByPage(currentPage ,pagePerRow ,searchWord);
			System.out.println(getTotalList +"<- getTotalList");
			
		%>
		<div class="container">
			<h2>��� ����Ʈ</h2>
			<form action="./memberList.jsp" method="post" class="text-right">
			
				<!-- placeholder=>text �ڽ��� �۾��� ���̵��� ���� -->
				<input type="text" name="searchWord" placeholder="Search...">
				
				<button type="submit">�˻�</button>
			</form><br>
			<table class="table table-hover">
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
						Member member = getTotalList.get(i);
					
				%>
						<tr>
							<td><%=member.getMemberNo()%></td>
							<td><a href="./memberAddrList.jsp?memberNo=<%=member.getMemberNo()%>"><%=member.getMemberName()%></a></td>
							<td><%=member.getMemberAge()%></td>
							<td><a href="../insertMember/insertMemberAddrForm.jsp?memberNo=<%=member.getMemberNo()%>">�ּ��Է�</a></td>
							<td><a href="../insertMember/insertMemberScoreForm.jsp?memberNo=<%=member.getMemberNo()%>">���� ���</a></td>
							<td><a href="../listMember/memberAndScoreList.jsp?memberName=<%=member.getMemberName()%>">���� ����</a></td>
							<td><a href="../deleteMember/deleteMember.jsp?memberNo=<%=member.getMemberNo()%>">�����ϱ�</a></td>
							<td><a href="../updateMember/updateMemberForm.jsp?memberNo=<%=member.getMemberNo()%>">�����ϱ�</a></td>
						</tr>
				<%
					}
					
				%>
			</table>
			<div class="text-center">
				<%
					int totalList = memberDao.countMemberList(pagePerRow ,searchWord);
					System.out.println(totalList +"<- totalList");
					if(currentPage>1){
				%>
						<a href = "./memberList.jsp?currentPage=<%=currentPage-1%>&searchWord=<%=searchWord%>" class="btn">�� ����</a>
				<%
					}
					for(int j=1; j<=totalList; j++) {
				%>
						<a href = "./memberList.jsp?currentPage=<%=j%>&searchWord=<%=searchWord%>" class="btn"><%=j%></a>
				<%
					}
					if(currentPage<totalList){
				%>
						<a href = "./memberList.jsp?currentPage=<%=currentPage+1%>&searchWord=<%=searchWord%>" class="btn">���� ��</a>
				<%
					}
				%>
				<a href="../../index.jsp" class="btn btn-default">&nbsp;&nbsp;Ȩ��������</a>
			</div>
		</div>
	</body>
</html>