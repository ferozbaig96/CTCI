package Chap1_ArraysAndStrings;


/**
 * 1.9 String Rotation: Assume you have a method isSubst ring which checks if one word is a substring
of another. Given two strings, 51 and 52, write code to check if 52 is a rotation of 51 using only one
call to isSubstring (e.g., "waterbottle" is a rotation of"erbottlewat").
Hints: #34, #88, #104
 */
public class StringRotation
{
    private static boolean isSubstring(String s1, String s2)
    {
        return s1.contains(s2);
    }
    
    private static boolean isRotation(String s1, String s2)
    {
        String s1s1 = s1+s1;
        return isSubstring(s1s1,s2);
    }
    
    public static void main(String s1, String s2)
    {
        boolean isRotation = StringRotation.isRotation(s1,s2);
        System.out.println(isRotation);
    }
}
