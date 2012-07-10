/*
 * Copyright (c) 2006-2012 Discoversites.com.
 */

package com.discoversites.util.collections.tree.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.discoversites.util.collections.tree.Tree;
import com.discoversites.util.collections.tree.TreeNode;

/**
 * @author Mark Baird
 *
 */
public class ArrayTreeTest
{
	private static final List<String> desiredBreadthFirstResults = new ArrayList<String>(9);
	private static final List<String> desiredDepthFirstResults = new ArrayList<String>(9);

	static
	{
		Collections.addAll(ArrayTreeTest.desiredBreadthFirstResults, 
				"Root", "A", "D", "B", "C", "E", "F", "G", "H");

		Collections.addAll(ArrayTreeTest.desiredDepthFirstResults, 
				"Root", "A", "B", "G", "C", "D", "E", "H", "F");
	}
	
	/**
	 * 
	 */
	@Test(timeout=10) // Test that this runs fast as well as correctly
	public void setupTree()
	{
		ArrayTree<String> tree = new ArrayTree<String>("Root");
		TreeNode<String> node = tree.getRoot().addElement("A");
		node.addElement("B").addElement("G");
		node.addElement("C");

		node = tree.getRoot().addElement("D");
		node.addElement("E").addElement("H");
		node.addElement("F");
		
		Assert.assertTrue(tree.getNodes(0).size() == 1);
		Assert.assertTrue(tree.getNodes(0).get(0) instanceof ArrayTreeRoot);
		
		Assert.assertEquals(0, tree.getRoot().getDepth());
		
		node = tree.getRoot().getChildren().iterator().next();
		Assert.assertEquals("A", node.getElement());
		Assert.assertEquals(1, node.getDepth());
		Assert.assertNotNull(node.getSiblings());
		Assert.assertEquals(1, node.getSiblings().size());
		Assert.assertEquals("D", node.getSiblings().iterator().next().getElement());
		
		this.verifySearches(tree);
	}

	/**
	 * @param tree 
	 * 
	 */
	public void verifySearches(final Tree<String> tree)
	{
		try 
		{
			Assert.assertEquals(3, tree.depth());
			Assert.assertEquals(9, tree.size());
			
			Assert.assertEquals(tree.getDepthFirstElements(), 
					ArrayTreeTest.desiredDepthFirstResults);
			Assert.assertEquals(tree.getBreadthFirstElements(), 
					ArrayTreeTest.desiredBreadthFirstResults);
		}
		catch (final Exception t)
		{
			t.printStackTrace();
			Assert.fail(t.getMessage());
		}
	}
}