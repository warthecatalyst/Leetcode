package interview.Meituan.Autumn.Pro3;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int n = scanner.nextInt(), m = scanner.nextInt();
        long[][] deliciousness = new long[n][m];
        long[] lineSum = new long[n];
        long[] colSum = new long[m];
        long allSum = 0;
        for(int i = 0;i<n;i++) {
            for(int j = 0;j < m;j++) {
                deliciousness[i][j] = scanner.nextLong();
                lineSum[i] += deliciousness[i][j];
                colSum[j] += deliciousness[i][j];
                allSum += deliciousness[i][j];
            }
        }
        long leftSum = 0;
        long rightSum = allSum;
        long minSub = allSum;
        //按照列来分割
        for(int j = 0;j<m;j++) {
            leftSum += colSum[j];
            rightSum -= colSum[j];
            minSub = Math.min(minSub, Math.abs(leftSum-rightSum));
        }
        long topSum = 0;
        long bottomSum = allSum;
        //按照行来分割
        for(int i = 0;i < n;i++) {
            topSum += lineSum[i];
            bottomSum -= lineSum[i];
            minSub = Math.min(minSub, Math.abs(topSum-bottomSum));
        }
        System.out.println(minSub);
    }
}
