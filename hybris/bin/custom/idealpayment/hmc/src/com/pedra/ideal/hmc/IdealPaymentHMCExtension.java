/**
 * 
 */
package com.pedra.ideal.hmc;

import de.hybris.platform.hmc.AbstractEditorMenuChip;
import de.hybris.platform.hmc.AbstractExplorerMenuTreeNodeChip;
import de.hybris.platform.hmc.EditorTabChip;
import de.hybris.platform.hmc.extension.HMCExtension;
import de.hybris.platform.hmc.extension.MenuEntrySlotEntry;
import de.hybris.platform.hmc.generic.ClipChip;
import de.hybris.platform.hmc.generic.ToolbarActionChip;
import de.hybris.platform.hmc.webchips.Chip;
import de.hybris.platform.hmc.webchips.DisplayState;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;


/**
 * @author Henrique Ordine
 * 
 */
public class IdealPaymentHMCExtension extends HMCExtension
{

	/** Edit the local|project.properties to change logging behavior (properties log4j.*). */
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(IdealPaymentHMCExtension.class.getName());

	/** Path to the resource bundles. */
	public static final String RESOURCE_PATH = "com.pedra.ideal.hmc.locales";


	/**
	 * 
	 */
	public IdealPaymentHMCExtension()
	{
		// YTODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.extension.HMCExtension#getEditorTabChips(de.hybris.platform.hmc.webchips.DisplayState,
	 * de.hybris.platform.hmc.AbstractEditorMenuChip)
	 */
	@Override
	public List<EditorTabChip> getEditorTabChips(final DisplayState arg0, final AbstractEditorMenuChip arg1)
	{
		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.extension.HMCExtension#getLocalizeResourceBundle(java.util.Locale)
	 */
	@Override
	public ResourceBundle getLocalizeResourceBundle(final Locale arg0) throws MissingResourceException
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.hmc.extension.HMCExtension#getMenuEntrySlotEntries(de.hybris.platform.hmc.webchips.DisplayState
	 * , de.hybris.platform.hmc.webchips.Chip)
	 */
	@Override
	public List<MenuEntrySlotEntry> getMenuEntrySlotEntries(final DisplayState arg0, final Chip arg1)
	{
		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.extension.HMCExtension#getResourcePath()
	 */
	@Override
	public String getResourcePath()
	{
		return RESOURCE_PATH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.extension.HMCExtension#getSectionChips(de.hybris.platform.hmc.webchips.DisplayState,
	 * de.hybris.platform.hmc.generic.ClipChip)
	 */
	@Override
	public List<ClipChip> getSectionChips(final DisplayState arg0, final ClipChip arg1)
	{
		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.hmc.extension.HMCExtension#getToolbarActionChips(de.hybris.platform.hmc.webchips.DisplayState,
	 * de.hybris.platform.hmc.webchips.Chip)
	 */
	@Override
	public List<ToolbarActionChip> getToolbarActionChips(final DisplayState arg0, final Chip arg1)
	{
		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.hmc.extension.HMCExtension#getTreeNodeChips(de.hybris.platform.hmc.webchips.DisplayState,
	 * de.hybris.platform.hmc.webchips.Chip)
	 */
	@Override
	public List<AbstractExplorerMenuTreeNodeChip> getTreeNodeChips(final DisplayState arg0, final Chip arg1)
	{
		return Collections.EMPTY_LIST;
	}

}
