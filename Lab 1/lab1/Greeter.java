package lab1;
import java.util.Scanner;

public class Greeter
{
	public static void main(String[] args)
	{
		System.out.println("Hello, Nick!");
	
		System.out.println();
		System.out.println("(and Hello COMS 227 TA :D  ) This is the Greeter.Java Class for Lab 1.");
		System.out.println();
		
		Scanner scnr = new Scanner(System.in);
		System.out.println("Is Checkpoint 1 and 2 Complete for Nick?: [Type: 1 for Yes / 2 for No]");
		int checkpoint = scnr.nextInt();
		System.out.println();

		if (checkpoint == 1)
		{
			System.out.println("Checkpoint 1 and 2 Complete. COMS 227 TA please mark complete. Thanks.");
			System.out.println("Nick will now proceed to show you Checkpoint 3 for Lab 1.");
			System.out.println("Thank you!");
		}

		else if (checkpoint == 2)
		{
			System.out.println("Checkpoint 1 Incomplete. Nick needs professional help if he can't do a simple greeter class.");
		} 
		
		else
		{
			System.out.println("Error. Nick sucks at programming haha wow jk (fr tho)");
		}
	}


}
