package lab3;


public class LinkedList<T> implements ListInterface<T> {

	private Node firstNode;
	private int numberOfNodes;
	private int numberOfItems;
	
	public LinkedList()
	{
		firstNode = null;
	}
	
	private Node getNodeAt(int position)
	{
		assert(firstNode != null) && (1<=position) && (position <= numberOfNodes);
		Node currentNode = firstNode;
		
		for(int i = 1; i < position; i++)
			currentNode = currentNode.getNextNode();
		
		assert currentNode != null;
		
		return currentNode;
	}
	
	
	@Override
	public boolean add(T newItem) {
		boolean result = false;
		Node newNode = new Node(newItem);
		if(isEmpty())
		{
			firstNode = newNode;
			result = true;
		}
		else
		{
			Node lastNode = getNodeAt(numberOfItems);
			lastNode.setNextNode(newNode);
			result = true;
		}
		numberOfItems++;
		return result;
	}

	@Override
	public void add(T newItem, int location) 
	{
		if((location >= 1) && (location <= numberOfItems + 1))
		{
			Node newNode = new Node(newItem);
			
			if(location == 1)
			{
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}
			else
			{
				Node nodeBefore = getNodeAt(location -1);
				Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			}
			numberOfItems++;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given");
	}// end method

	@Override
	public boolean remove(T item) {
		int locationCounter = 1;
		boolean found = false;
		Node currentNode = firstNode;
		while(!found && (currentNode != null))
		{
			if(item.equals(currentNode.getData()))
			{
				found = true;
			}
			else
			{
				locationCounter++;
				currentNode = currentNode.getNextNode();
			}
		}
		if(found)
			remove(locationCounter);
		return found;
	}

	@Override
	public T remove(int location) {
		T result = null;
		if((location >= 1) && (location <= numberOfItems))
		{
			assert !isEmpty() : "List is empty";
			if(location == 1)
			{
				result = firstNode.getData();
				firstNode = firstNode.getNextNode();
			}
			else
			{
				Node nodeBefore = getNodeAt(location -1);
				Node nodeToRemove = nodeBefore.getNextNode();
				Node nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter);
				result = nodeToRemove.getData();
				nodeToRemove = null;
			}
		
			numberOfItems--;
			return result;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to remove operation");
	}

	@Override
	public void clear() {
		firstNode = null;
		numberOfItems = 0;
	}

	@Override
	public T replace(int location, T newItem) {
		if((location >= 1) && (location <= numberOfItems))
		{
			assert !isEmpty() : "List is empty";
			Node target = getNodeAt(location);
			T originalItem = target.getData();
			target.setData(newItem);
			return originalItem;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to replace operation");
	}

	@Override
	public T look(int location) {
		if((location >= 1) && (location <= numberOfItems))
		{
			assert !isEmpty();
			return getNodeAt(location).data;
		}
		else throw new IndexOutOfBoundsException("Illegal position given to look operation");
	}

	@Override
	public T[] lookAll() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[numberOfItems];
		for(int i = 0; i < array.length; i++)
			array[i] = getNodeAt(i+1).getData();
		
		return array;
	}

	@Override
	public boolean contains(T anItem) {
		boolean found = false;
		Node currentNode = firstNode;
		while(!found && (currentNode != null))
		{
			if(anItem.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		}
		return found;
	}

	@Override
	public int getCount() {
		return numberOfItems;
	}

	@Override
	public boolean isEmpty() {
		boolean result;
		if(numberOfItems == 0)
		{
			assert firstNode == null : "numberOfItems is 0 but firstNode is not null";
			result = true;
		}
		else
		{
			assert firstNode != null : "numberOfItems is not 0 but firstNode is null";
			result = false;
		}
		return result;
	}
	
	private class Node
	{
		private T data;
		private Node next;
		
		private Node(T dataPortion)
		{
			this(dataPortion, null);
		}
		
		public Node(T dataPortion, Node nextNode) 
		{
			data = dataPortion;
			next = nextNode;
		}
		
		public T getData() 
		{
			return data;
		}
		
		private void setData(T newData) 
		{
			data = newData;
		}

		public Node getNextNode() 
		{
			return next;
		}
		
		public void setNextNode(Node nextNode) 
		{
			next = nextNode;
		}
		
	} // End inner class Node.

}
