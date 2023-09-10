package interview.Jingdong.Pro2;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    private static final int MOD = 1000000007;
    public static void main(String[] args) {
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for(int i = 0;i<n;i++) {
            nums[i] = scanner.nextInt();
        }
        int[][] dp = new int[n][10];
        int origin = nums[n-1] % 10;
        dp[n-1][origin] = 1;
        for(int i = n-2;i>=0;i--) {
            int mod = nums[i] % 10;
            for(int j = 0;j<10;j++) {
                int sum = (mod+j) % 10;
                int mul = (mod*j) % 10;
                dp[i][sum] = (dp[i][sum] + dp[i+1][j]) % MOD;
                dp[i][mul] = (dp[i][mul] + dp[i+1][j]) % MOD;
            }
        }
        for(int i = 0;i<10;i++) {
            System.out.print(dp[0][i]);
            if(i < 9) {
                System.out.print(" ");
            }
        }
    }
}
