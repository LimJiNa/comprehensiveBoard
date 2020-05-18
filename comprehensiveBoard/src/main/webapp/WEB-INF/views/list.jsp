<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head profile="http://www.w3.org/2005/10/profile">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
	<title>부트스트랩 101 템플릿</title>
	
	<!-- 부트스트랩 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="icon" type="image/png" href="http://example.com/myicon.png">
	
	<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
	<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
	<!--[if lt IE 9]>
	  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<div class="panel panel-default" style="width: 80%; margin: auto; text-align: center;">
		<div class="panel-heading" style="text-align: right; padding-right: 30px;">
			<button type="button" id="writeBtn" class="btn btn-primary">글작성</button>
		</div>
		<div class="panel-body">
			<table class="table" style="height: 100px; margin: auto; text-align: center; border: 1px;">
				<tr>
					<td>순번</td>
					<td>제목</td>
					<td>작성자</td>
					<td>작성일</td>
					<td>조회수</td>
				</tr>
				<c:if test="${fn:length(data) > 0}">
					<c:forEach items="${data}" var="boardVo" varStatus="status">
						<tr onclick="location.href='detail${pageMaker.makeSearch(pageMaker.cri.page)}&seq=${boardVo.seq}'" style="cursor:pointer">
							<td>${boardVo.seq}</td>
							<td>${boardVo.title}</td>
							<td>${boardVo.writer}</td>
							<td>${boardVo.writedate}</td>
							<td>${boardVo.hit}</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${fn:length(data) < 1}">
					<tr>
						<td colspan="5">검색 결과가 없습니다</td>
					</tr>
				</c:if>
			</table>
		</div>
		<div style="text-align: right; padding-right: 30px;" class="checkList">
			제목<input type="checkbox" name="searchType" id="searchType" value="title" <c:out value="${cri.searchType eq 'title' ? 'checked' : ' ' }" />>
			작성자<input type="checkbox" name="searchType" id="searchType" value="writer" <c:out value="${cri.searchType eq 'writer' ? 'checked' : ' ' }" />>
			내용<input type="checkbox" name="searchType" id="searchType" value="content" <c:out value="${cri.searchType eq 'content' ? 'checked' : ' ' }" />>
			작성일자<input type="checkbox" name="searchType" id="searchType" value="writedate" <c:out value="${cri.searchType eq 'writedate' ? 'checked' : ' ' }" />><br>
            <input type="text" id="keyword" value="${cri.keyword}">
            <button type="button" id="searchBtn">검색</button>
		</div>
		<div>
			<ul class="pagination">
				<c:if test="${pageMaker.prev}">
					<li><a href="list${pageMaker.makeSearch(pageMaker.startPage-1)}">&laquo;</a></li>
				</c:if>
				
				<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
					<li
						<c:out value="${pageMaker.cri.page == idx?'class=active':''}"/>>
						<a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
					</li>
				</c:forEach>
				
				<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
					<li><a href="list${pageMaker.makeSearch(pageMaker.endPage+1)}">&raquo;</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</body>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="../resources/common.js"></script>
	<script>
		$(document).ready(function() {
			function search(){
				var param = "list"
					+ '${pageMaker.makeQuery(1)}'
					+ "&keyword=" + encodeURIComponent($('#keyword').val());
					
				$("input[name=searchType]:checked").each(function(){
					param = param + "&searchType=" + $(this).val();
				});
				
				self.location = param;
			}
			
			$("#searchBtn").on("click", search);
			
			<c:forEach items="${cri.searchType}" var="type">
			   inputData( "[name=searchType]", "${type}" );
			</c:forEach>
			
			$("#keyword").keydown(function(key) {
		        if (key.keyCode == 13) {
					search();
		        }
		    });
			
			$("#writeBtn").on("click", function(){
				self.location = "writeForm";
			});
		});
		
		var result = '${msg}';
		
		if(result == 'success') {
			alert("처리가 완료되었습니다.");
		}
		
		if(result == 'fail') {
			alert("비밀번호가 일치하지 않습니다.");
		}
	</script>
</html>