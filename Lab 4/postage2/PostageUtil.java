package postage2;

public class PostageUtil

// THIS IS FOR FLOWCHART 2 // 

// Name: Nicholas McCullough

{
	/**
	 * Returns the cost of postage for a letter of the given weight.
	 * 
	 * @param weight given weight in ounces
	 * @return cost of postage for the weight
	 */
	public static double computePostage(double weight)
	{
		double cost = 0.0;

		if (weight <= 1)
		{
			cost = 0.47; // cost is 47 cent if less than or equal to 1
		}

		else
		{
			if (weight > 1 && weight <= 3.5)
			{
				cost = 0.47 + Math.ceil(weight - 1) * .21; // using math.ceil
			}

			else
			{
				if (weight > 3.5)
				{
					cost = .94 + Math.ceil(weight - 1) * .21; // using math.ceil
				}

				else
				{
					cost = 0.47 + Math.ceil(weight - 1) * .21; // using math.ceil
					
				}
			}

		}

		return cost; // return cost
	}

}

// THIS IS FOR FLOWCHART 2 // 
