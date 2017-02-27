package lab3;


public class QuickSort 
{
	
	int countMove = 0, countCompare = 0;
	
	public void recursiveQuickSort(Integer[] tempArray, int first, int last)
	{
		if(first < last)
		{
			int pivotIndex = partition(tempArray, first, last);
			recursiveQuickSort(tempArray, first, pivotIndex - 1);
			recursiveQuickSort(tempArray, pivotIndex + 1, last);
		}
	}
	
	
	private int partition(Integer[] tempArray, int first, int last)
	{
		int pivotValue = tempArray[last];
		int index = (first-1);
		for(int j = first; j < last; j++)
		{
			countCompare++;
			if(tempArray[j] <= pivotValue)
			{
				countCompare++;
				index++;
				swap(tempArray, index, j);
			}
		}
		
		swap(tempArray, index + 1, last);
		return index + 1;
	}
	
	public void iterativeQuickSort(Integer[] array, int first, int last)
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
	
	public void printArray(Integer[] tempArray)
	{
		for(int i = 0; i < tempArray.length; i++)
			System.out.print(tempArray[i] + " ");
		System.out.println();
	}
	
	private void swap(Integer[] tempArray, int pos1, int pos2)
	{
		int temp;
		temp = tempArray[pos1];
		tempArray[pos1] = tempArray[pos2];
		tempArray[pos2] = temp;
		countMove++;
	}
	
	public static void main(String args[])
	{
	}
}
