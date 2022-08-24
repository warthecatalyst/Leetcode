package pro1460;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        Map<Integer,Integer> arrMap = new HashMap<>();
        Map<Integer,Integer> tarMap = new HashMap<>();
        for(int num:target){
            tarMap.put(num,tarMap.getOrDefault(num,0)+1);
        }
        for(int num:arr){
            arrMap.put(num,arrMap.getOrDefault(num,0)+1);
        }
        for(Map.Entry<Integer,Integer> entry: tarMap.entrySet()){
            int val = arrMap.getOrDefault(entry.getKey(),0);
            if(val!=entry.getValue()){
                return false;
            }
        }
        return true;
    }
}
