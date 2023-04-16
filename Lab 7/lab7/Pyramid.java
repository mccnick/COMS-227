package lab7;

/**
 * CHECKPOINT 1.2 
 * Returns the amount of balls in a given level of pyramid.
 * 
 * Nicholas McCullough
 */

class Pyramid
{
	public static void main(String[] args)
	{
		// change value of n for different outputs
		int n = 10;
		System.out.println("There are " + n + " levels in this pyramid.");
		System.out.println("A pyramid with " + n + " levels has " + Pyramid.getPyramidCount(n) + " balls.");
	}

	// pyramid balls method, if else, recursion n*n * method(n-1), static recursive method getPyramidCount
	public static int getPyramidCount(int n)
	{

		if (n == 1)
		{
			return 1;
		}

		else
		{
			// given recursion equation --> return 10*10 + getPyramidCount(10-1) + .... 9*9  + 8*8  ... etc
			return n * n + getPyramidCount(n - 1);
		}

	}

}