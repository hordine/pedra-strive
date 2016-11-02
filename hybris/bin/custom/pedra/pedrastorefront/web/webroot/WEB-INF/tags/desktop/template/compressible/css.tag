<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- colorbox CSS --%>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/jquery.colorbox-1.3.16.css"/>

<%-- BeautyTips CSS --%>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/jquery.bt-0.9.5.css" />

<%-- blueprintcss --%>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/blueprint/screen.css" />

<%-- jQuery UI CSS --%>
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/jquery.ui.stars-3.0.1.css" />
<link rel="stylesheet" type="text/css" media="all" href="${commonResourcePath}/css/jquery.ui.autocomplete-1.8.18.css" />

<%--  AddOn Common CSS files --%>
<c:forEach items="${addOnCommonCssPaths}" var="addOnCommonCss">
    <link rel="stylesheet" type="text/css" media="all" href="${addOnCommonCss}" />
</c:forEach>

<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/css/changes.css" />

<%--  AddOn Theme CSS files --%>
<c:forEach items="${addOnThemeCssPaths}" var="addOnThemeCss">
    <link rel="stylesheet" type="text/css" media="all" href="${addOnThemeCss}" />
</c:forEach>