/*
 * Copyright (c) 2006-2012 Discoversites.com.
 */

package com.discoversites.util.collections;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * An implementation of TreeSet that restricts the size of the tree to the size
 * specified at construction.  If adding an element to BoundedTreeSet results in
 * the size of the set being maxSize + 1 then the element at the last position is
 * removed.  If maxSize is not specified then {@link Integer.MAX_VALUE} is used.
 * <p/>
 * BoundedTreeSet is backed by the standard Java TreeSet implementation.
 * 
 * @author Mark Baird
 * @param <T> The type of element to be stored the BoundedTreeSet
 *
 */
public class BoundedTreeSet<T> extends TreeSet<T>
{
	private static final long serialVersionUID = -7927034992880433117L;
	private int maxSize = Integer.MAX_VALUE;

	/**
	 * 
	 */
	public BoundedTreeSet()
	{
		super();
	}

	/**
	 * @param c A {@link Collection} of elements to be added to this BoundedTreeSet
	 */
	public BoundedTreeSet(Collection<? extends T> c)
	{
		this(Integer.MAX_VALUE, c);
	}

	/**
	 * @param c A {@link Comparator} implementation to be used for sorting the elements in this BoundedTreeSet
	 */
	public BoundedTreeSet(Comparator<? super T> c)
	{
		this(Integer.MAX_VALUE, c);
	}

	/**
	 * @param set A {@link SortedSet} containing pre-sorted objects to be added to this BoundedTreeSet
	 */
	public BoundedTreeSet(SortedSet<T> set)
	{
		this(Integer.MAX_VALUE, set);
	}
	
	/**
	 * @param maxSize The max size of this BoundedTreeSet
	 * @param c A {@link Collection} of elements to be added to this BoundedTreeSet
	 */
	public BoundedTreeSet(int maxSize, Collection<? extends T> c)
	{
		super();
		this.maxSize = maxSize;

		for (T t : c) {
			this.add(t);
		}
	}

	/**
	 * @param maxSize The max size of this BoundedTreeSet
	 * @param comparator A {@link Comparator} implementation to be used for sorting the elements in this BoundedTreeSet
	 */
	public BoundedTreeSet(int maxSize, Comparator<? super T> comparator)
	{
		super(comparator);
		this.maxSize = maxSize;
	}

	/**
	 * @param maxSize The max size of this BoundedTreeSet
	 * @param s A {@link SortedSet} containing pre-sorted objects to be added to this BoundedTreeSet
	 */
	public BoundedTreeSet(int maxSize, SortedSet<T> s)
	{
		super();
		this.maxSize = maxSize;

		int i = 0;
		for (Iterator<T> itr = s.iterator(); i < this.maxSize && itr.hasNext(); i++) {
			this.add(itr.next());
		}
	}

	/* (non-Javadoc)
	 * @see java.util.TreeSet#add(java.lang.Object)
	 */
	@Override
	public boolean add(T element)
	{
		boolean rtn = super.add(element);
		if (this.size() > this.maxSize) {
			this.remove(this.last());
		}

		return rtn;
	}

	/* (non-Javadoc)
	 * @see java.util.TreeSet#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends T> c)
	{
		boolean rtn = false;
		for (T t : c) {
			if (this.add(t))
				rtn = true;
		}

		return rtn;
	}

	/**
	 * @param maxSize The max size of this BoundedTreeSet
	 */
	public BoundedTreeSet(int maxSize)
	{
		super();
		this.maxSize = maxSize;
	}
}