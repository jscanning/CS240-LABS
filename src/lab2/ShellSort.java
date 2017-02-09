package lab2;

public class ShellSort 
{
	private void incrementalInsertionSort(int[] array, int first, int last, int increment)
	{
		for(int unsorted = first + increment; unsorted < last; unsorted += increment)
		{
			int nextToInsert = array[unsorted];
			int index = unsorted - increment;
			while((index >= first) && (nextToInsert > array[index]))
			{
				array[index + increment] = array[index];
				index = index - increment;
			}
			array[index + increment] = nextToInsert;
		}
	}
	
	public void iterativeShellSort(int[] array, int first, int last)
	{
		int sequence = 1;
		while(sequence < last)
		{
			for(int begin = first; begin <= first + sequence - 1; begin++)
			{
				incrementalInsertionSort(array, begin, last, sequence);
			}
			sequence = (2*sequence + 1);
		}
		incrementalInsertionSort(array, first, last, 1);
	}
	
	public void recursiveShellSort(int[] array, int first, int last)
	{
		
	}
}
