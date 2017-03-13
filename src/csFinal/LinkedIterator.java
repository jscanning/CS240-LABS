package csFinal;

import java.util.NoSuchElementException;

public class LinkedIterator<T> implements IteratorInterface<T> 
{
	private Node prevNode, targetNode, nextNode;
	private boolean canRemove;
	
	private LinkedIterator(Node firstNode)
	{
		nextNode = firstNode;
		targetNode = null;
		prevNode = null;
		prevNode.setNextNode(targetNode);
		canRemove = false;
	}	
	
	@Override
	public boolean hasNext() 
	{
		return nextNode != null;
	}

	@Override
	public T next() 
	{
		if(hasNext())
		{
			Node returnNode = nextNode;
			targetNode = nextNode;
			if(prevNode.getNextNode() != targetNode)
				prevNode = prevNode.getNextNode();
			nextNode = nextNode.getNextNode();
			canRemove = true;
			return returnNode.getData();
		}
		else
			throw new NoSuchElementException();
	}

	@Override
	public void remove() 
	{
		if(canRemove)
		{
			targetNode = nextNode;
			prevNode.setNextNode(targetNode);
			canRemove = false;
		}
		else
			throw new IllegalStateException();
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
	}
}
