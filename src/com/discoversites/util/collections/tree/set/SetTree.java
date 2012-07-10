/*
 * Copyright (c) 2006-2012 Discoversites.com.
 */

package com.discoversites.util.collections.tree.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import com.discoversites.util.collections.tree.Tree;
import com.discoversites.util.collections.tree.TreeNode;
import com.discoversites.util.collections.tree.TreeRoot;

/**
 * 
 * Implementation of {@link Tree} backed by a {@link HashSet} (actually
 * a {@link LinkedHashSet} to maintain insertion order).  
 * This implementation works just like ArrayTree, except a given node
 * can not have duplicate children.
 * 
 * @author Mark Baird
 * @param <T> The type of element to be stored in the SetTree
 *
 */
public class SetTree<T> implements Tree<T>
{
	private static final long serialVersionUID = 4733689793747411837L;
	private TreeRoot<T> root;
	private final List<Set<TreeNode<T>>> levels = new ArrayList<Set<TreeNode<T>>>();

	/**
	 * 
	 */
	public SetTree()
	{
		this(null);
	}
	
	/**
	 * @param element Element data to be placed in the root node of the tree.
	 * 
	 */
	public SetTree(final T element)
	{
		super();
		this.root = new SetTreeRoot<T>(this);
		this.root.setElement(element);
		this.saveNode(this.root, 0);
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
	 * 
	 */
	private void addLevel(final TreeNode<T> node) {
		Set<TreeNode<T>> level = new LinkedHashSet<TreeNode<T>>();
		level.add(node);
		this.levels.add(level);
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#depth()
	 */
	public int depth()
	{
		return this.levels.size() - 1;
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#getBreadthFirstElements()
	 */
	public Set<T> getBreadthFirstElements()
	{
		final Set<TreeNode<T>> nodes = this.getBreadthFirstNodes();
		final Set<T> elements = new LinkedHashSet<T>(nodes.size());
		for (final TreeNode<T> node : nodes)
		{
			elements.add(node.getElement());
		}
		
		return Collections.unmodifiableSet(elements);
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#getBreadthFirstNodes()
	 */
	public Set<TreeNode<T>> getBreadthFirstNodes()
	{
		final Set<TreeNode<T>> nodes = new LinkedHashSet<TreeNode<T>>();
		for (Set<TreeNode<T>> level : this.levels) {
			nodes.addAll(level);
		}
		
		return Collections.unmodifiableSet(nodes);
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#getDepthFirstList()
	 */
	public Set<T> getDepthFirstElements()
	{
		final Set<TreeNode<T>> nodes = this.getDepthFirstNodes();
		final Set<T> elements = new LinkedHashSet<T>(nodes.size());
		for (final TreeNode<T> node : nodes)
		{
			elements.add(node.getElement());
		}
		
		return Collections.unmodifiableSet(elements);
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#getDepthFirstNodeList()
	 */
	public Set<TreeNode<T>> getDepthFirstNodes()
	{
		final Set<TreeNode<T>> nodes = new LinkedHashSet<TreeNode<T>>();
		this.addDepthFirst(nodes, this.root);
		
		return Collections.unmodifiableSet(nodes);
	}
	
	/**
	 * @param nodes
	 * @param root2
	 */
	private void addDepthFirst(final Set<TreeNode<T>> nodes, final TreeNode<T> node)
	{
		nodes.add(node);
		for (final TreeNode<T> child : node.getChildren())
		{
			this.addDepthFirst(nodes, child);
		}
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#getList()
	 */
	public Set<T> getElements()
	{
		return this.getBreadthFirstElements();
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#getNodeList()
	 */
	public Set<TreeNode<T>> getNodes()
	{
		return this.getBreadthFirstNodes();
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#getRoot()
	 */
	public TreeRoot<T> getRoot()
	{
		return this.root;
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#iterator()
	 */
	public Iterator<T> iterator()
	{
		return this.getDepthFirstElements().iterator();
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#nodeIterator()
	 */
	public Iterator<TreeNode<T>> nodeIterator()
	{
		return this.getBreadthFirstNodes().iterator();
	}

	/* (non-Javadoc)
	 * @see com.discoversites.util.collections.tree.Tree#size()
	 */
	public int size()
	{
		int size = 0;
		for (Collection<TreeNode<T>> level : this.levels) {
			size += level.size();
		}
		
		return size;
	}
	
	/**
	 * Gets all the nodes at a certain depth in the tree.
	 * 
	 * @param depth The depth to retrieve nodes from.
	 * @return A {@link Set} of the nodes at this level.
	 */
	protected Set<TreeNode<T>> getNodes(final int depth) {
		if (depth < this.levels.size()) {
			return this.levels.get(depth);
		}
		
		return null;
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
}