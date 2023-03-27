//
// Created by 18317 on 2023/3/12.
//

#ifndef LEETCODE_DYNAMICPROGRAMMING_H
#define LEETCODE_DYNAMICPROGRAMMING_H
#include "global.h"

class DynamicProgramming {
public:
    struct Xiecheng{
        /*  携程DP题
            6 11
            2 3 4 8 1 2
            2 5 7 2 3 2
            6是商品个数，11是金额
            第二排的6个数是 商品的价值
            第三排的6个数是 用户对这个商品感兴趣的程度
            然后买一个商品，这个商品右边的一个商品就可以半价购买，（你也可以选择坚持原价购买，那么就递推到隔壁的商品半价）
            让你用11的金额，去买商品，最终让用户买到的商品 感兴趣程度的总额最大
            输出：最终买到的商品感兴趣程度最高
         */
        int buyStockings(int n,int total,const vector<int>& values,const vector<int>& interests){
            vector<vector<vector<int>>> dp(n,vector<vector<int>>(total+1,vector<int>(3,0))); //n个商品，total的钱，0没买，1全价买，2半价买
            //处理第一个商品
            dp[0][0][0] = 0;
            dp[0][values[0]][1] = interests[0];
            cout << dp[0][0][0] << " , " << dp[0][values[0]][1] << endl;
            //处理后续
            for(int i = 1;i<n;i++){
                for(int j = values[i];j<=total;j++){
                    //不买当前这个,dp[i][j][0] = max(dp[i-1][j][0-2])
                    dp[i][j][0] = std::max(dp[i-1][j][0],dp[i-1][j][1]);
                    dp[i][j][0] = std::max(dp[i][j][0],dp[i-1][j][2]);
                    //全款买当前这个，半价买或者全款买
                    dp[i][j][1] = std::max(dp[i-1][j-values[i]][0],dp[i-1][j-values[i]][1])+interests[i];
                    dp[i][j][1] = std::max(dp[i][j][1],dp[i-1][j-values[i]][2]+interests[i]);
                    //半价买当前的
                    dp[i][j][2] = dp[i-1][j-values[i]/2][1]+interests[i];

                    cout << "i,j = " << i << " , " << j << " , ans = " << dp[i][j][0] << "," << dp[i][j][1] << "," << dp[i][j][2] << endl;
                }
            }
            int maxVal = 0;
            for(int i = 0;i<=total;i++){
                maxVal = max(maxVal,dp[n-1][i][0]);
                maxVal = max(maxVal,dp[n-1][i][1]);
                maxVal = max(maxVal,dp[n-1][i][2]);
            }
            return maxVal;
        }
    };
};


#endif //LEETCODE_DYNAMICPROGRAMMING_H
