package Weekly_Contest.Week335.Pro4;

public class Main {
    public static void main(String[] args) {

    }
}

//6310. 获得分数的方法数
//考试中有 n 种类型的题目。给你一个整数 target 和一个下标从 0 开始的二维整数数组 types ，其中 types[i] = [counti, marksi] 表示第 i 种类型的题目有 counti 道，每道题目对应 marksi 分。
//
//返回你在考试中恰好得到 target 分的方法数。由于答案可能很大，结果需要对 109 +7 取余。
//
//注意，同类型题目无法区分。
//
//比如说，如果有 3 道同类型题目，那么解答第 1 和第 2 道题目与解答第 1 和第 3 道题目或者第 2 和第 3 道题目是相同的。
class Solution {
    static final int MOD = (int) (1e9+7);

    public int waysToReachTarget(int target, int[][] types) {
        int[] dp = new int[target + 1];
        int k = 0;
        while(k <= types[0][0]){
            if(k* types[0][1] <=target){
                dp[k * types[0][1]] = 1;
            }
            k++;
        }
        for(int i=1; i<types.length; i++){
            int[] dp2 = new int[target + 1];
            for(int j =0; j<=target; j++){
                int m = 0;
                while(m <= types[i][0]){
                    if(m*types[i][1] <= j){
                        dp2[j] = (dp2[j] + dp[j - m*types[i][1]])%MOD;
                    }
                    m++;
                }
            }
            System.arraycopy(dp2, 0, dp, 0, target + 1);
        }
        return dp[target];
    }
}
