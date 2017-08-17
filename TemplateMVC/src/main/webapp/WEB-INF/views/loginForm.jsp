<%@ include file="common/header.jspf"%>
<h2>
	<spring:message code="login.message" />
</h2>
<hr align="center" width="100%" size="1" color=red>

<div class="row">
	<div class="col-md-4">
		<section id="loginForm">
			<c:url var="post_url" value="/login" />
			<form:form method="post" action="${post_url}"
				role="form">
				<div class="validation-summary-errors text-danger">${errorMessage}</div>

				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>
				<div class="form-group">
						<input id="username" name="username" class="form-control"
							required="required" placeholder="e-mail" type="text" value="" />
				</div>

				<div class="form-group">
						<input id="password" name="password" placeholder="password"
							type="password" class="form-control"  required="required"
							value="" />
				</div>
				<div class="form-group">
						<input type="submit" class="btn btn-primary" value="Login">
						<a class="btn btn-default"
							href="<c:url value="/user/register"/>"><spring:message
								code="register.message" /></a>

				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form:form>
		</section>
	</div>
</div>
<%@ include file="common/footer.jspf"%>