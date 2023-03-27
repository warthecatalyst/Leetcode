package interview.Meituan.Pro3;

import java.util.*;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String str = scanner.next();
        int len = str.length();
        int[] diff = getPalidrome(str);
        StringBuilder finalAns = new StringBuilder(str);
        if(diff[0]==-1&&diff[1]==-1){
            //将第一个不是'a'的地方换成'a'
            int curChanged = 0;
            for(int i = 0;i<len/2;i++){
                if(str.charAt(i)!='a'){
                    curChanged = i;
                    break;
                }
            }
            finalAns.setCharAt(curChanged,'a');
            finalAns.setCharAt(len-1-curChanged,'a');
        }else if(diff[1]==-1){
            //一个地方不一样两边都换成'a'
            finalAns.setCharAt(diff[0],'a');
            finalAns.setCharAt(len-1-diff[0],'a');
            if(str.charAt(diff[0])=='a'||str.charAt(len-1-diff[0])=='a'){
                if(len%2==1){
                    finalAns.setCharAt(len/2,'a');
                }
            }
        }else{
            char char0 = str.charAt(diff[0]),char1 = str.charAt(len-1-diff[0]);
            char char2 = str.charAt(diff[1]),char3 = str.charAt(len-1-diff[1]);
            char c0 = (char) Math.min(char0,char1);
            char c1 = (char) Math.min(char2,char3);
            finalAns.setCharAt(diff[0],c0);
            finalAns.setCharAt(len-1-diff[0],c0);
            finalAns.setCharAt(diff[1],c1);
            finalAns.setCharAt(len-1-diff[1],c1);
        }
        System.out.println(finalAns);
    }

    // 判断有几个地方不一样,如果没有地方不一样的话,将第一个不是'a'的地方换成'a',一个地方不一样的话,
    static int[] getPalidrome(String str){
        int n = str.length();
        int[] ans = new int[]{-1,-1};
        int curChanged = 0;
        for(int i = 0;i<n/2&&curChanged<2;i++){
            if(str.charAt(i)!=str.charAt(n-i-1)){
                ans[curChanged] = i;
                curChanged++;
            }
        }
        return ans;
    }
}
