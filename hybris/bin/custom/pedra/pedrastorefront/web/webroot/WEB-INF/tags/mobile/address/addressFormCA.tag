<%@ attribute name="provinces" required="true" type="java.util.List"%>
<%@ attribute name="cancelUrl" required="false" type="java.lang.String"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/mobile/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<form:form method="post" commandName="addressForm">
	<common:errors/>
	<form:hidden path="addressId" class="add_edit_delivery_address_id" status="${not empty suggestedAddresses ? 'hasSuggestedAddresses' : ''}"/>
	<input id="countryIso" name="countryIso" type="hidden"/>
	<ul class="mFormList">
		<li>
			<formElement:formSelectBox idKey="address.title" labelKey="address.title" path="titleCode" mandatory="true" skipBlank="false"
									   skipBlankMessageKey="address.title.pleaseSelect" items="${titles}" selectedValue="${addressForm.titleCode}"/>
		</li>
		<li><formElement:formInputBox idKey="address.firstName" labelKey="address.firstName" path="firstName" inputCSS="text" mandatory="true"/></li>
		<li><formElement:formInputBox idKey="address.surname" labelKey="address.surname" path="lastName" inputCSS="text" mandatory="true"/></li>
		<li><formElement:formInputBox idKey="address.line1" labelKey="address.line1" path="line1" inputCSS="text" mandatory="true"/></li>
		<li><formElement:formInputBox idKey="address.line2" labelKey="address.line2" path="line2" inputCSS="text" mandatory="false"/></li>
		<li><formElement:formInputBox idKey="address.townCity" labelKey="address.townCity" path="townCity" inputCSS="text" mandatory="true"/></li>
		<li>
			<formElement:formSelectBox idKey="address.region" labelKey="address.province" path="regionIso" mandatory="true"
										skipBlank="false" skipBlankMessageKey="address.selectProvince" items="${provinces}"
										itemValue="isocode" selectedValue="${addressForm.regionIso}"/>
		</li>
		<li><formElement:formInputBox idKey="address.postcode" labelKey="address.postalcode" path="postcode" inputCSS="text" mandatory="true"/></li>
		<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
			<c:choose>
				<c:when test="${showSaveToAddressBook}">
					<li><formElement:formCheckbox idKey="saveAddressInMyAddressBook" labelKey="checkout.summary.deliveryAddress.saveAddressInMyAddressBook" path="saveInAddressBook" inputCSS="add-address-left-input" labelCSS="add-address-left-label" mandatory="false"/></li>
				</c:when>
				<c:when test="${not addressBookEmpty && not isDefaultAddress}">
					<li><formElement:formCheckbox idKey="address.default" labelKey="address.default" path="defaultAddress"
												  inputCSS="add-address-left-input" labelCSS="add-address-left-label" mandatory="false"/></li>
				</c:when>
			</c:choose>
		</sec:authorize>
		<li>
			<div class="ui-grid-a">
				<c:choose>
					<c:when test="${not noAddress}">
						<div class="ui-block-a">
							<c:url value="${cancelUrl}" var="cancel"/>
							<a href="${cancel}" class="form" data-role="button" data-theme="d" data-icon="delete">
								<spring:theme code="checkout.multi.cancel" text="Cancel"/>
							</a>
						</div>
						<div class="ui-block-b">
							<ycommerce:testId code="save">						
								<button class="form" data-theme="c" data-icon="check" data-rel="popup">
									<spring:theme code="text.button.save" text="Save"/>
								</button>
							</ycommerce:testId>
						</div>
					</c:when>
					<c:otherwise>
						<ycommerce:testId code="save">
							<button class="form" data-theme="c" data-icon="check">
								<spring:theme code="text.button.save" text="Save"/>
							</button>
						</ycommerce:testId>
					</c:otherwise>
				</c:choose>
			</div>
		</li>
	</ul>
</form:form>