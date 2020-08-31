package Chap1_ArraysAndStrings;

import java.util.*;

/**
 * 1.2 Check Permutation: Given two strings, write a method to decide if one is a permutation of the 
 * other.
Hints: #7, #84, #722, #737
 */
public class CheckPermutation
{
    public static boolean checkPerm(String x, String y)
    {
        if (x.length() != y.length())
            return false;

        if (x.length() == 0 && y.length() == 0)
            return true;

        // Can be easily done using arrays as well if a size of character set is assumed
        HashMap<Character,Integer> charFrequencyMap = new HashMap<>();

        for (int i=0;i<x.length();i++)
        {
            Character key = x.charAt(i);
            int count = charFrequencyMap.containsKey(key) ? charFrequencyMap.get(key) : 0;
            charFrequencyMap.put(key,count+1);
        }

        for (int j=0;j<y.length();j++)
        {
            Character key = y.charAt(j);
            if (charFrequencyMap.containsKey(key) == false)
                return false;

            int count = charFrequencyMap.get(key);
            if(count == 0)
                return false;

            charFrequencyMap.put(key,count-1);
        }
        return true;
    }
}
