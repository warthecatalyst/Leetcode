//
// Created by 周鑫宜 on 2023/10/22.
//

#ifndef LEETCODE_PRO72_H
#define LEETCODE_PRO72_H
#include "global.h"

class Solution {
public:
    int minDistance(string word1, string word2) {
        int n = word1.size(), m = word2.size();
        if(n*m == 0) return n + m;
        vector<vector<int>> dp(n+1, vector<int>(m+1, 0));
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for(int j = 0; j <=m; j++) {
            dp[0][j] = j;
        }
        for(int i = 1;i <= n; i++) {
            for(int j = 1;j <= m; j++) {
                int left = dp[i-1][j] + 1;
                int down = dp[i][j-1] + 1;
                int left_down = dp[i-1][j-1];
                if (word1[i-1] != word2[j-1]) left_down++;
                dp[i][j] = min(left, min(down, left_down));
            }
        }
        return dp[n][m];
    }
};

#endif //LEETCODE_PRO72_H
