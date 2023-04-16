package lab2;

/**
 * The increase in population each year is a random value in the range 0 through 9.
 */

// NAME: NICHOLAS MCCULLOUGH


public class RabbitModel
{
	private int population;

	/**
	 * Constructs a new RabbitModel.
	 */
	public RabbitModel()
	{
		// Sets or resets the state of the model to the initial conditions.
		reset();
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
		population += (int) (Math.random()*10);
	}

	/**
	 * Sets or resets the state of the model to the initial conditions.
	 */
	public void reset()
	{
		// resets to initial population
		population = 0;
	}

	/*
	 * main method to run
	 */
	public static void main(String[] args)
	{
		RabbitModel3 rm = new RabbitModel3();

		System.out.println("This is for Lab 2, Checkpoint 2, Rabbit Model 3");
		System.out.println();

		System.out.println("The beginning population is a random number between 1-10");
		// for loop to iterate through 10 years
		for (int i = 0; i < 5; i++)
		{
			rm.simulateYear();
			System.out.println("Population after the Simulation Year " + i+1 + " is " + rm.getPopulation());
		}
	}
}
