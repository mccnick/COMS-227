package lab7;

/**
 * CHECKPOINT 1.1 Returns the maximum value in the array.
 */

public class ArrayMaximum
{
	public static void main(String[] args)
	{
		int[] test =
		{3, 7, 9, 1, 2, 3, 2, 7, 10}; // max is 10
		int result = arrayMax(test);

		System.out.println("The maximum value of this array is: " + result);
	}

	public static int arrayMax(int[] arr)
	{
		return arrayMaximumValue(arr, 0, arr.length - 1);
	}

	private static int arrayMaximumValue(int[] arr, int start, int end)
	{
		if (start == end)
		{
			return arr[start];
		}

		else
		{
			int mid = (start + end) / 2;
			int leftSum = arrayMaximumValue(arr, start, mid);
			int rightSum = arrayMaximumValue(arr, mid + 1, end);

			// if statement for leftSum & rightSum
			if (leftSum > rightSum)
			{
				return leftSum;
			}
			else
			{
				return rightSum;
			}
		}
	}
}
