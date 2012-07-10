/*
 * Copyright (c) 2006-2012 Discoversites.com.
 */

package com.discoversites.util.collections.tree.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import com.discoversites.util.collections.tree.TreeNode;
import com.discoversites.util.collections.tree.TreeRoot;

/**
 * 
 * Implementation of {@link TreeRoot} backed by an {@link HashSet}
 * (actually a {@link LinkedHashSet} to maintain insertion order).
 *   
 * @author Mark Baird
 * @param <T> The type of element to be stored in the SetTreeRoot
 *
 */
public class SetTreeRoot<T> extends SetTreeNode<T> implements TreeRoot<T>
{
	private static final long serialVersionUID = -7327377222800352915L;
	private SetTree<T> parentTree;
	
	/**
	 * @param parentTree The parent {@link SetTree} object that this root node belongs to.
	 */
	public SetTreeRoot(final SetTree<T> parentTree)
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
	public SetTree<T> getTree() {
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