package interview.tencent.Pro4;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int N = scanner.nextInt();
        int[] A = new int[N];
        for(int i = 0;i<N;i++){
            A[i] = scanner.nextInt();
        }
        int ans = N;
        for(int i = 0;i<N-2;i++){
            int cnt1 = A[i]==1?1:0,cntOthers = A[i]==1?0:1;
            cnt1 += A[i+1]==1?1:0;cntOthers += A[i+1]==1?0:1;
            for(int j = i+2;j<N;j++){
                cnt1 += A[j]==1?1:0;
                cntOthers += A[j]==1?0:1;
                //System.out.println("cnt1 = "+cnt1+",cntOthers = "+cntOthers);
                if(cnt1>0&&cnt1%2==0&&cntOthers==1){
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }

    static boolean isValid(int cnt1,int cntOthers){
        return cnt1>0&&cnt1%2==0&&cntOthers==1;
    }
}
