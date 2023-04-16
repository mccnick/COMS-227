package postage3;

public class PostageUtil

// THIS IS FOR FLOWCHART 3 //

{
	/**
	 * Returns the cost of postage for a letter of the given weight.
	 * 
	 * @param weight given weight in ounces
	 * @return cost of postage for the weight
	 */
	public static double computePostage(double weight)
	{
		double cost = 0.47; // starting cost

		if (weight > 1)

		{
			cost = cost + Math.ceil(weight - 1) * 0.21;
		}

		else
		{
			if (weight <= 3.5)
			{
				cost = 0.47 + Math.ceil(weight - 1) * .21; // using math.ceil
			}

			else
			{
				cost = .94 + Math.ceil(weight - 1) * .21; // using math.ceil
			}
		}

		return cost;
	}

}
