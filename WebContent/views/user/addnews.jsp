<%@page import="com.example.model.UserModel"%>
<%@ page language="java"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Simple Sidebar - Start Bootstrap Template</title>

<!-- Bootstrap core CSS -->
<link
	href="http://localhost:8080/AwesomeNews/template/admin/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">



<!-- Custom styles for this template -->
<link
	href="http://localhost:8080/AwesomeNews/template/admin/css/simple-sidebar.css"
	rel="stylesheet">
</head>
<body>



	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<div class="bg-light border-right" id="sidebar-wrapper">
			<div class="sidebar-heading">USER PAGE</div>
			<div class="list-group list-group-flush">
				<a href="#" class="list-group-item list-group-item-action bg-light">Dashboard</a>
				<a href="/AwesomeNews/add-news"
					class="list-group-item list-group-item-action bg-light">Add
					News</a> <a href="/AwesomeNews/view-news"
					class="list-group-item list-group-item-action bg-light">View
					News</a> <a href="#"
					class="list-group-item list-group-item-action bg-light">Personal
					Information</a>
			</div>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">

			<%
				UserModel user = (UserModel) session.getAttribute("user");
			%>


			<nav
				class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
				<button class="btn btn-primary" id="menu-toggle">Toggle
					Menu</button>

				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav ml-auto mt-2 mt-lg-0">
						<li class="nav-item active"><a class="nav-link" href="#">Home
								<span class="sr-only">(current)</span>
						</a></li>
						<%
							if (user != null) {
						%>
						<li class="nav-item"><a class="nav-link"
							href="/AwesomeNews/logout">Hello, <%=user.getUserName() %> },
								Logout</a></li>
						<%
							}
						%>

					</ul>
				</div>
			</nav>
			<%
				String e = "";
				if (request.getParameter("e") != null) {
					e = request.getParameter("e");
				}
			%>

			<div class="container-fluid">
				<h1 class="mt-4">Add News</h1>

				<%
					if (e.equals("0")) {
				%>
				<p style="color: green;">News Added successfully!</p>
				<%
					}
				%>
				
				<%
					if (e.equals("1")) {
				%>
				<p style="color: red;">Fail to Upload!</p>
				<%
					}
				%>

				<form action="/AwesomeNews/add-news" method="post"
					enctype="multipart/form-data">
					<div class="form-group">
						<label for="exampleFormControlInput1">Title</label> <input
							type="text" class="form-control" id="exampleFormControlInput1"
							name="title">
					</div>
					<div class="form-group">
						<label for="exampleFormControlTextarea1">Description</label>
						<textarea class="form-control" id="exampleFormControlTextarea1"
							rows="3" name="description"></textarea>
					</div>

					<div class="form-group">
						<label for="exampleFormControlTextarea1">content</label>
						<textarea class="form-control" id="exampleFormControlTextarea1"
							rows="6" name="content"></textarea>
						<div class="form-group">
							<label for="">Image</label> <input type="file" name="image"
								accept="image/*">
						</div>
						<!-- EXPLOIT IDOR HORIZONTAL  -->
						<!-- 
						<div class="form-group">
							<label for="exampleFormControlInput1">Author</label> <input
								type="text" class="form-control" id="exampleFormControlInput1"
								name="author">
						</div>
						 -->
					</div>
					<input type="submit" value="ADD">


				</form>




			</div>
		</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- Bootstrap core JavaScript -->
	<script
		src="http://localhost:8080/AwesomeNews/template/admin/vendor/jquery/jquery.min.js"></script>
	<script
		src="http://localhost:8080/AwesomeNews/template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Menu Toggle Script -->
	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
	</script>

</body>
</html>