<%@ include file="common/header.jspf"%>
<h2><spring:message code="login.message" /></h2>
<hr align="center" width="100%" size="1" color=red>

<div class="row">
	<div class="col-md-8">
		<section id="loginForm">
			<c:url var="post_url" value="/login" />
			<form:form class="form-horizontal" method="post" action="${post_url}"
				role="form">
				<h4>Usar uma conta local para efetuar login.</h4>

				<div class="validation-summary-errors text-danger">

					${errorMessage}</div>

				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>
				<div class="form-group">
					<label for="email" class="col-md-2 control-label">E-mail</label>
					<div class="col-md-10">
						<input id="username" name="username" class="form-control"
							required="required" type="text" value="" />
					</div>
				</div>

				<div class="form-group">
					<label for="pwd" class="col-md-2 control-label">Password</label>
					<div class="col-md-10">
						<input id="password" name="password" type="password" class="form-control"
							required="required" value="" />
					</div>
				</div>



				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<input type="submit" class="btn btn-default btn-main"
							value="Efetuar login"> <a class="btn btn-default"
							href="<c:url value="/user/register"/>">Registre-se</a>

					</div>
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			</form:form>
		</section>
	</div>
</div>
<%@ include file="common/footer.jspf"%>