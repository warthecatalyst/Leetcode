package Pro1155;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.numRollsToTarget(1, 6, 3);
        System.out.println(ans);
    }
}

class Solution {
    private static final int MOD = (int) (1e9+7);
    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n+1][target+1];
        dp[0][0] = 1;
        for(int i = 1;i <= n;i++) {
            for(int t = target; t >= i; t--) {
                for (int roll = 1; roll <= Math.min(t, k); roll++) {
                    if (dp[i-1][t-roll] > 0) {
                        dp[i][t] = (dp[i][t] + dp[i-1][t-roll]) % MOD;
                    }
                }
            }
        }
//        for (int[] nums : dp) {
//            System.out.println(Arrays.toString(nums));
//        }
        return dp[n][target];
    }
}
