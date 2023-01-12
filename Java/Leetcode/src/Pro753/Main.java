package Pro753;

import java.util.HashSet;
import java.util.Set;


public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    Set<Integer> seen = new HashSet<>();
    StringBuffer ans = new StringBuffer();
    int highest;
    int k;

    public String crackSafe(int n, int k) {
        highest = (int)Math.pow(10,n-1);
        this.k = k;
        dfs(0);
        ans.append("0".repeat(Math.max(0, n - 1)));
        return ans.toString();
    }

    public void dfs(int node) {
        for (int x = 0; x < k; ++x) {
            int nei = node * 10 + x;
            if (!seen.contains(nei)) {
                seen.add(nei);
                dfs(nei % highest);
                ans.append(x);
            }
        }
    }
}
