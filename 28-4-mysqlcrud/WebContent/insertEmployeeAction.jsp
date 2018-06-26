<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement"%>
<%@ page import = "java.sql.SQLException"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertEmployeeAction.jsp</title>
	</head>
	<body>
		<%
			//request�� �� ������ euckr�� ���ڵ��Ͽ���
			request.setCharacterEncoding("euckr");
		
			//String data type���� ����� ���� name,age�� �Ѿ�� �Ķ���Ͱ����� �Ҵ��Ͽ���
			String name=request.getParameter("name");
			String age=request.getParameter("age");
			
			//���� �߹޾� �Դ��� console ���
			System.out.println(name+"<--name");
			System.out.println(age+"<--age");
			
			//class data type���� ������ �����ϰ� null�� �ʱ�ȭ �Ͽ���
			Connection conn=null;
			PreparedStatement pstmt=null;
			
			
			//ip�ּ�,��Ʈ��ȣ,db��,�����id,�н����带 ���� String data type���� ����� ������ ��ƶ�
			String dbname="jdbc:mysql://localhost:3306/284db?"+"useUnicode=true&characterEncoding=euckr";
			String userid="java";
			String userpw="java0000";
			
			//Driver�ε�
			String dbDriver="com.mysql.jdbc.Driver";
			Class.forName(dbDriver);
			System.out.println(Class.forName(dbDriver)+"<--Class.forName(dbDriver);");
			
			//db����
			//Connection ��ü�� ����,��ü�ּҸ� conn ������ �Ҵ��Ѵ�.
			conn=DriverManager.getConnection(dbname,userid,userpw);
			
			//try�̿��ִ� ����鿡�� ���ܰ� �߻��ϸ� catch�� �Ѿ��
			try{
				//���� �����غ�
				//conn�ּҸ� ã�ư��� prepareStatement�޼��忡  �Ű������� �������� ������ PreparedStatement��ü������ �ּҸ� pstmt�� �����Ѵ�. 
				pstmt=conn.prepareStatement("insert into employee(empoyee_name,employee_age) values(?,?)");
				
				//����ǥ�� ������ ����ִ� ������ �����Ѵ�
				pstmt.setString(1, name);
				pstmt.setString(2, age);
				
				System.out.println(pstmt+"<--pstmt");
				
				//���� ����
				pstmt.executeUpdate();
				
				System.out.println(pstmt+"<--pstmt");
			
			 //sql�� ���ܰ� �߻��Ͽ� catch�� �ִ� ������� �����Ѵ�.
			}catch(SQLException ex) {
				System.out.println("sql ������ �ƴѴ�.");
				out.println(ex.getMessage());
				ex.printStackTrace();
			
			 //���ܰ� �߻��ص� �ݵ�� �����Ѵ�
			}finally{
				//��ü ����
				pstmt.close();
				conn.close();
			}
			
			
		%>
	</body>
</html>