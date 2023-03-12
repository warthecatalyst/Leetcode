package Pro2363;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        Map<Integer,Integer> map = new TreeMap<>();
        for(int[] item:items1){
            int value = item[0], weight = item[1];
            map.put(value,map.getOrDefault(value,0)+weight);
        }
        for(int[] item:items2){
            int value = item[0], weight = item[1];
            map.put(value,map.getOrDefault(value,0)+weight);
        }
        List<List<Integer>> lists = new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            List<Integer> list = new ArrayList<>();
            list.add(entry.getKey());
            list.add(entry.getValue());
            lists.add(list);
        }
        return lists;
    }
}