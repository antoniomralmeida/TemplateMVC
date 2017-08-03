<%@ include file="common/header.jspf"%>
<c:choose>
	<c:when test="${not empty errorMsg}">
		<div class="alert alert-warning" role="alert">${errorMsg}</div>
	</c:when>
</c:choose>

<c:choose>
	<c:when test="${not empty message}">
		<div class="alert alert-success" role="alert">${message}</div>
	</c:when>
</c:choose>

<%@ include file="common/footer.jspf"%>