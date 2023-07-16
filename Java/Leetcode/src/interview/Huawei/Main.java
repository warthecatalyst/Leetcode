package interview.Huawei;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.result(new int[][]{{10,17},{10,19},{16,2},{19,18},{5,6}});
        System.out.println(ans);
    }
}

/*
给你一个二维整数数组 array ，其中 array[i] = [wi, hi] ，表示第 i 个箱子的宽度和高度。

当另一个箱子的宽度和高度都比这个箱子大的时候，这个箱子就可以放进另一个箱子里。

请计算 最多能有多少个 箱子能组成一组“套娃”箱子（即可以把一个箱子放到另一个箱子里面）。

注意：不允许旋转箱子。

示例 1：

> 输入：array = [[5,4],[6,4],[6,7],[2,3]] 输出：3 解释：最多箱子的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。

示例 2：

> 输入：array = [[1,1],[1,1],[1,1]] 输出：1

提示：

* 1 <= envelopes.length <= 10^5^
* envelopes[i].length == 2
* 1 <= wi, hi <= 10^5^
 */
class Solution {
    public int result(int[][] input) {
        int n = input.length;
        Arrays.sort(input, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]){
                    return Integer.compare(o1[1],o2[1]);
                }
                return Integer.compare(o1[0],o2[0]);
            }
        });
        for(int[] ints:input){
            System.out.print(Arrays.toString(ints)+", ");
        }
        System.out.println();
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        for(int i = 1;i<n;i++){
            int[] boxi = input[i];
            for(int j = i-1;j>=0;j--){
                int[] boxj = input[j];
                if(boxi[0]>boxj[0]&&boxi[1]>boxj[1]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
        int maxAns = dp[n-1];
        for(int i = n-2;i>=0;i--){
            maxAns = Math.max(maxAns,dp[i]);
        }
        return maxAns;
    }
}