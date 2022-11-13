package Pro1704;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    private char[] chars = {'a','e','i','o','u','A','E','I','O','U'};

    public boolean halvesAreAlike(String s) {
        int n = s.length();
        String leftHalf = s.substring(0,n/2), rightHalf = s.substring(n/2);
        Set<Character> set = new HashSet<>();
        for(char c:chars){
            set.add(c);
        }
        int leftcnt = 0,rightcnt = 0;
        for(int i = 0;i<n/2;i++){
            if(set.contains(leftHalf.charAt(i))){
                leftcnt++;
            }
            if(set.contains(rightHalf.charAt(i))){
                rightcnt++;
            }
        }
        return leftcnt==rightcnt;
    }
}
