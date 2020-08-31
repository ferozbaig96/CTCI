package Chap1_ArraysAndStrings;

/**
 * Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
 * 
 * e.g. {{1,2},{3,4}}
 * e.g. {{1,2,3},{4,5,6},{7,8,9}}
 * e.g. {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}}
 */

public class RotateMatrix
{
    private static int[][] rotateMatrix(int matrix[][])
    {
        int r=matrix.length;
        int c=matrix[0].length;

        // In-place Transpose
        for (int i=0; i<r-1; i++)
        {
            for (int j=i+1; j<c; j++)
            {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Reflection along y 
        for (int i=0; i<r; i++)
        {
            for (int j=0; j<c/2; j++)
            {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][c-1-j];
                matrix[i][c-1-j] = temp;
            }
        }

        return matrix;
    }

    private static void printMatrix(int matrix[][])
    {
        int r=matrix.length;
        int c=matrix[0].length;

        for (int i=0; i<r; i++)
        {
            for (int j=0; j<c; j++)
            {
                System.out.print(matrix[i][j]+ "\t");
            }
            System.out.println();
        }

        System.out.println();
    }

    /*
     * e.g. {{1,2},{3,4}}
     * e.g. {{1,2,3},{4,5,6},{7,8,9}}
     * e.g. {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}}
     */
    public static void main(int matrix[][])
        {
        int r=matrix.length;
        int c=matrix[0].length;

        if (r!=c || r==0)
            System.out.println("Cannot be rotated");
        else
        {

            RotateMatrix.printMatrix(matrix);
            int rotatedMatrix[][]= RotateMatrix.rotateMatrix(matrix);
            RotateMatrix.printMatrix(rotatedMatrix);
        }
    }
}
