
<%@ include file="common/header.jspf"%>

<h1>${message}</h1>	
<h2><spring:message code="welcome.message" /> ${currentUser.name}!</h2>

<%@ include file="common/footer.jspf"%>