<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/mobile/template"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/mobile/user"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/mobile/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<spring:theme code="forgottenPwd.title" var="title" />
<template:page pageTitle="${pageTitle}">
	<div class="item_container_holder">
		<div class="registerNewCustomerLinkHolder registerNewCustomerLinkHolderBack" data-theme="e" data-content-theme="e">
			<c:url value="/login" var="loginUrl" />
			<a href="${loginUrl}" data-role="link" data-theme="d">
				&laquo; <spring:theme code="register.back.login" />
			</a>
		</div>
		<div class="fakeHR"></div>
		<div id="globalMessages">
			<common:globalMessages />
		</div>

		<cms:pageSlot position="Section1" var="feature" element="div">
			<cms:component component="${feature}" />
		</cms:pageSlot>

		<user:forgottenPwd />

		<cms:pageSlot position="Section5" var="feature" element="div">
			<cms:component component="${feature}" />
		</cms:pageSlot>
	</div>
</template:page>
