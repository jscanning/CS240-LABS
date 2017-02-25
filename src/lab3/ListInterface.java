package lab3;

public interface ListInterface<T>
{
	/** 
	 * Adds a new item to the end of this list.
	 * Items already in the list are unaffected. 
	 * The size of the list increases by 1.
	 * @param newItem The object to be added as a new item.
	 * @return True if the operation was successful, false otherwise.
	 */
	public boolean add(T newItem);
	
	/**
	 * Adds a new item at a specified place in this list.
	 * Items at or below this specific place are shifted down.
	 * The list's size increases by 1.
	 * @param newItem The object to be added as a new item.
	 * @param location An integer that specifies the target position of the new item.
 	 * @throws IndexOutOfBoundsException if the target position is < 1 or otherwise invalid.
	 */
	public void add(T newItem, int location);
	
	/**
	 * Removes a specific item from the list.
	 * Items below this item are shifted up 
	 * and the list's size decreases by 1.
	 * @param item The specific object to be removed.
	 * @return True if the item is successfully removed, false if the item didn't exist in the list.
	 */
	public boolean remove(T item);
	
	/**
	 * Removes an item from a specific position in this list.
	 * Items below this position are shifted up and the list's size decreases by 1.
	 * @param location An integer indicating the target position.
	 * @return A reference to the removed item.
	 * @throws IndexOutOfBoundsException if location is < 1 or otherwise invalid.
	 */
	public T remove(int location);
	
	/**
	 * Removes all items from the list.
	 * List size is set to 0.
	 */
	public void clear();
	
	/**
	 * Replaces the item at a specific position with a new item.
	 * Other items in the list are unaffected, list size stays the same.
	 * @param location An integer that indicates the target position.
	 * @param anItem The object that will replace the item at the target position.
	 * @return The original item that was replaced.
	 * @throws IndexOutOfBoundsException if location is invalid.
	 */
	public T replace(int location, T newItem);
	
	/**
	 * Retrieves the item at the specified position in the list.
	 * @param location An integer indicating the position to look at.
	 * @return A reference to the object in the target position.
	 * @throws IndexOutOfBoundsException if location is invalid.
	 */
	public T look(int location);
	
	/**
	 * Retrieves all items in this list in the order in which they occur in the list.
	 * @return A new array of all the items in this list.
	 * 			If the list is empty, the array will be empty.
	 */
	public T[] lookAll();
	
	/**
	 * Determines if this list contains a specified item.
	 * @param anItem The object that is the desired item.
	 * @return True if this list contains the anItem, false otherwise.
	 */
	public boolean contains(T anItem);
	
	/**
	 * Gets the number of items in this list
	 * @return The integer number of items in this list.
	 */
	public int getCount();
	
	/**
	 * Determines if this list is empty.
	 * @return True if the list is empty, false otherwise.
	 */
	public boolean isEmpty();
	
	
	
} // end ListInterface
