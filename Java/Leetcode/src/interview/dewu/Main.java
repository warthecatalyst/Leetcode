package interview.dewu;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int n = scanner.nextInt(), x = scanner.nextInt();
        String s = scanner.next();
        boolean flag = false;
        for (int i = 0; i <= n-x; i++) {
            int left = 0, right = i+x-1;
            while (left < right) {
                if(s.charAt(left) == s.charAt(right)) {
                    left++;
                    right--;
                } else {
                    break;
                }
            }
            if (left >= right) {
                System.out.println("i = " + i);
                flag = true;
                break;
            }
        }
        if (flag) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
