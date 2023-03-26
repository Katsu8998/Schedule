<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/login/css/input.css">
 <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
      rel="stylesheet"
    />
<title>Schedule Input</title>
</head>
<body>
	<div class ="main">
		<div class="style">
			<form action="/login/Schedule"  name = "s_input" method="post" onsubmit= "return inputFunction()">

			<header>
				<h1>スケジュール管理</h1>
				<h2>スケジュール入力</h2>
			</header>

			<div class="Input">
				<label class="inputlabel" for = "day">年月日</label>
				<input type="date" id = "day" name = "day" required>
			</div>

			<div class="Input">
				<label class="inputlabel" for = "start">開始時間</label>
				<input type="time" id = "start" name = "start" required>
			</div>

			<div class="Input">
				<label class="inputlabel" for = "finish">終了時間</label>
				<input type="time" id = "finish" name = "finish" required>
			</div>

			<div class="Input">
				<label class="inputlabel" for = "title">件名</label>
				<input type="text" id = "title" name = "title" required>
			</div>

			<div class="Input">
				<label class="inputlabel" for = "detail">メモ</label>
				<input type="text" id = "detail" name = "detail" required>
			</div>

			<div class="btn_wrapper">
			 <button type="submit" id="btn_submit" class = "btn_submit" name = "action" value="s_input">
				<span>submit</span>
      		 </button>
       		<button type="button" class="btn_submit" onclick="history.back()">
       		 <span>Back</span>
       		</button>
			</div>
			</form>
		</div>
	</div>
	 <script src="/login/js/input.js"></script>

</body>
</html>