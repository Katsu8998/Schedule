<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
  <head>
<meta charset="UTF-8">
 <link rel="stylesheet" type="text/css" href="/login/css/login2.css">
  <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
      rel="stylesheet"
    />
<title>ログイン</title>
</head>
<body class ="back">
  <div>
    <h2>スケジュールアプリ</h2>
      <section>
       <h1>
       <span>
          ようこそ!
          </span>
          </h1>
            <form action = "/login/Login"id="form" method = "post"  onsubmit="return funcConfirm()">
              <label for = "text">ID</label><br>
              <input type="text" id="text" name = "id"><br>
              <label for="password">パスワード</label><br>
              <input type="password" id="password" name="password">
              <br>
            <button type="submit" id="submit" value = "Submit">Sign In</button>
            </form><br><br>
            <h4>アカウントを持っていないですか?<a href="/login/Login?action=register">Sign up</a></h4>
            </section>
    </div>
    <script src="/login/js/login.js"></script>
</body>
</html>

