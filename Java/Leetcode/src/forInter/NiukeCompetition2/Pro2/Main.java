package forInter.NiukeCompetition2.Pro2;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int n = scanner.nextInt();
        int[] nums = new int[n];
        int res = n+1;
        boolean hasAnswer = false;
        for(int i = 0;i<n;i++){
            nums[i] = scanner.nextInt();
            if(nums[i]==0){
                res--;
            }
        }
        for(int i = 0;i<n;i++){
            if(nums[i]>1){
                hasAnswer = true;
                break;
            }
        }
        if(!hasAnswer){
            System.out.println(-1);
        }else{
            System.out.println(res);
        }
    }
}
