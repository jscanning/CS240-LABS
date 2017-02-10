package lab2;

public class LinkedQueue<T> implements QueueInterface<T> 
{

	private Node firstNode;
	private Node lastNode;
	
	public LinkedQueue()
	{
		firstNode = null;
		lastNode = null;
	}
	
	@Override
	public void enqueue(T newEntry) 
	{
		Node newNode = new Node(newEntry);
		if(isEmpty())
			firstNode = newNode;
		else
			lastNode.setNextNode(newNode);
		lastNode = newNode;	
	} // end enqueue

	@Override
	public T dequeue() 
	{
		T front = getFront();
		assert firstNode != null;
		firstNode.setData(null);
		firstNode = firstNode.getNextNode();
		
		if(firstNode == null)
			lastNode = null;
		
		return front;
	} // end dequeue

	@Override
	public T getFront() 
	{
		if(isEmpty())
			throw new EmptyQueueException();
		else
			return firstNode.getData();
	} // end getFront

	@Override
	public boolean isEmpty() {
		return (firstNode == null) && (lastNode == null);
	} // end isEmpty

	@Override
	public void clear() {
		firstNode = null;
		lastNode = null;
	} // end clear
	
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
	
} // end class LinkedQueue
