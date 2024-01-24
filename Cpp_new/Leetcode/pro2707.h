//
// Created by smile on 2024/1/9.
//

#ifndef LEETCODE_PRO2707_H
#define LEETCODE_PRO2707_H
#include "global.h"

namespace p2707{
class Solution {
public:
    int minExtraChar(string s, vector<string>& dictionary) {
        int n = s.size();
        vector<int> dp(n+1, INT32_MAX);
        dp[0] = 0;
        unordered_set<string> dic(dictionary.begin(), dictionary.end());
        for(int i = 1;i <= n;i++) {
            dp[i] = dp[i-1] + 1;
            for(int j = i-1; j >= 0; j--) {
                if(dic.count(s.substr(j, i-j))) {
                    dp[i] = min(dp[i], dp[j]);
                }
            }
        }
        return dp[n];
    }
};
}

#endif //LEETCODE_PRO2707_H
