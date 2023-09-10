package interview.tencent.Autumn.Pro5;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int n,k;
    public static void main(String[] args) {
        n = scanner.nextInt();
        k = scanner.nextInt();
        int[] arr = new int[n];
        for(int i = 0;i < n;i++) {
            arr[i] = scanner.nextInt();
        }
        for(int i = 0;i<k;i++) {
            getMaxReverse(arr);
        }
        long result = 0;
        for(int i = 0;i < n;i++) {
            result += arr[i];
        }
        System.out.println(result);
    }

    // 贪心每次减少最大的那个
    private static void getMaxReverse(int[] arr) {
        int maxNum = 0, maxIdx = -1;
        for(int i = 0;i < n;i++) {
            int res = unsetLowestBit(arr[i]);
            if(arr[i] - res > maxNum) {
                maxNum = arr[i]-res;
                maxIdx = i;
            }
        }
        arr[maxIdx] = unsetLowestBit(arr[maxIdx]);
    }

    private static int unsetLowestBit(int num) {
        return num & (num-1);
    }
}
