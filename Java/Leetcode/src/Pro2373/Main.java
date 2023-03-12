package Pro2373;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] res = new int[n-2][n-2];
        for(int i = 1;i<=n-2;i++){
            for(int j = 1;j<=n-2;j++){
                int maxVal = Integer.MIN_VALUE;
                for(int k = i-1;k<=i+1;k++){
                    for(int l = j-1;l<=j+1;l++){
                        maxVal = Math.max(maxVal,grid[k][l]);
                    }
                }
                res[i-1][j-1] = maxVal;
            }
        }
        return res;
    }
}
