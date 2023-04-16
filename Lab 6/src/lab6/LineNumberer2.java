package lab6;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class LineNumberer2
{
  public static void main(String[] args) throws FileNotFoundException
  {
    File file = new File("/Users/Nick/eclipse-workspace/lab5/src/lab5/SimpleLoops.java");
    Scanner scanner = new Scanner(file);
    int lineCount = 1;

    while (scanner.hasNextLine())
    {
    	String line = scanner.nextLine();
    	char[] ch=line.toCharArray();
    	System.out.print(lineCount + " ");
    	int counter=0;
    
    	for(int i = 0; i < ch.length; i++)
    	{
    		if(ch[i]==' '||ch[i]==',')
    		{
    			// counter
    			counter++;	
    		}
    	}
    	// "There are x words in this line"
    	System.out.println(line+" "+ " -- There are " + (counter+1) + " words in this line.");
    	lineCount += 1;
    }
    scanner.close();
    System.out.println();
    System.out.println("***** ATTENTION TA *****");
    System.out.println();
    System.out.println("Before the scanner closed, there are " + (lineCount - 1) + " lines of code in this file.");
    System.out.println("Done");
    }  
}