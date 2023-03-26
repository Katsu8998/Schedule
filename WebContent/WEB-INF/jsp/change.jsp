<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/login/css/password.css">
  <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
      rel="stylesheet"
    />
<title>change_password</title>
</head>
<body>
<div class = "main">
	<div class = "style">
		<form action="/login/ChangePassword" method="post" name="updateForm" id="updateForm"  onsubmit="return validateSignupForm()">
			<h2 class="title">パスワード変更</h2>

			<div class="Input">
				<label class="inputlabel" for = "password">旧パスワード</label>
				<input type="password" id = "password" name = "password" required>
			</div>

			<div class="Input">
				<label class="inputlabel" for = "new_pass">新パスワード</label>
				<input type="password" id = "new_pass" name = "new_pass" required>
			</div>

			<div class="btn_wrapper">
				<button type="submit" id="btn_submit" class = "btn_submit">
				 <span>Update</span>
      			</button>
       			<button type="submit" class="btn_submit" onclick="history.back()">
       			<span>Back</span>
       			</button>
			</div>
		</form>
	</div>
</div>
 <script src="/login/js/password.js"></script>
</body>
</html>