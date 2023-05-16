//
// Created by 18317 on 2023/4/13.
//

#ifndef LEETCODE_PRO7_H
#define LEETCODE_PRO7_H

#include "global.h"

namespace Pro7 {

class Solution {
public:
    int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < INT_MIN / 10 || rev > INT_MAX / 10) {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }
};

} // Pro7

#endif //LEETCODE_PRO7_H
