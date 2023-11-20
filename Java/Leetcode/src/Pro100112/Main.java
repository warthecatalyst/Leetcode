package Pro100112;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        long ans = solution.maxBalancedSubsequenceSum(new int[]{-6, 8});
    }
}

class Solution {
    public long maxBalancedSubsequenceSum(int[] nums) {
        long ans = Arrays.stream(nums).max().getAsInt();
        List<long[]> stack = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            long cur = nums[i] - i;
            long temp = nums[i];
            while(!stack.isEmpty()) {
                long[] top = stack.get(stack.size() - 1);
                if (top[0] <= cur) {
                    temp = Math.max(temp, top[1] + nums[i]);
                    stack.remove(stack.size() - 1);
                } else {
                    break;
                }
            }
            ans = Math.max(temp, ans);
            stack.add(new long[]{cur, temp});
        }
        return ans;
    }
}
