package Pro6291;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int differenceOfSum(int[] nums) {
        int sum1 = Arrays.stream(nums).sum();
        int sum2 = 0;
        for(int num:nums){
            sum2 += getSumFromNum(num);
        }
        return Math.abs(sum1-sum2);
    }

    int getSumFromNum(int num){
        int res = 0;
        while(num>0){
            res += num%10;
            num/=10;
        }
        return res;
    }
}
