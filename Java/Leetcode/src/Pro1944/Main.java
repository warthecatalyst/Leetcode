package Pro1944;

import java.util.*;
public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i = n-1; i >= 0; i--) {
            int cnt = 0;
            while(!stack.isEmpty() && stack.peek() < heights[i]) {
                cnt++;
                stack.pop();
            }
            if(!stack.isEmpty()) {
                cnt++;
            }
            res[i] = cnt;
            stack.add(heights[i]);
        }
        return res;
    }
}
