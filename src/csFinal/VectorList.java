package csFinal;

import java.util.Vector;

import lab3.ListInterface;

public class VectorList<T> implements ListInterface<T> {

	private Vector<T> list;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 20;
	
	public VectorList()
	{
		this(DEFAULT_CAPACITY);
	}
	
	public VectorList(int capacity)
	{
		list = new Vector<T>(capacity);
		initialized = true;
	}
	
	private void checkInitialization() {
		if(!initialized)
			throw new SecurityException("ArrayStack object is not initialized properly.");
	}

	
	@Override
	public boolean add(T newItem) {
		checkInitialization();
		return list.add(newItem);
	}

	@Override
	public void add(T newItem, int location) {
		checkInitialization();
		list.add(location, newItem);
	}

	@Override
	public boolean remove(T item) {
		checkInitialization();
		return list.remove(item);
	}

	@Override
	public T remove(int location) {
		return list.remove(location);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public T replace(int location, T newItem) {
		T replaced = list.remove(location);
		list.add(location, newItem);
		return replaced;
	}

	@Override
	public T look(int location) {
		return list.get(location);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] lookAll() {
		return (T[]) list.toArray();
	}

	@Override
	public boolean contains(T anItem) {
		return list.contains(anItem);
	}

	@Override
	public int getCount() {
		return list.capacity();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

}
