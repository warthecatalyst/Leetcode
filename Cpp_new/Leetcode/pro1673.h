//
// Created by 18317 on 2023/4/24.
//

#ifndef LEETCODE_PRO1673_H
#define LEETCODE_PRO1673_H

#include "global.h"

namespace pro1673 {

class Solution {
public:
    vector<int> mostCompetitive(vector<int>& nums, int k) {
        vector<int> stk;
        int n = nums.size();
        int last = n - k;
        for (int i = 0; i < n; i ++)
        {
            while (!stk.empty() && last > 0 && stk.back() > nums[i])
            {
                stk.pop_back();
                last --;
            }
            stk.push_back(nums[i]);
        }
        for (int i = 0; i < last; i ++)
        {
            stk.pop_back();
        }
        return stk;
    }
};

} // pro1673

#endif //LEETCODE_PRO1673_H
