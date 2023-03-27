package Pro128;

import java.util.*;
public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        UnionFind uf = new UnionFind(nums.length);

        for (int i = 0; i < nums.length; i++) {
            // 存在重复元素，跳过
            if (map.containsKey(nums[i])) continue;

            if (map.containsKey(nums[i] - 1)) {
                uf.union(i, map.get(nums[i] - 1));
            }
            if (map.containsKey(nums[i] + 1)) {
                uf.union(i, map.get(nums[i] + 1));
            }
            map.put(nums[i], i);
        }
        return uf.getMaxConnectSize();
    }

    static class UnionFind{
        private int[] parent;
        private int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            parent[rootP] = rootQ;
            // 注意 别写反了
            size[rootQ] += size[rootP];
        }
        // get root id
        private int find(int x) {
            // 路径压缩
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        public int getMaxConnectSize() {
            int maxSize = 0;
            for (int i = 0; i < parent.length; i++) {
                if (i == parent[i]) {
                    maxSize = Math.max(maxSize, size[i]);
                }
            }
            return maxSize;
        }
    }
}
