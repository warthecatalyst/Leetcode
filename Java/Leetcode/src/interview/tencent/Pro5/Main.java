package interview.tencent.Pro5;

import java.util.*;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int n,k;
        n = scanner.nextInt(); k = scanner.nextInt();
        int[] nums = new int[n];
        int tt = 0;
        for(int i = 0;i<n;i++){
            nums[i] = scanner.nextInt();
            if(nums[i]%k==0){
                tt++;
            }
        }

        for(int i = 1;i<=tt;i++){

        }

    }
}
