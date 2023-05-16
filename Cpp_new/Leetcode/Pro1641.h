//
// Created by 18317 on 2023/3/29.
//

#ifndef LEETCODE_PRO1641_H
#define LEETCODE_PRO1641_H
#include "global.h"
namespace p1641{
class Solution {
public:
    int countVowelStrings(int n) {
        vector<int> dp(5, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < 5; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return accumulate(dp.begin(), dp.end(), 0);
    }
};
}


#endif //LEETCODE_PRO1641_H
