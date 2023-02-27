package Pro1144;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.movesToMakeZigzag(new int[]{9,6,1,6,2});
    }
}

class Solution {
    public int movesToMakeZigzag(int[] nums) {
        return Math.min(help(nums, 0), help(nums, 1));
    }

    public int help(int[] nums, int startPos) {
        int res = 0;
        for (int i = startPos; i < nums.length; i += 2) {
            int a = 0;
            if (i - 1 >= 0) {
                a = Math.max(a, nums[i] - nums[i - 1] + 1);
            }
            if (i + 1 < nums.length) {
                a = Math.max(a, nums[i] - nums[i + 1] + 1);
            }
            res += a;
        }
        return res;
    }
}
