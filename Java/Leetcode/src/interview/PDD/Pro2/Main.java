package interview.PDD.Pro2;

import java.util.*;

public class Main {
    static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        int N = scanner.nextInt();
        while (T-->0){
            int[] damages = new int[N];
            int cnt1 = 0;
            for(int i = 0;i<N;i++){
                damages[i] = scanner.nextInt();
                if(damages[i]==1){
                    cnt1++;
                }
            }
            int take1 = cnt1/2;
            int take2 = N-take1*2;
            System.out.println(take1+take2);
        }
    }
}
