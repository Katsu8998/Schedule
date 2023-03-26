<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/login/css/register.css">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
	rel="stylesheet" />
<title>register</title>
</head>
<body>
	<div class="mainDiv">
		<div class="style">
			<form action="/login/User_R" method="post"  onSubmit="return registerFunction()">
				<h1 class="formtitle">
					登録画面
					</h1>

						<h2 class="formt">登録フォーム</h2>
								<div class="inputDiv">
							<label class="inputLabel" for="name"> 名前</label> <input
								type="text" id="name" name="name" required>
						</div>

						<div class="inputDiv">
							<label class="inputLabel" for="password"> パスワード</label> <input
								type="password" id="password" name="password" required>
						</div>

						<div class="inputDiv">
							<label class="inputLabel" for="id"> ID</label> <input type="text"
								id="id" name="id" required>
						</div>


						<div class="buttonWrapper">
							<button type="submit" id="btn_submit" value = "register"
								 class="submitButton" name = "action">
								<span>登録</span>
							</button>
							<button type="button" name="back" onclick="history.back()">戻る</button>
						</div>
			</form>
		</div>
	</div>
    <script src="/login/js/register.js"></script>

</body>
</html>