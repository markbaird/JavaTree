/*
 * Copyright (c) 2006-2012 Discoversites.com.
 */

/**
 * 
 */
package com.discoversites.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.discoversites.util.collections.BoundedTreeSetTest;
import com.discoversites.util.collections.tree.array.ArrayTreeTest;
import com.discoversites.util.collections.tree.set.SetTreeTest;

/**
 * Test suite class
 * 
 * @author mbaird
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	BoundedTreeSetTest.class,
	ArrayTreeTest.class,
	SetTreeTest.class
})
public class AllTests
{
	// Junit 4 test suite classes are configured completely through annotations,
	// so this class has no contents.
}