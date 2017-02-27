package lab2;

public class RadixSort 
{
	int countCompare = 0;
	int countMove = 0;
	
	public void iterativeRadixSort(int[] array, int first, int last)
	{
		@SuppressWarnings("unchecked")
		LinkedQueue<Integer>[] buckets = new LinkedQueue[10];

		for(int i = 0; i < buckets.length; i++)
			buckets[i] = new LinkedQueue<Integer>();
		
		int expo = 1;
		int maxValue = getMaxValue(array);
		
		while(maxValue / expo > 0)
		{
			countCompare++;
			//for(int b = 0; b < 10; b++)
				//buckets[b].clear();
			
			for(int index = first; index <= last; index++, countCompare++)
			{
				int bucket = (array[index] / expo) % 10;
				
				buckets[bucket].enqueue(array[index]);
				countMove++;
			}
			
			expo *= 10;
			int index = 0;
			
			for(LinkedQueue<Integer> bucket : buckets)
			{
				countCompare++;
				while(!bucket.isEmpty())
				{
					array[index++] = bucket.dequeue();
					countMove++;
				}
			}
			
			//printArray(array);
		}
		System.out.println("Move: " + countMove);
		System.out.println("Compare: " + countCompare);
	}
	
	public void recursiveRadixSort(int[] array, int first, int last)
	{
		@SuppressWarnings("unchecked")
		LinkedQueue<Integer>[] buckets = new LinkedQueue[10];

		for(int i = 0; i < buckets.length; i++)
			buckets[i] = new LinkedQueue<Integer>();
		
		if(first < last)
		{
			
		}
	}

	private int getMaxValue(int[] array) 
	{
		int max = 0;
		for(int i = 0; i < array.length; i++)
			if(array[i] > max)
				max = array[i];
		return max;
	}

	public void printArray(int array[])
	{
		for(int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
		System.out.println();
	}
	
	public static void main(String args[])
	{
		RadixSort ob = new RadixSort();
		arrayGenerator ag = new arrayGenerator();
		int[] arr = ag.generateArray(100, 1000);
		ob.printArray(arr);
		ob.iterativeRadixSort(arr, 0, arr.length-1);
		ob.printArray(arr);
	}
}
