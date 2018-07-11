<!-- 2018. 07. 03 28�� ������ -->

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>insertTeacherForm</title>
		<style>
			* {
				margin:0;  /* ������ 0���� �մϴ�. */
				padding:0;
			}
			
			ul {
				list-style-type:none; /* ul�±��� ����� ���� �ʽ��ϴ�. */
			}
			
			h3 { /* h3�±��� ������ �����մϴ�. */
				margin:20px 0 0 50px;
			}
			
			#mem_form { /* id : mem_form �� ũ�� ���� ����ü ����ũ�� ���ڻ� ���� �����մϴ�.  */
				width:500px;
				margin:10px 0 0 50px;
				font-family:"����";
				font-size:12px;
				color:#444444;
				padding-top:5px;
				padding-bottom:10px;
				border-top:solid 1px #cccccc;
				border-bottom:solid 1px #cccccc;
			}
			
			.cols li { /* cols Ŭ������ li�±��� ���κ�� ǥ�� �������� �ݴϴ�.  */
				display:inline-block;
				margin-top:5px;
			}
			
			.cols li.col1 { /* cols Ŭ������ li.col1 �±��� ũ�⸦ 100px ���������� �����������մϴ�.  */
				width:100px;
				text-align:right;
			}
			
			.cols li.col2 { /* cols Ŭ������ li.col2 �±��� ũ�⸦ 350px�� �մϴ�. */
				width:350px;
			}
			
			.cols li.col2 input.hp { /* cols Ŭ������ li.col2�� input Ŭ���� hp�� ũ�⸦ 35px�� �մϴ�. */
				width:35px;
			}
			
			#intro {
				vertical-align:top; 
			}
			
		</style>
	</head>
	<body>
		<h3>�ּ� ���</h3>
			<form action="<%= request.getContextPath()%>/Teacher/insertTeacherAddrAction.jsp?no=<%=request.getParameter("no")%>" method="post">
				<ul id="mem_form">
					<li>
						<ul class="cols">
							<li class="col1">�ּ� :</li>
							<li class="col2"><input type="text" name="teacherAddrContent" placeholder="�ּ� �Է�" required></li>
						</ul>
					</li>
					<li>
						<ul class="cols">
							<li class="col1"></li>
							<li class="col2">
								<input type="submit" value="���">	
							</li>
						</ul>
					</li>
				</ul>
			</form>
	</body>
</html>