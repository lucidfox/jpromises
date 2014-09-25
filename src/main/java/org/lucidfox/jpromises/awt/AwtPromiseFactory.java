package org.lucidfox.jpromises.awt;

import java.awt.EventQueue;

import org.lucidfox.jpromises.core.DeferredInvoker;
import org.lucidfox.jpromises.core.PromiseFactory;

/**
 * A {@link PromiseFactory} specialized for AWT. This factory's {@link DeferredInvoker} executes tasks
 * on the AWT event queue in the event dispatch thread, using {@link EventQueue#invokeLater}.
 */
public class AwtPromiseFactory extends PromiseFactory {
	/**
	 * Instantiates a new AWT promise factory.
	 */
	public AwtPromiseFactory() {
		super(new DeferredInvoker() {
			@Override
			public void invokeDeferred(final Runnable task) {
				EventQueue.invokeLater(task);
			}
		});
	}
}
