//
// Created by 18317 on 2023/4/13.
//

#ifndef LEETCODE_PRO5_H
#define LEETCODE_PRO5_H

#include "global.h"

namespace Pro5 {

class Solution {
public:
    string longestPalindrome(string s) {
        int n = s.size();
        if(n<2){
            return s;
        }
        int maxLen = 1;
        int begin = 0;

        vector<vector<bool>> dp(n,vector<bool>(n));
        for(int i = 0;i<n;i++){
            dp[i][i] = true;
        }
        for (int L = 2; L <= n; L++) {
            for (int i = 0; i < n; i++) {
                int j = L + i - 1;
                if (j >= n) {
                    break;
                }

                if (s[i] != s[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substr(begin, maxLen);
    }
};
} // Pro5

#endif //LEETCODE_PRO5_H
