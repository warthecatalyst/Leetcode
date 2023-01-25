package Pro6293;


import java.util.*;
import java.util.jar.JarEntry;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    int n;
    public long countGood(int[] nums, int k) {
        n = nums.length;
        int start = 0, end = 0;
        Set<Long> hashSet = new HashSet<>();
        long res = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(nums[start],1);
        while(start<n){
            if(getGoodPair(map)>=k){
                for(int i = 0;i<=start;i++){
                    for(int j = end;j<n;j++){
                        long hash = getHash(i,j);
                        if(hashSet.contains(hash)){
                            continue;
                        }
                        hashSet.add(hash);
                        res++;
                    }
                }
                map.put(nums[start],map.get(nums[start])-1);
                start++;
            }else{
                if(end<n-1){
                    end++;
                    map.put(nums[end],map.getOrDefault(nums[end],0)+1);
                }else{
                    break;
                }
            }
        }
        return res;
    }

    public int getGoodPair(Map<Integer,Integer> map){
        int res = 0;
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            int val = entry.getValue();
            res += val*(val-1)/2;
        }
        return res;
    }

    long getHash(int x,int y){
        return (long) x *n+y;
    }
}
