package interview.Meituan.Autumn.Pro2;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int n = scanner.nextInt();
        long[] distances = new long[n];
        long totalDistance = 0;
        for(int i = 0;i<n;i++) {
            distances[i] = scanner.nextLong();
            totalDistance += distances[i];
        }
        int x = scanner.nextInt() - 1, y = scanner.nextInt() - 1;
        if (x == y) {
            System.out.println(0);
            return;
        }
        else if(x > y) {
            int temp = x;
            x = y;
            y = temp;
        }
        long roundDistance = 0, revRoundDistance = 0;
        for(int i = x;i < y;i++) {
            roundDistance += distances[i];
        }
        revRoundDistance = totalDistance-roundDistance;
        System.out.println(Math.min(roundDistance, revRoundDistance));
    }
}
