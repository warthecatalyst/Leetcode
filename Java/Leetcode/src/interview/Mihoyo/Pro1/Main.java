package interview.Mihoyo.Pro1;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        long n = scanner.nextLong(), m = scanner.nextLong();
        long x1,y1,x2,y2,x3,y3;
        x1 = scanner.nextLong();
        y1 = scanner.nextLong();
        x2 = scanner.nextLong();
        y2 = scanner.nextLong();
        x3 = scanner.nextLong();
        y3 = scanner.nextLong();
        long step = 0;
        //计算(x1,y1)和(x2,y2)之间的距离
        step += Math.min(Math.abs(x1-x2),n-Math.abs(x1-x2));
        step += Math.min(Math.abs(y1-y2),m-Math.abs(y1-y2));
        //计算(x2,y2)和(x3,y3)的距离
        step += Math.min(Math.abs(x2-x3),n-Math.abs(x2-x3));
        step += Math.min(Math.abs(y2-y3),m-Math.abs(y2-y3));
        System.out.println(step);
    }
}
