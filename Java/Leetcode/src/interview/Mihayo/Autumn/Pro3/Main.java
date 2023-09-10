package interview.Mihayo.Autumn.Pro3;


import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int n;
    static long[] imperfections;    //每个节点的不完美值
    static long[] dp;               //每个节点换到根节点下，能够少多少的分数
    static int[] depths;            //每个节点的深度
    static Set<Integer>[] graph;
    public static void main(String[] args) {
        n = scanner.nextInt();
        imperfections = new long[n];
        depths = new int[n];
        graph = new Set[n];
        dp = new long[n];
        for (int i = 0;i<n;i++) {
            imperfections[i] = scanner.nextLong();
            graph[i] = new HashSet<>();
        }
        for(int i = 0;i< n-1;i++) {
            int from = scanner.nextInt()-1, to = scanner.nextInt() - 1;
            graph[from].add(to);
            graph[to].add(from);
        }
        dfs(0, 1);
        //System.out.println(Arrays.toString(dp));
        //System.out.println(Arrays.toString(depths));
        long ans = dp[0];
        for(int i = 0;i<n;i++) {
            if(depths[i] > 2) {
                long res = newDfs(i,2);
                //System.out.println(i + " " + res);
                ans = Math.min(ans, dp[0] + res - dp[i]);
            }
        }
        System.out.println(ans);
    }

    static long dfs(int curNode, int depth) {
        depths[curNode] = depth;
        long sum = 0;
        for(int neigh:graph[curNode]) {
            graph[neigh].remove(curNode);
            long res = dfs(neigh, depth+1);
            sum += res;
        }
        sum += depth * imperfections[curNode];
        dp[curNode] = sum;
        return sum;
    }

    static long newDfs(int curNode, int depth) {
        long sum = 0;
        for(int neigh:graph[curNode]) {
            sum += newDfs(neigh, depth+1);
        }
        sum += depth * imperfections[curNode];
        return sum;
    }
}
