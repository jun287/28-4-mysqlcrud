<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import="service.MemberAddr" %>
<%@ page import="service.MemberAddrDao" %>
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
			String memberAddrContent = request.getParameter("sendContent");
			//ȭ�鿡�� �Է��Ѱ��� ����ϱ����� ������ �����մϴ�.
			MemberAddrDao memberAddrDao = new MemberAddrDao();
			int getExecution = memberAddrDao.insertMemberAddr(getMemberNo ,memberAddrContent);
			System.out.println(getExecution +"<-getExecution");
			//memberAddrDao���������� ����ִ� �ּҰ��� ã�ư� insertMemberAddr()�޼��忡 getMemberNO ,memberAddrContent������ ����ִ°��� ������ �����ϰ� ���ϰ��� ����ϱ����� getExecution������ �����߽��ϴ�.
			if(getExecution == 0 ) {
				System.out.println("�ּҰ��� �Է��ϼ���");
			
				response.sendRedirect("./insertMemberAddrFrom.jsp");
				//������ ���ϰ��� 0���� �ް� �Ǹ� ���� ���а� �Ǹ� From.jsp���Ϸ� �̵����׽��ϴ�
			}else {
				System.out.println("������ �Ϸ� �߽��ϴ�");
				
				response.sendRedirect("../listMember/memberList.jsp");
				//���ϰ��� 0�� �ƴϸ� ������ �Ǹ� List.jsp���Ϸ� �̵� ���׽��ϴ�.
			}
		%>
	</body>
</html>