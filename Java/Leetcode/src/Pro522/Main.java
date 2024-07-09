package Pro522;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findLUSlength(new String[]{"aba", "cdc","eaed"});
    }
}

class Solution {
    public int findLUSlength(String[] strs) {
        int n = strs.length;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            boolean check = false;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                // 判断str[i]是否是str[j]的子序列
                if (isSubSeq(strs[i], strs[j])) {
                    check = true;
                    break;
                }
            }
            if(!check) {
                ans = Math.max(ans, strs[i].length());
            }
        }
        return ans;
    }

    // 判断str1是否是str2的子序列
    private boolean isSubSeq(String str1, String str2) {
        int p1 = 0, p2 = 0;
        while(p1 < str1.length() && p2 < str2.length()) {
            if(str1.charAt(p1) == str2.charAt(p2)) {
                p1++;
            }
            p2++;
        }
        // p1到最后说明是子序列
        return p1 == str1.length();
    }
}
