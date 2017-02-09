package lab2;

public class InsertionSort 
{
	public void interative(int[] array, int first, int last)
	{
		for(int unsorted = first + 1; unsorted <= last; unsorted++)
		{
			int nextToInsert = array[unsorted];
			insertInOrder(nextToInsert, array, first, unsorted - 1);
		}
	}
	
	public void recursive(int[] array, int first, int last)
	{
		if(first < last)
		{
			recursive(array, first, last - 1);
			insertInOrder(array[last], array, first, last - 1);
		}
	}

	private void insertInOrder(int anEntry, int[] array, int begin, int end) 
	{
		int index = end;
		while((index >= begin) && (anEntry < array[index]))
		{
			array[index+1] = array[index];
			index--;
		}
		array[index+1] = anEntry;
	}
}
