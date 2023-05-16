package Pro1023;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>();
        for(String query:queries){
            ans.add(isMatch(query,pattern));
        }
        return ans;
    }

    boolean isMatch(String query,String pattern){
        int i = 0,j = 0;
        int n = query.length(), m = pattern.length();
        while(i<n&&j<m){
            if(query.charAt(i)==pattern.charAt(j)){
                i++;
                j++;
            }else if(Character.isLowerCase(query.charAt(i))){
                i++;
            }else{
                return false;
            }
        }
        if(j != m){
            return false;
        }
        while(i<n){
            if(Character.isLowerCase(query.charAt(i))){
                i++;
            }else{
                return false;
            }
        }
        return true;
    }
}

