package Pro1817;

import java.util.*;
public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        Map<Integer,Set<Integer>> userCount = new HashMap<>();
        for(int[] log:logs){
            int userId = log[0] , time = log[1];
            Set<Integer> set = userCount.getOrDefault(userId,new HashSet<>());
            set.add(time);
            userCount.put(userId,set);
        }
        int[] ans = new int[k];
        for(Map.Entry<Integer,Set<Integer>> setEntry:userCount.entrySet()){
            int cnt = setEntry.getValue().size();
            ans[cnt-1]++;
        }
        return ans;
    }
}
