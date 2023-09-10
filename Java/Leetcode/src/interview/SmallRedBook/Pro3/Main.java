package interview.SmallRedBook.Pro3;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int t = scanner.nextInt();
        while(t-- > 0) {
            int n = scanner.nextInt(), x = scanner.nextInt();
            long[] nums = new long[n];
            for(int i = 0;i<n;i++) {
                nums[i] = scanner.nextLong();
            }
            long[][] dp = new long[n][2];
            dp[0][0] = nums[0];
            dp[0][1] = x;
            long ans = Math.max(dp[0][0], dp[0][1]);
            for (int i = 1;i < n;i++) {
                dp[i][0] = Math.max(dp[i-1][0] + nums[i], nums[i]);
                ans = Math.max(dp[i][0], ans);
                dp[i][1] = Math.max(dp[i-1][0] + x, x);
                dp[i][1] = Math.max(dp[i-1][1] + nums[i], dp[i][1]);
                ans = Math.max(dp[i][1], ans);
            }
            System.out.println(ans);
        }
    }
}
