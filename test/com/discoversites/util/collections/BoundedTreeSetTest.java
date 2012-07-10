/*
 * Copyright (c) 2006-2012 Discoversites.com.
 */

/**
 * 
 */
package com.discoversites.util.collections;

import java.util.TreeSet;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Mark Baird
 *
 */
public class BoundedTreeSetTest
{
	private static final int SIZE = 30;
	
	/**
	 * 
	 */
	@Test(timeout=10)	// Test that this runs fast as well as correctly
	public void testBoundedTreeSet()
	{
		TreeSet<Integer> set = new BoundedTreeSet<Integer>(SIZE);
		for (int i = 0; i < (SIZE + 10); i++) {
			set.add(i);
		}
		
		Assert.assertEquals(SIZE, set.size());
		Assert.assertEquals(0, set.first());
		Assert.assertEquals(29, set.last());
	}
}