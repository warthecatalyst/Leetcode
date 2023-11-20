package Pro2656;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int maximizeSum(int[] nums, int k) {
        int maxNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxNum = Math.max(maxNum, nums[i]);
        }
        return (maxNum + maxNum + k - 1) * k / 2;
    }
}
