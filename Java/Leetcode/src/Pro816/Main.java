package Pro816;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> ans = solution.ambiguousCoordinates("(123)");
        for(String as:ans){
            System.out.println(as);
        }
    }
}


class Solution {
    public List<String> ambiguousCoordinates(String s) {
        s = s.substring(1,s.length()-1);
        System.out.println(s);
        List<String> result = new ArrayList<>();
        for(int i = 1;i<s.length();i++){
            System.out.println(s.substring(0,i));
            List<String> left = getPos(s.substring(0,i));
            if(left.isEmpty()){
                continue;
            }
            System.out.println(s.substring(i));
            List<String> right = getPos(s.substring(i));
            if(right.isEmpty()){
                continue;
            }
            for(String lf:left){
                for(String rg:right){
                    result.add("("+lf + ", "+rg+")");
                }
            }
        }
        return result;
    }

    public List<String> getPos(String s){
        List<String> pos = new ArrayList<>();
        if("0".equals(s)||s.charAt(0) != '0'){
            pos.add(s);
        }

        for(int i = 1;i<s.length();i++){
            if ((i != 1 && s.charAt(0) == '0') || s.charAt(s.length() - 1) == '0') {
                continue;
            }
            pos.add(s.substring(0, i) + "." + s.substring(i));
        }
        return pos;
    }
}
