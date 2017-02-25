package lab2;

public class InsertionSort 
{
	int countMove = 0;
	int countCompare = 0;
	
	public void iterInsertSort(int[] array, int first, int last) // iterative approach
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

	public void recInsertSort(int[] array, int first, int last) // recursive approach
	{
		if(first < last)
		{
			recInsertSort(array, first, last - 1);
			insertInOrder(array[last], array, first, last - 1);
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
		int[] arr = ag.generateArray(10, 100);
		InsertionSort is = new InsertionSort();
		is.printArray(arr);
		is.iterInsertSort(arr, 0, arr.length - 1);
	}
}
