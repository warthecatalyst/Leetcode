package Pro899;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public String orderlyQueue(String s, int k) {
        if(k==1){
            String minans = s;
            StringBuilder stringBuilder = new StringBuilder(s);
            for(int i = 1;i<s.length();i++){
                char ch = stringBuilder.charAt(0);
                stringBuilder.deleteCharAt(0);
                stringBuilder.append(ch);
                if(stringBuilder.toString().compareTo(minans)<0){
                    minans = stringBuilder.toString();
                }
            }
            return minans;
        }else{
            char[] cArray = s.toCharArray();
            Arrays.sort(cArray);
            return new String(cArray);
        }
    }
}
