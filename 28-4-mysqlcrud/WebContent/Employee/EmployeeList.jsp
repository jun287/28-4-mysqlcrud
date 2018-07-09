<!-- 28기 정민수 2018. 7.3(화)insertEmployee.jsp -->
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.EmployeeDao" %>
<%@ page import="service.Employee" %>
<%@ page import="java.util.ArrayList" %>
<% request.setCharacterEncoding("euckr"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<form action="./EmployeeList.jsp" method="post">
			<div>
				이름 :
				<input type="text" name="searchWord">
				<button type="button">검색</button>
			</div>
		</form>
		<table border="1">
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>나이</th>
				<th>주소</th>
				<th>삭제</th>
				<th>수정</th>
				<th>점수입력</th>
				<th>점수보기</th>
			</tr>
			<%
				String word=request.getParameter("searchWord");
				System.out.println(word+"<--word");
				//화면에 몇개씩 출력할건지 변수에 갯수를 저장함
				int StartRow=5;
			
				//현재 페이지
				int currentPage;
				//만약 현재 페이지에 값이 있으면 받아온 값을 currentpage에 다시 대입하고 없으면 currentPage에 1을 대입한다
				if(request.getParameter("currentPage")!=null){
					currentPage=Integer.parseInt(request.getParameter("currentPage"));
				}else{
					currentPage=1;
				}
				System.out.println(currentPage+"<--currentPage");
				
				EmployeeDao employeedao=new EmployeeDao();
				//employee의 주소를 찾아가서 메서드에 현재페이지 값과 몇개를 출력할 것인지를 매개변수에 대입하고 호출하여 주소값을 resultList에 대입한다
				ArrayList<Employee> resultList=employeedao.selectEmployeeAll(currentPage,StartRow,word);
				
				//ArryaList에 저장되어있는 갯수만큼 출력하고 dto주소를 찾아가서 값들을 가져와 화면에 출력한다
				for(int i=0;i<resultList.size();i++){
					Employee employee=resultList.get(i);
					
			%>
					<tr>
						<td><%=employee.getEmployeeNo() %></td>
						<td><a href="./EmployeeAddrList.jsp"><%=employee.getEmployeeName() %></a></td>
						<td><%=employee.getEmployeeAge() %></td>
						<!--주소입력을 눌렀을시  insertEmployeeAddrForm.jsp,삭제를 눌렀을시 deleteEmployee.jsp,수정을 눌렀을시 updateEmployeeForm.jsp에 각각 no변수에 employee.getEmployeeNo()값을 담아 넘긴다-->
						<td><a href="./insertEmployeeAddrForm.jsp?no=<%=employee.getEmployeeNo() %>">주소입력</a></td>
						<td><a href="./deleteEmployee.jsp?no=<%=employee.getEmployeeNo() %>">삭제</a></td>
						<td><a href="./updateEmployeeForm.jsp?no=<%=employee.getEmployeeNo() %>">수정</a></td>						
						<td><a href="./insertEmployeeScoreForm.jsp?no=<%=employee.getEmployeeNo() %>">점수입력</a></td>						
						<td><a href="./EmployeeAndScoreList.jsp?no=<%=employee.getEmployeeNo() %>">점수보기</a></td>						
					</tr>
			<%
				}
				
			%>
		</table>
		
	
		<%
			//employeedao주소를 찾아가서 paging메서드에 StartRow값을 대입후 호출한다
			int total=employeedao.paging(StartRow);
		
			//현재페이지가 1보다크면 이전이 있고 1이랑 같거나 작으면  이전이 없다
			if(currentPage>1){
		%>
				<a href="./EmployeeList.jsp?currentPage=<%=currentPage-1%>">이전</a>
		<%
			}
			//currentPage가 총 페이지보다  작으면 실행된다 
			if(currentPage<total){
		%>
				<a href="./EmployeeList.jsp?currentPage=<%=currentPage+1%>">다음</a>
		<%
			}
		%>
	</body>
</html>