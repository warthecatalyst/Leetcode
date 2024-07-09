package Pro2748;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int countBeautifulPairs(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                // nums[i]的第一个数字和nums[j]的最后一个数字互质
                int first = String.valueOf(nums[i]).charAt(0) - '0', second = nums[j] % 10;
                if (gcd(first, second) == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}