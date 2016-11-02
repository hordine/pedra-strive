<%@ attribute name="supportedCountries" required="false" type="java.util.List"%>
<%@ attribute name="regionsForCountries" required="false" type="java.util.Map"%>
<%@ attribute name="cancelUrl" required="false" type="java.lang.String"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/mobile/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/mobile/address"%>

<div id="countrySelector" style="display:none;">
	<form:form method="post" commandName="addressForm">
		<formElement:formSelectBox idKey="address.country" labelKey="address.country" path="countryIso" mandatory="true"
									skipBlank="false" skipBlankMessageKey="address.selectCountry" items="${supportedCountries}"
									itemValue="isocode" selectedValue="${addressForm.countryIso}"/>
	</form:form>
</div>

<c:if test="${not empty countryDataMap['US']}">
	<div id="addressFormUS" class="i18nAddressForm" style="display:none;">
		<address:addressFormUS states="${regionsForCountries['US']}" cancelUrl="${cancelUrl}"/>
	</div>
</c:if>

<c:if test="${not empty countryDataMap['CA']}">
	<div id="addressFormCA" class="i18nAddressForm" style="display:none;">
		<address:addressFormCA provinces="${regionsForCountries['CA']}" cancelUrl="${cancelUrl}"/>
	</div>
</c:if>

<c:if test="${not empty countryDataMap['CN']}">
	<div id="addressFormCN" class="i18nAddressForm" style="display:none;">
		<address:addressFormCN provinces="${regionsForCountries['CN']}" cancelUrl="${cancelUrl}"/>
	</div>
</c:if>

<c:if test="${not empty countryDataMap['JP']}">
	<div id="addressFormJP" class="i18nAddressForm" style="display:none;">
		<address:addressFormJP prefectures="${regionsForCountries['JP']}" cancelUrl="${cancelUrl}"/>
	</div>
</c:if>

<div id="defaultAddressForm" class="i18nAddressForm" style="display:none;">
	<address:defaultAddressForm cancelUrl="${cancelUrl}"/>
</div>