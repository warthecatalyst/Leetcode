package Pro2293;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int minMaxGame(int[] nums) {
        int n = nums.length;
        while(n>1){
            int[] newNums = new int[n/2];
            for(int i = 0;i<n/2;i++){
                if(i%2==0){
                    newNums[i] = Math.min(nums[i*2],nums[i*2+1]);
                }else{
                    newNums[i] = Math.max(nums[i*2],nums[i*2+1]);
                }
            }
            nums = newNums;
            n/=2;
        }
        return nums[0];
    }
}
