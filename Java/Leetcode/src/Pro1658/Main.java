package Pro1658;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{5,2,3,1,1};
        int ans = solution.minOperations(nums,5);
        System.out.println(ans);
    }
}

class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if(sum<x){
            return -1;
        }
        int right = 0;
        int lsum = 0, rsum = sum;
        int ans = n+1;
        for(int left = -1;left<n;left++){
            if(left != -1){
                lsum += nums[left];
            }
            while(right<n&&lsum+rsum>x){
                rsum -= nums[right];
                right++;
            }
            if(lsum+rsum==x){
                ans = Math.min(ans,(left+1)+(n-right));
            }
        }
        return ans > n? -1:ans;
    }
}
