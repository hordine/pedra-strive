package com.pedra.cockpits.jalo;

import com.pedra.cockpits.constants.PedraCockpitsConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class PedraCockpitsManager extends GeneratedPedraCockpitsManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( PedraCockpitsManager.class.getName() );
	
	public static final PedraCockpitsManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (PedraCockpitsManager) em.getExtension(PedraCockpitsConstants.EXTENSIONNAME);
	}
	
}
