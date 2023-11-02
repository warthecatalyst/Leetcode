//
// Created by 周鑫宜 on 2023/10/23.
//

#ifndef LEETCODE_PRO2678_H
#define LEETCODE_PRO2678_H
#include "global.h"

class Solution {
public:
    int countSeniors(vector<string>& details) {
        int ans = 0;
        for(const auto& str: details) {
            if(stoi(str.substr(11,2)) > 60 ) {
                ans++;
            }
        }
        return ans;
    }
};

#endif //LEETCODE_PRO2678_H
