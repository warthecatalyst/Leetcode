package interview.PDD.Autumn.Pro4;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int N,M;
    static Part[] parts;
    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        parts = new Part[N];
        int totalExchange = 0;
        int totalCnt = 0;
        for(int i = 0;i < N;i++) {
            parts[i] = new Part();
            parts[i].repairTime = scanner.nextInt();
            parts[i].repairCost = scanner.nextInt();
            parts[i].replaceTime = scanner.nextInt();
            parts[i].replaceCost = scanner.nextInt();
            totalExchange += parts[i].replaceTime;
            totalCnt += parts[i].replaceCost;
        }
        if(totalExchange > M) {
            System.out.println(-1);
            return;
        } else if (totalExchange == M) {
            System.out.println(totalCnt);
            return;
        }
        int totalCost = 0;
        PriorityQueue<Part> minHeap = new PriorityQueue<>(Comparator.comparingInt(p -> p.repairTime - p.replaceTime));
        minHeap.addAll(Arrays.asList(parts));
        while(!minHeap.isEmpty()) {
            Part part = minHeap.poll();
            if(totalExchange-part.replaceTime <= M - part.repairTime ) {
                totalCost += part.repairCost;
                M -= part.repairTime;
                totalExchange -= part.replaceTime;
            } else {
                totalCost += part.replaceCost;
            }
        }
        System.out.println(totalCost);
    }

    static class Part {
        int repairTime;
        int repairCost;
        int replaceTime;
        int replaceCost;
    }
}
