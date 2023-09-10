package interview.Meituan.Autumn.Pro1;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for(int i = 0;i<n;i++){
            nums[i] = scanner.nextInt();
        }
        int x = scanner.nextInt(), y = scanner.nextInt();
        boolean isNear = false;
        for(int i = 0;i < n - 1;i++){
            if (nums[i] == x && nums[i + 1] == y) {
                isNear = true;
                break;
            } else if (nums[i] == y && nums[i+1] == x) {
                isNear = true;
                break;
            }
        }
        if (isNear) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
