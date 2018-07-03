<!-- 2018. 07. 02 28기 공세준 -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
    
<%@ page import = "service.TeacherDao" %>
<%@ page import = "service.Teacher" %>
<%@ page import = "java.util.ArrayList" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<%	
			//페이징을 위해 첫 페이지의 값을 1로 지정합니다.
			int currentPage = 1;
			if(request.getParameter("currentPage")!=null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			// 페이지당 10개의 데이터를 보여줍니다.
			int rowPerPage = 10;
			TeacherDao teacherDao = new TeacherDao();
			ArrayList<Teacher> teacherList = teacherDao.selectTeacherByPage(currentPage, rowPerPage);
		%>
			<h2>교사 목록</h2>
			<table border="1">
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>나이</th>
					<th>주소입력</th>
					<th>삭제</th>
					<th>수정</th>
				</tr>
		<%
				//반복문을 사용해 teacherList객체참조변수의 주소값을 찾아가서 size()메서드를 호출하고 index 갯수만큼 반복하게 합니다.
				for(int i=0;i<teacherList.size();i++){
					Teacher teacher = teacherList.get(i); // Teacher 클래스타입의 teacher 객체참조변수에 teacherList 객체참조변수의 주소값을 찾아가서 get()메서드를 호출하고 teacher 객체들의 주소값을 대입합니다.
		%>
				<tr>
					<td><%=teacher.getTeacherNo()%></td> <!-- teacher 객체에서  getTeacherNo()메서드를 호출하여 데이터를 가져옵니다. -->
					<td><a href ="<%= request.getContextPath() %>/Teacher/teacherAddrList.jsp?no=<%=teacher.getTeacherNo()%>"><%=teacher.getTeacherName()%></a></td> <!-- teacher 객체에서  getTeacherName()메서드를 호출하여 데이터를 가져옵니다. -->
					<td><%=teacher.getTeacherAge()%></td> <!-- teacher 객체에서  getTeacherAge()메서드를 호출하여 데이터를 가져옵니다. -->
					<td><a href ="<%= request.getContextPath() %>/Teacher/insertTeacherAddrForm.jsp?no=<%=teacher.getTeacherNo()%>">주소입력</a></td>
					<td><a href ="<%= request.getContextPath() %>/Teacher/deleteTeacherAction.jsp?no=<%=teacher.getTeacherNo()%>">삭제</a></td>
					<td><a href ="<%= request.getContextPath() %>/Teacher/updateTeacherForm.jsp?no=<%=teacher.getTeacherNo()%>">수정</a></td>
					<!-- updateTeacherForm.jsp -> updateTeacherAction.jsp -->
				</tr>
		<%
				}
		%>
			</table>
			
			<form>
				<div>
					이름 :
					<input type="text" name="searchWord">
					<button type="button">검색</button>
				</div>
			</form>
		<%
			// 마지막 페이지를 구하기 위해 int 기본타입으로 lastPage 변수를 선언하고 teacherDao 클래스객체의 lastPage() 메서드를 호출하고 리턴값을 대입합니다.
			int lastPage = teacherDao.lastPage(rowPerPage);

			
			if(currentPage>1){
				%>
				<a href = "./teacherList.jsp?currentPage=<%=currentPage-1%>">◀ 이전</a>
				<%
			}

			if(currentPage<lastPage){
				%>
				<a href = "./teacherList.jsp?currentPage=<%=currentPage+1%>">다음 ▶</a>
				<%
			}
		%>
	</body>
</html>