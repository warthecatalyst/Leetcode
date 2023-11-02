//
// Created by 周鑫宜 on 2023/10/22.
//

#ifndef LEETCODE_PRO1402_H
#define LEETCODE_PRO1402_H
#include "global.h"

class Solution {
public:
    int maxSatisfaction(vector<int>& satisfaction) {
        int n = satisfaction.size();
        vector<vector<int>> dp(n+1, vector<int>(n+1));
        sort(satisfaction.begin(), satisfaction.end());
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = dp[i - 1][j - 1] + satisfaction[i - 1] * j;
                if (j < i) {
                    dp[i][j] = max(dp[i - 1][j], dp[i][j]);
                }
                res = max(res, dp[i][j]);
            }
        }
        return res;
    }
};

#endif //LEETCODE_PRO1402_H
