/**
 * 
 */
package com.pedra.commerce.util;

/**
 * @author henrique.ordine
 * 
 */
public interface BeanShellUtil
{
	void cockpitConfiguration(final String object, final String code, final Class expectedClass);

	void closeSessionsByUser(final String uid);

	void showProductPopularity();
}
