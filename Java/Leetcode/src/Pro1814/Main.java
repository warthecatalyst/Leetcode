package Pro1814;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    private static final int MOD = (int) (1e9+7);
    public int countNicePairs(int[] nums) {
        int res = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int num:nums){
            int temp = num, j = 0;
            while(temp > 0){
                j = j*10+temp%10;
                temp /= 10;
            }
            res = (res+map.getOrDefault(num-j,0))%MOD;
            map.put(num-j,map.getOrDefault(num-j,0)+1);
        }
        return res;
    }
}
