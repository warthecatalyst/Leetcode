package Pro2347;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public String bestHand(int[] ranks, char[] suits) {
        char suit0 = suits[0];
        if(suit0 == suits[1] && suit0 == suits[2] && suit0 == suits[3] && suit0 == suits[4]){
            return "Flush";
        }

        Map<Integer,Integer> cnt = new HashMap<>();
        for(int rank : ranks){
            cnt.put(rank, cnt.getOrDefault(rank,0)+1);
        }
        int largest = 0;
        for(Map.Entry<Integer,Integer> entry:cnt.entrySet()){
            largest = Math.max(largest,entry.getValue());
        }
        if(largest >= 3){
            return "Three of a Kind";
        }else if(largest == 2){
            return "Pair";
        }else{
            return "High Card";
        }
    }
}
