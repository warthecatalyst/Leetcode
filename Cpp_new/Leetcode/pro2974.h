//
// Created by Admin on 2024/7/12.
//

#ifndef LEETCODE_PRO2974_H
#define LEETCODE_PRO2974_H
#include "global.h"

namespace p2974 {
class Solution {
public:
    vector<int> numberGame(vector<int>& nums) {
        vector<int> ans;
        sort(nums.begin(), nums.end());
        for (int i = 0; i * 2 < nums.size(); i++) {
            ans.push_back(nums[i*2 + 1]);
            ans.push_back(nums[i * 2]);
        }
        return ans;
    }
};
}


#endif //LEETCODE_PRO2974_H
