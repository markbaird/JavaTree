/*
 * Copyright (c) 2006-2012 Discoversites.com.
 */

package com.discoversites.util.collections.tree.array;

import java.util.ArrayList;
import com.discoversites.util.collections.tree.TreeNode;
import com.discoversites.util.collections.tree.TreeRoot;

/**
 * 
 * Implementation of {@link TreeRoot} backed by an {@link ArrayList}
 * 
 * @author Mark Baird
 * @param <T> The type of element to be stored in the ArrayTreeRoot
 *
 */
public class ArrayTreeRoot<T> extends ArrayTreeNode<T> implements TreeRoot<T>
{
	private static final long serialVersionUID = 1461763078075522365L;
	private ArrayTree<T> parentTree;
	
	/**
	 * @param parentTree The parent {@link ArrayTree} object that this root node belongs to.
	 */
	protected ArrayTreeRoot(final ArrayTree<T> parentTree)
	{
		super();
		this.parentTree = parentTree;
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.array.ArrayTreeNode#isRoot()
	 */
	@Override
	public boolean isRoot() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.TreeRoot#getTree()
	 */
	@Override
	public ArrayTree<T> getTree() {
		return this.parentTree;
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.array.ArrayTreeNode#getParent()
	 */
	@Override
	public TreeNode<T> getParent() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.TreeNode#getLevel()
	 */
	@Override
	public int getDepth() {
		return 0;
	}
}