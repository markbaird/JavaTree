/*
 * Copyright (c) 2006-2012 Discoversites.com.
 */

package com.discoversites.util.collections.tree.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.discoversites.util.collections.tree.Tree;
import com.discoversites.util.collections.tree.TreeNode;
import com.discoversites.util.collections.tree.TreeRoot;

/**
 * 
 * Implementation of {@link Tree} backed by an {@link ArrayList}  
 * 
 * @author Mark Baird
 * @param <T> The type of element to be stored in the ArrayTree
 *
 */
public class ArrayTree<T> implements Tree<T>
{
	private static final long serialVersionUID = 7161315168915445688L;
	private TreeRoot<T> root;
	private final List<List<TreeNode<T>>> levels = new ArrayList<List<TreeNode<T>>>();
	
	/**
	 * 
	 */
	public ArrayTree()
	{
		this(null);
	}

	/**
	 * @param element Element data to be placed in the root node of the tree.
	 * 
	 */
	public ArrayTree(final T element)
	{
		super();
		this.root = new ArrayTreeRoot<T>(this);
		this.root.setElement(element);
		this.saveNode(this.root, 0);
	}

	/**
	 * 
	 */
	private void addLevel(final TreeNode<T> node) {
		List<TreeNode<T>> level = new ArrayList<TreeNode<T>>();
		level.add(node);
		this.levels.add(level);
	}
	
	/**
	 * @param node Node to be added to this tree.
	 * @param depth Depth (i.e. level) at which the node is to be added.
	 */
	protected void saveNode(final TreeNode<T> node, final int depth) {
		if (depth >= this.levels.size()) {
			this.addLevel(node);
		}
		else {
			this.levels.get(depth).add(node);
		}
	}

	/**
	 * @param node Node to be removed from this tree.
	 * @param depth Depth at which the node should be removed from.
	 */
	protected void removeNode(final TreeNode<T> node, final int depth) {
		if (depth < this.levels.size()) {
			this.levels.get(depth).remove(node);
		}
	}

	/**
	 * Gets all the nodes at a certain depth in the tree.
	 * 
	 * @param depth The depth to retrieve nodes from.
	 * @return A {@link List} of the nodes at this level.  The order of the nodes in the
	 * list may be insertion order, but is not guaranteed and should not be relied upon.
	 */
	protected List<TreeNode<T>> getNodes(final int depth) {
		if (depth < this.levels.size()) {
			return this.levels.get(depth);
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#getRoot()
	 */
	public TreeRoot<T> getRoot()
	{
		return this.root;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<T> iterator()
	{
		return this.getDepthFirstElements().iterator();
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#treeNodeIterator()
	 */
	public Iterator<TreeNode<T>> nodeIterator()
	{
		return this.getBreadthFirstNodes().iterator();
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#breadthFirstList()
	 */
	public List<T> getBreadthFirstElements()
	{
		final List<TreeNode<T>> nodes = this.getBreadthFirstNodes();
		final List<T> elements = new ArrayList<T>(nodes.size());
		for (final TreeNode<T> node : nodes)
		{
			elements.add(node.getElement());
		}
		
		return Collections.unmodifiableList(elements);
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#breadthFirstNodeList()
	 */
	public List<TreeNode<T>> getBreadthFirstNodes()
	{
		final List<TreeNode<T>> nodes = new LinkedList<TreeNode<T>>();
		for (List<TreeNode<T>> level : this.levels) {
			nodes.addAll(level);
		}
		
		return Collections.unmodifiableList(nodes);
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#depthFirstList()
	 */
	public List<T> getDepthFirstElements()
	{
		final List<TreeNode<T>> nodes = this.getDepthFirstNodes();
		final List<T> elements = new ArrayList<T>(nodes.size());
		for (final TreeNode<T> node : nodes)
		{
			elements.add(node.getElement());
		}
		
		return Collections.unmodifiableList(elements);
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#depthFirstNodeList()
	 */
	public List<TreeNode<T>> getDepthFirstNodes()
	{
		final List<TreeNode<T>> nodes = new LinkedList<TreeNode<T>>();
		this.addDepthFirst(nodes, this.root);
		
		return Collections.unmodifiableList(nodes);
	}

	/**
	 * @param nodes
	 * @param root2
	 */
	private void addDepthFirst(final List<TreeNode<T>> nodes, final TreeNode<T> node)
	{
		nodes.add(node);
		for (final TreeNode<T> child : node.getChildren())
		{
			this.addDepthFirst(nodes, child);
		}
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#depth()
	 */
	public int depth()
	{
		return this.levels.size() - 1;
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#size()
	 */
	public int size()
	{
		int size = 0;
		for (List<TreeNode<T>> level : this.levels) {
			size += level.size();
		}
		
		return size;
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#getList()
	 */
	public List<T> getElements()
	{
		return this.getBreadthFirstElements();
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#getNodeList()
	 */
	public List<TreeNode<T>> getNodes()
	{
		return this.getBreadthFirstNodes();
	}
}