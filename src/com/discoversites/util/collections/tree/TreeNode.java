/*
 * Copyright (c) 2006-2012 Discoversites.com.
 */

package com.discoversites.util.collections.tree;

import java.io.Serializable;
import java.util.Collection;

/**
 * 
 * Interface for a single node of a {@link Tree}
 * 
 * @author Mark Baird
 * @param <T> The type of element to be stored in the TreeNode
 *
 */
public interface TreeNode<T> extends Serializable
{
	/**
	 * @return The root node of the tree this node belongs to.
	 */
	public abstract TreeRoot<T> getRoot();
	
	/**
	 * @return The parent node of this node.
	 */
	public abstract TreeNode<T> getParent();
	
	/**
	 * @param node A node to be added as a child to this node.
	 */
	public abstract void addNode(final TreeNode<T> node);
	
	/**
	 * @param root A node to be added as a child to this node.
	 */
	public abstract void addNode(final TreeRoot<T> root);
	
	/**
	 * @param element An element to be added as a child node to this node.
	 * @return The node that was created to store the element.
	 */
	public abstract TreeNode<T> addElement(final T element);
	
	/**
	 * @param node A child node of this node, that is to be removed from this node.
	 */
	public abstract void removeNode(final TreeNode<T> node);
	
	/**
	 * @param element An element to be stored in this node, replacing the current element if one exists.
	 */
	public abstract void setElement(final T element);
	
	/**
	 * @return The element stored in this node.
	 */
	public abstract T getElement();
	
	/**
	 * @param nodes One or more nodes to be added as children to this node.
	 */
	public abstract void addAll(final Collection<TreeNode<T>> nodes);
	
	/**
	 * @return The child nodes of this node.
	 */
	public abstract Collection<TreeNode<T>> getChildren();
	
	/**
	 * @return The sibling nodes of this node.
	 */
	public abstract Collection<TreeNode<T>> getSiblings();
	
	/**
	 * @return True if this is a leaf node (i.e. it has no children), false otherwise.
	 */
	public abstract boolean isLeaf();
	
	/**
	 * @return True if this is a root node (i.e. it has no parents), false otherwise.
	 */
	public abstract boolean isRoot();
	
	/**
	 * @return The depth (i.e. level) that this node occupies in it's parent {@link Tree}.
	 */
	public abstract int getDepth();
	
	/**
	 * @return The {@link Tree} that this node belongs to.
	 */
	public abstract Tree<T> getTree();
}