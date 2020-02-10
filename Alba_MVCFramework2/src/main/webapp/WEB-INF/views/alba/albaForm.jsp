<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>정보 입력 폼</title>
	<jsp:include page="/includee/preScript.jsp"/>
	<style type="text/css">
		.error{
			color: red;
		}
	</style>
</head>
<body>
	<form id="albaForm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="al_id" value="${alba.al_id }"/>
		<table class="table">
			<c:if test="${not empty alba.al_id }">
				<tr>
					<th>알바생코드</th>
					<td>
						<input type="text" name="al_id" value="${ alba.al_id }" required />
						<span class="error">${errors.al_id }</span>
					</td>
				</tr>
			</c:if>
			<tr>
				<th>이름</th>
				<td>
					<input type="text" name="al_name" value="${ alba.al_name }" required />
					<span class="error">${errors.al_name }</span>
				</td>
			</tr>
			<tr>
				<th>나이</th>
				<td>
					<input type="text" name="al_age" value="${ alba.al_age }" required />
					<span class="error">${errors.al_age }</span>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="al_address"
					value="${ alba.al_address }" required /> <span class="error">${errors.al_address }</span>
				</td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td>
					<input type="text" name="al_hp" value="${ alba.al_hp }" required />
					<span class="error">${errors.al_hp }</span>
				</td>
			</tr>
			<tr>
				<th>특기사항</th>
				<td>
					<textarea name="al_spec">${ alba.al_spec }</textarea>
					<span class="error">${errors.al_spec }</span>
				</td>
			</tr>
			<tr>
				<th>비고</th>
				<td>
					<textarea name="al_desc">${ alba.al_desc }</textarea>
					<span class="error">${errors.al_desc }</span>
				</td>
			</tr>
			<tr>
				<th>학력</th>
				<td>
					<select name="al_grade">
						<option value="">학력 선택</option>
						
						<c:forEach var="grade" items="${gradeList }">
							<c:if test="${alba.gradeList['al_grade'] eq grade.gr_code }">
								<option value="${ grade.gr_code}" selected>${grade.gr_name }</option>
							</c:if>
						
							<c:if test="${alba.gradeList['al_grade'] ne grade.gr_code }">
								<option value="${ grade.gr_code}">${grade.gr_name }</option>
							</c:if>
						</c:forEach>	
					</select>
				</td>
			</tr>
			<tr>
				<th>경력사항</th>
				<td>
					<textarea name="al_career">${ alba.al_career }</textarea>
					<span class="error">${errors.al_career }</span>
				</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
					남성: <input type="radio" name="al_gen" value="M" />
					<br>
					여성: <input type="radio" name="al_gen" value="F" />
				</td>
			</tr>
			<tr>
				<th>혈액형</th>
				<td>
					<select name="al_btype">
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="O">O</option>
						<option value="AB">AB</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input type="text" name="al_mail" value="${ alba.al_mail }"	required />
					<span class="error">${errors.al_mail }</span>
				</td>
			</tr>
			<tr>
				<th>자격증</th>
				<td>
					<div id="licDiv">
					<select name="lic_code">
						<option value="">자격증 선택</option>
						
						<c:forEach var="license" items="${licenseList }">
							<c:if test="${alba.licenseList['lic_code'] eq grade.gr_code }">
								<option value="${license.lic_code}" selected>${license.lic_name }</option>
							</c:if>
							
							<c:if test="${alba.licenseList['lic_code'] ne grade.gr_code }">
								<option value="${license.lic_code}" >${license.lic_name }</option>
							</c:if>
						</c:forEach>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="file" name="lic_img" accept="image/*" />
					<br>
					</div>
					
					<input type="button" id="addLicBtn" value="자격증 추가">
				</td>
				
			<tr>
				<td colspan="2">
					<input type="submit" value="저장"/>
					<input type="button" value="뒤로가기" id="returnBtn"/>
				</td>
			</tr>
		</table>
	</form>


	<script>
		var licDiv = $("#licDiv");
		$("#addLicBtn").on("click", function(){
			var licClone = licDiv.clone();
			$(this).before(licClone);
		// 위에서 선택한 자격증은 아래 select박스에서는 hide시켜야함
		// 자격증 최대 갯수(6개) 이상으로는 추가가 안되게 해야함
		})
		
		("#returnBtn").on("click", function(){
			location.href="<c:url value='alba/albaList.do'/>"
		})
		
	</script>
	

</body>
</html>