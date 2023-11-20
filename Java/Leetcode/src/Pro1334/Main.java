package Pro1334;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[] ans = new int[]{Integer.MAX_VALUE / 2, -1};
        int[][] mp = new int[n][n];
        // floyd算法
        for (int i = 0; i < n; i++) {
            Arrays.fill(mp[i], Integer.MAX_VALUE/2);
        }
        for(int[] edge : edges) {
            int from = edge[0], to = edge[1], weight = edge[2];
            mp[from][to] = mp[to][from] = weight;
        }
        for (int k = 0; k < n; ++k) {
            mp[k][k] = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    mp[i][j] = Math.min(mp[i][j], mp[i][k] + mp[k][j]);
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            int cnt = 0;
            for (int j = 0; j < n; ++j) {
                if (mp[i][j] <= distanceThreshold) {
                    cnt++;
                }
            }
            if (cnt <= ans[0]) {
                ans[0] = cnt;
                ans[1] = i;
            }
        }
        return ans[1];
    }
}
