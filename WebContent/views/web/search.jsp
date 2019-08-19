<%@page import="java.util.ArrayList"%>
<%@page import="com.example.model.NewsModel"%>
<%@page import="com.example.dao.NewsDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>

<!-- Bootstrap core CSS -->
<link href="template/web/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="template/web/css/blog-home.css" rel="stylesheet">

</head>
<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="/AwesomeNews/home-page">Start Bootstrap</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link"
						href="/AwesomeNews/home-page">Home <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#">About</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Services</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Contact</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">

		<div class="row">
			<%
				String keyword = (String) request.getAttribute("keyword");
				NewsDAO newsDAO = new NewsDAO();
				ArrayList<NewsModel> listResults = newsDAO.searchNewsByKeyword(keyword);
				//keyword.replaceAll("<", "&gt");
				//keyword.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
				//keyword.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
			%>
			<!-- Blog Entries Column -->
			<div class="col-md-8">
				<h1 class="my-4">Searching <%=keyword%></h1>

				<!-- Blog Post -->
				<%
					for (NewsModel news : listResults) {
				%>
				<div class="card mb-4">
					<img class="card-img-top" src="http://placehold.it/750x300"
						alt="Card image cap">
					<div class="card-body">
						<h2 class="card-title"><%=news.getTitle()%></h2>
						<p class="card-text"><%=news.getContent()%></p>
						<a href="/AwesomeNews/detail-news?ID=<%=news.getID()%>"
							class="btn btn-primary">Read More &rarr;</a>
					</div>
					<div class="card-footer text-muted">
						Posted on January 1, 2017 by <a href="#"><%=news.getAuthor()%>
						</a>
					</div>
				</div>
				<%
					}
				%>

				<!-- Pagination -->
				<ul class="pagination justify-content-center mb-4">
					<li class="page-item"><a class="page-link" href="#">&larr;
							Older</a></li>
					<li class="page-item disabled"><a class="page-link" href="#">Newer
							&rarr;</a></li>
				</ul>

			</div>

			<!-- Sidebar Widgets Column -->
			<div class="col-md-4">

				<!-- Search Widget -->
				<div class="card my-4">
					<h5 class="card-header">Search</h5>
					<div class="card-body">
						<div class="input-group">
							<!-- Form Search -->
							<form action="/AwesomeNews/search-result" method="GET">
								<input type="text" class="form-control"
									placeholder="Search for..." name="keyword"> <input
									type="submit" class="btn btn-secondary input-group-btn"
									value="Go!">
							</form>
							<!-- ./ Form Search -->
						</div>
					</div>
				</div>

				<!-- Categories Widget -->
				<div class="card my-4">
					<h5 class="card-header">Categories</h5>
					<div class="card-body">
						<div class="row">
							<div class="col-lg-6">
								<ul class="list-unstyled mb-0">
									<li><a href="#">Web Design</a></li>
									<li><a href="#">HTML</a></li>
									<li><a href="#">Freebies</a></li>
								</ul>
							</div>
							<div class="col-lg-6">
								<ul class="list-unstyled mb-0">
									<li><a href="#">JavaScript</a></li>
									<li><a href="#">CSS</a></li>
									<li><a href="#">Tutorials</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>

				<!-- Side Widget -->
				<div class="card my-4">
					<h5 class="card-header">Side Widget</h5>
					<div class="card-body">You can put anything you want inside
						of these side widgets. They are easy to use, and feature the new
						Bootstrap 4 card containers!</div>
				</div>

			</div>

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->

	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2019</p>
		</div>
		<!-- /.container -->
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="template/web/vendor/jquery/jquery.min.js"></script>
	<script src="template/web/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>