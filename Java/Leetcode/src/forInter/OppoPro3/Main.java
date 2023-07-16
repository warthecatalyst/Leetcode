package forInter.OppoPro3;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int n,k;
    public static void main(String[] args) {
        n = scanner.nextInt();
        k = scanner.nextInt();
        int[] nums = new int[n];
        for(int i = 0;i<n;i++){
            nums[i] = scanner.nextInt();
        }
        //滑动窗口
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<k;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        int bar = k/2;
        if(k%2==1){
            bar++;
        }
        int cnt = 0;
        for(int i = 0;i<n-k;i++){
            if(checkMap(map,bar)){
                cnt++;
            }
            map.put(nums[i], map.get(nums[i])-1);
            if(map.get(nums[i])==0){
                map.remove(nums[i]);
            }
            map.put(nums[i+k], map.getOrDefault(nums[i+k], 0)+1);
        }
        cnt += checkMap(map,bar) ? 1:0;
        System.out.println(cnt);
    }

    private static boolean checkMap(Map<Integer,Integer> map,int bar){
        if(map.keySet().size()>k-bar+1){
            return false;
        }
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            if(entry.getValue()>=bar){
                return true;
            }
        }
        return false;
    }
}
