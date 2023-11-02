//
// Created by 18317 on 2023/4/24.
//

#ifndef LEETCODE_PRO28_H
#define LEETCODE_PRO28_H

#include "global.h"

namespace pro28 {

class Solution {
public:
    int strStr(string haystack, string needle) {
        if (needle.empty()) return 0;
        int n = haystack.length(), m = needle.length();
        haystack = " " + haystack;
        needle = " " + needle;
        vector<int> next(m+1);
        for(int i = 2, j = 0;i <= m;i++) {
            while (j > 0 && needle[i] != needle[j+1]) j = next[j];
            if (needle[i] == needle[j + 1]) j++;
            next[i] = j;
        }
        for (int i = 1, j = 0; i<=n; i++) {
            while(j && haystack[i] != needle[j+1]) j = next[j];
            if (haystack[i] == needle[j+1]) j++;
            if (j == m) return i - m;
        }
        return -1;
    }
};

} // pro28

#endif //LEETCODE_PRO28_H
