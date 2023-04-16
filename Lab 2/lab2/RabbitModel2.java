package lab2;

/**
 * The population starts at 500 and decreases by half each year.
 */
public class RabbitModel2
{
	private int population;

	/**
	 * Constructs a new RabbitModel.
	 */
	public RabbitModel2()
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
		population /= 2;
	}

	/**
	 * Sets or resets the state of the model to the initial conditions.
	 */
	public void reset()
	{
		// resets to initial population of 500
		population = 500;
	}

	/*
	 * main method to run
	 */
	public static void main(String[] args)
	{
		RabbitModel2 rm = new RabbitModel2();

		System.out.println("This is for Lab 2, Checkpoint 2, Rabbit Model 2");
		System.out.println();

		System.out.println("The beginning population is 500");
		// for loop to iterate through 10 years
		for (int i = 1; i <= 10; i++)
		{
			rm.simulateYear();
			System.out.println("Population after the Simulation Year " + i + " is " + rm.getPopulation());
		}
	}
}
