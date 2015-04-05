package org.simbiosis.systemui.client;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Organisation implements EntryPoint {
	AppEntryPoint appEntryPoint = new AppEntryPoint();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		appEntryPoint.start();
	}

}
