package Pro6161;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.removeStars("leet**cod*e");
        System.out.println(s);
    }
}

class Solution {
    public String removeStars(String s) {
        Stack<Character> ans = new Stack<>();
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i)!='*'){
                ans.add(s.charAt(i));
            }else{
                ans.pop();
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(char c:ans){
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}
