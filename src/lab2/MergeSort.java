package lab2;

public class MergeSort 
{
	static int countMove = 0;
	static int countCompare = 0;

	public void iterativeMergeSort(int[] a) 
	{
		int[] aux = new int[a.length];
		
		for (int blockSize=1; blockSize<a.length; blockSize*=2)
			for (int start=0; start<a.length; start+=2*blockSize)
				merge(a, aux, start, start+blockSize, start+2*blockSize);
	}


	public static void recursiveMergeSort(int[] a, int[] aux, int lo, int hi) 
	{
		// base case
		if (hi - lo > 1) 
		{
			// sort each half, recursively
			int mid = lo + (hi - lo) / 2;
			recursiveMergeSort(a, aux, lo, mid);
			recursiveMergeSort(a, aux, mid, hi);
			// merge back together
			merge(a, aux, lo, mid, hi);
		}
	}

	public void recursiveMergeSort(int[] a) 
	{
		int n = a.length;
		int[] aux = new int[n];
		recursiveMergeSort(a, aux, 0, n);
	}

	private static void merge(int[] a, int[] aux, int begin, int mid, int end) 
	{
		if (mid < a.length) 
		{
			countCompare++;
			if (end > a.length) 
				end = a.length;
			
			int i = begin; 
			int j = mid;
			
			countCompare++;
			for (int k = begin; k < end; k++, countCompare++, countMove++) // compares k to end every loop
			{
				countCompare++; // incrementing before the comparison (i to mid)
				if (i == mid)
					aux[k] = a[j++];
				// when this else/if block comes out false countCompare does not get added to. Other blocks account for this.
				else if (j == end) { countCompare++; // only adds if true, when false next else block accounts for this comparison
					aux[k] = a[i++];
				}
				
				else if (a[j] < a[i]) { countCompare+=2; // adding this comparison and the previous.
					aux[k] = a[j++];
				}
				
				else   // no comparison made but since previous else/ifs only added when true must add those comparisons.
				{	countCompare+=2; // adding previous two comparisons whose results were false.
					aux[k] = a[i++];
				}
			}
			
			for (int k = begin; k < end; k++, countCompare++, countMove++)
				a[k] = aux[k];
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
		ob.iterativeMergeSort(arr);
		ob.printArray(arr);
		System.out.println("Comparisons: " + countCompare + " Moves: " + countMove);
	}


}
