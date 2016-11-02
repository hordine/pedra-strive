<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="deliveryAddress" required="true" type="de.hybris.platform.commercefacades.user.data.AddressData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="single-checkout" tagdir="/WEB-INF/tags/desktop/checkout/single" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>

<spring:url value="/checkout/single/summary/getDeliveryAddresses.json" var="getDeliveryAddressesUrl" />
<spring:url value="/checkout/single/summary/setDeliveryAddress.json" var="setDeliveryAddressUrl" />
<spring:url value="/checkout/single/summary/getDeliveryAddressForm.json" var="getDeliveryAddressFormUrl" />
<spring:url value="/checkout/single/summary/setDefaultAddress.json" var="setDefaultAddressUrl" />

<script type="text/javascript">
	var getDeliveryAddressesUrl = '${getDeliveryAddressesUrl}';
		setDeliveryAddressUrl = '${setDeliveryAddressUrl}';
		getDeliveryAddressFormUrl = '${getDeliveryAddressFormUrl}';
		setDefaultAddressUrl = '${setDefaultAddressUrl}';
</script>


<script id="deliveryAddressSummaryTemplate" type="text/x-jquery-tmpl">
	<div class="checkout_summary_flow_a {{if deliveryAddress}}complete{{/if}}" id="checkout_summary_deliveryaddress_div">
		<div class="item_container_holder">
			<div class="title_holder">
				<h2><spring:theme code="checkout.summary.deliveryAddress.header" htmlEscape="false"/><span></span></h2>
			</div>

			<div class="item_container">
				<ul>
					{{if deliveryAddress}}
						<li>{{= deliveryAddress.title}} {{= deliveryAddress.firstName}} {{= deliveryAddress.lastName}}</li>
						<li>{{= deliveryAddress.line1}}</li>
						<li>{{= deliveryAddress.line2}}</li>
						<li>{{= deliveryAddress.town}}</li>
						{{if deliveryAddress.region}}
							<li>{{= deliveryAddress.region.name}}</li>
						{{/if}}
						<li>{{= deliveryAddress.postalCode}}</li>
						{{if deliveryAddress.country}}
							<li>{{= deliveryAddress.country.name}}</li>
						{{/if}}
					{{else}}
						<li><spring:theme code="checkout.summary.deliveryAddress.noneSelected"/></li>
					{{/if}}
				</ul>
			</div>
		</div>

		{{if deliveryAddress}}
				<ycommerce:testId code="checkout_changeAddress_element">
				<a href="#" class="edit_complete change_address_button" data-address="{{= deliveryAddress.id}}"><spring:theme code="checkout.summary.deliveryAddress.editDeliveryAddressButton"/></a>
				</ycommerce:testId>
		{{else}}
				<ycommerce:testId code="checkout_changeAddress_element">
				<button class="form change_address_button" data-address="{{= deliveryAddress.id}}"><spring:theme code="checkout.summary.deliveryAddress.enterDeliveryAddressButton"/></button>
				</ycommerce:testId>
		{{/if}}
	</div>
</script>

<script id="deliveryAddressesTemplate" type="text/x-jquery-tmpl">
	{{if !addresses.length}}
		<spring:theme code="checkout.summary.deliveryAddress.noExistingAddresses"/>
	{{/if}}
	{{if addresses.length}}
		<form>
			{{each addresses}}
				<div class="existing_address">
					<div class="left">
						<div class="checkmark">
							{{if defaultAddress}}
								<theme:image code="img.iconSelected" alt="Selected"/>
							{{/if}}
						</div>
						<button class="form left edit" data-address="{{= $value.id}}"><spring:theme code="checkout.summary.deliveryAddress.edit"/></button>
					</div>
					<ul>
						<li>{{= title}} {{= firstName}} {{= lastName}}</li>
						<li>{{= line1}}</li>
						<li>{{= line2}}</li>
						<li>{{= town}}</li>
						{{if region}}
							<li>{{= region.name}}</li>
						{{/if}}
						<li>{{= postalCode}}</li>
						{{if country}}
							<li>{{= country.name}}</li>
						{{/if}}
					</ul>
					{{if !(defaultAddress)}}
						<button class="form right default" data-address="{{= $value.id}}"><spring:theme code="text.setDefault" text="Set as default" /></button>
					{{/if}}
					<button class="positive right pad_left use_address" data-address="{{= $value.id}}"><spring:theme code="checkout.summary.deliveryAddress.useThisAddress"/></button>
				</div>
			{{/each}}
		</form>
	{{/if}}
</script>

<c:set value="${not empty deliveryAddress}" var="deliveryAddressOk"/>

<div class="checkout_summary_flow_a ${deliveryAddressOk ? 'complete' : ''}" id="checkout_summary_deliveryaddress_div">
	<div class="item_container_holder">
		<ycommerce:testId code="checkout_deliveryAddressData_text">
			<div class="title_holder">
				<h2><spring:theme code="checkout.summary.deliveryAddress.header" htmlEscape="false"/><span></span></h2>
			</div>

			<div class="item_container">
				<ul>
					<c:choose>
						<c:when test="${deliveryAddressOk}">
							<li>${fn:escapeXml(deliveryAddress.title)}&nbsp;${fn:escapeXml(deliveryAddress.firstName)}&nbsp;${fn:escapeXml(deliveryAddress.lastName)}</li>
							<li>${fn:escapeXml(deliveryAddress.line1)}</li>
							<li>${fn:escapeXml(deliveryAddress.line2)}</li>
							<li>${fn:escapeXml(deliveryAddress.town)}</li>
							<li>${fn:escapeXml(deliveryAddress.region.name)}</li>
							<li>${fn:escapeXml(deliveryAddress.postalCode)}</li>
							<li>${fn:escapeXml(deliveryAddress.country.name)}</li>
						</c:when>
						<c:otherwise>
							<li><spring:theme code="checkout.summary.deliveryAddress.noneSelected"/></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</ycommerce:testId>
	</div>
	<ycommerce:testId code="checkout_changeAddress_element">
		<c:choose>
			<c:when test="${deliveryAddressOk}">
				<a href="#" class="edit_complete change_address_button" data-address="${deliveryAddress.id}"><spring:theme code="checkout.summary.deliveryAddress.editDeliveryAddressButton"/></a>
			</c:when>
			<c:otherwise>
				<button class="form change_address_button" data-address="${deliveryAddress.id}"><spring:theme code="checkout.summary.deliveryAddress.enterDeliveryAddressButton"/></button>
			</c:otherwise>
		</c:choose>
	</ycommerce:testId>
</div>
<div style="display:none">
	<div class="" id="popup_checkout_delivery_address">
		<div class="span-20 last" id="checkout_delivery_address">
			<div class="span-10">
				<div class="item_container_holder" id="create_update_address_form_container_div">
					<single-checkout:deliveryAddressForm />
				</div>
			</div>
			<div class="span-10 last">
				<div class="item_container_holder">
					<div class="title_holder">
						<h2><spring:theme code="checkout.summary.deliveryAddress.selectExistingAddress"/></h2>
					</div>
					<ycommerce:testId code="checkout_existingAddresses">
						<div class="item_container delivery_addresses_list">
							<%-- List of delivery addresses is injected here --%>
						</div>
					</ycommerce:testId>
				</div>
			</div>
		</div>
	</div>
</div>