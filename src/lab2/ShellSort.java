package lab2;

public class ShellSort 
{
	
	public void iterativeShellSort(int[] array)
	{
		int gap = getHibbardSeq(array.length/2);
		while(gap > 1)
		{
			insertSort(array, gap);
			gap = getHibbardSeq(gap-1);
		}
		insertSort(array, 1);
	}
	
	public void recursiveShellSort(int[] array, int gap)
	{
		if(gap > 1)
		{
			recursiveShellSort(array, getHibbardSeq(gap-1));
			insertSort(array, gap);
		}
		insertSort(array, 1);
	}
	
	private void insertSort(int[] arr, int gap) 
	{
		for(int unsorted = gap; unsorted < arr.length; unsorted += gap)
		{
			int nextToInsert = arr[unsorted];
			int index = unsorted - gap;
			while((index >= 0) && (nextToInsert < arr[index]))
			{
				arr[index+gap] = arr[index];
				index -= gap;
			}
			arr[index+gap] = nextToInsert;
		}
	}

	private int getHibbardSeq(int limit) 
	{
		int sequence = 1;
		int k = 1;
		
		while(sequence < limit)
		{
			k++;
			sequence = (int)(Math.pow(2, k)-1);
		}
		
		if(sequence > limit){
			--k;
			return (int)(Math.pow(2, k)- 1);
		}
		System.out.println("the gap is " + sequence);
		return sequence;
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
		int[] arr = ag.generateArray(10, 1000);
		ShellSort sort = new ShellSort();
		sort.printArray(arr);
		sort.recursiveShellSort(arr, sort.getHibbardSeq(arr.length/2));
		sort.printArray(arr);
	}
}
