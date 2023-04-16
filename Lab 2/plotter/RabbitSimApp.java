package plotter;

import lab2.RabbitModel;
import plotter.SimulationPlotter;;

public class RabbitSimApp
{

	public static void main(String[] args)
	{

// Construct a SimulationPlotter

		SimulationPlotter plotter = new SimulationPlotter();

		RabbitModel rm = new RabbitModel();
		plotter.simulate(rm);

	}
}
