package Pro100118;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        long ans = solution.maximumScoreAfterOperations(new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}}
                , new int[]{20, 10, 9, 7, 4, 3, 5});
        System.out.println(ans);
    }
}

class Solution {
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        int n = values.length;
        TreeNode[] treeNodes = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            treeNodes[i] = new TreeNode(values[i]);
        }

        //通过edges构建树
        buildTreeNodes(treeNodes, edges);

        //DFS获取最大值
        long totalScore = 0;
        for(int value : values) {
            totalScore += value;
        }
        return totalScore - dfs(treeNodes[0]);
    }

    private void buildTreeNodes(TreeNode[] treeNodes, int[][] edges) {
        int n = treeNodes.length;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] edge : edges) {
            int from = edge[0], to = edge[1];
            graph[from].add(to);
            graph[to].add(from);
        }
        // BFS构建
        boolean[] visited = new boolean[n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{-1, 0});
        visited[0] = true;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int from = cur[0], to = cur[1];
            if (from >= 0) {
                treeNodes[from].children.add(treeNodes[to]);
            }
            for (int neigh : graph[to]) {
                if(visited[neigh]) {
                    continue;
                }
                visited[neigh] = true;
                queue.add(new int[]{to, neigh});
            }
        }
    }

    private long dfs(TreeNode node) {
        if (node.children.isEmpty()) {
            return node.val;
        }
        long ans = 0;
        for(TreeNode child : node.children) {
            ans += dfs(child);
        }
        return Math.min(ans, node.val);
    }

    class TreeNode {
        int val;
        List<TreeNode> children;

        TreeNode(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }
}
