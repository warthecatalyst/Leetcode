package forInter.NiukeCompetition.Pro4;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int q;
    public static void main(String[] args) {
        q = scanner.nextInt();
        while(q-->0){
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            char[][] ans = new char[n][m];
            for(int i = 0;i<n;i++){
                Arrays.fill(ans[i],'.');
            }
            if(n<5&&m<5){
                for(int i = 0;i<n;i++){
                    System.out.println(ans[i]);
                }
            }else if(n<5){
                for(int i = 0;i<n;i++){
                    for(int j = 0;j<m;j++){
                        if(j%5==4){
                            ans[i][j] = 'o';
                        }
                    }
                }
                for(int i = 0;i<n;i++){
                    System.out.println(ans[i]);
                }
            }else if(m<5){
                for(int i = 0;i<n;i++){
                    if(i%5==4){
                        for(int j = 0;j<m;j++){
                            ans[i][j] = 'o';
                        }
                    }
                }
            }
        }
    }
}
