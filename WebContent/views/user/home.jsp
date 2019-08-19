<%@page import="com.example.model.UserModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Simple Sidebar - Start Bootstrap Template</title>

<!-- Bootstrap core CSS -->
<link href="http://localhost:8080/AwesomeNews/template/admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="http://localhost:8080/AwesomeNews/template/admin/css/simple-sidebar.css" rel="stylesheet">
</head>
<body>

	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<div class="bg-light border-right" id="sidebar-wrapper">
			<div class="sidebar-heading">Start Bootstrap</div>
			<div class="list-group list-group-flush">
				<a href="#" class="list-group-item list-group-item-action bg-light">Dashboard</a>
				<a href="/AwesomeNews/add-news" class="list-group-item list-group-item-action bg-light">Add News</a>
				<a href="/AwesomeNews/view-news" class="list-group-item list-group-item-action bg-light">View News</a>
				<a href="#" class="list-group-item list-group-item-action bg-light">Personal Information</a>
			</div>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">

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
				
				<%
					UserModel user = (UserModel) session.getAttribute("user");
				%>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav ml-auto mt-2 mt-lg-0">
						<li class="nav-item"><a class="nav-link" href="#">Hello, <%=user.getUserName() %></a></li>
						<li class="nav-item active"><a class="nav-link" href="#">Home
								<span class="sr-only"></span>
						</a></li>
						<%
							if (user != null) {
						%>
						<li class="nav-item"><a class="nav-link" href="/AwesomeNews/logout">Logout</a></li>
						<%
							}
						%>
					</ul>
				</div>
			</nav>

			<div class="container-fluid">
				<h1 class="mt-4">Simple Sidebar</h1>
				<p>The starting state of the menu will appear collapsed on
					smaller screens, and will appear non-collapsed on larger screens.
					When toggled using the button below, the menu will change.</p>
				<p>
					Make sure to keep all page content within the
					<code>#page-content-wrapper</code>
					. The top navbar is optional, and just for demonstration. Just
					create an element with the
					<code>#menu-toggle</code>
					ID which will toggle the menu when clicked.
				</p>
			</div>
		</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- Bootstrap core JavaScript -->
	<script src="http://localhost:8080/AwesomeNews/template/admin/vendor/jquery/jquery.min.js"></script>
	<script src="http://localhost:8080/AwesomeNews/template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Menu Toggle Script -->
	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
	</script>

</body>
</html>