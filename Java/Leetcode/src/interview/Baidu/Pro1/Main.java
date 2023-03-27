package interview.Baidu.Pro1;

import java.util.*;

public class Main {
    static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while(t-->0){
            String str = scanner.next();
            if(str.length()!=5){
                System.out.println("No");
            }else{
                Set<Character> set = new HashSet<>();
                for(char c:str.toCharArray()){
                    set.add(c);
                }
                if(set.contains('B')&&set.contains('a')&&set.contains('i')&&set.contains('d')&&set.contains('u')){
                    System.out.println("Yes");
                }else{
                    System.out.println("No");
                }
            }
        }
    }
}
