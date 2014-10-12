/**
 * Copyright 2014 Maia Everett <maia@lucidfox.org>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.lucidfox.jpromises.core;

import org.junit.Test;

public class SimpleTests extends AbstractPromiseTestCase {
	@Test
	public void testSimplePromise() {
		runTest(new PromiseTest() {
			@Override
			public void run(final PromiseFactory factory, final PromiseTestHandler handler) throws Exception {
				factory.promise(new PromiseHandler<String>() {
					@Override
					public void handle(final Resolver<String> resolve, final Rejector reject) {
						resolve.resolve("Hello World!");
					}
				}).then(new ResolveCallback<String, Void>() {
					@Override
					public Promise<Void> onResolve(final String value) {
						System.out.println("testSimplePromise: " + value);
						handler.done();
						return null;
					}
				}, null);
			}
		});
	}

	@Test
	public void testTwoThens() throws InterruptedException {
		runTest(new PromiseTest() {
			@Override
			public void run(final PromiseFactory factory, final PromiseTestHandler handler) throws Exception {
				factory.promise(new PromiseHandler<String>() {
					@Override
					public void handle(final Resolver<String> resolve, final Rejector reject) {
						System.out.println("testTwoThens: In handle");
						resolve.resolve("Hello World!");
					}
				}).then(new ResolveCallback<String, String>() {
					@Override
					public Promise<String> onResolve(final String value) {
						System.out.println("testTwoThens: " + value);
						return null;
					}
				}, null).then(new ResolveCallback<String, Void>() {
					@Override
					public Promise<Void> onResolve(final String value) {
						System.out.println("testTwoThens: In second then");
						handler.done();
						return null;
					}
				}, null);
			}
		});
	}
}
