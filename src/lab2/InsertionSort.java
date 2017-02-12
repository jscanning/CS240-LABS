package lab2;

public class InsertionSort 
{
	int countMove = 0;
	int countCompare = 0;
	
	public void iterative(int[] array, int first, int last)
	{
		for(int unsorted = first + 1; unsorted <= last; unsorted++, countCompare++)
		{
			int nextToInsert = array[unsorted];
			insertInOrder(nextToInsert, array, first, unsorted - 1);
		}
		printArray(array);
		System.out.println("M: " + countMove);
		System.out.println("C: " + countCompare);
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
			countCompare++;
			countMove++;
			array[index+1] = array[index];
			index--;
		}
		array[index+1] = anEntry;
		countMove++;
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
		InsertionSort is = new InsertionSort();
		is.printArray(arr);
		is.iterative(arr, 0, arr.length - 1);
	}
}
