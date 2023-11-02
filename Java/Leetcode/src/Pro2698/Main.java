package Pro2698;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int punishmentNumber(int n) {
        int ans = 0;
        for (int i = 1; i <= n;i++) {
            if (isPunishment(i)) {
                ans += i * i;
            }
        }
        return ans;
    }

    private boolean isPunishment(int num) {
        if (num == 1) {
            return true;
        }
        int numSquare = num * num;
        return dfs(0, 0, String.valueOf(numSquare), num);
    }

    private boolean dfs(int idx, int current, String str, int target) {
        if (idx == str.length()) {
            return current == target;
        }
        int sum = 0;
        for (int i = idx; i < str.length(); i++) {
            sum = sum * 10 + str.charAt(i) - '0';
            if (sum + current > target) {
                break;
            }
            if (dfs(i+1, current + sum, str, target)) {
                return true;
            }
        }
        return false;
    }
}
