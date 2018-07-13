<!-- 28기 이원상 2018. 7. 10(화) StudentListAboveAverage.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="StudentDAO.StudentScoreDao" %>
<%@ page import="StudentDTO.StudentAndScore" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/main.css">
<link rel= "stylesheet" type= "text/css" href="<%=request.getContextPath() %>/css/studentList.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/script/studentList.js">
</script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>평균이상 점수의 학생 List</title>
</head>
<body>
	<div id="main" style="align:center">주소 목록
		<h1>평균점수 이상 학생 리스트</h1>
	<%
		StudentScoreDao studentScoreDao = new StudentScoreDao();
		double selectStudentScoreAverage = studentScoreDao.selectStudentScoreAverage();
		// 학생 평균점수 산출 메소드 및 selectStudentScoreAverage 변수 대입
	
		int currentPage = 1;
		if(request.getParameter("currentPage") != null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int pagePerRow = 15;
		if(request.getParameter("pagePerRow") != null){
			pagePerRow = Integer.parseInt(request.getParameter("pagePerRow"));
		}
		int lastPage = studentScoreDao.countStudentAndScoreAboveAverage(pagePerRow);
		System.out.println(lastPage+"<--lastPage");
		// 평균점수 이상 학생 전채행의 갯수를 구해 볼 페이지로 나눠 lastPage에 대입(%가 0이 아닐시 lastPage를 1증가)
		
		
		StudentAndScore studentAndScore = new StudentAndScore();
		
		ArrayList<StudentAndScore> StudentAndScoreAboveAverageList = new ArrayList<>();
		StudentAndScoreAboveAverageList = studentScoreDao.selectStudentAndScoreAboveAverage(currentPage,pagePerRow);
	%>
		<form action="<%=request.getContextPath() %>/Student/StudentListAboveAvg.jsp" method="post" id="selectForm">
	<%
	if(pagePerRow == 3){
	%>
			<select id="pagePerRow" name="pagePerRow">
				<option value="3" selected>3개씩 보기</option>
				<option value="5">5개씩 보기</option>
				<option value="7">7개씩 보기</option>
				<option value="10">10개씩 보기</option>
				<option value="15">15개씩 보기</option>
			</select>
	<%		
	}else if(pagePerRow == 5){
	%>
			<select id="pagePerRow" name="pagePerRow">
				<option value="3">3개씩 보기</option>
				<option value="5" selected>5개씩 보기</option>
				<option value="7">7개씩 보기</option>
				<option value="10">10개씩 보기</option>
				<option value="15">15개씩 보기</option>
			</select>
	<%		
	}else if(pagePerRow == 7){
	%>
			<select id="pagePerRow" name="pagePerRow">
				<option value="3">3개씩 보기</option>
				<option value="5">5개씩 보기</option>
				<option value="7" selected>7개씩 보기</option>
				<option value="10">10개씩 보기</option>
				<option value="15">15개씩 보기</option>
			</select>
	<%		
	}else if(pagePerRow == 10){
	%>
			<select id="pagePerRow" name="pagePerRow">
				<option value="3">3개씩 보기</option>
				<option value="5">5개씩 보기</option>
				<option value="7">7개씩 보기</option>
				<option value="10" selected>10개씩 보기</option>
				<option value="15">15개씩 보기</option>
			</select>
	<%
	}else if(pagePerRow == 15){
	%>	
			<select id="pagePerRow" name="pagePerRow">
				<option value="3">3개씩 보기</option>
				<option value="5">5개씩 보기</option>
				<option value="7">7개씩 보기</option>
				<option value="10">10개씩 보기</option>
				<option value="15" selected>15개씩 보기</option>
			</select>
	<%
	}
	%>	
			<button type="button" id="pagePerRowButton">보기설정</button>	
		</form>
		<div>
			평균점수 : <%=selectStudentScoreAverage %>
		</div>
		<table>
			<thead>
				<tr>
					<th>학생번호</th><th>학생이름</th><th>학생나이</th><th>점수구분</th><th>점수</th>
				</tr>
			</thead>
			<tbody>
	<%	
		for(int i=0; i<StudentAndScoreAboveAverageList.size(); i++){
			studentAndScore = StudentAndScoreAboveAverageList.get(i);
	%>
				<tr>
					<td><%=studentAndScore.getStudent().getStudentNo() %></td>
					<td><%=studentAndScore.getStudent().getStudentName() %></td>
					<td><%=studentAndScore.getStudent().getStudentAge() %></td>
					<td><%=studentAndScore.getStudentScore().getStudentScoreNumber() %></td>
					<td><%=studentAndScore.getStudentScore().getScore()%></td>
				</tr>
			</tbody>
	<%			
		}
	%>	
		</table>
		<div>
	<%
		if(currentPage !=0 && currentPage != 1){
	%>
				<a href="<%=request.getContextPath() %>/Student/StudentListAboveAvg.jsp?currentPage=<%=currentPage-1 %>&pagePerRow=<%=pagePerRow%>">이전</a>
	<%
		}for(int p=1; p<=lastPage; p++){
	%>		
				<a href="<%=request.getContextPath() %>/Student/StudentListAboveAvg.jsp?currentPage=<%=p%>&pagePerRow=<%=pagePerRow%>"><%=p%></a>
	<%		
		}if(currentPage < lastPage){
	%>	
				<a href="<%=request.getContextPath() %>/Student/StudentListAboveAvg.jsp?currentPage=<%=currentPage+1 %>&pagePerRow=<%=pagePerRow%>">다음</a>
	<%
		}
	%>		
		</div>
	</div>
</body>
</html>