<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script>
			//로딩 되었을 때 안에 있는 문장들을 실행 시켜라
			window.addEventListener("load", function() {
	
		 	 	var btn=document.getElementById("btn");
				var score=document.getElementById("score").value;
				
				btn.addEventListener("click", function() {
					if(score>=100){
						document.getElementById("result").innerHTML="100이상금지";
					}else{
						document.getElementById("scoreFrom").submit();
					}
				});
			});
		</script>
	</head>
	<body>
		<%
			int no=Integer.parseInt(request.getParameter("no"));
			System.out.println(no+"<--no");
		%>
		<form action="<%=request.getContextPath()%>/Employee/insertEmployeeScoreAction.jsp?no=<%=no %>" method="post" id="scoreFrom">
			<div>
				<lable>점수</lable>
				<input type="text" name="score" id="score">
				<button type="button" id="btn">점수입력</button>
				<span id="result"></span>
			</div>
		</form>
	</body>
</html>