package Pro3033;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int[][] modifiedMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] res = new int[m][n];
        int[] rowMax = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowMax[j] = Math.max(rowMax[j], matrix[i][j]);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = matrix[i][j] == -1 ? rowMax[j] : matrix[i][j];
            }
        }
        return res;
    }
}
