package interview.Ali_rongqi;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int res = getLargestSubtraction(new int[]{1,17,18,3,2,19,4});
        System.out.println(res);
    }

    public static int getLargestSubtraction(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        dp[n-1] = Integer.MAX_VALUE;
        for(int i = n-2;i>=0;i--){
            dp[i] = Math.min(dp[i+1],nums[i+1]);
        }
        //System.out.println(Arrays.toString(dp));
        int maxVal = Integer.MIN_VALUE;
        for(int i = 0;i<n;i++){
            maxVal = Math.max(maxVal,nums[i]-dp[i]);
        }
        return maxVal;
    }
}
