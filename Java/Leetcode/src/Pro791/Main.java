package Pro791;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public String customSortString(String order, String s) {
        Map<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<order.length();i++){
            map.put(order.charAt(i),i);
        }
        List<Character> list = new ArrayList<>();
        for(char c:s.toCharArray()){
            list.add(c);
        }
        list.sort(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return map.getOrDefault(o1,100)-map.getOrDefault(o2,100);
            }
        });
        StringBuilder sb = new StringBuilder();
        for(char c:list){
            sb.append(c);
        }
        return sb.toString();
    }
}
