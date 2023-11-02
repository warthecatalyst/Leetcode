//
// Created by 周鑫宜 on 2023/10/3.
//

#ifndef LEETCODE_PRO122_H
#define LEETCODE_PRO122_H
#include "global.h"

class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int curPrice = prices[0], profit = 0;
        for (int i = 1; i < prices.size(); i++) {
            if (prices[i] > curPrice) {
                profit += prices[i] - curPrice;
            }
            curPrice = prices[i];
        }
        return profit;
    }
};


#endif //LEETCODE_PRO122_H
