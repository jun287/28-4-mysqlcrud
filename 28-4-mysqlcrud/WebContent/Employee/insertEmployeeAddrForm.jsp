<!-- 28기 정민수 2018. 7. 3(화)insertEmployeeAction.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="../style/insertEmployeeForm.css">
	</head>
	<body>
		<%
			//부모테이블의 번호를 알아야 외래키로 같이 저장할 수있기 때문에 no의 값을 받아온다
			String no=request.getParameter("no");
			System.out.println(no+"<--no");
		%>	
			<!-- 주소 입력 -->
			<form action="./insertEmployeeAddrAction.jsp?no=<%=no %>" method="post">
				<div id="name">
					주소&nbsp;:&nbsp;
					<input type='text' name="addr">
				</div>
				<div>
					<input type="submit" value="주소입력" id="ok">
				</div>
			</form>
		
		
	</body>
</html>