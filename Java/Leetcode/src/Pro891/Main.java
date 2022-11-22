package Pro891;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    private static final int MOD = (int)1e9+7;
    public int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        long res = 0;
        long x = nums[0], y = 2;
        for (int j = 1; j < nums.length; j++) {
            res = (res + nums[j] * (y - 1) - x) % MOD;
            x = (x * 2 + nums[j]) % MOD;
            y = y * 2 % MOD;
        }
        return (int) ((res + MOD) % MOD);
    }
}