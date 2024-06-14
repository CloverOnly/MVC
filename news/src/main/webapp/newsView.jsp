<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, inital-scale=1">

<title>뉴스 관리 앱</title>
</head>
<body>
<div class="container w-75 mt-5 mx-auto">
	<h2>${news.title }</h2>
	<hr>
	<div class="card w-75 mx-auto">
		<img class="card-img-top" src="${news.img}">
		<div class="card-body">
			<h4 class="card-title">Date: ${news.date}</h4>
			<p class="card-text">Content: ${news.content}</p>
		</div>
	</div>
	<hr>
	<a href="javascript:history.back()" class="btn btn-primary">Back</a>
</div>
</body>
</html>