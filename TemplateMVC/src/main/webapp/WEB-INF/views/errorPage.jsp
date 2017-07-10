<%@ include file="common/header.jspf"%>

<h1>
<spring:message code="error.message" /></h1>
<h3>${errorMsg}</h3>
<h4>Failed URL:${url}</h4>
<h5>Exception:  ${exception.message}</h5>    
<%@ include file="common/footer.jspf"%>