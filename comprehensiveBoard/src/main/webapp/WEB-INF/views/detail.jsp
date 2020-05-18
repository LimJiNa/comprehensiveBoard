<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<button type="button" id="modifyCheckPwBtn" class="btn btn-primary">수정</button>
			<button type="button" id="removeBtn" class="btn btn-warning">삭제</button>
			<button type="button" id="listBtn" class="btn btn-danger">목록</button>
		</div>
		<div class="panel-body">
		    <form role="form" method="post" enctype="multipart/form-data" class="form-horizontal form-border">
				<div class="form-group">
					<label class="col-sm-3 control-label">제목</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="title" id="title" value="${data.title}" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">작성자</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="writer" id="writer" value="${data.writer}" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">내용</label>
					<div class="col-sm-6">
						<textarea class="form-control" cols="30" rows="10" name="content" id="content" readonly="readonly">${data.content}</textarea>
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
					</div>
				</div>
		    </form>
	    </div>
    </div>
    
    <!-- reply -->
	<!-- <div class="panel panel-info" style="width: 80%; margin: auto; text-align: center; text-align: left;">
		<div class="panel-heading">
			<h3 class="panel-title" id="panel-title">Comment</h3>
		</div>
		<div class="panel-body">
			<label for="replyWriter">작성자</label>
			<input type="text" class="form-control" id="replyWriter">
			<label style="margin-top:10px;" for="replyContent">내용</label>
			<input type="text" class="form-control" id="replyContent">
		</div>
		<div>
			<div class="panel-footer">
				<button type="button" class="btn btn-primary" id="replyregisterBtn">작성</button>
			</div>
		</div>
	</div> -->
	
	<!-- reply 페이징 -->
	<%-- <ul class="timeline">
	<li class="time-label" id="repliesBtn"><span class="bg-green">Replies List<small id="replycntSmall"> [${boardVO.replycnt}]</small></span></li>
	</ul>
	<div class="text-center">
	<ul id="pagination" class="pagination pagination-sm no-margin"></ul>
	</div> --%>
    
    <!-- start check Modal -->
    <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">비밀번호확인</h4>
                </div>
                <form class="form-horizontal" role="form" method="post" action="pwCheck" id="modifyPwcheckForm">
                <div class="modal-body">
                	 <input type="hidden" class="form-control" id="actionType" name="actionType" value="">
                     <input type="hidden" class="form-control" id="seq" name="seq" value="${data.seq}">
                     <input type="hidden" class="form-control" id="fileSeq" name="fileSeq" value="${data.fileSeq}">
                     <div class="form-group">
                         <label for="inputPassword3" class="col-sm-2 control-label">비밀번호</label>
                         <div class="col-sm-10">
                             <input type="password" class="form-control" id="password" name="password">
                         </div>
                     </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
                    <button type="button" class="btn btn-primary" id="modifycheckBtn">확인</button>
                </div>
                </form>
            </div>
        </div>
    </div>
    <!-- end check Modal -->
    
	<!-- start reply Modal -->
	<!-- <div id="replyModifyModal" class="modal modal-primary fade" role="dialog">
		<div class="modal-dialog">
		Modal content
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body" data-replySeq>
					<p><input type="text" id="replytext" class="form-control"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" id="replyModBtn">Modify</button>
					<button type="button" class="btn btn-danger" id="replyDelBtn">Delete</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div> -->
	<!-- end reply Modal -->
		
</body>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="../resources/common.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
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
			
			$("#keyword").keydown(function(key) {
		        if (key.keyCode == 13) {
					search();
		        }
		    });
			
			$("#modifyCheckPwBtn").click(function(){
				$("#actionType").val("modify");
	        	$("div.modal").modal();
	        });
			
			$("#removeBtn").click(function(){
				$("#actionType").val("remove");
	        	$("div.modal").modal();
	        });
			
			$("#listBtn").on("click", function(){
				var plus = "list?page=${page}&perPageNum=${perPageNum}&keyword=${keyword}";
				for(var i = 0; i < getParameters("searchType").length; i++){
					plus = plus + "&searchType=" + getParameters("searchType")[i];
				}
				self.location = plus;
			});
			
	        $("#modifycheckBtn").click(function(){
	        	$("#modifyPwcheckForm").submit();
	        });
	        
	        var result = '${msg}';
	        
	        if(result == 'success') {
				alert("처리가 완료되었습니다.");
			}
			
			if(result == 'fail') {
				alert("비밀번호가 일치하지 않습니다.");
			}
		});
	</script>
	
	<script id="template" type="text/x-handlebars-template">
		{{#each .}}
		<li class="replyLi" data-replySeq={{replySeq}}>
			<i class="fa fa-comments bg-blue"></i>
			<div class="fimeline-item">
				<span class="time">
					<i class="fa fa-clock-o"></i>{{prettifyDate replyWritedate}}
				</span>
				<h3 class="timeline-header"><strong>{{replySeq}}</strong> -{{replyWriter}}</h3>
				<div class="timeline-body">{{replyContent}}</div>
				<div class="timeline-footer">
					<a class="btn btn-primary btn-xs"><data-toggle="modal" data-target="replyModifyModal">수정</a>
				</div>
		</li>
		{{/each}}
	</script>
	
	<script>
	/*$(document).ready(function() {
		$("replyRegisterBtn").click(function(){
			var	replyWriterObj = $("replyWriter");
			var replyContentObj = $("replyContent");
			var replyWriter = replyWriterObj.val();
			var replyContent = replyContentObj.val();
			
			console.log(JSON.stringify({replySeq:replySeq, replyWriter:replyWriter, replyContent:replyContent}));
			
			$.ajax({
				type:"post",
				url:"/"
			})
		})
	}*/
	</script>
</html>