package interview.Mihayo.Pro3;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static final int MOD = 1000000007;
    static final int MAX_NUM = 1000000;
    public static void main(String[] args) {
        int N = scanner.nextInt();
        int[] nums = new int[N];
        for(int i = 0;i<N;i++){
            nums[i] = scanner.nextInt();
        }
        Arrays.sort(nums);
        int ans = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<N;i++){
            int num = nums[i];
            int cnt = map.getOrDefault(num,0);
            //System.out.println("num = "+num+" ,cnt = "+cnt);
            ans += (1 <<cnt)-1;
            ans = ans%MOD;
            for(int k = 1;num*k<=MAX_NUM;k++){
                int nnum = k*num;
                map.put(nnum,map.getOrDefault(nnum,0)+1);
            }
        }
        System.out.println(ans);
    }
}
