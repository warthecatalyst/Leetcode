package interview.Mayi.Pro3;

import java.util.*;


/*
   小红定义一个数组的权值为：相邻两数乘积为奇数的对数。例如：[4,3,1,5,2]的权值为2，3*1=3,1*5=5
   小红想知道长度为n的所有排列权值之和为多少？对10^9+7取模
 */
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static final int MOD = 1000000007;
    public static void main(String[] args) {
        int N = scanner.nextInt();
        if(N<=2){
            System.out.println(0);
            return;
        }
        int[][] dp = new int[N/2+2][N/2+1]; // dp[i][j]代表i个奇数，j个偶数的排列权值
        dp[1][1] = dp[1][0] = dp[0][1] = dp[0][0];
        for(int i = 0;i<=N/2;i++){
            dp[0][i] = dp[1][i] = 0;
        }
        dp[2][0] = 2;
        for(int i = 3;i<=N;i++){

        }
    }
}
