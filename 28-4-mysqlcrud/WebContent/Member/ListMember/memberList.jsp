<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.Member" %>
<%@ page import="service.MemberDao" %>
<!-- 2018.07.02 28�� ������ -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>ȸ�� ���� List���â</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("euc-kr");
			
			int currentPage = 1; //���� �������� 1�� �����߽��ϴ�.
			System.out.println(currentPage +"<- currentPage");
			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			
			
			int pagePerRow = 5; //�� �������� ������ ������ �����Դϴ�.
			System.out.println(pagePerRow +"<- pagePerRow");
			MemberDao memberDao = new MemberDao();
			ArrayList<Member> get_totalList = memberDao.selectMemberByPage(currentPage ,pagePerRow);
			System.out.println(get_totalList +"<- totalList");
			//memberDao������ ����ִ� �ּҰ��� ã�ư� selectMemberByPage�޼���ȿ� ����ִ� currentPage������ pagePerRow������ ���� ������ ������ �մϴ�
			//������ return���� MemberŬ���� Ÿ���� ArrayList���� ������ �� get_list������ �Ҵ��� �߽��ϴ�. 
		%>
		<h2>��� ����Ʈ</h2>
		<table>
			<tr>
				<th>��ȣ </th>
				<th>�̸� </th>
				<th>���� </th>
				<th>�����ϱ�</th>
				<th>�����ϱ�</th>
			</tr> 
		<%
			for(int i=0; i<get_totalList.size(); i++){
				Member member = get_totalList.get(i);
		%>
				<tr>
					<th><%=member.getMemberNo()%></th>
					<th><a href="./memberAddrList.jsp"><%=member.getMemberName()%></a></th>
					<th><%=member.getMemberAge()%></th>
					<th><a href="../delete/deleteMemberAction.jsp?member_no=<%=member.getMemberNo()%>">����</a></th>
					<th><a href="../update/updateMemberForm.jsp?member_no=<%=member.getMemberNo()%>">����</a></th>
				</tr>
				<!-- member������ ����ִ� �ּҰ��� ã�ư� getMemberXx()�޼��带 �������� ���� ������ ������ ���׽��ϴ�. -->
		<%
			}
		%>
		</table>
		<form>
			<!-- �̸� :  -->
			<input type="text" name="searchWord">
			<button type="submit">�˻�</button>
		</form>
		<%
			int lastPage = memberDao.CountMemberList(pagePerRow);

			if(currentPage>1){
				%>
				<a href = "./MemberList.jsp?currentPage=<%=currentPage-1%>">�� ����</a>
				<%
			}

			if(currentPage<lastPage){
				%>
				<a href = "./MemberList.jsp?currentPage=<%=currentPage+1%>">���� ��</a>
				<%
			}
		%>
	</body>
</html>