package LCR084;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    int n;
    List<List<Integer>> ans;
    List<Integer> path;
    boolean[] visited;
    public List<List<Integer>> permuteUnique(int[] nums) {
        n = nums.length;
        ans = new ArrayList<>();
        path = new ArrayList<>();
        visited = new boolean[n];
        Arrays.sort(nums);
        backtrack(0, nums, new ArrayList<>());
        return ans;
    }

    private void backtrack(int idx, int[] nums, List<Integer> perm) {
        if (idx == n) {
            ans.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0;i < n; i++) {
            if (visited[i] || (i > 0 && nums[i] == nums[i-1] && !visited[i - 1])) {
                continue;
            }

            perm.add(nums[i]);
            visited[i] = true;
            backtrack(idx+1, nums, perm);
            visited[i] = false;
            perm.remove(idx);
        }
    }
}
