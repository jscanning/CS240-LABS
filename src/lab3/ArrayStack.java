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
	private static final int MAX_CAP = 10000; //currently no checkCapacity function
	
	public ArrayStack()
	{
		this(DEFAULT_CAPACITY);
	}
	
	private ArrayStack(int initCapacity)
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
		ensureCapacity(); //unimplemented; would allocate more space for the array if necessary and valid.
		checkInitialization();
		try{
			stack[topIndex + 1] = newEntry;
			topIndex++;
		}catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Attempted to add an entry to a full stack.");
		}
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
	
} // end class ArrayStack
