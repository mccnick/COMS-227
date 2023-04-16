package lab7;

import java.util.Scanner;

public class mason
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the size of walkway : ");
		int number = sc.nextInt(); // scans for walkway int
		int numberOfPattern = countPatterns(number); // calls countPatterns method using walkway number
		System.out.println("Total way of arranging tiles are : " + numberOfPattern);
		sc.close(); // close scanner
	}


	public static int countPatterns(int n)
	{

		int totalResult = 0;
		int one, three, remaining;

		for (one = 0; one <= n; one++)
		{

			remaining = n - one;
			if (remaining % 3 == 0)
			{

				three = remaining / 3;
				System.out.println("Number of one foot tiles : " + one + "\nNo of three foot tiles : " + three);

				if (one == 0 || three == 0)
				{
					System.out.println("total combination : 1");
					totalResult++;

				}

				else
				{
					int max = one;
					if (one < three)
					{
						max = three;
					}

					int totalCombinations = getFactorial(one + three) / getFactorial(max);
					System.out.println("total combination : " + totalCombinations);
					totalResult += totalCombinations;
				}
				System.out.println("\n\n\n");
			}

		}
		return totalResult;
	}

	public static int getFactorial(int n)
	{

		int result = 1;

		for (int i = 1; i <= n; i++)
		{
			result *= i;
		}

		return result;

	}

}