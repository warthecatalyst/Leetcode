//
// Created by 18317 on 2023/4/21.
//

#ifndef LEETCODE_PRO9_H
#define LEETCODE_PRO9_H

#include "global.h"

namespace pro9 {

class Solution {
public:
    bool isPalindrome(int x) {
        if(x<0){
            return false;
        }
        int originX = x;
        int revx = 0;
        while(x>0){
            if(revx>INT_MAX/10){
                return false;
            }
            revx = revx*10+x%10;
            x/=10;
        }
        return originX==revx;
    }
};

} // pro9

#endif //LEETCODE_PRO9_H
