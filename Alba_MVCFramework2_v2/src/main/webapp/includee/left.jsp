<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul>
	<!-- <li><a data-command="GUGUDAN">구구단</a></li> -->								<!-- 방법1-1 -->
	<!-- <li><a href="javascript:handler('STANDARD');">StandardJSP</a></li> -->			<!-- 방법2 -->
	<!-- <li><a href="#" onclick="handler('IMAGESTREAMING');">이미지스트리밍</a></li> -->	<!-- 방법3 -->
	
	<li><a href="#" data-command="STANDARD">StandardJSP</a></li>	
	<li><a href="#" data-command="GUGUDAN">구구단</a></li>	
	<li><a href="#" data-command="IMAGESTREAMING">이미지스트리밍</a></li>	
	<li><a href="#" data-command="VIDEOSTREAMING">동영상스트리밍</a></li>	
	<li><a href="#" data-command="CALENDAR">달력</a></li>	
	<li><a href="#" data-command="SESSIONTIMER">세션타이머</a></li>	
	
</ul>
<form action="">
	<input type="hidden" name="command" /> <!-- 데이터 입력받기위한 용도X, 데이터 활용?위해 -> 보여줄필요X -->
</form>
<script type="text/javascript"> <!-- 방법1 -->
	var commandTag = $("[name='command']") 		//한번만 실행되게
	$("#left a").on("click", function(){
		let command =  $(this).data("command");	//var : 변수의 스코프 명확하지 않음 , let : 변수의 스코프 명확 , const : 상수 선언
		commandTag.val(command);
		commandTag.closest("form").submit();	//가장 가까운 위치에있는 엘리먼트
	});
</script>