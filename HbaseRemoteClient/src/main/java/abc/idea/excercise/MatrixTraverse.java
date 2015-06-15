package abc.idea.excercise;

/**
 * Created by ashok.agarwal on 5/29/15.
 * Imagine a robot sitting on the upper left hand corner of an NxN grid
 * The robot can only move in two directions: right and down
 * How many possible paths are there for the robot?
 *
 *
 */
public class MatrixTraverse {
    static int count = 0;
    public static void main(String[] args) {

        int mat[][] = { { 1, 2, 3 }, { 4, 5, 6 } };
        int m = mat.length;
        int n = mat[0].length;
        paths(0, 0, m-1, n-1, mat, new int[m * n], 0);
    }

    static void paths(int i, int j, int m, int n, int mat[][], int path[], int index) {
        if (i <= m && j <= n) {
            path[index] = mat[i][j];
            if (i == m && j == n) {
                for (int ii = 0; ii <= index; ii++) {
                    System.out.print(path[ii] + " ");
                }
                System.out.println();
            }
            // move right
            paths(i, j + 1, m, n, mat, path, index + 1);

            // move bottom
            paths(i + 1, j, m, n, mat, path, index + 1);
        }
    }
}