<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.Member" %>
<%@ page import="service.MemberDao" %>
<!-- 2018.07.02 28�� ������ -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
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
			//int������ i������ get_totalList������ ����ִ� indexũ�⺸�� ���������� �ݺ��� �����߽��ϴ�.
				Member member = get_totalList.get(i);
				//get_totalList������ �ִ� index���� i������ ����ִ� ������ �ҷ���  MemberŬ������ ���� ����� member������ ������ �Ѵ�.
		%>
				<tr>
					<td><%=member.getMemberNo()%></td>
					<td><a href="./memberAddrList.jsp"><%=member.getMemberName()%></a></td>
					<td><%=member.getMemberAge()%></td>
					<td><a href="../deleteMember/deleteMember.jsp?memberNo=<%=member.getMemberNo()%>">����</a></td>
					<td><a href="../updateMember/updateMemberForm.jsp?memberNo=<%=member.getMemberNo()%>">����</a></td>
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
			// memberDao������ ����ִ� �ּҰ��� ã�ư� CountMemberList()�޼���ȿ� pagePerRow�������� ������ ������ �ϰ� ������� int�������� ������ lsatPage������ �����߽��ϴ�.
			
			if(currentPage>1){
				%>
				<a href = "./memberList.jsp?currentPage=<%=currentPage-1%>">�� ����</a>
				<%
			}
			//currentPage�� 1���� ũ�� ���� a link�±װ� �������� ���� �߽��ϴ�.

			if(currentPage<lastPage){
				%>
				<a href = "./memberList.jsp?currentPage=<%=currentPage+1%>">���� ��</a>
				<%
			}
			//currentPage�� lastPage���� ������ a link�±װ� �������� �����߽��ϴ� 
		%>
	</body>
</html>