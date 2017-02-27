package lab2;


public class QuickSort 
{
	
	int countMove = 0, countCompare = 0;
	
	public void recursiveQuickSort(int[] array, int first, int last)
	{
		if(first < last)
		{
			int pivotIndex = partition(array, first, last);
			recursiveQuickSort(array, first, pivotIndex - 1);
			recursiveQuickSort(array, pivotIndex + 1, last);
		}
	}
	
	
	private int partition(int[] array, int first, int last)
	{
		int pivotValue = array[last];
		int index = (first-1);
		for(int j = first; j < last; j++)
		{
			countCompare++;
			if(array[j] <= pivotValue)
			{
				countCompare++;
				index++;
				swap(array, index, j);
			}
		}
		
		swap(array, index + 1, last);
		return index + 1;
	}
	
	public void iterativeQuickSort(int[] array, int first, int last)
	{
		
		int stack[] = new int[last - first + 1];
		int top = -1;
		stack[++top] = first;
		stack[++top] = last;
		
		while(top >= 0)
		{
			countCompare++;
			last = stack[top--];
			first = stack[top--];
			
			int pivot = partition(array, first, last);
			
			if(pivot - 1 > 1)
			{
				countCompare++;
				stack[++top] = first;
				stack[++top] = pivot - 1;
			}
			
			if(pivot + 1 < last)
			{
				stack[++top] = pivot + 1;
				stack[++top] = last;
			}
		}
	//	System.out.println("Move: " + countMove);
		//System.out.println("Compare: " + countCompare);
	}
	
	public void printArray(int array[])
	{
		for(int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
		System.out.println();
	}
	
	private void swap(int[] array, int pos1, int pos2)
	{
		int temp;
		temp = array[pos1];
		array[pos1] = array[pos2];
		array[pos2] = temp;
		countMove++;
	}
	
	public static void main(String args[])
	{
		QuickSort ob = new QuickSort();
		arrayGenerator ag = new arrayGenerator();
		int[] arr = ag.generateArray(30, 30);
		//int[] arr = new int[]{-1, 4, 0,1,-2};
		ob.printArray(arr);
		ob.recursiveQuickSort(arr, 0, arr.length-1);
		ob.printArray(arr);
	}
}
