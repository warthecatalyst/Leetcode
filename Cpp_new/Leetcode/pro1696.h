//
// Created by Admin on 2024/2/5.
//

#ifndef LEETCODE_PRO1696_H
#define LEETCODE_PRO1696_H
#include "global.h"

namespace p1696 {
class Solution {
public:
    int maxResult(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> dp(n, 0);
        dp[0] = nums[0];
        deque<int> queue;
        queue.push_back(0);
        for (int i = 1; i < n; i++) {
            while(!queue.empty() && queue.front() < i-k) {
                queue.pop_front();
            }
            dp[i] = dp[queue.front()] + nums[i];
            while(!queue.empty() && dp[queue.back()] <= dp[i]) {
                queue.pop_back();
            }
            queue.push_back(i);
        }
        return dp[n-1];
    }
};
}

#endif //LEETCODE_PRO1696_H
