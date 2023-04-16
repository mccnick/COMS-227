package lab2;

/**
 * The population starts at zero and increases each year by 1 rabbit, 
 * but after every 5 years, oversaturation brings the population back down to 0. 
 * (Hint: consider an expression using the mod operator "%". 
 * If you divide the population by 5, what is the remainder?)
 */
public class RabbitModel1
{
  private int population;
  private int previousYear;
  private int otherYear;
  
  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModel1()
  {
	  // Sets or resets the state of the model to the initial conditions.
	  reset();
  }  
 
  /**
   * Returns the current number of rabbits.
   * @return
   *   current rabbit population
   */
  public int getPopulation()
  {
    // returns int population variable
    return population;
    
  }
  
  /**
   * Updates the population to simulate the
   * passing of one year.
   */
  public void simulateYear()
  {
    // simulates one year passing
	  otherYear = previousYear;
      previousYear = population;
      population = (population + 1) % 5; // remainder 5
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    // resets to initial population of 2
	  population = 0;
  }
  
  /*
   * main method to run
   */
  public static void main(String[] args)
  {
	   RabbitModel1 rm = new RabbitModel1();
	   
	   System.out.println("This is for Lab 2, Checkpoint 2, Rabbit Model 1");
	   System.out.println();
	   
	   System.out.println("The beginning population is 0");
	   // for loop to iterate through 10 years
	   for (int i = 1; i <= 10; i++) {
           rm.simulateYear();
           System.out.println("Population after the Simulation Year " + i + " is " + rm.getPopulation());
       }
  }
}
