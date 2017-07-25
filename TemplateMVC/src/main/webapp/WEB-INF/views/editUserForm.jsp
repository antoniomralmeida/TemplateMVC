<%@ include file="common/header.jspf"%>
<h2>Editar usuário.</h2>
<hr align="center" width="100%" size="1" color=red>

<div class="row">
	<div class="col-md-8">
		<section id="registerForm">
			<c:url var="post_url" value="/user/edit" />
			<form:form class="form-horizontal" method="post" action="${post_url}"
				modelAttribute="user" role="form">
				<h4>Alterar dados do usuário.</h4>

				<div class="validation-summary-errors text-danger">
					<ul>
						<li style="display: none"><form:errors path="*" /></li>
					</ul>
				</div>
				<form:input type="hidden" path="id" id="id" />

				<div class="form-group">
					<form:label path="name" class="col-md-2 control-label" for="name">Nome</form:label>
					<div class="col-md-10">
						<form:input path="name" class="form-control" required="required" />
					</div>
					<form:errors path="name"
						cssClass="field-validation-valid text-danger" />
				</div>

				<div class="form-group">
					<form:label path="email" class="col-md-2 control-label" for="Email">Email</form:label>
					<div class="col-md-10">
						<form:input path="email" class="form-control" required="required"
							readonly="true" />
					</div>
					<form:errors path="email"
						cssClass="field-validation-valid text-danger" />
				</div>

				<div class="form-group">
					<form:label path="pwd" class="col-md-2 control-label">senha</form:label>
					<div class="col-md-10">
						<form:input path="pwd" type="password" class="form-control"
							required="required" />
					</div>
					<form:errors path="pwd"
						cssClass="field-validation-valid text-danger" />
				</div>

				<div class="form-group">
					<form:label path="userProfiles" class="col-md-2 control-label">Profile</form:label>
					<div class="col-md-10">
						<form:select path="userProfiles" items="${roles}" multiple="true"
							itemValue="id" itemLabel="type" class="form-control input-sm" />
					</div>
					<form:errors path="userProfiles"
						cssClass="field-validation-valid text-danger" />
				</div>


				<div class="form-group">
					<form:label path="state" class="col-md-2 control-label">Estado</form:label>
					<div class="col-md-10">
						<form:select path="state">
							<form:option value="NONE"> --SELECT--</form:option>
							<form:options items="${states}"></form:options>
						</form:select>
					</div>
					<form:errors path="state"
						cssClass="field-validation-valid text-danger" />
				</div>

				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<input type="submit" class="btn btn-default btn-main"
							value="Confirmar"> <a class="btn btn-default"
							href="<c:url value="/wellcome"/>">Cancelar</a>

					</div>
				</div>
			</form:form>
		</section>
	</div>
</div>
<%@ include file="common/footer.jspf"%>