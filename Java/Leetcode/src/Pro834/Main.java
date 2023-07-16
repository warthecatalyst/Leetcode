package Pro834;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    int[] ans;
    int[] sz;
    int[] dp;
    List<Integer>[] graph;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        ans = new int[n];
        sz = new int[n];
        dp = new int[n];
        graph = new List[n];
        for(int i = 0;i < n;i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge:edges) {
            int u = edge[0], v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        dfs(0, -1);
        dfs2(0, -1);
        return ans;
    }

    private void dfs(int u, int f) {
        sz[u] = 1;
        dp[u] = 0;
        for(int v:graph[u]) {
            if (v == f) {
                continue;
            }
            dfs(v,u);
            dp[u] += dp[v] + sz[v];
            sz[u] += sz[v];
        }
    }

    private void dfs2(int u, int f) {
        ans[u] = dp[u];
        for (int v: graph[u]) {
            if (v == f) {
                continue;
            }
            int pu = dp[u], pv = dp[v];
            int su = sz[u], sv = sz[v];

            dp[u] -= dp[v] + sz[v];
            sz[u] -= sz[v];
            dp[v] += dp[u] + sz[u];
            sz[v] += sz[u];

            dfs2(v, u);

            dp[u] = pu;
            dp[v] = pv;
            sz[u] = su;
            sz[v] = sv;
        }
    }
}
