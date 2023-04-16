package lab2;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModelTest
{
  private int population;
  //Rabbit Model 1
  
 
  
  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModelTest()
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
	  population += 1;
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    // resets to initial population of 2
	  population = 2;
  }
  
  /*
   * main method to run
   */
  public static void main(String[] args)
  {
	   RabbitModelTest rm= new RabbitModelTest();
	   System.out.println("Poulation before SimulateYear: "+rm.getPopulation());
	   rm.simulateYear();
	   System.out.println("Poulation after SimulateYear: "+rm.getPopulation());
  }
}
