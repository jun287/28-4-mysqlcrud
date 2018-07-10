<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.Member" %>
<%@ page import="service.MemberDao" %>
<!-- 2018.07.02 28�� ������ -->
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
		
			
			int currentPage = 1; //���� �������� 1�� �����߽��ϴ�.
			System.out.println(currentPage +"<- currentPage");
			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			
			String word = request.getParameter("searchWord");
			System.out.println(word +"<- word");
			int pagePerRow = 5; //�� �������� ������ ������ �����Դϴ�.
			System.out.println(pagePerRow +"<- pagePerRow");
			MemberDao memberDao = new MemberDao();
			ArrayList<Member> getTotalList = memberDao.selectMemberByPage(currentPage ,pagePerRow ,word);
			System.out.println(getTotalList +"<- getTotalList");
			//memberDao������ ����ִ� �ּҰ��� ã�ư� selectMemberByPage�޼���ȿ� ����ִ� �ŰԺ����� ����ִ� ���� ������ ������ �մϴ�
			//������ return���� MemberŬ���� Ÿ���� ArrayList���� ������ �� get_list������ �Ҵ��� �߽��ϴ�. 
		%>
		<div class="container">
			<h2>��� ����Ʈ</h2>
			<form action="./memberList.jsp" method="post">
				<!-- �̸� :  -->
				<input type="text" name="searchWord" placeholder="Search...">
				<!-- placeholder=>text�� �۾��� ���̵��� ���� -->
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
					//int������ i������ get_totalList������ ����ִ� indexũ�⺸�� ���������� �ݺ��� �����߽��ϴ�.
						Member member = getTotalList.get(i);
						//get_totalList������ �ִ� index���� i������ ����ִ� ������ �ҷ���  MemberŬ������ ���� ����� member������ ������ �Ѵ�.
				%>
						<tr>
							<td><%=member.getMemberNo()%></td>
							<td><a href="./memberAddrList.jsp?memberNo=<%=member.getMemberNo()%>"><%=member.getMemberName()%></a></td>
							<td><%=member.getMemberAge()%></td>
							<td><a href="../insertMember/insertMemberAddrForm.jsp?memberNo=<%=member.getMemberNo()%>">�ּ��Է�</a></td>
							<td><a href="../insertMember/insertMemberScoreForm.jsp?memberNo=<%=member.getMemberNo()%>">���� ���</a></td>
							<td><a href="../listMember/memberAndScoreList.jsp?memberNo=<%=member.getMemberNo()%>">���� ����</a></td>
							<td><a href="../deleteMember/deleteMember.jsp?memberNo=<%=member.getMemberNo()%>">����</a></td>
							<td><a href="../updateMember/updateMemberForm.jsp?memberNo=<%=member.getMemberNo()%>">����</a></td>
						</tr>
						<!-- member������ ����ִ� �ּҰ��� ã�ư� getMemberXx()�޼��带 �������� ���� ������ ������ ���׽��ϴ�. -->
				<%
					}
					
				%>
			</table>
			<div class="text-center">
				<%
					int lastPage = memberDao.CountMemberList(pagePerRow);
					// memberDao������ ����ִ� �ּҰ��� ã�ư� CountMemberList()�޼���ȿ� pagePerRow�������� ������ ������ �ϰ� ������� int�������� ������ lsatPage������ �����߽��ϴ�.
					
					if(currentPage>1){
				%>
						<a href = "./memberList.jsp?currentPage=<%=currentPage-1%>" class="btn">�� ����</a>
				<%
					}
					//currentPage�� 1���� ũ�� ���� a link�±װ� �������� ���� �߽��ϴ�.
			
					if(currentPage<lastPage){
				%>
						<a href = "./memberList.jsp?currentPage=<%=currentPage+1%>" class="btn">���� ��</a>
				<%
					}
					//currentPage�� lastPage���� ������ a link�±װ� �������� �����߽��ϴ� 
				%>
				<a href="../../index.jsp" class="btn">&nbsp;&nbsp;Ȩ��������</a>
			</div>
		</div>
	</body>
</html>