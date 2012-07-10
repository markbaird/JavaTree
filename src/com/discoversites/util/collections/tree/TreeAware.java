/*
 * Copyright (c) 2006-2012 Discoversites.com.
 */

package com.discoversites.util.collections.tree;

/**
 * 
 * Any object that needs to be aware that it is in a tree collection
 * (for example if it needs to have access to it's siblings) should
 * implement this interface.
 * 
 * @author Mark Baird
 *
 */
public interface TreeAware
{
	/**
	 * @param node The node this element belongs to
	 */
	public abstract void setNode(final TreeNode<?> node);
}