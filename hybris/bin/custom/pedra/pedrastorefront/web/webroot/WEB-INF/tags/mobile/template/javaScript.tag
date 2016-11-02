<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="g" uri="http://granule.com/tags/accelerator" %>
<%@ taglib prefix="compressible" tagdir="/WEB-INF/tags/mobile/template/compressible" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="cms" tagdir="/WEB-INF/tags/desktop/template/cms" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/mobile/template" %>


<c:url value="/" var="siteRootUrl"/>


<template:javaScriptVariables />


<c:choose>
	<c:when test="${granuleEnabled}">
		<g:compress urlpattern="${encodingAttributes}">
			<compressible:js/>
		</g:compress>
	</c:when>
	<c:otherwise>
		<compressible:js/>
	</c:otherwise>
</c:choose>

<script type="text/javascript">
	/*<![CDATA[*/
	$(document).bind("mobileinit", function() {
		$.mobile.ajaxEnabled = false;
		$.mobile.ajaxLinksEnabled = false;
		$.mobile.selectmenu.prototype.options.nativeMenu = true;
		$.mobile.defaultPageTransition = "none";
		$.mobile.defaultDialogTransition = "none";
		$.mobile.minScrollBack = 250;
		$.mobile.loadingMessageTheme = "f";
	});
	/*]]>*/
</script>

<%-- jQuery mobile --%>
<%-- jQuery mobile does not work with granule (see https://code.google.com/p/granule/issues/detail?id=12 and https://code.google.com/p/granule/issues/detail?id=17) --%>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.mobile-1.1.0.min.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.mobile.simpledialog2.min.1.0.1-2012022700.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.mobile.autocomplete.custom.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.mobile.collapsiblelistview.hybris.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.ui.stars.min.3.0.1.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.form.2.67.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/mobile-tooltip.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.colorbox-1.3.16.js"></script>

<%-- ACCMOB --%>
<script type="text/javascript" src="${commonResourcePath}/js/accmob.messages.js"></script>

<script type="text/javascript" src="${commonResourcePath}/js/jquery.pstrength.custom-1.2.0.js"></script>

<script type="text/javascript">
    /*<![CDATA[*/
    $(function() {
        $('.strength').pstrength({ verdicts:["<spring:theme code="password.strength.veryweak" />",
                                             "<spring:theme code="password.strength.weak" />",
                                             "<spring:theme code="password.strength.medium" />",
                                             "<spring:theme code="password.strength.strong" />",
                                             "<spring:theme code="password.strength.verystrong" />"],
                                   tooShort: "<spring:theme code="password.strength.tooshortpwd" />",
                                   minCharText: "<spring:theme code="password.strength.minchartext"/>" });
    });
    /*]]>*/
</script>
<script type="text/javascript" src="${commonResourcePath}/js/accmob.password.js"></script>

<%-- Fix for SkipLinks (Needs to be loaded last)  --%>
<script type="text/javascript" src="${commonResourcePath}/js/accmob.skiplinks.js"></script>


<cms:previewJS cmsPageRequestContextData="${cmsPageRequestContextData}" />