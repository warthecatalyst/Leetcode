package interview.SmallRedBook.Pro1;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        long n = scanner.nextLong();
        int m = scanner.nextInt();
        long k = scanner.nextLong();
        long ans = 0;
        long[][] list = new long[m][2];
        for(int i = 0;i < m;i++) {
            list[i][0] = scanner.nextLong();
            list[i][1] = scanner.nextLong();
        }
        boolean flag = false;
        for(int i = 0;i < m;i++) {
            if(flag) {
                //剪枝
                break;
            }
            long sum = 0;
            long st = list[i][0], end = st + k;
            if(end > n) {
                flag = true;
            }
            int sx = binarySearch(list, end);
            for(int j = i; j < sx;j++) {
                // 二分
//                if (list[j][0] >= end) {
//                    break;
//                }
                sum += Math.min(list[j][1], end) - list[j][0];
            }
            ans = Math.max(ans, sum);
        }
        System.out.println(ans);
    }

    public static int binarySearch(long[][] list, long s) {
        int n = list.length;
        if(list[n-1][0] < s) {
            return n;
        }
        //查找第一个大于s的开始
        int left = 0, right = n - 1;
        while(left < right) {
            int mid = (left+right)>>1;
            if(list[mid][0] <= s) {
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left;
    }
}

