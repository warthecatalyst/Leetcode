//
// Created by 周鑫宜 on 2023/10/3.
//

#ifndef LEETCODE_PRO123_H
#define LEETCODE_PRO123_H
#include "global.h"

namespace P123 {
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            buy1 = max(buy1, -prices[i]);
            sell1 = max(sell1, buy1 + prices[i]);
            buy2 = max(buy2, sell1 - prices[i]);
            sell2 = max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }
};
} // end namespaceP123

#endif //LEETCODE_PRO123_H
