//
// Created by Admin on 2024/7/16.
//

#ifndef LEETCODE_P2956_H
#define LEETCODE_P2956_H
#include "global.h"

namespace p2956 {
class Solution {
public:
    vector<int> findIntersectionValues(vector<int>& nums1, vector<int>& nums2) {
        bool bucket1[101], bucket2[101];
        for (const auto& n : nums1) {
            bucket1[n] = true;
        }
        for (const auto& n : nums2) {
            bucket2[n] = true;
        }
        vector<int> res(2, 0);
        for (const auto& n : nums1) {
            res[0] += bucket2[n];
        }
        for (const auto& n : nums2) {
            res[1] += bucket1[n];
        }
        return res;
    }
};
}


#endif //LEETCODE_P2956_H
