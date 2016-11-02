<%@ attribute name="supportedCountries" required="false" type="java.util.List"%>
<%@ attribute name="regionsForCountries" required="false" type="java.util.Map"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/mobile/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/mobile/address"%>

<div id="countrySelector">
	<formElement:formSelectBox idKey="address.country" labelKey="address.country" path="billTo_country" mandatory="true"
		skipBlank="false" skipBlankMessageKey="address.selectCountry" items="${supportedCountries}" itemValue="isocode" />
</div>

<c:if test="${not empty countryDataMap['US']}">
	<div id="addressFormUS" class="i18nAddressForm" style="display:none;">
		<formElement:formInputBox idKey="address.firstName" labelKey="address.firstName" path="billTo_firstName" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.surname" labelKey="address.surname" path="billTo_lastName" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.line1" labelKey="address.line1" path="billTo_street1" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.line2" labelKey="address.line2" path="billTo_street2" inputCSS="text" mandatory="false"/>
		<formElement:formInputBox idKey="address.townCity" labelKey="address.townCity" path="billTo_city" inputCSS="text" mandatory="true"/>
		<formElement:formSelectBox idKey="address.region" labelKey="address.state" path="billTo_state" mandatory="true" skipBlank="false" skipBlankMessageKey="address.selectState" items="${regionsForCountries['US']}" itemValue="isocodeShort" />
		<formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="billTo_postalCode" inputCSS="text" mandatory="true"/>	
	</div>
</c:if>

<c:if test="${not empty countryDataMap['CA']}">
	<div id="addressFormCA" class="i18nAddressForm" style="display:none;">
		<formElement:formInputBox idKey="address.firstName" labelKey="address.firstName" path="billTo_firstName" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.surname" labelKey="address.surname" path="billTo_lastName" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.line1" labelKey="address.line1" path="billTo_street1" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.line2" labelKey="address.line2" path="billTo_street2" inputCSS="text" mandatory="false"/>
		<formElement:formInputBox idKey="address.townCity" labelKey="address.townCity" path="billTo_city" inputCSS="text" mandatory="true"/>
		<formElement:formSelectBox idKey="address.region" labelKey="address.province" path="billTo_state" mandatory="true" skipBlank="false" skipBlankMessageKey="address.selectProvince" items="${regionsForCountries['CA']}" itemValue="isocodeShort" />
		<formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="billTo_postalCode" inputCSS="text" mandatory="true"/>
	</div>
</c:if>

<c:if test="${not empty countryDataMap['CN']}">
	<div id="addressFormCN" class="i18nAddressForm" style="display:none;">
		<formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="billTo_postalCode" inputCSS="text" mandatory="true"/>
		<formElement:formSelectBox idKey="address.region" labelKey="address.province" path="billTo_state" mandatory="true" skipBlank="false" skipBlankMessageKey="address.selectProvince" items="${regionsForCountries['CN']}" itemValue="isocodeShort" />
		<formElement:formInputBox idKey="address.townCity" labelKey="address.townCity" path="billTo_city" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.line2" labelKey="address.district_and_street" path="billTo_street2" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.line1" labelKey="address.building_and_room" path="billTo_street1" inputCSS="text" mandatory="false"/>
		<formElement:formInputBox idKey="address.surname" labelKey="address.surname" path="billTo_lastName" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.firstName" labelKey="address.firstName" path="billTo_firstName" inputCSS="text" mandatory="true"/>
	</div>
</c:if>

<c:if test="${not empty countryDataMap['JP']}">
	<div id="addressFormJP" class="i18nAddressForm" style="display:none;">
		<formElement:formInputBox idKey="address.surname" labelKey="address.surname" path="billTo_lastName" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.firstName" labelKey="address.firstName" path="billTo_firstName" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.postcode" labelKey="address.postcodeJP" path="billTo_postalCode" inputCSS="text" mandatory="true"/>
		<formElement:formSelectBox idKey="address.region" labelKey="address.prefecture" path="billTo_state" mandatory="true" skipBlank="false" skipBlankMessageKey="address.selectPrefecture" items="${regionsForCountries['JP']}" itemValue="isocodeShort" />
		<formElement:formInputBox idKey="address.townCity" labelKey="address.townJP" path="billTo_city" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.line2" labelKey="address.subarea" path="billTo_street2" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.line1" labelKey="address.furtherSubarea" path="billTo_street1" inputCSS="text" mandatory="true"/>
	</div>
</c:if>

<div id="defaultAddressForm" class="i18nAddressForm" style="display:none;">
		<formElement:formInputBox idKey="address.firstName" labelKey="address.firstName" path="billTo_firstName" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.surname" labelKey="address.surname" path="billTo_lastName" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.line1" labelKey="address.line1" path="billTo_street1" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.line2" labelKey="address.line2" path="billTo_street2" inputCSS="text" mandatory="false"/>
		<formElement:formInputBox idKey="address.townCity" labelKey="address.townCity" path="billTo_city" inputCSS="text" mandatory="true"/>
		<formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="billTo_postalCode" inputCSS="text" mandatory="true"/>
</div>