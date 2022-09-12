package Pro1608;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int l = nums.length;
        for(int i = 1;i<=l;i++){
            int min = nums[l-i];
            if(min<i){
                continue;
            }
            //min >= i
            if(i==l||nums[l-i-1]<i){
                return i;
            }
        }
        return -1;
    }
}
