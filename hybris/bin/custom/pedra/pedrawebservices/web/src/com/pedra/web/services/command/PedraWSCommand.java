/**
 * 
 */
package com.pedra.web.services.command;

import de.hybris.platform.webservices.AbstractCommand;

import org.apache.log4j.Logger;

import com.pedra.web.services.customresource.DiagnosticResource;


/**
 * @author henrique.ordine
 * 
 */
public class PedraWSCommand extends AbstractCommand<DiagnosticResource>
{

	private static final Logger LOG = Logger.getLogger(PedraWSCommand.class);

	@Override
	public Object execute(final DiagnosticResource resourceEntity, final Object requestEntity) throws Exception
	{
		LOG.info("Executing PedraWSCommand...");
		LOG.info("Request Entity type is[" + requestEntity.getClass().getName() + "]");
		return resourceEntity.getDiagnosticData();
	}

}
