import java.util.*;

/**
 * Convert number in String type with base to decimal format
 *
 */
public class ToDecimalBase
{
    private static int convertToDecimalBase(String numStr, int base) throws Exception
    {
        if (base < 2 || (base > 10 && base != 16))
            return -1;

        int result = 0;
        boolean isNumberNegative = false;
        if(numStr.charAt(0) == '-')
        {
            isNumberNegative = true;
            numStr=numStr.substring(1);
        }

        for(int i=numStr.length()-1; i>=0; i--)
        {
            int digitValue = digitToValue(numStr.charAt(i));

            // digit value cannot be greater than the base.
            // e.g. base 2 means 0,1
            if(digitValue >= base)
                return -1;

            int exponent = numStr.length()-1-i;
            result += digitValue * Math.pow(base, exponent);
        }

        if (isNumberNegative)
            result = result * -1;

        return result;
    }

    private static int digitToValue(char ch) throws Exception
    {
        int val;

        if (ch >= '0' && ch <= '9')
            return ch - '0';
        else if(ch >= 'A' && ch <='Z')
            return ch-'A' + 10;
        else if(ch >= 'a' && ch <='z')
            return ch-'a' + 10;
        else
            throw new Exception("Invalid Input");

    }

    public static void main(String numStr, int base) throws Exception
    {
        int base10value = convertToDecimalBase(numStr.trim(), base);
        
        if (base10value != -1)
            System.out.println(numStr+" in base "+base+
                " = "+base10value+
                " in base 10(Decimal)");
        else

            System.out.println("Invalid Input");
    }
}
