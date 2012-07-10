/*
 * Copyright (c) 2006-2012 Discoversites.com.
 */

/**
 * 
 */
package com.discoversites.util.collections.tree.set;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;
import com.discoversites.util.collections.tree.Tree;
import com.discoversites.util.collections.tree.TreeNode;

/**
 * @author Mark Baird
 *
 */
public class SetTreeTest
{
	private static final Set<String> desiredBreadthFirstResults = new LinkedHashSet<String>(9);
	private static final Set<String> desiredDepthFirstResults = new LinkedHashSet<String>(9);

	static
	{
		Collections.addAll(SetTreeTest.desiredBreadthFirstResults, 
				"Root", "A", "D", "B", "C", "E", "F", "G", "H");

		Collections.addAll(SetTreeTest.desiredDepthFirstResults, 
				"Root", "A", "B", "G", "C", "D", "E", "H", "F");
	}
	
	/**
	 * 
	 */
	@Test(timeout=10) // Test that this runs fast as well as correctly
	public void setupTree()
	{
		SetTree<String> tree = new SetTree<String>("Root");
		TreeNode<String> node = tree.getRoot().addElement("A");
		node.addElement("B").addElement("G");
		node.addElement("C");

		node = tree.getRoot().addElement("D");
		node.addElement("E").addElement("H");
		node.addElement("F");
		node.addElement("F");
		node.addElement("F");
		node.addElement("F");
		
		Assert.assertTrue(tree.getNodes(0).size() == 1);
		Assert.assertTrue(tree.getNodes(0).iterator().next() instanceof SetTreeRoot);
		
		Assert.assertEquals(0, tree.getRoot().getDepth());
		
		node = tree.getRoot().getChildren().iterator().next();
		Assert.assertEquals("A", node.getElement());
		Assert.assertEquals(1, node.getDepth());
		Assert.assertNotNull(node.getSiblings());
		Assert.assertEquals(1, node.getSiblings().size());
		Assert.assertEquals("D", node.getSiblings().iterator().next().getElement());
		
		this.verifySearches(tree);
	}

	private void verifySearches(final Tree<String> tree)
	{
		try 
		{
			Assert.assertEquals(3, tree.depth());
			Assert.assertEquals(9, tree.size());
					
			Assert.assertEquals(SetTreeTest.desiredBreadthFirstResults,
					tree.getBreadthFirstElements());
			Assert.assertEquals(SetTreeTest.desiredDepthFirstResults,
					tree.getDepthFirstElements());
			Assert.assertTrue(this.verifyOrder(SetTreeTest.desiredBreadthFirstResults,
					tree.getBreadthFirstElements()));
			Assert.assertTrue(this.verifyOrder(SetTreeTest.desiredDepthFirstResults,
					tree.getDepthFirstElements()));
		}
		catch (final Exception t)
		{
			t.printStackTrace();
			Assert.fail(t.getMessage());
		}
	}
	
	private boolean verifyOrder(Collection<? extends Object> s1, Collection<? extends Object> s2)
	{
		if (s1.size() != s2.size())
			return false;
		
		Iterator<? extends Object> it1 = s1.iterator();
		Iterator<? extends Object> it2 = s2.iterator();
		
		while (it1.hasNext() && it2.hasNext()) {
			Object o1 = it1.next();
			Object o2 = it2.next();
			
			if (! o1.equals(o2))
				return false;
		}
		
		return true;
	}
}