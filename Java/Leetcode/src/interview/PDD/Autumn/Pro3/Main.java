package interview.PDD.Autumn.Pro3;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int T;
    public static void main(String[] args) {
        T = scanner.nextInt();
        while(T-- > 0) {
            int N = scanner.nextInt();
            int M = scanner.nextInt();
            int[][] matrix = new int[N][M];
            // lines和cols分别代表这一行/这一列是否全为0
            boolean[] lines = new boolean[N];
            boolean[] cols = new boolean[M];
            for(int i = 0; i < N; i++) {
                for (int j = 0;j < M;j++) {
                    matrix[i][j] = scanner.nextInt();
                    if (matrix[i][j] == 0) {
                        lines[i] = true;
                        cols[j] = true;
                    }
                }
            }
            boolean flag = true;    //能否构建
            int count = 0;          //最多多少个1
            for(int i = 0; i < N && flag;i++) {
                for(int j = 0;j < M;j++) {
                    if (matrix[i][j] == 1) {
                        if (lines[i] && cols[j]) {
                            flag = false;
                            break;
                        } else if (!lines[i] && !cols[j]) {
                            count++;
                        }
                    }
                }
            }
            if (!flag) {
                System.out.println(-1);
            } else {
                System.out.println(count);
            }
        }
    }
}
