package lab3;
import org.junit.Test;
import balloon.Balloon; // change balloon (original) to balloon1, balloon2, etc to test the different balloons
import static org.junit.Assert.*;

/**
 * A newly constructed Balloon should have radius zero.
 * A newly constructed Balloon should not be popped.
 * After calling blow(5) on a Balloon with maximum radius 10, the radius should be 5.
 */

public class BalloonTests 
{
	
	@Test
	public void getRadius()
	{
	Balloon b = new Balloon(10); // 10 for original balloon
	assertEquals(0, b.getRadius()); // 0 for original balloon
	}
	
	@Test
	public void isPopped()
	{
	Balloon b = new Balloon(10); // 10 for original balloon
	assertEquals(false, b.isPopped()); // false for original balloon
	}

	@Test
	public void blow()
	{
	Balloon b = new Balloon(10); // 10 for original balloon
	b.blow(5); // 5 for original balloon
	assertEquals(5, b.getRadius()); // 5 for original balloon
	}
	
	@Test
	public void pop()
	{
	Balloon b = new Balloon(10); // 10 for original balloon
	assertEquals(false, b.isPopped()); // false for original balloon
	}
	
}
	
	
