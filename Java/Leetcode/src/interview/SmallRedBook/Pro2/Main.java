package interview.SmallRedBook.Pro2;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        long n = scanner.nextLong(), k = scanner.nextLong();
        long ans = (n+1)*n/2 * k;
        System.out.println(ans);
    }
}
