package interview.Meituan.Pro4;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int N = scanner.nextInt(), X = scanner.nextInt(), Y = scanner.nextInt();    //N个商品,X元,Y张券
        int[][] stocks = new int[N][];
        for(int i = 0;i<N;i++){
            int originPrice = scanner.nextInt(), discountPrice = scanner.nextInt();
            stocks[i] = new int[]{originPrice,discountPrice};
        }
        int[][][] dp = new int[N+1][X+1][Y+1];  //dp[i][j][k]表示到第i个商品，花了j元k张消费券，买了多少商品
        for(int i = 0;i<N;i++){
            int[] stock = stocks[i];
            for(int j = 0;j<=X;j++){
                for(int k = 0;k<=Y;k++){
                    dp[i+1][j][k] = Math.max(dp[i+1][j][k],dp[i][j][k]);
                    if(j>=stock[0]){
                        dp[i+1][j][k] = Math.max(dp[i+1][j][k],dp[i][j-stock[0]][k]+1);
                    }
                }
            }for(int j = 0;j<=X;j++){
                for(int k = 1;k<=Y;k++){
                    dp[i+1][j][k] = Math.max(dp[i+1][j][k],dp[i][j][k]);
                    if(j>=stock[1]){
                        dp[i+1][j][k] = Math.max(dp[i+1][j][k],dp[i][j-stock[1]][k-1]+1);
                    }
                }
            }
//            for(int j = 0;j<=X;j++){
//                System.out.println("i = "+i+", j = "+j +" , dp[][][k] = "+Arrays.toString(dp[i+1][j]));
//            }
        }

        int ans0 = 0,minMoney = X;
        for(int j=X;j>=0;j--){
            for(int k=Y;k>=0;k--){
                if(dp[N][j][k]>ans0){
                    ans0 = dp[N][j][k];
                    minMoney = j;
                }else if(dp[N][j][k]==ans0&&j<minMoney){
                    ans0 = dp[N][j][k];
                    minMoney = j;
                }
            }
        }
        System.out.println(ans0+" "+minMoney);
    }
}
