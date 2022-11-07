package Pro754;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.reachNumber(2);
        System.out.println(res);
    }
}

class Solution {
    public int reachNumber(int target) {
        target = Math.abs(target);
        int k = 0;
        while (target > 0) {
            k++;
            target -= k;
        }
        return target % 2 == 0 ? k : k + 1 + k % 2;
    }
}

