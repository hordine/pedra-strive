/**
 * 
 */
package com.pedra.core.workflow;

import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.workflow.jobs.AutomatedWorkflowTemplateJob;
import de.hybris.platform.workflow.model.WorkflowActionModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author henrique.ordine
 * 
 */
public abstract class AbstractProductReviewActionJob implements AutomatedWorkflowTemplateJob
{


	@Autowired
	private ModelService modelService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserService userService;

	protected CustomerReviewModel getAttachedReview(final WorkflowActionModel action)
	{
		final List<ItemModel> attachments = action.getAttachmentItems();
		if (attachments != null)
		{
			for (final ItemModel item : attachments)
			{
				if (item instanceof CustomerReviewModel)
				{
					return (CustomerReviewModel) item;
				}
			}
		}
		return null;
	}

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	protected ModelService getModelService()
	{
		return this.modelService;
	}

	public EmailService getEmailService()
	{
		return emailService;
	}

	public void setEmailService(final EmailService emailService)
	{
		this.emailService = emailService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

}
