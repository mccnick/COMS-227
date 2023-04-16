package lab5;

public class initials
{

    /**
     * 
     * CHECKPOINT 1
     * Write a static method that takes a string argument containing a person's full name as a parameter, and returns a string containing just their initials. 
     * The name may have many parts or just one, e.g., for the name "Cher" the method returns "C", 
     * for the name "Edna del Humboldt von der Schooch" the method returns "EdHvdS". Test your code.
     * 
     * Write a static method to find the index of the first vowel in a string (returning -1 if there are no vowels). 
     * Note an easy way to write the boolean condition "character ch is a vowel" is "aeiouAEIOU".indexOf(ch) >= 0
     */
    public static String nameInitials(final String name) {

        
        if (name == null || name.isEmpty()) 
        {
            return "";
        }
       
        String[] inititalsInName = name.split(" ");
        String initials = "";

        for (String wordInName : inititalsInName) 
        {
            String initialChar = wordInName.trim().charAt(0) + "";
            initials += initialChar;
        }
        return initials;
    }
    
    // end //

    public static int findVowels(final String searchForString) 
    {
        
        if (searchForString == null || searchForString.isEmpty()) 
        {
            return -1;
        }
        int length = searchForString.length();
        int index = -1;

        
        for (int i = 0; i < length; i++) 
        {
            char ch = searchForString.charAt(i);
            if ("aeiouAEIOU".indexOf(ch) >= 0) 
            {
                index = i;
                break;
            }
        }
        return index;
    }
    
    // end //

    /**
     * testing initials class
     */
    public static void main(String[] args) {

    	System.out.println();
    	System.out.println("********** CHECKPOINT 1 *********");
    	System.out.println();
    	
    	 String nameThree = "Nicholas McCullough";
         String initialsFromNameThree = nameInitials(nameThree);
         System.out.println("Name : " + nameThree);
         System.out.println("Initials: " + initialsFromNameThree);
         System.out.println();
    	
        String nameOne = "Cher";
        String initialsFromNameOne = nameInitials(nameOne);
        System.out.println("Name : " + nameOne);
        System.out.println("Initials: " + initialsFromNameOne);
        System.out.println();

        String nameTwo = "Edna del Humboldt von der Schooch";
        String initialsFromNameTwo = nameInitials(nameTwo);
        System.out.println("Name : " + nameTwo);
        System.out.println("Initials: " + initialsFromNameTwo);
        System.out.println();
        
        String firstVowels = "NIck";
        int index1 = findVowels(firstVowels);

        System.out.println("First Vowels Example: " + firstVowels);
        System.out.println("Index = " + index1);
        System.out.println();

        String secondVowels = "aeios";
        int index2 = findVowels(secondVowels);
        System.out.println("Second Vowels Example: " + secondVowels);
        System.out.println("Index = " + index2);
        
    }
}