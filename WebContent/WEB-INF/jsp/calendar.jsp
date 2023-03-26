<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" type="text/css" href="/login/css/calendar.css">
  <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
      rel="stylesheet"
    />
<title>カレンダー</title>
</head>
<body>
<div class = "container">
	<div class="calendar">
<header>
	<h2>ようこそ! ${user.name}さん</h2>

	<h2>${now.year}年 ${now.month}月</h2>
<a href="/login/Calendar?year=${now.year}&month=${now.month-2}" class="prev fontawesome-angle-left">前月</a>
<a href="/login/Calendar?year=${now.year}&month=${now.month}" class="next fontawesome-angle-right">翌月</a>
<form action="/login/Login">
 <div class="btn_wrapper">
 	<button  type="submit" name="action" value="logout" class="btn"><span>ログアウト</span></button>
 	<button  type="submit" name="action" value="change" class="btn"><span>パスワード変更</span></button>
 	<button  type="submit" name="action" value="research" class="btn"><span>検索</span></button>
</div>
</form>
</header>
<table>
 <tr>
 	<th>日</th>
 	<th>月</th>
 	<th>火</th>
 	<th>水</th>
 	<th>木</th>
 	<th>金</th>
 	<th>土</th>
 </tr>
<tbody>
 	 <c:forEach var="Newnow" items="${now.date}">
 	 	<tr>
 			<c:forEach var="col" items="${Newnow}">
 			<th><a href="/login/Schedule?schedule=view&day=${col}&month=${now.month}&year=${now.year}"><c:out value="${col}"/></a></th>
 	 		</c:forEach>
 	 	</tr>
 	  </c:forEach>
 </tbody>
</table>
</div>
</div>
</body>
</html>