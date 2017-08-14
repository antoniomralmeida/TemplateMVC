<%@ include file="common/header.jspf"%>
<h2>
	<spring:message code="newUser.message" />
</h2>
<hr align="center" width="100%" size="1" color=red>

<div class="row">
	<div class="col-lg-12">
		<section id="registerForm">
			<c:url var="post_url" value="/user/register" />
			<form:form method="post" action="${post_url}" modelAttribute="user"
				role="form">
				<div class="validation-summary-errors text-danger">
					<ul>
						<li style="display: none"><form:errors path="*" /></li>
					</ul>
				</div>
				<div class="form-group">
					<form:input path="name" placeholder="Nome" class="form-control"
						required="required" />
					<form:errors path="name"
						cssClass="field-validation-valid text-danger" />
				</div>

				<div class="form-group">
					<form:input path="email" placeholder="e-mail" class="form-control"
						required="required" />
					<form:errors path="email"
						cssClass="field-validation-valid text-danger" />
				</div>

				<div class="form-group">
					<form:input path="pwd" placeholder="Password" type="password"
						class="form-control" required="required" />
					<form:errors path="pwd"
						cssClass="field-validation-valid text-danger" />
				</div>

				<div class="form-group">
					<input type="submit" class="btn btn-default btn-main"
						value="<spring:message code="confirm.message" />"> <a
						class="btn btn-default" href="<c:url value="/wellcome"/>"><spring:message
							code="cancel.message" /></a>
				</div>
			</form:form>
		</section>
	</div>
</div>
<%@ include file="common/footer.jspf"%>