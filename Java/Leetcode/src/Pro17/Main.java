package Pro17;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    List<String> ans;
    String[] digitToChars = new String[]{"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    public List<String> letterCombinations(String digits) {
        if(digits.isEmpty()){
            return new ArrayList<>();
        }
        ans = new ArrayList<>();
        dfs(0,digits.toCharArray(),new StringBuilder());
        return ans;
    }

    public void dfs(int index, char[] digits,StringBuilder cur){
        if(index >= digits.length){
            ans.add(cur.toString());
            return;
        }
        String chars = digitToChars[digits[index]-'2'];
        for(char c:chars.toCharArray()){
            cur.append(c);
            dfs(index+1,digits,cur);
            cur.deleteCharAt(cur.length()-1);
        }
    }
}