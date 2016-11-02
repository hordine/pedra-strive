/**
 * 
 */
package com.pedra.core.payment.commands.factory.impl;

import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.commands.IsApplicableCommand;
import de.hybris.platform.payment.commands.factory.CommandFactory;
import de.hybris.platform.payment.commands.factory.CommandNotSupportedException;
import de.hybris.platform.payment.commands.factory.impl.DefaultCommandFactoryRegistryImpl;
import de.hybris.platform.payment.commands.request.IsApplicableCommandReqest;
import de.hybris.platform.payment.dto.BasicCardInfo;

import java.util.Collection;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;


/**
 * @author henrique.ordine
 * 
 */
public class PedraCommandFactoryRegistryImpl extends DefaultCommandFactoryRegistryImpl
{

	private static final Logger LOG = Logger.getLogger(PedraCommandFactoryRegistryImpl.class.getName());

	private Collection<CommandFactory> commandFactoryList;


	@Override
	public CommandFactory getFactory(final String paymentProvider) throws AdapterException
	{
		for (final CommandFactory commandFactory : this.commandFactoryList)
		{
			if (commandFactory.getPaymentProvider().equals(paymentProvider))
			{
				return commandFactory;
			}
		}

		throw new AdapterException("Card can not be served!");
	}

	@Override
	public CommandFactory getFactory(final BasicCardInfo card, final boolean threeD) throws AdapterException
	{
		for (final CommandFactory commandFactory : getCommandFactoryList())
		{
			if (isApplicable(card, threeD, commandFactory))
			{
				return commandFactory;
			}
		}

		throw new AdapterException("Card can not be served!");
	}

	private boolean isApplicable(final BasicCardInfo card, final boolean threeD, final CommandFactory commandFactory)
	{
		IsApplicableCommand command;
		try
		{
			command = commandFactory.createCommand(IsApplicableCommand.class);
		}
		catch (final CommandNotSupportedException localCommandNotSupportedException)
		{
			return false;
		}
		return command.perform(new IsApplicableCommandReqest(card, threeD)).isApplicable();
	}

	@Override
	public void afterPropertiesSet() throws BeansException
	{
		if ((getCommandFactoryList() != null) && (!(getCommandFactoryList().isEmpty())))
		{
			return;
		}
		LOG.warn("Missing command factory! At least one command factory bean should be bound to the current spring application context");
		this.commandFactoryList = Collections.EMPTY_LIST;

	}

	public Collection<CommandFactory> getCommandFactoryList()
	{
		return commandFactoryList;
	}

	@Override
	public void setCommandFactoryList(final Collection<CommandFactory> commandFactoryList)
	{
		this.commandFactoryList = commandFactoryList;
	}



}
