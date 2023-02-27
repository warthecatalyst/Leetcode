package Pro1326;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int minTaps(int n, int[] ranges) {
        int[][] intervals = new int[n+1][];
        for(int i = 0;i<=n;i++){
            int start = Math.max(0,i-ranges[i]);
            int end = Math.min(n,i+ranges[i]);
            intervals[i] = new int[]{start,end};
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for(int[] interval:intervals){
            int start = interval[0], end = interval[1];
            if(dp[start] == Integer.MAX_VALUE){
                return -1;
            }
            for(int j = start;j<=end;j++){
                dp[j] = Math.min(dp[j],dp[start]+1);
            }
        }
        return dp[n];
    }
}
