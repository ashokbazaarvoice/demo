package com.abc.experiment;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/1/15
 * Time: 10:17 AM
 * To change this template use File | Settings | File Templates.
 *
 * There are two ways to rotate a matrix 1) rotate all four corners first then rotate 10, 41, 34 , 03 and 10 so on.
 * 2) Transpose matrix then swap rows from top to bottom then rotation is 90 degree clockwise. if swap is left to right then rotation is anticlockwise.
 *
 *
 * [04, 14, 24, 34, 44]
 [03, 13, 23, 33, 43]
 [02, 12, 22, 32, 42]
 [01, 11, 32, 31, 41]
 [00, 10, 20, 30, 40]
 */
public class RotateMatrix {
    public static void main(String[] args) {

        int[][] matrix = {
                {00, 01, 02, 03, 04},
                {10, 11, 12, 13, 14},
                {20, 21, 22, 23, 24},
                {30, 31, 32, 33, 34},
                {40, 41, 42, 43, 44}
        };
        //nij => n23 = 23
        System.out.println(matrix.length);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
//        int[][] matrix1 = rotateMatrixV2(matrix);
//        System.out.println(matrix1.length);
//        for (int[] row : matrix1) {
//            System.out.println(Arrays.toString(row));
//        }
//        System.out.println();
//        int[][] matrix2 = rotate(matrix);
//        System.out.println(matrix2.length);
//        for (int[] row : matrix2) {
//            System.out.println(Arrays.toString(row));
//        }
//        MatrixTranspose(matrix);
//        for (int[] row : matrix) {
//            System.out.println(Arrays.toString(row));
//        }

        MatrixTranspose(matrix);
        System.out.println(matrix.length);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        MatrixTranspose(matrix);
        System.out.println(matrix.length);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static int[][] tranposeMatrixV2(int[][] matrix) {
        for (int j = 0; j < matrix.length; j++) {
            for (int i = j; i < matrix.length; i++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        return matrix;
    }

    public static int[][] rotateMatrixV2(int[][] matrix) {
        int n = matrix.length;
        for (int j = 0; j < matrix.length; j++) {
            for (int i = j; i < matrix.length; i++) {
                int temp = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[i][j];
                matrix[n-1-i][n-1-j] = temp;
                //matrix[i][j]
            }
        }
        return matrix;
    }

    public static int [][] rotate(int [][] input){

        int n = input.length;
        int m = input[0].length;
        int [][] output = new int [m][n];

        for (int i=0; i<n; i++)
            for (int j=0;j<m; j++)
                output [j][n-1-i] = input[i][j];
        return output;
    }

    public static int[][] rotateMatrix(int[][] matrix) {
        int[][] newMatrix = new int[matrix.length][matrix.length];
        int j = 0;
        for (int[] row : matrix) {
            int i = 0;
            for (int element : row) {
                newMatrix[i][j] = element;
                i++;
            }
            j++;
        }
        return newMatrix;
    }

    private static void MatrixTranspose(int[][] matrix)
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = i; j < matrix.length; j++) // Note j=i
            {
                int temp = matrix[i][ j];
                matrix[i][ j] = matrix[j][i];
                matrix[j][ i] = temp;
            }
        }
    }

    public static void rotate(int[][] matrix, int n) {
        for (int layer = 0; layer < n / 2; ++layer) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; ++i) {
                int offset = i - first;
                int top = matrix[first][i]; // save top
// left -> top
                matrix[first][i] = matrix[last - offset][first];
// bottom -> left
                matrix[last - offset][first] = matrix[last][last - offset];
// right -> bottom
                matrix[last][last - offset] = matrix[i][last];
// top -> right
                matrix[i][last] = top; // right <- saved top
            }
        }
    }
}
