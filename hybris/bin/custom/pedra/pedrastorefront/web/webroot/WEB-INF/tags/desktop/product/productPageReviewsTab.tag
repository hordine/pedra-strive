<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url value="${product.url}/reviewhtml/3" var="getPageOfReviewsUrl"/>
<c:url value="${product.url}/reviewhtml/all" var="getAllReviewsUrl"/>
<script type="text/javascript">
/*<![CDATA[*/
	var showReviewForm = <c:out value="${showReviewForm}" default="false"/>;
	var getPageOfReviewsUrl = '${getPageOfReviewsUrl}';
	var getAllReviewsUrl = '${getAllReviewsUrl}';
/*]]>*/
</script>


<div id="reviews"></div>
<div id="write_reviews" style="display:none">
	<ul class="review_actions">
		<li><a href="#" id="read_reviews_action"><spring:theme code="review.back"/></a></li>
	</ul>
	<div class="write_review">
		<h3><spring:theme code="review.write.title"/></h3>
		<p><spring:theme code="review.write.description"/></p>
		<p><spring:theme code="review.required"/></p>
		<c:url value="${product.url}/review" var="productReviewActionUrl"/>
		<form:form method="post" action="${productReviewActionUrl}" commandName="reviewForm">
			<div class="write_review_container">
				<formElement:formInputBox idKey="review.headline" labelKey="review.headline" path="headline" inputCSS="text" mandatory="true"/>
				<formElement:formTextArea idKey="review.comment" labelKey="review.comment" path="comment" areaCSS="textarea" mandatory="true"/>
				<spring:bind path="rating">
					<c:if test="${not empty status.errorMessages}">
						<span class="form_field_error">
					</c:if>
					<div class="form_field-label"><label><spring:theme code="review.rating"/>:</label></div>
					<div id="stars-wrapper">
						<form:select path="rating" >
							<form:option value='1'>1/5</form:option>
							<form:option value='2'>2/5</form:option>
							<form:option value='3'>3/5</form:option>
							<form:option value='4'>4/5</form:option>
							<form:option value='5'>5/5</form:option>
						</form:select>
					</div>
					<div><form:errors path="rating" /></div>
					<c:if test="${not empty status.errorMessages}">
						</span>
					</c:if>
				</spring:bind>
				<div class="form_field-label"><label for="alias"><spring:theme code="review.alias"/>:</label></div>
				<div class="form_field-input"><form:input path="alias" /></div>
			</div>
			<div style="clear:both;"></div>
			<div>
				<button class="form" type="submit" value="<spring:theme code="review.submit"/>"><spring:theme code="review.submit"/></button>
			</div>
		</form:form>
	</div>
</div>


