/**
 * 
 */
package com.pedra.commerce.service.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerreview.impl.DefaultCustomerReviewService;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.workflow.WorkflowProcessingService;
import de.hybris.platform.workflow.WorkflowService;
import de.hybris.platform.workflow.WorkflowTemplateService;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowModel;
import de.hybris.platform.workflow.model.WorkflowTemplateModel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.pedra.commerce.service.PedraCustomerReviewService;


/**
 * @author henrique.ordine
 * 
 */
public class PedraCustomerReviewServiceImpl extends DefaultCustomerReviewService implements PedraCustomerReviewService
{

	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private WorkflowTemplateService workflowTemplateService;
	@Autowired
	private WorkflowProcessingService workflowProcessingService;
	@Autowired
	private UserService userService;
	@Autowired
	private ModelService modelService;

	private static final Logger LOG = Logger.getLogger(PedraCustomerReviewServiceImpl.class.getName());

	@Override
	public CustomerReviewModel createCustomerReview(final Double rating, final String headline, final String comment,
			final UserModel user, final ProductModel product)
	{
		final CustomerReviewModel review = super.createCustomerReview(rating, headline, comment, user, product);
		// start the registration workflow for the new player
		final WorkflowTemplateModel workflowTemplate = this.getWorkflowTemplateService().getWorkflowTemplateForCode(
				"NewProductReviewRegistration");

		LOG.debug("Creating workflow from NewProductReviewRegistration template...");
		final WorkflowModel workflow = getWorkflowService().createWorkflow(workflowTemplate, review,
				getUserService().getAdminUser());
		getModelService().save(workflow);
		for (final WorkflowActionModel action : workflow.getActions())
		{
			getModelService().save(action);
		}
		getModelService().saveAll(workflow.getAttachments());
		LOG.debug("Starting workflow[" + workflow.getCode() + "]...");
		getWorkflowProcessingService().startWorkflow(workflow);

		return review;
	}

	public void setWorkflowService(final WorkflowService workflowService)
	{
		this.workflowService = workflowService;
	}

	public void setWorkflowTemplateService(final WorkflowTemplateService workflowTemplateService)
	{
		this.workflowTemplateService = workflowTemplateService;
	}

	public void setWorkflowProcessingService(final WorkflowProcessingService workflowProcessingService)
	{
		this.workflowProcessingService = workflowProcessingService;
	}

	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	@Override
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public WorkflowService getWorkflowService()
	{
		return workflowService;
	}

	public WorkflowTemplateService getWorkflowTemplateService()
	{
		return workflowTemplateService;
	}

	public WorkflowProcessingService getWorkflowProcessingService()
	{
		return workflowProcessingService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Override
	public ModelService getModelService()
	{
		return modelService;
	}

}
