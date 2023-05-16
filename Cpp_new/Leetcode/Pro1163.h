//
// Created by 18317 on 2023/4/24.
//

#ifndef LEETCODE_PRO1163_H
#define LEETCODE_PRO1163_H

#include "global.h"

namespace pro1163 {
class Solution {
public:
    string lastSubstring(string s) {
        int i = 0, j = 1, n = s.size();
        while (j < n) {
            int k = 0;
            while (j + k < n && s[i + k] == s[j + k]) {
                k++;
            }
            if (j + k < n && s[i + k] < s[j + k]) {
                int t = i;
                i = j;
                j = max(j + 1, t + k + 1);
            } else {
                j = j + k + 1;
            }
        }
        return s.substr(i, n - i);
    }
};
} // pro1163

#endif //LEETCODE_PRO1163_H
