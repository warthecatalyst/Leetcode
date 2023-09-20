package Pro2560;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int minCapability(int[] nums, int k) {
        int lower = Arrays.stream(nums).min().getAsInt();
        int higher = Arrays.stream(nums).max().getAsInt();
        while (lower <= higher) {
            int middle = (lower + higher) / 2;
            int count = 0;
            boolean visited = false;
            for (int x : nums) {
                if (x <= middle && !visited) {
                    count++;
                    visited = true;
                } else {
                    visited = false;
                }
            }
            if (count >= k) {
                higher = middle - 1;
            } else {
                lower = middle + 1;
            }
        }
        return lower;
    }
}
