<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
</html>
<table class="table table-bordered">
<thead>
		<tr>
			<th>행번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>나이</th>
			<th>주소</th>
			<th>전화번호</th>
			<th>직업</th>
			<th>성별</th>
			<th>혈액형</th>
			<th>메일</th>
		</tr>
	</thead>
	<tbody id="listBody">
	</tbody>
	<tfoot>
	<tr>
			<td colspan="7">
				<div class="form-inline mb-3 justify-content-center">
					<select id="searchType" class="form-control mr-2">
						<!-- <option value="">전체</option>
						<option value="title">제목</option>
						<option value="writer">작성자</option>
						<option value="content">내용</option> -->
					</select>
					<input type="text" id="searchWord"  class="form-control mr-2"
						value="${param.searchWord }"
					/>
					<input type="button" value="검색" id="searchBtn" class="btn btn-info mr-2"/>
					<input type="button" value="새글쓰기" class="btn btn-info" 
						onclick="location.href='<c:url value='/alba/albaInsert.do'/>';"
					/>
					
				</div>
					<div id="pagingArea">
				
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm">
	<input type="text" name="page" />
<%-- 	<input type="text" name="searchType" value="${param.searchType }"/>
	<input type="text" name="searchWord" value="${param.searchWord }"/> --%> <!-- get:form제공 post:data처리 -->
</form> 

<script type="text/javascript">

//새글쓰기 버튼 눌렀을때
/*
$("#insertBtn").on("click",function(){
	location.href="<c:url value='/board/boardInsert.do' />";
});
*/

var searchTypeTag = $("#searchType");
searchTypeTag.val("${param.searchType }");
var searchWordTag = $("#searchWord");


var listBody = $("#listBody");
var pagingArea = $("#pagingArea");
var searchForm = $("#searchForm").on("submit", function(event){ //비동기 요청으로 바꾸기 1.form에대한 submit 취소
	event.preventDefault();
	//코드사용해서 비동기로 요청발생시키기 :searchForm을 submit했을때 상황과 동일하게
	let queryString = $(this).serialize();
	let action = $(this).attr("action");
	let method = $(this).attr("method");
	if(!action) action = "<c:url value='/alba/albaList.do' />";
	if(!method) method = "get";
	$.ajax({
		url : action, //지금은 action없기때문에 생략가능 -> 현재 브라우저의 주소값
		method : method, //get방식 생략가능
		data : queryString,
		dataType : "json", //Accept(요청헤더), Content-Type(응답헤더) => 둘이한쌍
		success : function(resp) { //resp : pagingVO (Controller참고)
//			console.log(resp);
			let dataList = resp.dataList; //albaList
			let trTags = [];
			if(dataList.length>0){ //있을때
				$(dataList).each(function(index, alba){ //index, prodVO

					let eachTr = $("<tr>")
					eachTr.append(
						$("<td>").text(alba.rnum),	
						$("<td>").text(alba.al_id),	
						$("<td>").html(
							$("<a>").attr({
								"href":"<c:url value='/alba/albaView.do'/>?what="+alba.al_id
							})
							.text(alba.al_name)
						),	
						$("<td>").text(alba.al_age),	
						$("<td>").text(alba.al_address),	
						$("<td>").text(alba.al_hp),	
						$("<td>").text(alba.al_career),
						$("<td>").text(alba.al_gen),
						$("<td>").text(alba.al_btype),
						$("<td>").text(alba.al_mail)
					);
					trTags.push(eachTr);
				});
			}else{
				trTags.push( //만든 tr태그 trTags에 넣음
					$("<tr>").html(
						$("<td>").attr({
							colspan:"10"
						}).text("게시글없음.")
					)
				);
			}
			listBody.empty();
			listBody.append(trTags);
			//페이징 바꾸기
			let pagingHTML = resp.pagingHTML;
			pagingArea.empty(); //**
			pagingArea.html(pagingHTML);
		},
		error : function(xhr) {
			console.log(xhr.status);
		}
	});
	return false;
});

//검색버튼 눌렀을때
var searchBtn = $("#searchBtn").on("click", function(){
	let searchType = searchTypeTag.val();
	let searchWord = searchWordTag.val();
	searchForm.find("[name='searchType']").val(searchType); 
	searchForm.find("[name='searchWord']").val(searchWord); 
	searchForm.find("[name='page']").val(1);
	searchForm.submit();
});

//페이지 넘버 클릭했을때
pagingArea.on("click","a",function(event){
	event.preventDefault;
	
	//this로부터 pageNumber꺼내옴
	let page = $(this).data("page");
	searchForm.find("[name='page']").val(page);
	searchForm.submit();
	return false; //안전하게 한번 더 
});

searchForm.submit(); //검색버튼 누르지않아도 searchForm을통해 요청발생 가능 -동기요청과 비동기요청 분리
					//동기요청은 껍데기(디자인)만 가져옴/비동기요청은 껍데기에 넣을 데이터 가져옴
</script>
</body>