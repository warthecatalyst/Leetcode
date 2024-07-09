package Pro2876;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public long maxScore(int[] nums, int x) {
        var dp = new long[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        long res = nums[0];
        dp[nums[0] % 2] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int oddOrEven = nums[i] & 1;
            long cur = Math.max(dp[oddOrEven] + nums[i], dp[1-oddOrEven] - x +nums[i]);
            res = Math.max(res, cur);
            dp[oddOrEven] = Math.max(dp[oddOrEven], cur);
        }
        return res;
    }
}
