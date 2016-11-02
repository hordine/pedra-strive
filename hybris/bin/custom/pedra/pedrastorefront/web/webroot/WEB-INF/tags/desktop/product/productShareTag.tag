<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!-- Lockerz Share BEGIN -->
<div class="a2a_kit a2a_default_style">
	<a class="a2a_dd" href="http://www.addtoany.com/share_save" title="<spring:theme code="product.share.viewMoreServices"/>" >Share</a>
	<span class="a2a_divider"></span>
	<a class="a2a_button_facebook" title="<spring:theme code="product.share.sendToFacebook"/>"></a>
	<a class="a2a_button_twitter" 	title="<spring:theme code="product.share.tweet"/>"></a>
	<a class="a2a_button_email" title="<spring:theme code="product.share.email"/>"></a>
	<a class="" title="<spring:theme code="product.share.print"/>" >
	<img src="${commonResourcePath}/images/print.png" alt="Print" onclick="window.print()" > </a>
</div>
<script type="text/javascript" src="http://static.addtoany.com/menu/page.js"></script>
<!-- Lockerz Share END -->