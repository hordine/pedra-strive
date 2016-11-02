<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- j query query 2.1.7 --%>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.query-2.1.7.js"></script>
<%-- jquery tabs dependencies --%>
<script type="text/javascript" src="${commonResourcePath}/js/jquery-ui-1.8.18.min.js"></script>
<%-- j carousel --%>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.jcarousel-0.2.8.min.js"></script>
<%-- j query templates --%>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.tmpl-1.0.0pre.min.js"></script>
<%-- j query block UI --%>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.blockUI-2.39.js"></script>
<%-- Galleriffic --%>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.galleriffic-2.0.1.js"></script>
<%-- colorbox --%>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.colorbox.custom-1.3.16.js"></script>
<%-- Slide Viewer --%>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.slideviewer.1.2.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.easing.1.3.js"></script>
<%-- Wait for images --%>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.waitforimages.min.js"></script>
<%-- Scroll to --%>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.scrollTo-1.4.2-min.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.ui.stars-3.0.1.min.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.form-3.09.js"></script>
<%-- BeautyTips  --%>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.bgiframe-2.1.2.min.js"></script>
<!--[if IE]><script type="text/javascript" src="${commonResourcePath}/js/excanvas-r3.compiled.js"></script>-->
<script type="text/javascript" src="${commonResourcePath}/js/jquery.bt-0.9.5-rc1.min.js"></script>
<%-- PasswordStrength  --%>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.pstrength.custom-1.2.0.js"></script>
<%-- Custom ACC JS --%>
<script type="text/javascript" src="${commonResourcePath}/js/acc.common.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.cms.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.product.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.refinements.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.storefinder.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.carousel.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.autocomplete.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.pstrength.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.password.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.cartpopup.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.producttabs.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.pickupinstore.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.userlocation.js"></script>
<c:if test="${showReviewForm}">
	<script type="text/javascript">
		$(function() {
			$("#prod_tabs").tabs( {selected: $('#tab_strip').children().size() - 1});
		});
	</script>
</c:if>
<c:if test="${!showReviewForm}">
	<script type="text/javascript">
		$(function() {
			$( "#prod_tabs" ).tabs({ selected: 0 });
		});
	</script>
</c:if>
<script type="text/javascript" src="${commonResourcePath}/js/acc.langcurrencyselector.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.paginationsort.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.mapscrollfix.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.mapstore.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.mapstores.js"></script>
<c:if test="${showCheckoutStrategies && not empty cartData.entries}" >
	<script type="text/javascript" src="${commonResourcePath}/js/acc.checkout.js"></script>
</c:if>
<script type="text/javascript" src="${commonResourcePath}/js/acc.cartremoveitem.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.payment.js"></script>

<script type="text/javascript" src="${commonResourcePath}/js/acc.placeorder.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.address.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.updatebillingaddress.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.deliverymodedescription.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.deliverymode.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.refresh.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.checkoutcartdetails.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.removepaymentdetails.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.checkoutpickupdetails.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.silentorderpost.js"></script>
<script type="text/javascript" src="${commonResourcePath}/js/acc.termsandconditions.js"></script>
<%-- AddOn JavaScript files --%>
<c:forEach items="${addOnJavaScriptPaths}" var="addOnJavaScript">
    <script type="text/javascript" src="${addOnJavaScript}"></script>
</c:forEach>

<%-- Fix for Webkit Browsers (Needs to be loaded last)  --%>
<script type="text/javascript" src="${commonResourcePath}/js/acc.skiplinks.js"></script>