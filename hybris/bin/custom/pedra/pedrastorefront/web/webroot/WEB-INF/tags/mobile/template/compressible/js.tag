<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- jQuery --%>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.1.7.1.min.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.tmpl.1.0.0pre.min.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/jquery-ui-1.8.19.custom.min.js"></script>
<%-- ACCMOB --%>
<script type="text/javascript" src="${commonResourcePath}/js/accmob.common.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/accmob.menu.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/accmob.autocomplete.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/accmob.address.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/accmob.silentorderpost.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/accmob.termsandconditions.js"></script>

<%-- AddOn JavaScript files --%>
<c:forEach items="${addOnJavaScriptPaths}" var="addOnJavaScript">
    <script type="text/javascript" src="${addOnJavaScript}"></script>
</c:forEach>

