<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/login/css/research.css">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
	rel="stylesheet" />
<title>research</title>
</head>
<body>

	<div class="search-box">
		<button class="btn-search">
			<i class="fas fa-search"></i>
		</button>
		<header>
			<h1 class="title">スケジュール管理</h1>
			<h2 class="title2">どの人のスケジュールを探していますか</h2>
		</header>
		<form action="/login/Search" method="post"
			onsubmit="return searchFunction()">
			<input type="number" class="input-search" name="id" id="search"
				placeholder="検索したい人のIDを入力してください">
		</form>
		<div class="btn-wrapper">
			<button type="button" class="btn_submit" onclick="history.back()">
				<span>Back</span>
			</button>
		</div>
	</div>

	<script src="/login/js/search.js"></script>

</body>
</html>