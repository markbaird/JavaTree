/*
 * Copyright (c) 2006-2012 Discoversites.com.
 */

package com.discoversites.util.collections.tree;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * This interface is meant to provide a template for the creation 
 * of classes that implement an unordered tree data structure.
 * The main goal of this interface is to provide a tree datastructure
 * to complement the Java Collections API.
 * </p>
 * This project started as a way to store generic objects that need
 * to be stored in a hierarchical manner for later presentation in a
 * tree format in a UI.  An example of this would be a drop down menu
 * system on a web site that can be of variable depth. 
 * 
 * @author Mark Baird
 * @param <T> The type of element to be stored in the Tree
 *
 */
public interface Tree<T> extends Iterable<T>, Serializable
{
	/**
	 * @return The root node of the tree
	 */
	public abstract TreeRoot<T> getRoot();
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	/**
	 * @return An {@link Iterator} that iterates over the elements stored in the tree's nodes.
	 * The order the elements are returned is not gauranteed by this method.
	 */
	public abstract Iterator<T> iterator();
	
	/**
	 * @return An {@link Iterator} that iterates over the tree's nodes.
	 * The order the nodes are returned is not gauranteed by this method.
	 */
	public abstract Iterator<TreeNode<T>> nodeIterator();
	
	/**
	 * @return An {@link Collection} that contains the elements stored in the tree's nodes.
	 * The elements are returned in breadth first order.
	 */
	public abstract Collection<T> getBreadthFirstElements();
	
	/**
	 * @return An {@link Collection} that contains the tree's nodes.
	 * The nodes are returned in breadth first order.
	 */
	public abstract Collection<TreeNode<T>> getBreadthFirstNodes();
	
	/**
	 * @return An {@link Collection} that contains the elements stored in the tree's nodes.
	 * The elements are returned in depth first order.
	 */
	public abstract Collection<T> getDepthFirstElements();
	
	/**
	 * @return An {@link Collection} that contains the tree's nodes.
	 * The nodes are returned in depth first order.
	 */
	public abstract Collection<TreeNode<T>> getDepthFirstNodes();
	
	/**
	 * @return A {@link Collection} of all the elements in the tree.
	 * The order of the elements is not gauranteed by this method.
	 */
	public abstract Collection<T> getElements();
	
	/**
	 * @return A {@link Collection} of all nodes in the tree.
	 * The order of the nodes is not gauranteed by this method.
	 */
	public abstract Collection<TreeNode<T>> getNodes();
	
	/**
	 * @return The number of elements in the tree.
	 */
	public abstract int size();
	
	/**
	 * @return The depth of the tree, i.e. the number of levels the tree has.
	 */
	public abstract int depth();
}