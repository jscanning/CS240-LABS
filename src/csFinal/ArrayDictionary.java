package csFinal;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDictionary<K, V> implements DictionaryInterface<K, V> 
{
	private Entry<K, V>[] dictionary;
	private int numberOfEntries;
	private boolean initialized = false;
	private final static int DEFAULT_CAPACITY = 25;
	private final static int MAX_CAPACITY = 10000;
	
	public ArrayDictionary()
	{
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayDictionary(int initialCapacity)
	{
		checkCapacity(initialCapacity);
		@SuppressWarnings("unchecked")
		Entry<K,V>[] tempDict = (Entry<K,V>[])new Entry[initialCapacity];
		dictionary = tempDict;
		numberOfEntries = 0;
		initialized = true;
	}
	
	private void checkCapacity(int capacity) {
		if(capacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a stack whose capacity exceeds allowed maximum of " + MAX_CAPACITY);
	} // end checkCapacity
	
	
	private class Entry<X, Y>
	{
		private X key;
		private VectorList<Y> values = new VectorList<Y>();
		
		private Entry(X searchKey, Y dataValue)
		{
			key = searchKey;
			values.add(dataValue);
		}
		
		private X getKey()
		{
			return key;
		}
		
		private Y[] getValues()
		{
			return values.lookAll();
		}
		
		private void addValue(Y newValue)
		{
			values.add(newValue);
		}
		
		private void removeValue(Y aValue)
		{
			values.remove(aValue);
		}
		
		private boolean hasValue(Y aValue)
		{
			return values.contains(aValue);
		}
	}
	
	private void checkInitialization() {
		if(!initialized)
			throw new SecurityException("ArrayStack object is not initialized properly.");
	} // end checkInitialization
	
	private int locateIndex(K key) 
	{
		int index = 0;
		while((index < numberOfEntries) && !key.equals(dictionary[index].getKey()))
			index++;
		return index;
	}
	
	@Override
	public V add(K key, V value) 
	{
		checkInitialization();
		if((key == null) || (value == null))
			throw new IllegalArgumentException();
		else
		{
			int keyIndex = locateIndex(key);
			
			if((keyIndex < numberOfEntries) && key.equals(dictionary[keyIndex].getKey()))
			{
				//Key found; add value to entry
				dictionary[keyIndex].addValue(value);
			}
			else //Key not found; add new entry to dictionary
			{
				dictionary[keyIndex] = new Entry<>(key, value);
				numberOfEntries++;
				ensureCapacity();
			}
		}
		return null;
	}

	private void ensureCapacity() 
	{
		if(numberOfEntries + 1 >= dictionary.length)
		{
			@SuppressWarnings("unchecked")
			Entry<K,V>[] newDict = (Entry<K,V>[])new Entry[dictionary.length * 2];
			for(int i = 0; i < dictionary.length; i++)
			{
				newDict[i] = dictionary[i];
			}
			dictionary = newDict;
		}
	}

	@Override
	public V remove(K key) {
		checkInitialization();
		V result = null;
		int keyIndex = locateIndex(key);
		if(keyIndex < numberOfEntries)
		{
			result = dictionary[keyIndex].getValues()[0];
			dictionary[keyIndex] = dictionary[numberOfEntries - 1];
			dictionary[numberOfEntries - 1] = null;
			numberOfEntries--;
		}
		return result;
	}

	@Override
	public V getValue(K key) // returns the first value in the entry
	{
		checkInitialization();
		V result = null;
		int keyIndex = locateIndex(key);
		if(keyIndex < numberOfEntries)
			result = dictionary[keyIndex].getValues()[0];
		
		return result;
	}

	@Override
	public boolean contains(K key) 
	{
		boolean result = false;
		int index = 0;
		while((index < numberOfEntries) && (result == false))
		{
			if(key.equals(dictionary[index].getKey()))
				result = true;
			else
				index++;
		}
		return result;
	}

	@Override
	public Iterator<K> getKeyIterator() 
	{
		return new KeyIterator<K>();
	}

	public class KeyIterator<K> implements Iterator<K> 
	{
		private int index;
		
		private KeyIterator()
		{
			index = 0;
		}
		
		@Override
		public boolean hasNext() 
		{
			return (dictionary[index] != null);
		}

		@SuppressWarnings("unchecked")
		@Override
		public K next() 
		{
			K result;
			if(hasNext())
			{
				result = (K)dictionary[index].getKey();
				index++;
			}
			else
				throw new NoSuchElementException();
			
			return result;
		}
		
	}


	@Override
	public Iterator<V> getValueIterator() 
	{
		return new ValueIterator<V>();
	}
	
	public class ValueIterator<V> implements Iterator<V>
	{
		private int index, vctrIndx;
		
		private ValueIterator()
		{
			index = 0;
			vctrIndx = 0;
		}
		
		@Override
		public boolean hasNext() 
		{
			return !(dictionary[index].values.isEmpty());
		}

		@SuppressWarnings("unchecked")
		@Override
		public V next() 
		{
			V result;
			if(hasNext())
			{
				result = (V) dictionary[index].values.look(vctrIndx);
				if(dictionary[index].values.getCount() > vctrIndx)
					vctrIndx++;
				else
					index++;
			}
			else
				throw new NoSuchElementException();
			
			return result;
		}
		
	}

	@Override
	public boolean isEmpty() 
	{
		return numberOfEntries == 0;
	}

	@Override
	public int getSize() 
	{
		return numberOfEntries;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() 
	{
		if(!isEmpty())
		{
			int length = dictionary.length;
			dictionary = (Entry<K,V>[])new Entry[length];
			numberOfEntries = 0;
		}
	}

}
