package interview.Mihoyo.Pro2;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int n,k;
    static List<Integer>[] tree;
    static int[] treeSize;
    static long ans = 0;
    static int maxDepth = 0;
    static boolean[] visited;
    public static void main(String[] args) {
        n = scanner.nextInt();
        k = scanner.nextInt();
        tree = new List[n];
        visited = new boolean[n];
        for(int i = 0;i<n;i++) {
            tree[i] = new ArrayList<>();
        }
        for(int i = 0;i<n-1;i++) {
            int from = scanner.nextInt() - 1;
            int to = scanner.nextInt() - 1;
            tree[from].add(to);
            tree[to].add(from);
        }
        dfs(0, 0, true);
        //System.out.println(maxDepth);
        treeSize = new int[maxDepth+1];
        Arrays.fill(visited, false);
        dfs(0, 0, false);
        //System.out.println(Arrays.toString(treeSize));
        for(int i = 0;i<=Math.min(maxDepth, k);i++) {
            ans += treeSize[i];
        }
        if(k>maxDepth) {
            ans += (long)treeSize[maxDepth] * (k-maxDepth);
        }
        System.out.println(ans);
    }

    //第一次遍历获取深度,第二次遍历获取每一层添加后有多少
    static void dfs(int node,int depth, boolean isFirst) {
        if(isFirst && depth > maxDepth) {
            maxDepth = depth;
        }
        visited[node] = true;
        if(!isFirst) {
            treeSize[depth]++;
        }
        boolean hasChild = false;
        for(int neigh:tree[node]) {
            if(!visited[neigh]) {
                hasChild = true;
                dfs(neigh, depth+1, isFirst);
            }
        }
        if(!isFirst && !hasChild) {
            for(int i = depth+1;i<treeSize.length;i++) {
                treeSize[i]++;
            }
        }
    }

}
