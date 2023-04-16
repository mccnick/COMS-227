package lab6;

// Nicholas McCullough

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import plotter.Plotter;
import plotter.Polyline;

public class PolyLineCheckPoint2
{
	
	public static void main(String args[]) throws FileNotFoundException
	{
		List<Polyline> arrayListvals = readFile("hello.txt");
		Plotter p = new Plotter();
		for (Polyline p2 : checkpoint2)
		{
			p.plot(p2);
		}
	}
	public static ArrayList<Polyline> checkpoint2 = new ArrayList<Polyline>();

	private static ArrayList<Polyline> readFile(String filename) throws FileNotFoundException
	{
		// open the file
		File file = new File(filename);
		Scanner scanner = new Scanner(file);
		Plotter plotter = new Plotter();

		// while there are more lines...

		while (scanner.hasNextLine())
		{

			// get the next line
			String line = scanner.nextLine();
			line = line.trim();

			if (!line.isEmpty() && !line.startsWith("#"))
			{
				// construct a temporary scanner, just to read data from this line
				Scanner temp = new Scanner(line);
				System.out.println(line);

				// get the first and last name
				String first = temp.next();
				boolean isStringNumber = first.matches("[0-9]+");
				String last = "";
				int n = 0;

				if (isStringNumber)
				{
					n = Integer.parseInt(first);
					last = temp.next();
				}

				else
				{
					last = first;
				}

				System.out.println(first + " " + last);
				Polyline p1 = new Polyline(last, n);

				// add up all the scores
				double total = 0.0;
				
				while (temp.hasNextInt())
				{
					int value = temp.nextInt();
					int value2 = 0;
					if (temp.hasNextInt())
						value2 = temp.nextInt();

					// System.out.print(value+"values:"+value2);
					p1.addPoint(new Point(value, value2));
				}
				checkpoint2.add(p1);
			}
		}
		// close the file
		scanner.close();
		return checkpoint2;
	}
}