<%@ include file="common/header.jspf"%>
<c:url var="delete_url" value="/user/delete/" />
<!-- Modal -->
<div class="modal fade" id="delete-modal" tabindex="-1" role="dialog"
	aria-labelledby="modalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Fechar">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="modalLabel">
					<spring:message code="titleDel.message" />
				</h4>
			</div>
			<div class="modal-body">
				<spring:message code="confirmDel.message" />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary"
					onclick="deleteUser('${delete_url}' + id_global);"
					data-dismiss="modal">Sim</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
			</div>
		</div>
	</div>
</div>
<!-- /.modal -->

<h2>
	<spring:message code="users.message" />
</h2>
<hr align="center" width="100%" size="1" color=red>


<div class="col-md-3">
	<h2>Localizar</h2>
</div>

<div class="col-md-6">
	<c:url var="post_url" value="/user/list" />
	<form:form action="${post_url}" method="post" role="form"
		modelAttribute="criteria">

		<div class="input-group h2">
			<form:input path="criteria" class="form-control" id="name"
				placeholder="Critério" />
			<span class="input-group-btn">
				<button class="btn btn-primary" type="submit">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</span>

		</div>
	</form:form>
</div>

<div class="col-md-3">
	<a href="<c:url value="/user/register"/>"
		class="btn btn-primary pull-right h2"><spring:message
			code="newUser.message" /></a>
</div>

<c:if test="${not empty userList}">

	<div id="list" class="row">
		<div class="table-responsive col-md-12">

			<table class="table table-striped" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>e-mail</th>
						<th>Última atualização</th>
						<th class="actions">Ações</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userList}" var="user" varStatus="status">
						<tr>
							<td>${ user.id}</td>
							<td>${ user.name}</td>
							<td>${ user.email}</td>
							<td>${ user.timestamp}</td>
							<td class="actions"><a class="btn btn-success btn-xs"
								href="view.html">Visualizar</a> <a
								class="btn btn-warning btn-xs" href="edit.html">Editar</a>
								<button type="button" class="btn btn-danger btn-xs"
									data-toggle="modal" data-target="#delete-modal"
									onclick="id_global=${user.id};location.href='#';">Excluir</button></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
	<div id="msg" class="vol-md-1 validation-summary-errors text-danger">
	</div>
	<!-- /#list -->

	<div id="bottom" class="row">
		<div class="col-md-12">

			<ul class="pagination">
				<c:choose>
					<c:when test="${page==1}">
						<li class="disabled"><a>Anterior</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="<c:url value="/user/list/${page-1}?criteria=${criteria.criteria}"/>">Anterior</a></li>
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="1" end="${pages}">
					<c:choose>
						<c:when test="${i==page}">
							<li class="disabled"><a>${i }</a></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="<c:url value="/user/list/${i}?criteria=${criteria.criteria}"/>">${i }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${page==pages}">
						<li class="disabled"><a>Próximo</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="<c:url value ="/user/list/${page+1}?criteria=${criteria.criteria}"/>">Próximo</a></li>
					</c:otherwise>
				</c:choose>

			</ul>
			<!-- /.pagination -->
		</div>
	</div>
	<!-- /#bottom -->
</c:if>


<%@ include file="common/footer.jspf"%>