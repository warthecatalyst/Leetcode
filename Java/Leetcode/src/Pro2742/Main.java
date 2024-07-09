package Pro2742;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        int[] f = new int[n * 2 + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[n] = 0;
        for (int i = 0; i < n; ++i) {
            int[] g = new int[n * 2 + 1];
            Arrays.fill(g, Integer.MAX_VALUE / 2);
            for (int j = 0; j <= n * 2; ++j) {
                // 付费
                g[Math.min(j + time[i], n * 2)] = Math.min(g[Math.min(j + time[i], n * 2)], f[j] + cost[i]);
                // 免费
                if (j > 0) {
                    g[j - 1] = Math.min(g[j - 1], f[j]);
                }
            }
            f = g;
        }
        int ans = f[n];
        for (int i = n + 1; i <= n * 2; i++) {
            ans = Math.min(ans, f[i]);
        }
        return ans;
    }
}