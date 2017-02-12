<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page session="false" %>
<html>
<head>
    <title>Spitter</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/style.css" />">
</head>
<body>
<%--                            mannual SpEl--%>
<%--<security:authorize access="isAuthenticated() and hasRole('SPITTER') and principal.username == 'Leonti'">--%>
<%--better way of implementing above logic--%>
<security:authorize url="/spitter/">
    <%--Not rendered--%>
    <security:authentication property="principal.username" var="loginId" scope="request"/>
    <%--Rendered--%>
    <h1><security:authentication property="principal.username"/></h1>
</security:authorize>
<%--
authorities -   A collection of GrantedAuthority objects that represent the privileges granted to the user
credentials -   The credentials that were used to verify the principal (commonly, this is the user’s password)
details	    -   Additional information about the authentication (IP address, certificate serial number, session ID, and so on)
principal   -   The user’s principal
--%>

<h1><s:message code="spittr.welcome"/></h1>
<a href="<c:url value="/spittles" />">Spittles</a> |
<a href="<c:url value="/spitter/register" />">Register</a>
</body>
</html>
