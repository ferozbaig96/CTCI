package Chap1_ArraysAndStrings;

/**
 * One Away: There are three types of edits that can be performed on strings: insert a character,
remove a character, or replace a character. Given two strings, write a function to check if they are
one edit (or zero edits) away.
EXAMPLE
pale, pIe -> true
pales. pale -> true
pale. bale -> true
pale. bake -> false
Hints: #23, #97, #130

ab, baa -> false
baa, ab -> false
 */
public class OneAway
{
    private static boolean isOneReplaceAway(String str1, String str2)
    {

        char str1arr[] = str1.toCharArray();
        char str2arr[] = str2.toCharArray();
        boolean foundOneEdit = false;

        for (int i=0; i< str1.length(); i++)
        {
            if (str1arr[i] == str2arr[i])
                continue;
            else
            {
                if (foundOneEdit)
                    return false;

                foundOneEdit = true;
            }
        }

        return true;
    }

    // str1 is shorter than str2. So insertion is to be done on str1 (or removal on str2)
    private static boolean isOneInsertAway(String str1, String str2)
    {
        int index1=0, index2=0;
        char str1arr[] = str1.toCharArray();
        char str2arr[] = str2.toCharArray();

        while (index1 < str1.length() && index2 < str2.length())
        {
            if (str1arr[index1] == str2arr[index2])
            {
                index1++; index2++; continue;
            }
            else{
                if (index1 != index2)
                    return false;
                else
                {
                    index2++; continue;
                }
            }
        }

        return true;
    }

    private static boolean isOneEditAway(String str1, String str2)
    {
        int str1len = str1.length();
        int str2len = str2.length();

        if (str1len == str2len)
            return isOneReplaceAway(str1,str2);
        // str1 is shorter
        else if (str1len + 1 == str2len)
            return isOneInsertAway(str1,str2);
        // str2 is shorter
        else if (str1len == str2len + 1)
            return isOneInsertAway(str2,str1);
        else
            return false;
    }

    /* public static boolean main(String str1, String str2)
    {
    return OneAway.isOneEditAway(str1,str2);
    }*/

    // via CLI - java OneAway.java bale pale
    public static void main(String args[])
    {
        System.out.println(OneAway.isOneEditAway(args[0],args[1]));
    }
}
