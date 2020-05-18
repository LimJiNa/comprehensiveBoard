<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
	<title>부트스트랩 101 템플릿</title>
	
	<!-- 부트스트랩 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	
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
			<button type="button" id="modifyBtn" class="btn btn-primary">수정</button>
			<button type="button" id="listBtn" class="btn btn-danger">목록</button>
		</div>
		<div class="panel-body">
		    <form action="modify" role="form" method="post" enctype="multipart/form-data" class="form-horizontal form-border" id="modifyForm">
		    	<input type="hidden" class="form-control" name="seq" id="seq" value="${data.seq}">
				<div class="form-group">
					<label class="col-sm-3 control-label">제목</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="title" id="title" value="${data.title}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">작성자</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="writer" id="writer" value="${data.writer}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">비밀번호</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" name="password" id="password" value="${data.password}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">내용</label>
					<div class="col-sm-6">
						<textarea class="form-control" cols="30" rows="10" name="content" id="content">${data.content}</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">작성일자</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="writedate" id="writedate" value="${data.writedate}" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">첨부파일</label>
					<div class="col-sm-6">
						<a class="form-control" href="/download/${data.fileSeq}">${data.originalName}</a>
						<input type="file" name="uploadFile" id="uploadFile" class="form-control">
						<input type="hidden" class="form-control" name="fileSeq" id="fileSeq" value="${data.fileSeq}">
					</div>
				</div>
		    </form>
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
					alert(param);
				});
				
				self.location = param;
			}
			
			$("#searchBtn").on("click", search);
			
			$("#keyword").keydown(function(key) {
		        if (key.keyCode == 13) {
					search();
		        }
		    });
			
			$("#listBtn").on("click", function(){
				var plus = "list?page=${page}&perPageNum=${perPageNum}&keyword=${keyword}";
				for(var i = 0; i < getParameters("searchType").length; i++){
					plus = plus + "&searchType=" + getParameters("searchType")[i];
					alert(plus);
				}
				self.location = plus;
			});
			
	        $("#modifyBtn").click(function(){
	        	
	        	$("#modifyForm").submit();
	        });
		});
	</script>
</html>