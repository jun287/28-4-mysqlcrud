<!-- 28�� ���μ� 2018. 7. 3(ȭ)insertEmployeeAction.jsp -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
	<%
		//�θ����̺��� ��ȣ�� �˾ƾ� �ܷ�Ű�� ���� ������ ���ֱ� ������ no�� ���� �޾ƿ´�
		int no=Integer.parseInt(request.getParameter("no"));
		System.out.println(no+"<--no");

	%>	
		<div align="center">
			<!-- �ּ� �Է� -->
			<form action="./insertEmployeeAddrAction.jsp?no=<%=no %>" method="post" id="addr">
				�ּ�&nbsp;:&nbsp;
				<input type='text' name="addr">
				<input type="submit" value="�ּ��Է�" id="addrok">
			</form>
		</div>
	</body>
</html>