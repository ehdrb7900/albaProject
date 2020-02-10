<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
	<table class="table table-bordered">
		<tr>
			<th>알바생코드</th>
			<td>${alba.al_id}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${alba.al_name}</td>
		</tr>
		<tr>
			<th>나이</th>
			<td>${alba.al_age}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${alba.al_address}</td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td>${alba.al_hp}</td>
		</tr>
		<tr>
			<th>특기사항</th>
			<td>${alba.al_spec}</td>
		</tr>
		<tr>
			<th>비고</th>
			<td>${alba.al_desc}</td>
		</tr>
		<tr>
			<th>학력</th>
			<td>${alba.gr_code}</td>
		</tr>
		<tr>
			<th>경력사항</th>
			<td>${alba.al_career}</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${alba.al_gen}</td>
		</tr>
		<tr>
			<th>혈액형</th>
			<td>${alba.al_btype}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${alba.al_mail}</td>
		</tr>
		<tr>
			<c:set value="${alba.grade }" var="grade"></c:set>
			<th>학력</th>
			<td>${grade.gr_name}</td>
		</tr>

		<tr>
			<th>자격증목록</th>
			<td>
				<table border=1 class="table table-bordered">
					<thead>
						<tr>
							<th>자격증 코드</th>
							<th>자격증 명</th>
						</tr>
					</thead>
					<tbody>
						<c:set value="${alba.licenseList }" var="licenseList"></c:set>
						<c:if test="${not empty licenseList }">
							<c:forEach items="${licenseList }" var="license">
								<tr>
									<td>${license.lic_code}</td>
									<td>${license.lic_name}</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty licenseList }">
							<tr>
								<td colspan="2">자격증 없음.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2"><c:url value="/alba/albaUpdate.do"
					var="albaUpdateURL">
					<c:param name="what" value="${alba.al_id }" />
				</c:url> <input type="button" value="수정" class="btn btn-success"
				onclick="location.href='${albaUpdateURL }';" /> <input
				type="button" id="deleteBtn" value="삭제" class="btn btn-success"
				data-toggle="modal" data-target="#deleteModal" /> <input
				type="button" value="목록으로" class="btn btn-info"
				onclick="location.href='<c:url value="/alba/albaList.do"/>';" /> <input
				type="button" value="뒤로가기" class="btn btn-info"
				onclick="history.back();" /></td>
		</tr>
	</table>

	<!-- <button data-toggle="modal" data-target="#exampleModalLong">눌러</button> -->
	<!-- Modal -->

	<input type="hidden" name="al_id" value="${alba.al_id}" />
	<input>
	<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLongTitle" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle"></h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<form id="deleteForm"
					action="<c:url value='/alba/albaDelete.do'/>"
					method="post">
					<input type="hidden" name="al_id" value="${alba.al_id }" />
					<div class="modal-footer">
						<h4>정말로 삭제하시겠습니까?</h4>
						<button type="submit" id="modalDelBtn" class="btn btn-primary">삭제</button>
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">닫기</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		//** form 리셋
		let deleteForm = $("#deleteForm");
		let deleteModal = $("#deleteModal").on("hidden.bs.modal", function() {
			deleteForm.get(0).reset();
		});

		//삭제버튼을 누르면 
		$("#modalDelBtn").on("click", function() {
			deleteForm.submit(); //Form submit
			deleteModal.modal("hide"); //모달 닫음
		});
	</script>
</body>
</html>