package Pro1260;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.shiftGrid(new int[][]{{1},{2},{3},{4},{7},{6},{5}},23);
    }
}

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        List<List<Integer>> ans = new ArrayList<>();
        k = k%(m*n);
        int curIndex = (m*n-k)%(m*n);
        while(ans.size()<m){
            List<Integer> curLine = new ArrayList<>();
            for(int i = 0;i<n;i++){
                curLine.add(grid[curIndex/n][curIndex%n]);
                curIndex = (curIndex+1)%(m*n);
            }
            ans.add(curLine);
        }
        return ans;
    }
}
