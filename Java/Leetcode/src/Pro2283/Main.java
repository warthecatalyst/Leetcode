package Pro2283;

import java.util.*;
public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public boolean digitCount(String num) {
        Map<Integer,Integer> digitsCnt = new HashMap<>();
        for(char c:num.toCharArray()){
            digitsCnt.put(c-'0',digitsCnt.getOrDefault(c-'0',0)+1);
        }
        for(int i = 0;i<num.length();i++){
            int curCnt = digitsCnt.getOrDefault(i,0);
            if(curCnt!=num.charAt(i)-'0'){
                return false;
            }
        }
        return true;
    }
}
