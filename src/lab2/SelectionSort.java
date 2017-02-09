package lab2;

public class SelectionSort 
{
	int countMove = 0;
	int countCompare = 0;
	public void interative(int[] array)
	{
		for(int i = 0; i < array.length - 1; i++)
		{	
			int indexOfNextMin = smallestIndexFrom(i, array);
			swap(array, i, indexOfNextMin);
		}
	}
	
	private int smallestIndexFrom(int index, int[] array) 
	{
		int pos = index;
		for(int i = index + 1; i < array.length; i++)
			if(array[i] < array[pos])
			{
				countCompare++;
				pos = i;
			}
		return pos;
	}

	private void swap(int[] array, int pos1, int pos2)
	{
		int temp;
		temp = array[pos1];
		array[pos1] = array[pos2];
		array[pos2] = temp;
		countMove += 2;
	}
	
	public void recursive(int[] array, int first, int last)
	{
		if(first < last)
		{
			int indexOfNextMin = smallestIndexFrom(first, array);
			swap(array, first, indexOfNextMin);
			recursive(array, first + 1, last);
		}
	}
}
