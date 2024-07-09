package Pro3101;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public long countAlternatingSubarrays(int[] nums) {
         long ans = 0, cur = 0;
         int pre = -1;
         for (int num : nums) {
             cur = (pre != num) ? cur + 1 : 1;
             pre = num;
             ans += cur;
         }
         return ans;
    }
}
