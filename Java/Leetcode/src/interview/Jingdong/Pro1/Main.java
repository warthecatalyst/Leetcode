package interview.Jingdong.Pro1;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int n = scanner.nextInt();
        String str = scanner.next();
        StringBuilder stringBuilder = new StringBuilder(str);
        int ans = 0;
        int idx;
        while(chooseOne(stringBuilder)) {
            ans++;
            char c = stringBuilder.charAt(0);
            stringBuilder = new StringBuilder(stringBuilder.substring(1) + c);
            //System.out.println(stringBuilder);
        }
        while((idx = isPalidrome(stringBuilder))!=-1) {
            //System.out.println(idx);
            ans++;
            char rev = stringBuilder.charAt(n - 1 - idx);
            stringBuilder.setCharAt(idx, rev);
            //System.out.println(stringBuilder);
        }
        System.out.println(ans);
    }

    static int isPalidrome(StringBuilder str) {
        int left = 0, right = str.length()-1;
        while(left < right) {
            if(str.charAt(left) != str.charAt(right)) {
                return left;
            }
            left++;
            right--;
        }
        return -1;
    }

    static boolean chooseOne(StringBuilder stringBuilder) {
        int n = stringBuilder.length();
        char c = stringBuilder.charAt(0);
        int leftCount = 1,rightCount = 0;
        for(int i = 1;i<n/2;i++) {
            if(stringBuilder.charAt(i) == c) {
                leftCount++;
            }
        }
        for(int i = n-1;i>=n/2;i--) {
            if(stringBuilder.charAt(i) == c) {
                rightCount++;
            }
        }
        return (leftCount-rightCount) >= 2;
    }
}
