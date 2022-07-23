package pro1200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5,7,9};
        Solution solution = new Solution();
        System.out.println(solution.minimumAbsDifference(arr));
    }
}

class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> list = new ArrayList<>();
        int lowest = Integer.MAX_VALUE;
        for(int i = 0;i<arr.length-1;i++){
            int numi = arr[i],numi_1 = arr[i+1];
            if(numi_1-numi<lowest){
                lowest = numi_1-numi;
                list.clear();
                List<Integer> cur = new ArrayList<>();
                cur.add(numi);
                cur.add(numi_1);
                list.add(cur);
            }else if(numi_1-numi==lowest){
                List<Integer> cur = new ArrayList<>();
                cur.add(numi);
                cur.add(numi_1);
                list.add(cur);
            }
        }
        return list;
    }
}
