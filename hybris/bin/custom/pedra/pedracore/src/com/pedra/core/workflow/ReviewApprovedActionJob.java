/**
 * 
 */
package com.pedra.core.workflow;

import de.hybris.platform.customerreview.enums.CustomerReviewApprovalType;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;

import org.apache.log4j.Logger;


/**
 * @author henrique.ordine
 * 
 */
public class ReviewApprovedActionJob extends AbstractProductReviewActionJob
{


	private static final Logger LOG = Logger.getLogger(ReviewApprovedActionJob.class.getName());

	@Override
	public WorkflowDecisionModel perform(final WorkflowActionModel action)
	{
		final CustomerReviewModel review = getAttachedReview(action);

		if (review.getProduct() != null && review.getUser() != null)
		{
			LOG.info("Review for product[" + review.getProduct().getCode() + "] and customer[" + review.getUser().getUid()
					+ "] approved. Confirmation email will be sent.");
			if (!getUserService().isAnonymousUser(review.getUser()))
			{
				action.setSendEmail(Boolean.TRUE);
				action.setEmailAddress(review.getUser().getUid());
			}
		}

		if (!review.getApprovalStatus().equals(CustomerReviewApprovalType.APPROVED))
		{
			review.setApprovalStatus(CustomerReviewApprovalType.APPROVED);
			getModelService().save(review);
		}

		for (final WorkflowDecisionModel decision : action.getDecisions())
		{
			return decision;
		}
		return null;
	}

}
