package Chap1_ArraysAndStrings;

/**
 * 1.4 Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome.
A palindrome is a word or phrase that is the same forwards and backwards. A permutation
is a rea rrangement of letters. The palindrome does not need to be limited to just dictionary words.
EXAMPLE
Input: Tact Coa
Output: True (permutations: "taco cat". "atco cta". etc.)
Hints: #106, #121, #134, #136
 */
public class PalindromePermutation
{
    static boolean isPalindromePermutation(String s)
    {   
        // Assuming character set: a-z
        int count[]=new int['z'-'a'+1];

        // converting string to lower case
        s=s.toLowerCase();

        for (char ch: s.toCharArray())
        {
            if(ch >= 'a' && ch <= 'z')
                count[ch-'a']++;
        }

        return checkMaxOneOdd(s,count);
    }

    private static boolean checkMaxOneOdd(String s,int[] count)
    {
        int oddFrequencies = 0;

        for (char ch: s.toCharArray())
        {
            if (ch >= 'a' && ch <= 'z')
            {
                if (count[ch-'a'] % 2 != 0)
                {   
                    oddFrequencies++;

                    if (oddFrequencies > 1)
                        return false;
                }
            }
        }

        return true;
    }
}