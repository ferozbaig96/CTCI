package Chap1_ArraysAndStrings;

/**
 * 1.3 URLify: Write a method to replace all spaces in a string with '%20: You may assume that the string
has sufficient space at the end to hold the additional characters, and that you are given the "true"
length of the string. (Note: If implementing in Java, please use a character array so that you can
perform this operation in place.)
EXAMPLE
Input: "Mr John Smith "J 13
Output: "Mr%20J ohn%20Smith"
Hints: #53, #7 78
 */
public class URLify
{
    private static String replaceSpaces(char[] arr, int length)
    {
        // I am assuming length < arr.length

        int spacesCount = 0;
        for (int i=0;i<length;i++)
        {
            char ch = arr[i];
            if (ch == ' ')
                spacesCount++;
        }

        // for each whitespace ' ', a '%20' is added. So two more characters added for a ' '
        
        int totalLength = length + (2 * spacesCount);
        int index = totalLength-1;
        
        for (int j = length-1; j >=0; j--)
        {
            char ch = arr[j];
            
            if (ch == ' ')
            {
                arr[index]   = '0';
                arr[index-1] = '2';
                arr[index-2] = '%';
                
                index = index - 3;
            }
            else
            {
                arr[index--] = ch;
            }
        }
        
        return new String(arr).substring(0,totalLength);
    }
    
    public static String main(String s, int len)
    {
        return URLify.replaceSpaces(s.toCharArray(),len);
    }
}
