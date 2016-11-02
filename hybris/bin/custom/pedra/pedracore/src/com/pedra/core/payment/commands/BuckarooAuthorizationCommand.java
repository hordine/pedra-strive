/**
 * 
 */
package com.pedra.core.payment.commands;

import de.hybris.platform.payment.commands.Command;

import com.pedra.core.payment.commands.request.BuckarooAuthorizationRequest;
import com.pedra.core.payment.commands.result.BuckarooAuthorizationResult;


/**
 * @author henrique.ordine
 * 
 */
public interface BuckarooAuthorizationCommand extends Command<BuckarooAuthorizationRequest, BuckarooAuthorizationResult>
{
	// for additional methods
}
