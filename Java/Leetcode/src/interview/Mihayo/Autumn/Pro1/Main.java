package interview.Mihayo.Autumn.Pro1;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int t = scanner.nextInt();
        int cnt = 0;
        while(t-- > 0) {
            int n = scanner.nextInt();
            scanner.nextLine();
            String qiuqiu = scanner.nextLine();
            String miXY = scanner.nextLine();
            String[] qiqiuWords = qiuqiu.split(" ");
            String[] miXYWords = miXY.split(" ");
            int score = 0;
            for(int i = 0;i<n;i++){
                if(qiqiuWords[i].equals(miXYWords[i])) {
                    score++;
                } else {
                    score--;
                    if(score<0){
                        break;
                    }
                }
            }
            if (score >= 0) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
