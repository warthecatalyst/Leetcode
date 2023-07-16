package forInter.NiukeCompetition.Pro1;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int L,R,x;
        L = scanner.nextInt();
        R = scanner.nextInt();
        x = scanner.nextInt();
        String x_str = String.valueOf(x);
        int ans = 0;
        for(int i = L;i<=R;i++){
            String i_str = String.valueOf(i);
            if(i_str.contains(x_str)){
                ans++;
            }
        }
        System.out.println(ans);
    }
}
