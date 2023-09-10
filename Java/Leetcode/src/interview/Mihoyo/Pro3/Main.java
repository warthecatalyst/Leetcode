package interview.Mihoyo.Pro3;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        double p = scanner.nextDouble();
        double ans = calculateExpectation(p);
        System.out.println(ans);
    }

    private static double calculateExpectation(double p) {
        int n = 180; //连续没有抽到当期五星的最大次数,两次大保底必抽到，所以最多180次
        //dp[i][j][k]表示抽了i次，有连续j次没抽到五星
        double[][][] dp = new double[n+1][90][2];
        dp[1][0][0] = p/2;
        dp[1][0][1] = 1;
        for(int i = 2;i<=n;i++) {
            for(int j = 0;j<Math.min(89,i);j++) {
                for(int k = 0;k<=1;k++) {
                    dp[i][j][k] = (1-k)*dp[i-1][j+1][k] + k * ((1-p) * dp[i-1][j+1][0]
                    + dp[i-1][0][1]);
                }
            }
        }
        double ans = 0;
        for (int i = 1;i<=n;i++) {
            for(int j = 0;j<90;j++) {
                ans += dp[i][j][0] * (j+1);
                ans += dp[i][j][1] * (j+1);
            }
        }
        return ans;
    }
}

