//
// Created by smile on 2024/1/5.
//

#ifndef LEETCODE_PRO1944_H
#define LEETCODE_PRO1944_H

#include "global.h"

namespace p1944{
class Solution {
public:
    vector<int> canSeePersonsCount(vector<int>& heights) {
        int n = heights.size();
        vector<int> stack;
        vector<int> res(n, 0);

        for(int i = n - 1;i >= 0; i--) {
            int h = heights[i];
            while(!stack.empty() && stack.back() < h) {
                stack.pop_back();
                res[i]++;
            }
            if (!stack.empty()) {
                res[i]++;
            }
            stack.push_back(h);
        }
        return res;
    }
};
}


#endif //LEETCODE_PRO1944_H
