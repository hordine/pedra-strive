package com.pedra.core.jalo;

import com.pedra.core.constants.PedraCoreConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class PedraCoreManager extends GeneratedPedraCoreManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( PedraCoreManager.class.getName() );
	
	public static final PedraCoreManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (PedraCoreManager) em.getExtension(PedraCoreConstants.EXTENSIONNAME);
	}
	
}
