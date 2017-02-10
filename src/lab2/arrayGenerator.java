package lab2;

import java.util.Random;

public class arrayGenerator 
{
	public int[] generateArray(int length, int range)
	{
		int[] array = new int[length];
		Random rand = new Random();
		for(int i = 0; i < array.length; i++)
		{
			array[i] = rand.nextInt(range);
		}
		return array;
	}
}
