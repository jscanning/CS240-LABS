package lab3;

import lab2.LinkedQueue;

public class RadixSort 
{
	int countCompare = 0;
	int countMove = 0;
	
	public void iterativeRadixSort(Integer[] integers, int first, int last)
	{
		@SuppressWarnings("unchecked")
		LinkedQueue<Integer>[] buckets = new LinkedQueue[10];

		for(int i = 0; i < buckets.length; i++)
			buckets[i] = new LinkedQueue<Integer>();
		
		int expo = 1;
		int maxValue = getMaxValue(integers);
		
		while(maxValue / expo > 0)
		{
			countCompare++;
			//for(int b = 0; b < 10; b++)
				//buckets[b].clear();
			
			for(int index = first; index <= last; index++, countCompare++)
			{
				int bucket = (integers[index] / expo) % 10;
				
				buckets[bucket].enqueue(integers[index]);
				countMove++;
			}
			
			expo *= 10;
			int index = 0;
			
			for(LinkedQueue<Integer> bucket : buckets)
			{
				countCompare++;
				while(!bucket.isEmpty())
				{
					integers[index++] = bucket.dequeue();
					countMove++;
				}
			}
			
	//		printArray(integers);
		}
	//	System.out.println("Move: " + countMove);
	//	System.out.println("Compare: " + countCompare);
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

	private int getMaxValue(Integer[] integers) 
	{
		int max = 0;
		for(int i = 0; i < integers.length; i++)
		{
			if(integers[i] > max)
				max = integers[i];
		}
		return max;
	}

	public void printArray(Integer[] integers)
	{
		for(int i = 0; i < integers.length; i++)
			System.out.print(integers[i] + " ");
		System.out.println();
	}
	
	public static void main(String args[])
	{
		RadixSort ob = new RadixSort();
		Integer[] arr = new Integer[]{5, 300, 302, 309, 301, 304, 303, 1};
		ob.printArray(arr);
		ob.iterativeRadixSort(arr, 0, arr.length-1);
		ob.printArray(arr);
		System.out.println();
	}
}
