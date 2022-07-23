package Pro556;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int nextGreaterElement(int n) {
        char[] nums = Integer.toString(n).toCharArray();
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }

        int j = nums.length - 1;
        while (j >= 0 && nums[i] >= nums[j]) {
            j--;
        }
        swap(nums, i, j);
        reverse(nums, i + 1);
        long ans = Long.parseLong(new String(nums));
        return ans > Integer.MAX_VALUE ? -1 : (int) ans;
    }

    private void swap(char[] nums,int i,int j){
        char tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(char[] nums,int begin){
        int i = begin, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}
