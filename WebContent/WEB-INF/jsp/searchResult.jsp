<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/login/css/researchResult.css">
<meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
      rel="stylesheet"
    />
<title>SearchResult</title>
</head>
<body>
	<section>
		<header>
		<c:choose>
			<c:when test="${research.size() > 0}">
			<div class="header-logo">${research[0].name}さんのスケジュール</div>
			<div class="list">
				<ul>
					<li>ID: ${research[0].user_id}</li>
				</ul>
			</div>
			</c:when>
				<c:otherwise>
				<div class="header-logo">${user.name}さんのスケジュール</div>
			<div class="list">
				<ul>
					<li>ID: ${user.id}</li>
				</ul>
			</div>
			</c:otherwise>
						</c:choose>

		</header>
		<div class="tb1-header">
			<table>
				<thead>
					<tr>
						<th>年月日</th>
						<th>開始時間</th>
						<th>終了時間</th>
						<th>件名</th>
						<th>メモ</th>
					</tr>
				</thead>
			</table>
		</div>

		<div class="tb-content">
			<table>
				<tbody>
					<tr>
						<c:choose>
							<c:when test="${research.size() == 0}">
								<div class=btn_wrapper>
									<h2>予定は入力されていません</h2>
									<button type="button" class="btn_submit"
										onclick="history.back()">
										<span>Back</span>
									</button>
								</div>

							</c:when>
							<c:otherwise>
								<c:forEach begin="0" end="${research.size()-1}" step="1" var="i">
									<tr>
										<td><c:out value="${research[i].date}" /></td>
										<td><c:out value="${research[i].start}" /></td>
										<td class="t_end"><c:out value="${research[i].end}" /></td>
										<td class="t_title"><c:out value="${research[i].title}" /></td>
										<td class="t_detail"><c:out value="${research[i].detail}" /></td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
				</tbody>
			</table>

			<c:if test="${research.size() > 0}">
						<div class=btn_wrapper>
				<button type="button" class="btn_submit" onclick="history.back()">
					<span>Back</span>
				</button>
				</div>
			</c:if>

		</div>
	</section>

</body>
</html>