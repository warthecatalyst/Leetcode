package interview.Mayi.Pro1;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String str = scanner.next();
        int cntA = 0;
        int half = str.length()/2;
        for(char c:str.toCharArray()){
            if(c=='a'){
                cntA++;
            }
        }
        int ans = Math.abs(half-cntA);
        System.out.println(ans);
    }
}
