package Pro22;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}


class Solution {
    List<String> ans;
    public List<String> generateParenthesis(int n) {
        ans = new ArrayList<>();
        if(n==0){
            return ans;
        }
        DFS(n,n,new StringBuilder());
        return ans;
    }

    public void DFS(int left,int right,StringBuilder curStr){
        if(left==0&&right==0){
            ans.add(curStr.toString());
            return;
        }
        if(left>right){
            return;
        }
        if(left>0){
            curStr.append('(');
            DFS(left-1,right,curStr);
            curStr.deleteCharAt(curStr.length()-1);
        }
        if(right>0){
            curStr.append(')');
            DFS(left,right-1,curStr);
            curStr.deleteCharAt(curStr.length()-1);
        }
    }
}