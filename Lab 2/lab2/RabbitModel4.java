package lab2;

/**
 * The population follows the Fibonacci sequence, in which the current
 * population is the sum of the previous two years' populations. Assume it
 * starts out with value 1 for the last year's population and 0 for the year
 * before that, so that the initial population is also 1 + 0 = 1. For example,
 * the values would look like this for the first few years:
 */
public class RabbitModel4
{
	
	// global variables
	private int population;
	private int lastYear;
	private int yearBefore;

	/**
	 * Constructs a new RabbitModel.
	 */
	
	public RabbitModel4()
	{
		lastYear = 1;
		yearBefore = 0;
		population = 0;
	}

	/**
	 * Returns the current number of rabbits.
	 * 
	 * @return current rabbit population
	 */
	
	public int getPopulation()
	{
		// returns int population variable
		return population;
	}

	/**
	 * Updates the population to simulate the passing of one year.
	 */
	public void simulateYear()
	{
		// simulates one year passing
		population = lastYear + yearBefore;
		yearBefore = lastYear;
		lastYear = population;
	}

	/**
	 * Sets or resets the state of the model to the initial conditions.
	 */
	public void reset()
	{
		// final population
		lastYear = 1;
		yearBefore = 0;
		population = 0;
	}

	/*
	 * main method to run
	 */
	public static void main(String[] args)
	{
		RabbitModel4 rm = new RabbitModel4();

		System.out.println("This is for Lab 2, Checkpoint 2, Rabbit Model 4");
		System.out.println();

		System.out.println("The beginning population is 0");
		// for loop to iterate through 10 years
		for (int i = 1; i < 20; i++)
		{
			rm.simulateYear();
			System.out.println("Population after the Simulation Year " + (i + 1) + " is " + rm.getPopulation());
		}
	}
}
