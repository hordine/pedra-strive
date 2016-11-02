<%@ attribute name="provinces" required="false" type="java.util.List"%>
<%@ attribute name="cancelUrl" required="false" type="java.lang.String"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<form:form method="post" commandName="addressForm" id="DEaddressForm">
	
		<form:hidden path="addressId" class="add_edit_delivery_address_id" status="${not empty suggestedAddresses ? 'hasSuggestedAddresses' : ''}"/>
		<input id="countryIso" name="countryIso" type="hidden"/>
		<input id="regionIso" name="regionIso" value="" type="hidden"/>
		<formElement:formSelectBox idKey="address.title" labelKey="address.title" path="titleCode" mandatory="true" skipBlank="false" skipBlankMessageKey="address.title.pleaseSelect" items="${titles}" selectedValue="${addressForm.titleCode}"/>
		<formElement:formInputBox idKey="address.firstName" labelKey="address.firstName" path="firstName" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.surname" labelKey="address.surname" path="lastName" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.line1" labelKey="address.line1" path="line1" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.line2" labelKey="address.line2" path="line2" inputCSS="text" mandatory="false"/>
		<formElement:formInputBox idKey="address.townCity" labelKey="address.townCity" path="townCity" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="postcode" inputCSS="text" mandatory="true"/>
		<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
			<c:choose>
				<c:when test="${showSaveToAddressBook}">
					<formElement:formCheckbox idKey="saveAddressInMyAddressBook" labelKey="checkout.summary.deliveryAddress.saveAddressInMyAddressBook" path="saveInAddressBook" inputCSS="add-address-left-input" labelCSS="add-address-left-label" mandatory="false"/>
				</c:when>
				<c:when test="${not addressBookEmpty && not isDefaultAddress}">
					<formElement:formCheckbox idKey="defaultAddress" labelKey="address.default" path="defaultAddress"
											  inputCSS="add-address-left-input" labelCSS="add-address-left-label" mandatory="false"/>
				</c:when>
			</c:choose>
		</sec:authorize>
	
	<div class="form_button-section">
		<c:choose>
			<c:when test="${edit eq true}">
				<ycommerce:testId code="multicheckout_saveAddress_button">
					<button class="form change_address_button"><spring:theme code="checkout.multi.saveAddress" text="Save address"/></button>
				</ycommerce:testId>
			</c:when>
			<c:otherwise>
				<ycommerce:testId code="multicheckout_saveAddress_button">
					<button class="form change_address_button"><spring:theme code="checkout.checkout.multi.next" text="Next &raquo;"/></button>
				</ycommerce:testId>
			</c:otherwise>
		</c:choose>

		<c:if test="${not noAddress}">
			<ycommerce:testId code="multicheckout_cancel_button">
				<c:url value="${cancelUrl}" var="cancel"/>
					<button class="form" type="button" onclick="window.location='${cancel}'"><spring:theme code="checkout.multi.cancel" text="Cancel"/></button>
			</ycommerce:testId>
		</c:if>
	</div>
</form:form>
