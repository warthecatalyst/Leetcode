package interview.tencent.Autumn.Pro3;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int n;
    static int[] a;
    public static void main(String[] args) {
        n = scanner.nextInt();
        a = new int[n];
        for (int i = 0;i < n;i++) {
            a[i] = scanner.nextInt();
        }
        Arrays.sort(a);
        int right = n-2, left = 0;
        long ans = a[n-1];
        while(left < right) {
            ans += a[right] - a[left];
            right--;
            left++;
        }
        System.out.println(ans);
    }
}
