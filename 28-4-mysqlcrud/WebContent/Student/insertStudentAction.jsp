<!-- 28기 이원상 2018. 6. 25(월)insertStudentAction.jsp -->
<%@ page language = "java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "service.StudentDao"%> 
<%request.setCharacterEncoding("euc-kr"); %>	<!-- usebean액션태그를 통해 생성된 객체의 데이터가 담길때 한글이 잘 인코딩되도록 jsp액션태그 앞에 사용해야함. -->
<jsp:useBean id="stu" class="service.Student"/>	<!-- Student클래스를 임포트 및 클래스를 통해 객체를 생성하였음 -->
<jsp:setProperty name="stu" property="*"/>		<!-- jsp:usebean 액션태그로 생성된 객체의 프로퍼티의 값을 *로 지정할 경우 같은 이름을 같는 파라미터의 값을 설정 -->
<%
	StudentDao sDao = new StudentDao();
	sDao.insertStudent(stu);
	response.sendRedirect(request.getContextPath()+"/Student/studentList.jsp");
%>
