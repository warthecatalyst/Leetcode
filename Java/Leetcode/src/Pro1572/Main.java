package Pro1572;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int sum = 0;
        for(int i = 0;i < n;i++) {
            sum += mat[i][i];
            int j = n-i;
            if (i != j) {
                sum += mat[i][j];
            }
        }
        return sum;
    }
}
