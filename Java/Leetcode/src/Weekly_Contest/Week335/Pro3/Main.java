package Weekly_Contest.Week335.Pro3;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.findValidSplit(new int[]{4,7,8,15,3,5});
        System.out.println(res);
    }
}

//给你一个长度为 n 的整数数组 nums ，下标从 0 开始。
//
//如果在下标 i 处 分割 数组，其中 0 <= i <= n - 2 ，使前 i + 1 个元素的乘积和剩余元素的乘积互质，则认为该分割 有效 。
//
//例如，如果 nums = [2, 3, 3] ，那么在下标 i = 0 处的分割有效，因为 2 和 9 互质，而在下标 i = 1 处的分割无效，因为 6 和 3 不互质。在下标 i = 2 处的分割也无效，因为 i == n - 1 。
//返回可以有效分割数组的最小下标 i ，如果不存在有效分割，则返回 -1 。
//
//当且仅当 gcd(val1, val2) == 1 成立时，val1 和 val2 这两个值才是互质的，其中 gcd(val1, val2) 表示 val1 和 val2 的最大公约数。

class Solution {
    public int findValidSplit(int[] nums) {
        int i = 0, j = 0;
        while(j <= i){
            for(int k = nums.length-1; k>i; k--){
                if(gcd(nums[j], nums[k])){
                    i = k;
                    break;
                }
            }
            j++;
        }
        if(i == nums.length -1){
            return -1;
        }
        return i;
    }

    private boolean gcd(int a,int b){
        while(b > 0){
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a > 1;
    }
}
