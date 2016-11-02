<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String" %>
<%@ attribute name="action" required="true" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<div class="item_container_holder">
	<div class="title_holder">
		<h2><spring:theme code="login.title"/></h2>
	</div>

	<div class="item_container">
		<p><spring:theme code="login.description"/></p>
		<p class="required"><spring:theme code="login.required.message"/></p>

		<form:form action="${action}" method="post" commandName="loginForm">
			<c:if test="${not empty message}">
				<span class="errors">
					<spring:theme code="${message}"/>
				</span>
			</c:if>
			<c:if test="${loginError}">
				<div class="form_field_error">
			</c:if>
			<div class="form_field-elements">
				<formElement:formInputBox idKey="j_username" labelKey="login.email" path="j_username" inputCSS="text" mandatory="true"/>
				<formElement:formPasswordBox idKey="j_password" labelKey="login.password" path="j_password" inputCSS="text password" mandatory="true"/>
				<div class="form_field_error-message">
					<a href="<c:url value='/login/pw/request'/>" class="password-forgotten"><spring:theme code="login.link.forgottenPwd"/></a>
				</div>
			</div>
			<c:if test="${loginError}">
				</div>
			</c:if>

			<div class="form-field-button">
				<ycommerce:testId code="login_Login_button">
					<button type="submit" class="form"><spring:theme code="${actionNameKey}"/></button>
				</ycommerce:testId>
			</div>
		</form:form>
	</div>
</div>
