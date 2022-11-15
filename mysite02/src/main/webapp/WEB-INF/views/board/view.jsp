<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	pageContext.setAttribute("newline", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${boardVo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td colspan=4>
							<div class="view-content">
								${boardVo.contents }
								${fn:replace(boardVo.contents, newline, "<br/>") }
							</div>
						</td>
					</tr>
				</table>
				<!-- authUser no와 userVo no 같게 하기 -->
				<!-- /board?a=view$no=10 -->
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board">글목록</a>
					<a href="${pageContext.request.contextPath }/board?a=modify">글수정</a>  <!-- authUser not empty 로그인시만 보이기+자기글만 -->
					<a href="${pageContext.request.contextPath }/board?a=relpy">답글</a>  <!-- authUser not empty 로그인시만 보이기 -->
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>