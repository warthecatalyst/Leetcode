package Pro1331;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.arrayRankTransform(new int[]{4,1,2,3,3});
    }
}

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        Set<Integer> set = new TreeSet<>();
        for(int num:arr){
            set.add(num);
        }
        int[] arrIndex = new int[arr.length];
        Map<Integer,Integer> mp = new HashMap<>();
        int idx = 1;
        for(int num:set){
            mp.put(num,idx++);
        }
        for(int i = 0;i<arr.length;i++){
            arrIndex[i] = mp.get(arr[i]);
        }
        return arrIndex;
    }
}
