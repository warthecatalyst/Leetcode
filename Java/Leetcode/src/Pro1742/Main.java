package Pro1742;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int countBalls(int lowLimit, int highLimit) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = lowLimit;i<=highLimit;i++){
            int res = countNum(i);
            map.put(res,map.getOrDefault(res,0)+1);
        }
        int maxNum = Integer.MIN_VALUE;
        int maxI = 0;
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            if(entry.getValue()>maxNum){
                maxNum = entry.getValue();
                maxI = entry.getKey();
            }
        }
        return maxI;
    }

    public int countNum(int n){
        int res = 0;
        while(n>0){
            res += n%10;
            n/=10;
        }
        return res;
    }
}
