package interview.Tplink;

public class Main {
    public static void main(String[] args) {
        int[][] nums = new int[4][];
        nums[0] = new int[]{2,2,3};
        nums[1] = new int[]{2,2,3,1};
        nums[2] = new int[]{2,2,3,1,4};
        nums[3] = new int[]{2,3,4,5,1};
        int ans = getThirdMaxNumber(new int[]{2,2,3});
        System.out.println(ans);
    }

    static int getThirdMaxNumber(int[] nums){
        int max_1 = Integer.MIN_VALUE, max_2 = Integer.MIN_VALUE,
                max_3 = Integer.MIN_VALUE;
        for(int num:nums){
            if(num>max_1){
                max_3 = max_2;
                max_2 = max_1;
                max_1 = num;
            }else if(num>max_2&&num<max_1){
                max_3 = max_2;
                max_2 = num;
            }else if(num>max_3&&num<max_2){
                max_3 = num;
            }
        }
        if(max_3 == Integer.MIN_VALUE){
            return max_1;
        }else{
            return max_3;
        }
    }
}
