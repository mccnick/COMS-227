package lab2;

public class StringTest {

	public static void main(String[] args) {
		String message;
		message = "Hello, World!";
		System.out.println(message);
		
		int theLength = message.length();
		System.out.println(theLength);
		
		char theChar = message.charAt(0);
		System.out.println(theChar);

		theChar = message.charAt(12);
		System.out.println(theChar);
		
		System.out.println(message.toUpperCase());
			// need to use double parenthesis to make this work
		
		System.out.println("Hello" + message.substring(theLength));
		//Random rand = new Random();

		
	}
}
