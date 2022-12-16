<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-spa.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
var startNo = 0;
var fetchList = function(){
	$.ajax({
		url: "${pageContext.request.contextPath }/guestbook/api/list/" + startNo, // get방식으로 할 때 뒤에 ? 또는  + $.param(formData)
		async: true,   // 비동기할거임
		type: "get",   // GET / POST
		dataType: "json",
		success: function(response){
			console.log(response); // console에 5개씩 넘어오나 확인
			
			response.forEach(function(vo){
				var htmls = 
				"<li data-no='" + vo.no +"'>" +
				"<strong>지나가다가</strong>" +
				"<p>" + vo.contents.replace(/\n/gi, "<br>") + "<p>" +  // 정규표현식 사용해야함  /~/: 하나 /~/g : 모든 것  /~/gi: 대소문자 가리지말고 다
				"<strong></strong>" +
				"<a href='' data-no='"+ vo.no +"'>삭제</a> " +
				"</li>";
				$("#list-guestbook").append(htmls);
			}); // 스크롤을 최대로 내려서 5개 리스트가 보여준 후 다음 리스트 보여주는 것만 해결하면 됨
			
			startNo = $("#list-guestbook li").last().data("no");
			console.log(startNo);
		},
		error: function(xhr, status, error){
			console.error(status, error);
		}
	});
}

$(function(){
	$("#add-form").submit(function(event){
		event.preventDefault();  // 방명록 insert 방식과 맞지 않으면 페이지 안 넘어감
	});
	$(window).scroll(function(){
		var windowHeight = $(this).height();
		var documentHeight = $(document).height();
		var scrollTop = $(this).scrollTop();
		
		if(scrollTop + windowHeight + 10 > documentHeight){
			fetchList();
		}
	});
	// 최초 리스트 가져오기
	fetchList();
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" placeholder="이름">
					<input type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook"></ul>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form>
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			<div id="dialog-message" title="" style="display:none">
  				<p></p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>