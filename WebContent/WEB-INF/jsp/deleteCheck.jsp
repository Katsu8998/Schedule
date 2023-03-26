<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/login/css/deleteCheck.css">
 <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
      rel="stylesheet"
    />
<title>Schedule Delete</title>
</head>
<body>
	<div class ="main">
		<div class="style">
			<form action="/login/Schedule"  name = "s_delete" method="post" onsubmit= "return inputFunction()">

			<header>
				<h1>削除確認画面</h1>
				<h2>こちらを削除してもよろしいですか</h2>
			</header>

			<div class="Input">
				<h2 class="title">年月日</h2>
				<h3>${SB_d.date}</h3>
			</div>

			<div class="Input">
				<h2 class="title">開始時間</h2>
				<h3>${SB_d.start}</h3>
			</div>

			<div class="Input">
				<h2 class="title">終了時間</h2>
				<h3>${SB_d.end}</h3>
			</div>

			<div class="Input">
				<h2 class = "title">件名</h2>
				<h3>${SB_d.title}</h3>
			</div>

			<div class="Input">
				<h2 class = "title">メモ</h2>
			<h3>	${SB_d.detail}</h3>
			</div>

			<div class="btn_wrapper">
			 <button type="submit" id="btn_submit" class = "btn_submit" name = "action" value="s_delete">
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