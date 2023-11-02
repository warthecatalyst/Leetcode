package pro46;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    List<List<Integer>> ans;
    public List<List<Integer>> permute(int[] nums) {
        ans = new ArrayList<>();
        List<Integer> numsList = new ArrayList<>();
        for (int num : nums) {
            numsList.add(num);
        }
        backtrack(0, numsList);
        return ans;
    }

    private void backtrack(int cur, List<Integer> nums) {
        if(cur == nums.size()) {
            ans.add(new ArrayList<>(nums));
            return;
        }
        for (int i = cur; i < nums.size(); i++) {
            Collections.swap(nums, cur, i);
            backtrack(cur+1, nums);
            Collections.swap(nums, cur, i);
        }
    }
}
