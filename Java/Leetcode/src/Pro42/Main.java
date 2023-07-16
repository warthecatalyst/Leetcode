package Pro42;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int trap(int[] height) {
        int sum = 0;
        int current = 0;
        Stack<Integer> stack = new Stack<>();
        while(current < height.length) {
            while(!stack.empty()&&height[stack.peek()] < height[current]) {
                int h = height[stack.peek()];
                stack.pop();
                if(stack.empty()) {
                    break;
                }
                int distance = current - stack.peek() - 1;
                int hei = Math.min(height[stack.peek()], height[current]) - h;
                sum += + distance * hei;
            }
            stack.add(current);
            current++;
        }
        return sum;
    }
}
