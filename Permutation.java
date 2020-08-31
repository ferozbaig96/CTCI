/*
 * "string" -> charToInsert at every index, remainingString
 * 
 * "abc" -> "a", "bc"
 *                  -> "b", "c"
 *                            -> ["c"]
 *                  -> ["bc", "cb"]
 *        -> ["abc", "bac", "bca", "acb", "cab", "cba"]
 */

import java.io.*;
import java.util.*;

public class Permutation{

    public static void findPermutations(String s)
    {   
        if (s.length() == 0)
        {
            System.out.println("String length is zero");
            return;
        }

        String allPermStrings[] = perm(s);
        for(String x:allPermStrings)
        {
            System.out.println(x);
        }
        
        System.out.println(allPermStrings.length);
    }

    public static String[] perm(String s)
    {
        if (s.length() == 1)
        {    
            return new String[]{s};
        }

        char charToInsert = s.charAt(0);
        String tempResult[] = perm(s.substring(1));

        String result[] = new String [tempResult.length * (tempResult[0].length() + 1)];

        int counter = 0;
        for (String permStr: tempResult)
        {
            for (int i=0; i< permStr.length() + 1; i++)
            {
                StringBuilder sb = new StringBuilder(permStr);
                result[counter++] = sb.insert(i,charToInsert).toString();
            }
        }

        return result;
    }

    public static void main(String args[])
    {
        String s="ABCDEF";

        findPermutations(s); 
    }

}