package interview.Meituan.Test1;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while(scanner.hasNextInt()){
            int n = scanner.nextInt(), m = scanner.nextInt();
            double temp = n;
            double ans = 0;
            while(m-->0){
                ans += temp;
                temp = Math.sqrt(temp);
            }
            System.out.printf("%.2f\n",ans);
        }
    }
}
