package Pro6160;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ans = solution.answerQueries(new int[]{4,5,2,1},new int[]{3,10,21});
        System.out.println(Arrays.toString(ans));
    }
}

class Solution {
    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length, m = queries.length;
        Arrays.sort(nums);
        int[] ans = new int[m];
        for(int i = 0;i<m;i++){
            int sum = 0,cnt = 0;
            int target = queries[i];
            while(sum<=target&&cnt<n){
                sum+=nums[cnt++];
            }
            if(sum>target)
                ans[i] = cnt-1;
            else
                ans[i] = cnt;
        }
        return ans;
    }
}
