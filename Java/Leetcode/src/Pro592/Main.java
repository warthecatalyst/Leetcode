package Pro592;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String ans = solution.fractionAddition("1/3-1/2");
        System.out.println(ans);
    }
}

class Solution {
    public String fractionAddition(String expression) {
        StringBuilder express = new StringBuilder();
        String sum = "0";
        for(int i = 0;i<expression.length();i++){
            char c = expression.charAt(i);
            if(c=='-'){
                express.append("+-");
            }else{
                express.append(c);
            }
        }
        //System.out.println(express);
        String[] numbers = express.toString().split("\\+");
        //System.out.println(Arrays.toString(numbers));
        for(String number:numbers){
            if(number.isEmpty()){
                continue;
            }
            sum = stringAdd(sum,number);
        }
        return sum;
    }

    public String stringAdd(String sum,String number){
        if(sum.equals("0")){
            return number;
        }
        String[] s1 = sum.split("/"),s2 = number.split("/");
        //System.out.println(Arrays.toString(s1)+"----"+Arrays.toString(s2));
        int s1top = Integer.parseInt(s1[0]),s1bottom = Integer.parseInt(s1[1]);
        int s2top = Integer.parseInt(s2[0]),s2bottom = Integer.parseInt(s2[1]);
        int bottom = s2bottom*s1bottom;
        int top = s1top*s2bottom+s1bottom*s2top;
        boolean flag = top<0;
        top = Math.abs(top);
        int newtop = top/gcd(Math.min(top,bottom),Math.max(top,bottom));
        int newbottom = bottom/gcd(Math.min(top,bottom),Math.max(top,bottom));
        if(flag){
            return "-"+newtop+"/"+newbottom;
        }
        return newtop+"/"+newbottom;
    }

    int gcd(int a,int b){
        if(a>0){
            return gcd(b%a,a);
        }
        return b;
    }
}
