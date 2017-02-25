package lab2;

public class SelectionSort 
{
	int countMove = 0;
	int countCompare = 0;
	
	
	public void iterativeSelectSort(int[] array) // "red arrow"
	{
		for(int i = 0; i < array.length - 1; i++, countCompare++)
		{	
			int indexOfNextMin = smallestIndexFrom(i, array);
			swap(array, i, indexOfNextMin);
		}
		printArray(array);
		System.out.println("M: "+ countMove + " C: " + countCompare);
	}
	
	private int smallestIndexFrom(int index, int[] array) // "green arrow" 
	{
		int pos = index;
		for(int i = index + 1; i < array.length; i++, countCompare++)
		{
			countCompare++;
			if(array[i] < array[pos])
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
	
	public void recursiveSelectSort(int[] array, int first, int last)
	{
		if(first < last) // base case reach end of array
		{
			int indexOfNextMin = smallestIndexFrom(first, array); // find next smallest index
			swap(array, first, indexOfNextMin);
			recursiveSelectSort(array, first + 1, last);
		}
	}
	
	public void printArray(int array[])
	{
		for(int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
		System.out.println();
	}
	
	public static void main(String args[])
	{
		arrayGenerator ag = new arrayGenerator();
		int[] arr = ag.generateArray(1000, 1000);
		SelectionSort ss = new SelectionSort();
		ss.printArray(arr);
		ss.iterativeSelectSort(arr);
	}
}
