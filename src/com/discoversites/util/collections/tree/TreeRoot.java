/*
 * Copyright (c) 2006-2012 Discoversites.com.
 */

package com.discoversites.util.collections.tree;

import java.io.Serializable;

/**
 * 
 * Interface for a root node of a {@link Tree}
 * 
 * @author Mark Baird
 * @param <T> The type of element to be stored in the TreeRoot
 *
 */
public interface TreeRoot<T> extends TreeNode<T>, Serializable
{
}