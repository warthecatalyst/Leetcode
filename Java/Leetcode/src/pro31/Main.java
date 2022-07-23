package pro31;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length-2;
        while (i>=0&&nums[i]>=nums[i+1]){
            i--;
        }

        if(i>=0){
            int j = nums.length-1;
            while(j>=0&&nums[i]>=nums[j]){
                j--;
            }
            swap(nums,i,j);
        }
        reverse(nums,i+1);
    }

    private void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums,int begin){
        int i = begin,j = nums.length-1;
        while(i<j){
            swap(nums,i,j);
            i++;
            j--;
        }
    }
}
