package Pro2741;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        var ans = solution.smallestString("aaaaa");
        System.out.println(ans);
    }
}

class Solution {
    public String smallestString(String s) {
        int i = 0;
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        while (i < n && s.charAt(i) == 'a') {
            i++;
        }
        while (i < n && s.charAt(i) != 'a') {
            sb.setCharAt(i, (char) (s.charAt(i++) - 1));
        }
        if (i == n && s.charAt(n - 1) == 'a') {
            sb.setCharAt(n - 1, 'z');
            return sb.toString();
        }
        return sb.toString();
    }
}
