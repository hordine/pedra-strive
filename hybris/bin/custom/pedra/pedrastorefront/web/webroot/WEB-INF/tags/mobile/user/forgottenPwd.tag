<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/mobile/formElement"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/mobile/common"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>

<div data-content-theme="d" data-theme="d">
	<h3><spring:theme code="forgottenPwd.title"/></h3>
	<p>
		<div class="item_container">
			<p class="continuous-text"><spring:theme code="forgottenPwd.description"/></p>
			<form:form method="post" commandName="forgottenPwdForm">
				<common:errors/>
				<ul class="mFormList" data-split-theme="d" data-theme="d">
					<li>
						<formElement:formInputBox idKey="forgottenPwd.email" labelKey="forgottenPwd.email" path="email" inputCSS="text" mandatory="true"/>
					</li>
					<li>
						<ycommerce:testId code="forgottenPassword_sendEmail">
							<button data-theme="c" data-role="button" class="form">
								<spring:theme code="forgottenPwd.submit"/>
							</button>
						</ycommerce:testId>
					</li>
				</ul>
			</form:form>
		</div>
	</p>
</div>
