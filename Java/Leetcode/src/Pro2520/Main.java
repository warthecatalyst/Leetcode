package Pro2520;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int countDigits(int num) {
        int ans = 0;
        String str = String.valueOf(num);
        for (int i = 0; i < str.length(); i++) {
            int n = str.charAt(i)-'0';
            if(n == 0) {
                continue;
            }
            if (num %n == 0) {
                ans++;
            }
        }
        return ans;
    }
}
