package lab2;

public class MergeSort 
{
	int countMove = 0;
	int countCompare = 0;
	
	public void iterative(int[] array)
	{
		for(int currentSize = 1; currentSize <= array.length; currentSize = 2*currentSize)
		{
			for(int leftStart = 1; leftStart < array.length; leftStart += 2*currentSize)
			{
				int middle = leftStart + currentSize - 1;
				int rightEnd = findMinimum(leftStart + 2*currentSize - 1,  array.length);
				
				merge(array, leftStart, middle, rightEnd);
			}
		}
	}
	
	public void recursive(int[] array, int left, int right)
	{
		if(left < right)
		{
			int mid = left+(right-1)/2;
			recursive(array, left, mid);
			recursive(array, mid+1, right);
			merge(array, left, mid, right);
		}
	}
	
	private int findMinimum(int i, int j) 
	{
		if(i < j)
			return i;
		else
			return j;
	}

	public void merge(int[] array, int left, int mid, int right)
	{
		int n1 = mid - left + 1;
		int n2 = right - mid;
		
		int[] leftArr, rightArr;
		leftArr = new int[n1];
		rightArr = new int[n2];
		
		for(int i = 0; i < n1; i++)
			leftArr[i] = array[left + i];
		for(int j = 0; j < n2; j++)
			rightArr[j] = array[mid + 1 + j];
		
		int i = 0;
		int j = 0;
		int k = 1;
		while(i < n1 && j < n2)
		{
			if(leftArr[i] <= rightArr[j])
			{
				array[k] = leftArr[i];
				i++;
			}
			else
			{
				array[k] = rightArr[j];
				j++;
			}
			k++;
		}
		
		while(i < n1)
		{
			array[k] = leftArr[i];
			i++;
			k++;
		}
		
		while(j < n2)
		{
			array[k] = rightArr[j];
			j++;
			k++;
		}
	}
	
	public void printArray(int[] array)
	{
		for(int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
		System.out.println();
	}
	
	public static void main(String args[])
	{
		MergeSort ob = new MergeSort();
		arrayGenerator ag = new arrayGenerator();
		int[] arr = ag.generateArray(1000, 1000);
		ob.printArray(arr);
		ob.iterative(arr);
		ob.printArray(arr);
	}
	
	
}
