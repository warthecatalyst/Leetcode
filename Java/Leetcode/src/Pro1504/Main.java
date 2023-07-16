package Pro1504;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public static int numSubmat(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int ans = 0;
        int[] histogram = new int[m]; // 以每一行打底的直方图数组
        for (int row = 0; row < n; row++) {
            // 计算以每一行打底的直方图
            for (int col = 0; col < m; col++) {
                if (mat[row][col] == 1) histogram[col]++;
                else histogram[col] = 0;
            }
            // 计算以每一行打底时的全1子矩阵的数量
            int count = getCountOfBackingWithThisRow(histogram);
            ans += count;
        }
        return ans;
    }

    // 计算以某一行打底（形成的直方图数组histogram）的全1子矩阵的数量
    private static int getCountOfBackingWithThisRow(int[] histogram) {
        int n = histogram.length;
        int count = 0;
        Stack<Integer> stack = new Stack<>(); // 单调栈：栈底 -> 栈顶： 由小 -> 大
        // 每个元素依次入栈，维持单调栈的严格单调递增结构，不符合时，弹出元素，弹出即结算
        for (int i = 0; i < n; i++) {
            // 维持单调栈的严格单调递增结构，不符合时，弹出元素，弹出即结算
            while (!stack.isEmpty() && histogram[stack.peek()] >= histogram[i]) {
                int index = stack.pop();
                int leftIndex = stack.isEmpty() ? -1 : stack.peek(); // 左侧比[index]小、离index最近的元素位置
                int rightIndex = i; // 右侧比[index]小、离index最近的元素位置
                int l = rightIndex - leftIndex - 1; // 形成的直方图长度
                // 形成的直方图的有效结算高度 h ：
                int h = histogram[index] - Math.max(leftIndex == -1 ? 0 : histogram[leftIndex], histogram[rightIndex]);
                count += ((l * (l+1))/2) * h; // 公式计算个数
            }
            stack.push(i);
        }
        // 结算单调栈中最后剩下的元素，弹出元素，弹出即结算
        while (!stack.isEmpty()) {
            int index = stack.pop();
            int leftIndex = stack.isEmpty() ? -1 : stack.peek(); // 左侧比[index]小、离index最近的元素位置
            int rightIndex = n; // 右侧比[index]小、离index最近的元素位置
            int l = rightIndex - leftIndex - 1; // 形成的直方图长度
            // 形成的直方图的有效结算高度 h ：
            int h = histogram[index] - (leftIndex == -1 ? 0 : histogram[leftIndex]);
            count += ((l * (l+1))/2) * h; // 公式计算个数
        }
        return count;
    }
}
