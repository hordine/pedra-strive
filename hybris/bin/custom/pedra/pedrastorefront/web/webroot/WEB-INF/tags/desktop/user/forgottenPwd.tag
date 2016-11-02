<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<cms:pageSlot position="SideContent" var="feature" element="div" class="span-4 side-content-slot cms_disp-img_slot">
	<cms:component component="${feature}"/>
</cms:pageSlot>

<div class="span-20 last">
	<div class="item_container_holder">
		<div class="title_holder">
			<h2><spring:theme code="forgottenPwd.title"/></h2>
		</div>

		<div class="item_container">
			<p><spring:theme code="forgottenPwd.description"/></p>
			<p class="required"><spring:theme code="form.required"/></p>
			<form:form method="post" commandName="forgottenPwdForm">
				<div class="form_field-elements">
					<div class="form_field-input">
						<formElement:formInputBox idKey="forgottenPwd.email" labelKey="forgottenPwd.email" path="email" inputCSS="text" mandatory="true"/>
					</div>
				</div>
				<div class="form-field-button">
					<button class="form"><spring:theme code="forgottenPwd.submit"/></button>
				</div>
			</form:form>
		</div>
	</div>
</div>
