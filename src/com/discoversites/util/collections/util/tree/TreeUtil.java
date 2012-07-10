/*
 * Copyright (c) 2006-2012 Discoversites.com.
 */

/**
 * 
 */
package com.discoversites.util.collections.util.tree;

import java.io.PrintStream;
import com.discoversites.util.collections.tree.Tree;
import com.discoversites.util.collections.tree.TreeNode;
import com.discoversites.util.collections.tree.TreeRoot;

/**
 * Various utility methods for working with {@link Tree} objects.
 * 
 * @author Mark Baird
 *
 */
public class TreeUtil
{
	/**
	 * Print the tree to STDOUT
	 * 
	 * @param tree The tree to print
	 */
	public static void prettyPrint(Tree<? extends Object> tree)
	{
		prettyPrint(tree, System.out);
	}
	
	/**
	 * Print the tree to the specified {@link PrintStream}
	 * 
	 * @param tree The tree to print
	 * @param ps The {@link PrintStream} to print to
	 */
	public static void prettyPrint(Tree<? extends Object> tree, PrintStream ps)
	{
		TreeRoot<? extends Object> root = tree.getRoot();
		ps.println(root.getElement().toString());
		printChildren(root, 0, ps);	
	}

	private static void printChildren(TreeNode<? extends Object> node, int level, PrintStream ps)
	{	
		int childCount = 0;
		for (TreeNode<? extends Object> child : node.getChildren()) {
			childCount++;
			
			for (int i = 0; i < level + 1; i++) {
					ps.print("|   ");
			}
			ps.println("");
			
			for (int i = 0; i < level + 1; i++) {
				if (i == level)
					ps.print("+ ");
				else
					ps.print("| ");
				if (i < level) {
					ps.print("  ");
				}
			}
			
			ps.println("- " + child.getElement().toString());
			printChildren(child, level + 1, ps);
		}
	}
}