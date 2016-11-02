<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<div id="footer" class="footer-link-component">
	<ul class="Fa">
		<c:forEach items="${navigationNodes}" var="node">
			<c:if test="${node.visible}">
				<li>
					<ul class="Fb">
						<li>
							<h3>${node.title}</h3>
							<c:forEach items="${node.links}" step="${component.wrapAfter}" varStatus="i">
								<ul class="Fc ${i.count < 2 ? 'left_col' : 'right_col'}">
									<c:forEach items="${node.links}" var="childlink" begin="${i.index}" end="${i.index + component.wrapAfter - 1}">
										<cms:component component="${childlink}" evaluateRestriction="true" element="li" class="Fc ${i.count < 2 ? 'left_col' : 'right_col'}"/>
									</c:forEach>
								</ul>
							</c:forEach>
						</li>
					</ul>
				</li>
			</c:if>
		</c:forEach>
	</ul>
</div>
<div id="copyright">
	<p>${notice}</p>
</div>
