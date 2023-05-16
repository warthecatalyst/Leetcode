//
// Created by 18317 on 2023/4/19.
//

#ifndef LEETCODE_P1043_H
#define LEETCODE_P1043_H

#include "global.h"

namespace p1043 {
class Solution {
public:
    int maxSumAfterPartitioning(vector<int>& arr, int k) {
        int n = arr.size();
        vector<int> dp(n);
        int maxNum = 0;
        for (int i=0; i<n; i++) {
            if (i<k) {
                maxNum = max(maxNum,arr[i]);
                dp[i] = maxNum * (i+1);
            } else {
                maxNum = 0;
                for (int j = i;j>i-k;j--) {
                    maxNum = max(maxNum,arr[j]);
                    dp[i] = max(dp[i],dp[j-1] + maxNum * (i-j+1));
                }
            }
        }
        return dp[n-1];
    }
};

} // p1043

#endif //LEETCODE_P1043_H
