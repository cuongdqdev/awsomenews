<%@ page language="java"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Admin Page</title>

<!-- Bootstrap core CSS -->
<link
	href="http://localhost:8080/AwesomeNews/template/admin/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>
	<%
		String e = "";
		if (request.getParameter("e") != null) {
			e = request.getParameter("e");
		}
	%>
	<div class="container">
		<h2>Admin Area</h2>
		<form action="/AwesomeNews/login" method="post">
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					class="form-control" name="username" placeholder="Enter username"
					name="username">
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					class="form-control" name="password" placeholder="Enter password"
					name="password">
			</div>
			<%
				if (e.equals("1")) {
			%>
			<div class="form-group">
				<p style="color: red;">Kiểm tra lại thông tin đăng nhập!</p>
			</div>

			<%
				}
			%>



			<div class="checkbox">
				<label><input type="checkbox" name="remember">
					Remember me</label>
			</div>
			<button type="submit" class="btn btn-success">Submit</button>
		</form>
	</div>
</body>
</html>