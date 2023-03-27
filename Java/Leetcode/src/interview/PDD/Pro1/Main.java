package interview.PDD.Pro1;

import java.util.*;

public class Main {
    static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        String str = scanner.next();
        int cur = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<str.length();i++){
            if(Character.isDigit(str.charAt(i))){
                cur = cur*10+str.charAt(i)-'0';
            }else{
                char c = str.charAt(i);
                for(int j = 0;j<cur;j++){
                    sb.append(c);
                }
                cur = 0;
            }
        }
        System.out.println(sb);
    }
}
