package lab3;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 
 * @author Jeremy Canning
 * An implementation of the stack interface as an array.
 */

public final class ArrayStack<T> implements StackInterface<T>
{
	private T[] stack;
	private int topIndex;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 10;
	private static final int MAX_CAP = 500000; //currently no checkCapacity function
	
	public ArrayStack()
	{
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayStack(int initCapacity)
	{	
		checkCapacity(initCapacity);
		
		@SuppressWarnings("unchecked")
		T[] tempStack = (T[])new Object[initCapacity];
		stack = tempStack;
		topIndex = -1;
		initialized = true;
	}
	
	@Override
	public void push(T newEntry) 
	{
		ensureCapacity(); 
		checkInitialization();
		stack[topIndex + 1] = newEntry;
		topIndex++;
	}
	
	// multi-push method for pushing several copies of the same entry onto the stack.
	public void push(T newEntry, int numOfCopies)
	{
		for(int i = numOfCopies; i > 0; i--)
			push(newEntry);
	}

	@Override
	public T pop() 
	{
		checkInitialization();
		if(isEmpty())
			throw new EmptyStackException();
		else
		{
			T top = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			ensureCapacity();
			return top;
		}
	}

	private void ensureCapacity() 
	{
		if(topIndex == stack.length - 1)
		{
			int newLength = 2*stack.length;
			checkCapacity(newLength);
			stack = Arrays.copyOf(stack, newLength);
		}
		else if(topIndex <= stack.length / 4)
		{
			int newLength = stack.length / 2;
			checkCapacity(newLength);
			stack = Arrays.copyOf(stack, newLength);
		}
	}

	private void checkCapacity(int newLength) 
	{
		if(newLength > MAX_CAP)
			throw new IllegalStateException("Attempted to create a stack whose "
					+ "capacity exceeds allowed maximum of " + MAX_CAP);
	}

	@Override
	public T peek() 
	{
		checkInitialization();
		
		if(isEmpty())
			throw new EmptyStackException();
		else
			return stack[topIndex];
	}

	private void checkInitialization() 
	{
		if(!initialized)
			throw new SecurityException("ArrayStack object is not initialized properly.");
	}

	@Override
	public boolean isEmpty() 
	{
		return topIndex < 0;
	}

	@Override
	public void clear() 
	{
		while(!isEmpty())
			pop();
	}
	
	public int getLength()
	{
		return topIndex + 2;
	}
	
	public T[] toArray()
	{
		return stack;
	}
} // end class ArrayStack
