/*
 * Program to convert Decimal base number to any other base (2 to 10, and 16)
 */
public class ToAnyBase
{
    private static String convertToBase(int num, int base)
    {
        if (base < 0 || (base > 10 && base!=16))
            return "-1";

        String result="";
        boolean isNumberNegative = (num < 0);
        num = Math.abs(num);

        while(num!=0)
        {
            int remainder = num % base;
            if (remainder > 9)
            {
                char hexValue = (char)(remainder + 'A' - 10);
                result = hexValue + result;
            }
            else
                result = remainder + result;

            num = num / base;
        }

        if (isNumberNegative)
            result = "-"+result;

        return result;
    }

    public static void main(int decimalNumber, int base)
    {
        String convertedBaseValue = convertToBase(decimalNumber,base);

        if (convertedBaseValue != "-1")
            System.out.println(decimalNumber+" in base "+
                base+" is: "+convertedBaseValue);
        else
            System.out.println("Invalid Base");
    }
}