package Chap1_ArraysAndStrings;

/**
 * 1.1 Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
cannot use additional data structures?
Hints: #44, # 777, # 7 32
1.2
 */
public class IsUnique
{
    public static boolean hasUniqueCharacters(String s)
    {
        // Assuming character set is ASCII (256 for extended ASCII) and not Unicode

        if (s.length() > 256)
            return false;

        boolean char_exists[] = new boolean[256];
        
        for (int i=0;i<s.length();i++)
        {
            int charIndex = s.charAt(i);
            
            if (char_exists[charIndex])
                return false;
             
            char_exists[charIndex] = true;
        }

        return true;
    }
}
