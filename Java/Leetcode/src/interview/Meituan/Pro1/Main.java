package interview.Meituan.Pro1;

import java.util.*;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int N = scanner.nextInt(),A = scanner.nextInt(),B = scanner.nextInt();
        int[][] enemies = new int[N][];
        for(int i = 0;i<N;i++){
            int x = scanner.nextInt(), y = scanner.nextInt();
            enemies[i] = new int[]{x,y};
        }
//        Arrays.sort(enemies, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if(o1[0]==o2[0]){
//                    return Integer.compare(o1[1],o2[1]);
//                }
//                return Integer.compare(o1[0],o2[0]);
//            }
//        });
        int ans = 0;
        for(int sx = 1;sx<=1000;sx++){
            for(int sy = 1;sy<=1000;sy++){
                int temp = 0;
                for(int[] enemy:enemies){
                    int ex = enemy[0], ey = enemy[1];
                    if(ex<sx||ey<sy){
                        continue;
                    }
                    if(ex-sx<=A&&ey-sy<=B){
                        temp++;
                    }
                }
                ans = Math.max(ans,temp);
            }
        }
        System.out.println(ans);
    }
}
