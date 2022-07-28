<%@page import="java.util.List"%>
<%@page import="mvc.domain.Bbs"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mvc.dao.BbsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String userID = null;
if (session.getAttribute("userID") != null) {
	userID = (String) session.getAttribute("userID");
}

	int pageNumber = (Integer) request.getAttribute("pageNumber");
	List<Bbs> list = (List) request.getAttribute("list");
	boolean nextPage = (Boolean) request.getAttribute("nextPage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>BBS</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse -1"
				aria-expanded="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.jsp">JSP 게시판 웹 사이트</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.jsp">메인</a></li>
				<li class="active"><a href="bbs.jsp">게시판</a></li>
			</ul>
			<%
			if (userID == null) {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a>

					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<li><a href="join.jsp">회원가입</a></li>
					</ul></li>
			</ul>
			<%
			} else {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a>

					<ul class="dropdown-menu">
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul></li>
			</ul>
			<%
			}
			%>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align:center; border:1px sold #ddd">
			<thead>
			<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			</tr>
			</thead>
			<tbody>
<%
	for(int i = 0; i < list.size(); i++){
%>			
			<tr>
			<td><%=list.get(i).getBbsID() %></td>
			<td><a href="view.jsp?bbsID=<%=list.get(i).getBbsID() %>"><%=list.get(i).getBbsTitle() %></a></td>
			<td><%=list.get(i).getUserID() %></td>
			<td><%=list.get(i).getBbsDate() %></td>
			</tr>
<%} %>
			</tbody>
			</table>
			<!-- 페이징 처리 영역 -->
			<%
			if(pageNumber != 1){
			%>
			<a href="bbs.jsp?pageNumber=<%=pageNumber - 1 %>"
				class="btn btn-success btn-arraw-left">이전</a>
			<%
			} if (nextPage){
			%>
			<a href="bbs.jsp?pageNumber=<%=pageNumber + 1 %>"
				class="btn btn-success btn-arraw-left">다음</a>
			<%
			}
			%>
			
			
			<a href="write" class="btn btn-primary pull-right" role="button">글쓰기</a>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>