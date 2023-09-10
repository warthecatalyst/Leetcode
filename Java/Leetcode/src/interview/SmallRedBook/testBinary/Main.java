package interview.SmallRedBook.testBinary;

import java.util.*;

public class Main {
    static Random random = new Random();
    public static void main(String[] args) {
        int[] nums = new int[10];
        for(int i = 0;i<10;i++) {
            nums[i] = random.nextInt(1000);
        }
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        for(int i = 0; i < 20;i++) {
            int target = random.nextInt(1000);
            int idx = binarySearch(nums, target);
            System.out.println("target = " + target + " ,idx = " + idx);
        }
    }

    static int binarySearch(int[] nums,int target) {
        //在nums中找到第一个比target大的数
        int n = nums.length;
        if(nums[n-1] < target) {
            return n;
        }
        int left = 0, right = n-1;
        while(left < right) {
            int mid = (left + right) >> 1;
            if(nums[mid] <= target) {
                left = mid+1;
            }else {
                right = mid;
            }
        }
        return left;
    }
}
