package Pro2511;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int captureForts(int[] forts) {
        int ans = 0, pre = -1;
        for(int i = 0;i < forts.length;i++) {
            if(forts[i] == 1 || forts[i]== -1) {
                if(pre >= 0 && forts[i] != forts[pre]) {
                    ans = Math.max(ans, i-pre-1);
                }
                pre = i;
            }
        }
        return ans;
    }
}
