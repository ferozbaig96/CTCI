package Chap1_ArraysAndStrings;

/**
 * String Compression: Implement a method to perform basic string compression using the counts
of repeated characters. For example, the string aabcccccaaa would become a2b1c5a3. If the
"compressed" string would not become smaller than the original string, your method should return
the original string. You can assume the string has only uppercase and lowercase letters (a - z).
Hints: #92, # 11 0
 */
public class StringCompression
{
    private static String compressedString(String s)
    {
        StringBuilder sb = new StringBuilder();

        int count = 1;
        char ch = s.charAt(0);
        for(int i=1; i<s.length(); i++)
        {
            char newCh = s.charAt(i);

            if (ch == newCh)
            {
                count++; continue;
            }
            else
            {
                sb.append(ch);
                sb.append(count);

                ch = newCh;
                count=1;
                continue;
            }
        }

        sb.append(ch);
        sb.append(count); 

        return sb.length() <  s.length() ? sb.toString(): s;
    }

    public static void main(String args[])
    {
        System.out.println(StringCompression.compressedString(args[0]));
    }
}
