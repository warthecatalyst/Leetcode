package interview.tencent.Autumn.Pro2;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int t;
    public static void main(String[] args) {
        t = scanner.nextInt();
        while(t-- > 0) {
            int n = scanner.nextInt();
            List<Integer> a = new ArrayList<>(n);
            int[] b = new int[n-1];
            for(int i = 0;i<n;i++) {
                a.add(scanner.nextInt());
            }
            for(int i = 0;i < n-1;i++) {
                int index = scanner.nextInt();
                b[i] = a.get(index-1);
            }
            Collections.sort(a);
            printMedian(a);
            for(int elem : b) {
                Integer element = elem;
                int index = a.indexOf(element);
                a.remove(index);
                printMedian(a);
            }
            System.out.println();
        }
    }

    private static void printMedian(List<Integer> arr) {
        int n = arr.size();
        if (n % 2 == 0) {
            int sum = arr.get(n/2-1) + arr.get(n/2);
            if (sum % 2 == 0) {
                System.out.print(sum/2 + " ");
            } else {
                System.out.printf("%.1f ", (double)sum/2);
            }
        } else {
            System.out.print(arr.get(n/2) + " ");
        }
    }
}
