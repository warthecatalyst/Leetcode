package Pro10;

import java.util.*;
public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[][] dp = new boolean[n+1][m+1]; // dp[i][j]表示前i个是否能与前j个相匹配
        dp[0][0] = true;
        for (int i = 0; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i][j-2];  //匹配0个
                    if (matches(s, p, i, j-1)) {    //匹配1-n个
                        dp[i][j] = dp[i][j] || dp[i][j-1];
                    }
                } else {
                    if(matches(s, p, i, j)) {
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
            }
        }
        return dp[n][m];
    }

    private boolean matches(String s, String p, int i, int j) {
        if(i == 0) {
            return false;
        }
        if (p.charAt(j-1) == '.') {
            return true;
        }
        return s.charAt(i-1) == p.charAt(j-1);
    }
}
