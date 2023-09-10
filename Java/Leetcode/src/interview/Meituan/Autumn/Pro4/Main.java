package interview.Meituan.Autumn.Pro4;

import java.util.*;

public class Main {
    static int n;
    static Scanner scanner = new Scanner(System.in);
    static final int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) {
        n = scanner.nextInt();
        String str = scanner.next();
        int ans = solve(str);
        System.out.println(ans);
    }

    private static int solve(String str) {
        int ans = n;
        for (int i = 1; i <= Math.sqrt(n);i++) {
            if(n%i == 0) {
                ans = Math.min(ans, getAns(i, str));
                ans = Math.min(ans, getAns(n/i, str));
            }
        }
        return ans;
    }

    //每行有colNum列
    private static int getAns(int colNum, String str) {
        char[][] charSet = new char[n/colNum][colNum];
        for(int i = 0;i<n/colNum;i++) {
            for(int j = 0; j < colNum;j++) {
                charSet[i][j] = str.charAt(i * colNum + j);
            }
        }
        boolean[][] visited = new boolean[n/colNum][colNum];
        int blocks = 0;
        for(int i = 0;i < n/colNum;i++) {
            for(int j = 0;j < colNum;j++) {
                if (!visited[i][j]) {
                    blocks++;
                    dfs(charSet, i, j, visited, n/colNum, colNum);
                }
            }
        }
        return blocks;
    }

    private static void dfs(char[][] charArray, int i, int j, boolean[][] visited, int m,int n) {
        visited[i][j] = true;
        for(int l = 0;l < 4;l++) {
            int newI = i + directions[l][0], newJ = j + directions[l][1];
            if (isIn(newI, newJ, m, n) && charArray[newI][newJ] == charArray[i][j] && !visited[newI][newJ]) {
                dfs(charArray, newI, newJ, visited,m, n);
            }
        }
    }

    private static boolean isIn(int i,int j, int m,int n) {
        return i>=0 && i < m && j >= 0 && j < n;
    }
}
