package lab3;


/**
 * 
 * @author Jeremy Canning
 * A two-part circular linked implementation of the ADT Queue.
 * 
 */
public class CircularLinkedQueue<T> implements QueueInterface<T> 
{
	private Node backNode;
	private Node freeNode;
	private int size;
	private static final int MAX_SIZE = 50;
	
	public CircularLinkedQueue()
	{
		freeNode = new Node(null); // initially has an empty node
		freeNode.setNextNode(freeNode); // that references itself
		backNode = freeNode; // both references point to the same node at the start
		size = 0;
	} // end default constructor

	@Override
	public void enqueue(T newEntry) 
	{
		if(size < MAX_SIZE)
		{
			freeNode.setData(newEntry);
			if(isChainFull())
			{
				Node newNode = new Node(null, freeNode.getNextNode());
				freeNode.setNextNode(newNode);
				size++;
			}
			freeNode = freeNode.getNextNode();
		}
		else
			throw new RuntimeException("Queue at maximum size of " + MAX_SIZE + ". No further entries may be enqueued without first dequeuing at least one entry.");
	} // end enqueue

	@Override
	public T dequeue()
	{
		T front = getFront();
		assert !isEmpty();
		backNode.setData(null);
		backNode = backNode.getNextNode();
		return front;
	} // end dequeue

	@Override
	public T getFront() 
	{
		if(isEmpty())
			throw new EmptyQueueException();
		else
			return backNode.getData();
	} // end getFront

	@Override
	public boolean isEmpty() 
	{
		return backNode == freeNode;
	} // end isEmpty
	
	private boolean isChainFull()
	{
		return backNode == freeNode.getNextNode();
	} // end isChainFull

	@Override
	public void clear() 
	{
		while(!isEmpty())
			dequeue();
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
