<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="memberDTO.MemberAddr" %>
<%@ page import="memberDAO.MemberAddrDao" %>
<!-- 2018.07.06 28�� ������-->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>�ּ� �Է� ó��</title>
	</head>
	<body>
		<%
			request.setCharacterEncoding("euc-kr");
			//ȭ��â���� �Է��� �ѱ۱����� �����մϴ�
			int getMemberNo = Integer.parseInt(request.getParameter("memberNo"));
			
			if(request.getParameter("sendContent") == "") {
				System.out.println("�ּҰ��� �Է��ϼ���");				
				response.sendRedirect("./insertMemberAddrForm.jsp");
				//sendContent���� �����̸� �ٽ� Form.jsp���Ϸ� �̵���ŵ�ϴ�.
			}else {
				String memberAddrContent = request.getParameter("sendContent");
				//ȭ�鿡�� �Է��Ѱ��� ����ϱ����� ������ �����մϴ�.
				MemberAddrDao memberAddrDao = new MemberAddrDao();
				int getExecution = memberAddrDao.insertMemberAddr(getMemberNo ,memberAddrContent);
				System.out.println(getExecution +"<-getExecution");
				//memberAddrDao���������� ����ִ� �ּҰ��� ã�ư� insertMemberAddr()�޼��忡 getMemberNO ,memberAddrContent������ ����ִ°��� ������ �����ϰ� ���ϰ��� ����ϱ����� getExecution������ �����߽��ϴ�.
				
				System.out.println("������ �Ϸ� �߽��ϴ�");
				response.sendRedirect("../listMember/memberList.jsp");
				//���� �Ϸ��� memberList.jsp���Ϸ� �̵����׽��ϴ�.
			}
		%>
	</body>
</html>