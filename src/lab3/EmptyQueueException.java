package lab3;

public class EmptyQueueException extends RuntimeException 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8715338676573611704L;

	public EmptyQueueException()
	{
		this(null);
	} // end default constructor
	
	public EmptyQueueException(String message)
	{
		super(message);
	} // end constructor
} // end EmptyQueueException
