package com.pedra.core.payment.commands.impl;

import de.hybris.platform.payment.commands.AuthorizationCommand;
import de.hybris.platform.payment.commands.request.AuthorizationRequest;
import de.hybris.platform.payment.commands.result.AuthorizationResult;
import de.hybris.platform.payment.dto.AvsStatus;
import de.hybris.platform.payment.dto.CvnStatus;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;


/**
 * @author henrique.ordine
 * 
 */
public class PedraAuthorizationCommandImpl extends GenericPedraCommand implements AuthorizationCommand
{

	private static final Logger LOG = Logger.getLogger(PedraAuthorizationCommandImpl.class);

	@Override
	public AuthorizationResult perform(final AuthorizationRequest request)

	{
		final AuthorizationResult result = new AuthorizationResult();

		result.setCurrency(request.getCurrency());
		result.setTotalAmount(request.getTotalAmount());

		result.setAvsStatus(AvsStatus.NO_RESULT);
		result.setCvnStatus(CvnStatus.NOT_PROCESSED);

		result.setAuthorizationTime(new Date());

		final Calendar today = Calendar.getInstance();
		if (today.get(1) > request.getCard().getExpirationYear().intValue())

		{
			result.setTransactionStatus(TransactionStatus.REJECTED);
			result.setTransactionStatusDetails(TransactionStatusDetails.INVALID_CARD_EXPIRATION_DATE);
		}
		else if ((today.get(1) == request.getCard().getExpirationYear().intValue())
				&& (today.get(2) > request.getCard().getExpirationMonth().intValue()))

		{
			result.setTransactionStatus(TransactionStatus.REJECTED);
			result.setTransactionStatusDetails(TransactionStatusDetails.INVALID_CARD_EXPIRATION_DATE);
		}
		else if (request.getTotalAmount().longValue() > 500L)

		{
			result.setTransactionStatus(TransactionStatus.REVIEW);
			result.setTransactionStatusDetails(TransactionStatusDetails.REVIEW_NEEDED);
		}
		else
		{
			result.setTransactionStatus(TransactionStatus.ACCEPTED);
			result.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL);
		}

		LOG.debug("AuthorizationResult is [" + result.getTransactionStatus() + "]");

		genericPerform(request, result);

		return result;
	}

}
