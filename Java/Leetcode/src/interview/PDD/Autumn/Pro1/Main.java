package interview.PDD.Autumn.Pro1;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int T = scanner.nextInt();
        while(T-- > 0) {
            int n = scanner.nextInt(), a = scanner.nextInt(), b = scanner.nextInt();
            int sub = b-a;
            int minSubSum = (n-1)*n/2;
            if (sub >= minSubSum) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
