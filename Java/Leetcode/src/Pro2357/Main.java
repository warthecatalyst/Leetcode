package Pro2357;

import java.util.*;
public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int minimumOperations(int[] nums) {
        int res = 0;
        while(!isOver(nums)){
            int minVal = Integer.MAX_VALUE;
            for(int num:nums){
                if(num>0){
                    minVal = Math.min(minVal,num);
                }
            }
            for(int i = 0;i<nums.length;i++){
                if(nums[i]>0){
                    nums[i]-=minVal;
                }
            }
            res++;
        }
        return res;
    }

    public boolean isOver(int[] nums){
        for(int num:nums){
            if(num>0){
                return false;
            }
        }
        return true;
    }
}
