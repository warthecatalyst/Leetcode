//
// Created by 周鑫宜 on 2023/10/12.
//

#ifndef LEETCODE_PRO2562_H
#define LEETCODE_PRO2562_H
#include "global.h"

class Solution {
public:
    long long findTheArrayConcVal(vector<int>& nums) {
        long long ans = 0;
        int n = nums.size();
        for (int i = 0; i < n / 2; i++) {
            string temp = to_string(nums[i]) + to_string(nums[n - 1 - i]);
            ans += stoll(temp);
        }
        if (n % 2 == 1) {
            ans += nums[n/2];
        }
        return ans;
    }
};

#endif //LEETCODE_PRO2562_H
