package Pro421;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    static final int HIGH_BIT = 30;
    public int findMaximumXOR(int[] nums) {
        int x = 0;
        for (int k = HIGH_BIT; k >= 0; k--) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add((num >> k) & 0x1);
            }

            int xNext = x * 2 + 1;
            boolean found = false;
            // 枚举 i
            for (int num : nums) {
                if (set.contains(xNext ^ (num >> k))) {
                    found = true;
                    break;
                }
            }

            if(found) {
                x = xNext;
            } else {
                x = xNext - 1;
            }
        }
        return x;
    }
}
