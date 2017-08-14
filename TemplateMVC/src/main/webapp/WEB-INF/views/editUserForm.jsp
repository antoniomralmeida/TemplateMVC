<%@ include file="common/header.jspf"%>
<h2>
	<spring:message code="editUser.message" />
</h2>
<hr align="center" width="100%" size="1" color=red>

<div class="row">
	<div class="col-md-8">
		<section id="registerForm">
			<c:url var="post_url" value="/user/edit" />
			<form:form method="post" action="${post_url}"
				modelAttribute="user" role="form">

				<form:hidden path="id" id="id" />
				<form:hidden path="timestamp" id="timestamp" />


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
							required="required" readonly="true" />
					<form:errors path="email"
						cssClass="field-validation-valid text-danger" />
				</div>

				<div class="form-group">
					<form:label path="userProfiles">Profile</form:label>
						<form:select path="userProfiles" multiple="true" items="${roles}"
							itemValue="type" itemLabel="type" class="form-control input-sm">
						</form:select>
					<form:errors path="userProfiles"
						cssClass="field-validation-valid text-danger" />
				</div>

				<div class="form-group">
					<form:label path="state">Status</form:label>

					<form:select path="state">
						<form:options items="${states}"></form:options>
					</form:select>
					<form:errors path="state"
						cssClass="field-validation-valid text-danger" />
				</div>

				<div class="form-group">
						<input type="submit" class="btn btn-default btn-main"
							value="Confirmar"> <a class="btn btn-default"
							href="<c:url value="/wellcome"/>">Cancelar</a>
				
				</div>
			</form:form>
		</section>
	</div>
</div>
<%@ include file="common/footer.jspf"%>