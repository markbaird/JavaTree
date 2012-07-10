/*
 * Copyright (c) 2006-2012 Discoversites.com.
 */

package com.discoversites.util.collections.tree.array;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.discoversites.util.collections.tree.TreeAware;
import com.discoversites.util.collections.tree.TreeNode;
import com.discoversites.util.collections.tree.TreeRoot;

/**
 * 
 * Implementation of {@link TreeNode} backed by an {@link ArrayList}
 * 
 * @author Mark Baird
 * @param <T> The type of element to be stored in the ArrayTreeNode
 *
 */
public class ArrayTreeNode<T> implements TreeNode<T>
{
	private static final long serialVersionUID = -7761180713441293469L;
	private final List<TreeNode<T>> children = new ArrayList<TreeNode<T>>(); 
	private TreeNode<T> parent;
	private T element;
	
	/**
	 * @param parent The parent {@link ArrayTree} object that this node belongs to.
	 */
	protected ArrayTreeNode(final TreeNode<T> parent)
	{
		this();
		this.parent = parent;
	}
	
	/**
	 * @param parent The parent {@link ArrayTree} object that this node belongs to.
	 * @param element The object to be stored as this node's element
	 */
	protected ArrayTreeNode(final TreeNode<T> parent, final T element)
	{
		this(parent);
		this.setElement(element);
	}

	/**
	 * 
	 */
	protected ArrayTreeNode()
	{
		super();
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.TreeNode#addElement(java.lang.Object)
	 */
	public TreeNode<T> addElement(final T element)
	{
		final TreeNode<T> node = new ArrayTreeNode<T>(this);
		this.addNode(node);
		node.setElement(element);
		
		return node;
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.TreeNode#addNode(com.discoversites.util.collections.tree.TreeNode)
	 */
	public void addNode(final TreeNode<T> node)
	{
		this.children.add(node);
		this.getTree().saveNode(node, this.getDepth() + 1);
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.TreeNode#addNode(com.discoversites.util.collections.tree.TreeRoot)
	 */
	public void addNode(final TreeRoot<T> root)
	{
		this.addAll(root.getChildren());
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.TreeNode#getChildren()
	 */
	public Collection<TreeNode<T>> getChildren()
	{
		return Collections.unmodifiableCollection(this.children);
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.TreeNode#getParent()
	 */
	public TreeNode<T> getParent()
	{
		return this.parent;
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.TreeNode#getRoot()
	 */
	public ArrayTreeRoot<T> getRoot()
	{
		TreeNode<T> node = this.parent;
		while (! (node.isRoot()))
		{
			node = node.getParent();
		}
		
		return (ArrayTreeRoot<T>) node;
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.TreeNode#addAll(java.util.Collection)
	 */
	public void addAll(final Collection<TreeNode<T>> nodes)
	{
		for (final TreeNode<T> node : nodes)
		{
			this.addNode(node);
		}
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.TreeNode#getElement()
	 */
	public T getElement()
	{
		return this.element;
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.TreeNode#setElement(java.lang.Object)
	 */
	public void setElement(final T element)
	{
		this.element = element;
		
		if (element != null && element instanceof TreeAware) {
			((TreeAware) element).setNode(this);
		}
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.TreeNode#removeNode(com.discoversites.util.collections.tree.TreeNode)
	 */
	public void removeNode(final TreeNode<T> node)
	{
		this.children.remove(node);
		this.getTree().removeNode(node, this.getDepth() + 1);
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.TreeNode#isLeaf()
	 */
	public boolean isLeaf()
	{
		return (this.children.size() == 0);
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.TreeNode#isRoot()
	 */
	public boolean isRoot() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.TreeNode#getLevel()
	 */
	public int getDepth() {
		int level = 1;
		TreeNode<T> node = this.parent;
		while (! (node.isRoot())) {
			node = node.getParent();
			level++;
		}
		
		return level;
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.TreeNode#getSiblings()
	 */
	public Collection<TreeNode<T>> getSiblings() {
		List<TreeNode<T>> nodes = this.getTree().getNodes(this.getDepth());
		List<TreeNode<T>> siblings = new ArrayList<TreeNode<T>>(nodes.size() - 1);

		// Trying to be more efficient than siblings.addAll(nodes); siblings.remove(this);
		for (TreeNode<T> node : nodes) {
			if (node != this)
				siblings.add(node);
		}
		
		return siblings;
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.TreeNode#getTree()
	 */
	public ArrayTree<T> getTree() {
		return this.getRoot().getTree();
	}
}