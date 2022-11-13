package Weekly_Contest.Week319.Pro2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int subarrayLCM(int[] nums, int k) {
        int n = nums.length;
        int beginIndex = 0;
        int cnt = 0;
        for(;beginIndex<nums.length;beginIndex++){
            for(int endIndex = beginIndex;endIndex<nums.length;endIndex++){
                if(calSCD(nums,beginIndex,endIndex)==k){
                    cnt++;
                }else if(k%calSCD(nums,beginIndex,endIndex)!=0){
                    break;
                }
            }
        }
        return cnt;
    }

    public int calSCD(int[] nums,int i,int j){
        int maxIdx = i;
        for(int idx = i+1;idx<=j;idx++){
            if(nums[idx]>nums[maxIdx]){
                maxIdx = idx;
            }
        }
        int max = nums[maxIdx];
        for(;;max++){
            boolean b = true;
            for(int idx = i;idx<=j;idx++){
                if(max%nums[idx]!=0){
                    b = false;
                    break;
                }
            }
            if(b){
                return max;
            }
        }
    }
}
