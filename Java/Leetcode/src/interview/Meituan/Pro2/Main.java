package interview.Meituan.Pro2;

import java.util.*;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int N = scanner.nextInt(),K = scanner.nextInt();
        int[] colors = new int[N];
        for(int i = 0;i<N;i++){
            colors[i] = scanner.nextInt();
        }
        int ans = 0;
        for(int i = 0;i<N;i++){
            Set<Integer> set = new HashSet<>();
            int j = i;
            for(;j<N;j++){
                set.add(colors[j]);
                if(set.size()>K){
                    break;
                }
            }
            ans = Math.max(j-i,ans);
        }
        System.out.println(ans);
    }
}
