package Chap1_ArraysAndStrings;

import java.util.List;
import java.util.ArrayList;
/**
 * Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
column are set to O.
Hints: # 17, #74, #102
 */
public class ZeroMatrix
{
    private static int[][] processZeroMatrix(int matrix[][])
    {
        int r=matrix.length;
        int c=matrix[0].length;

        List<Integer> rowZero = new ArrayList<>();
        List<Integer> columnZero = new ArrayList<>();

        for (int i=0; i<r; i++)
        {
            for (int j=0; j<c; j++)
            {
                if(matrix[i][j]==0)
                {
                    rowZero.add(i);
                    columnZero.add(j);
                }
            }
        }
        
        for(int row: rowZero)
        {
            for (int j=0; j<c; j++)
                matrix[row][j]=0;
        }
        
        for(int column: columnZero)
        {
            for (int i=0; i<r; i++)
                matrix[i][column]=0;
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
     * e.g. {{1,0},{3,4}}
     * e.g. {{1,2,0},{4,0,6},{7,8,9}}
     * e.g. {{1,0,3,4},{5,6,7,8},{9,0,11,12},{13,0,15,0}}
     */
    
    public static void main(int [][]matrix)
    {
        ZeroMatrix.printMatrix(matrix);
        int processedZeroMatrix[][]= ZeroMatrix.processZeroMatrix(matrix);
        ZeroMatrix.printMatrix(processedZeroMatrix);
    }
}
