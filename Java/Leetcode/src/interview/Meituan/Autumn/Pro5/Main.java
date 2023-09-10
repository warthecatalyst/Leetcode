package interview.Meituan.Autumn.Pro5;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int n;
    static long[] weights;
    static List<Integer>[] graph;
    public static void main(String[] args) {
        n = scanner.nextInt();
        weights = new long[n];
        graph = new List[n];
        for(int i = 0;i<n;i++) {
            weights[i] = scanner.nextLong();
            graph[i] = new ArrayList<>();
        }
        for(int i = 0;i<n-1;i++) {
            int from = scanner.nextInt()-1, to = scanner.nextInt() - 1;
            graph[from].add(to);
            graph[to].add(from);
        }
//        for(int i = 0;i<n;i++) {
//            System.out.println(graph[i]);
//        }
        int[][] dp = new int[n][2];
        boolean[] visited = new boolean[n];
        for(int i = 0;i < n;i++) {
            Arrays.fill(dp[i], -1);
        }
        visited[0] = true;
        dfs(0, dp, visited);
        for(int i = 0; i < n;i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        int ans = Math.max(dp[0][0], dp[0][1]);
        System.out.println(ans);
    }

    private static void dfs(int node,int[][] dp, boolean[] visited) {
        dp[node][0] = dp[node][1] = 0;
        for(int neigh:graph[node]) {
            if(!visited[neigh]) {
                visited[neigh] = true;
                dfs(neigh, dp, visited);
                //System.out.println(Arrays.toString(dp));
                if(isSquareNum(weights[node] * weights[neigh])) {
                    dp[node][1] += 2 + dp[neigh][0];
                }
                dp[node][0] += Math.max(dp[node][0], Math.max(dp[neigh][0], dp[neigh][1]));
            }
        }
    }

    private static boolean isSquareNum(long num) {
        int sqrt = (int) Math.sqrt(num);
        return (long) sqrt * sqrt == num;
    }
}
