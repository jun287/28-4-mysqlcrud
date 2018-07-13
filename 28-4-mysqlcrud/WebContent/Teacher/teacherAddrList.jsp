<!-- 2018. 07. 03 28기 공세준 -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ page import = "TeacherDAO.TeacherAddrDao" %>
<%@ page import = "TeacherDTO.TeacherAddr" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>teacherAddrList</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	</head>
	<body>
	<%	
		/*  
			번호와 주소값을 받아와서 화면에 출력하기위해
			teacher_no(no)값을 형변환후 int teacherNo 변수에 대입합니다.
			TeacherAddrDao 클래스 객체 생성후 
			teacherAddrDao 클래스 객체내에 selectTeacherAddr 메서드에 teacherNo를 대입후 호출하면
			TeacherAddr 클래스 객체 타입으로 teacherAddr 객체참조변수에 
			조건에 맞는 조회결과가 담긴 객체의 주소가 리턴되고
			
			그 주소값을 찾아가서 메서드를 호출하고 조건문을 사용해 주소가 없으면(null) 주소입력폼으로 이동시키고
			주소값이 있으면 화면에 출력합니다.
		*/
		
		int teacherNo = Integer.parseInt(request.getParameter("no"));
		TeacherAddrDao teacherAddrDao = new TeacherAddrDao();
		TeacherAddr teacherAddr = teacherAddrDao.selectTeacherAddr(teacherNo);
		String teacherAddress= null;
		
		if(teacherAddr.getTeacherAddrContent() == null){
			response.sendRedirect(request.getContextPath()+"/Teacher/insertTeacherAddrForm.jsp?no="+teacherNo);
		}else{
			teacherAddress = teacherAddr.getTeacherAddrContent();
		}
	%>
		<div id="main" align="center" >
			<h2>주소 목록</h2><br>
			<table>
				<tr>
					<th>번호</th>
					<th>주소</th>
				</tr>
				<tr>
					<td><%=teacherAddr.getTeacherNo()%></td>
					<td><%=teacherAddress%></td>
				</tr>
			</table>
			<a href="<%=request.getContextPath()%>/Teacher/teacherList.jsp">목록으로</a>
		</div>
	</body>
</html>