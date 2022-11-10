package Pro1684;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        Set<Character> set = new HashSet<>();
        for(char c:allowed.toCharArray()){
            set.add(c);
        }
        int res = 0;
        for(String word:words){
            boolean all_contain = true;
            for(char c:word.toCharArray()){
                if(!set.contains(c)){
                    all_contain = false;
                    break;
                }
            }
            if(all_contain){
                res++;
            }
        }
        return res;
    }
}
