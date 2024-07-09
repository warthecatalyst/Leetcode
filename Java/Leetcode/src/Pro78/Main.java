package Pro78;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        dfs(nums, new ArrayList<>(), 0);
        return res;
    }

    private void dfs(int[] nums, List<Integer> current, int index) {
        if (index == nums.length) {
            res.add(new ArrayList<>(current));
            return;
        }
        // 取或者不取当前数
        current.add(nums[index]);
        dfs(nums, current, index + 1);
        current.removeLast();
        dfs(nums, current, index + 1);
    }
}
