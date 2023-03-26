<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%--   <%@page import ="model.ScheduleBeans" %>
    <%@ page import = "java.util.*" %>
    <% List <ScheduleBeans> S = (List<ScheduleBeans>) application.getAttribute("SB"); %> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" type="text/css" href="/login/css/view_schedule.css">
<meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
      rel="stylesheet"
    />

<title>スケジュール一覧</title>
</head>
<body>
	<section>
  <h1>スケジュール表</h1>
  <div class="tb1-header">
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>年月日</th>
          <th>開始時間</th>
          <th>終了時間</th>
          <th>件名</th>
          <th>メモ</th>
          <th>その他</th>
          </tr>
      </thead>
    </table>
  </div>
   <div class="tb-content">
    <table class="t_body">
      <tbody>
        <tr>
	<%-- 	<c:forEach var="s" items="${SB}" varStatus="loop">
					<tr><td class="t_id"><c:out value="${s.id}"/></td>
					<td><c:out value="${s.date}"/></td>
				    <td><c:out value="${s.start}"/></td>
					<td class="t_end"><c:out value="${s.end}"/></td>
					<td class="t_title"><c:out value="${s.title}"/></td>
					<td class="t_detail"><c:out value="${s.detail}"/></td>
					<td class="b">



					<form action ="/login/Schedule">--%>
			<%-- 	<input type="hidden" name ="s_id" value = <%=S.get(0)%>>--%>
<c:choose>
<c:when test="${SB.size() == 0}">
	<div class=btn_wrapper>
			 <form action="/login/Schedule">
				<button type="submit" name="schedule" value="input" class="btn"><span>スケジュール入力</span></button>
				<button type="button" class="btn_submit" onclick="history.back()">
       			<span>Back</span>
       			</button>
			 </form>
			</div>

</c:when>
<c:otherwise>
			 <c:forEach begin="0" end="${SB.size()-1}" step="1" var="i">
             <tr><td class="t_id"><c:out value="${SB[i].id}"/></td>
					<td><c:out value="${SB[i].date}"/></td>
				    <td><c:out value="${SB[i].start}"/></td>
					<td class="t_end"><c:out value="${SB[i].end}"/></td>
					<td class="t_title"><c:out value="${SB[i].title}"/></td>
					<td class="t_detail"><c:out value="${SB[i].detail}"/></td>
					<td class="b">
					<form action ="/login/Schedule">
					<input type="hidden" name ="s_id" value ="${SB[i].schedule_id}"/>
					<button type="submit" name="schedule" value="edit" class="btn">
					<span>編集</span></button></form>
					<form action ="/login/Schedule">
					<input type="hidden" name ="s_id" value ="${SB[i].schedule_id}"/>
					<button type="submit" name="schedule" value="delete" class="btn">
					<span>削除</span></button></form>
					</td>
					</tr>
			</c:forEach>
			</c:otherwise>
			</c:choose>
				</tbody>
			</table>
<c:if test="${SB.size() > 0}">
			<div class=btn_wrapper>
			 <form action="/login/Schedule">
				<button type="submit" name="schedule" value="input" class="btn"><span>スケジュール入力</span></button>
				<button type="button" class="btn_submit" onclick="history.back()">
       			<span>Back</span>
       			</button>
			 </form>
			</div>
		</c:if>

		</div>
	</section>
</body>
</html>