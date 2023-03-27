//
// Created by 18317 on 2023/3/18.
//

#ifndef LEETCODE_SOLUTION_H
#define LEETCODE_SOLUTION_H
#include "global.h"

namespace P1616 {
class Solution {
public:
    bool checkPalindromeFormation(string a, string b) {
        return checkConcatenation(a,b)|| checkConcatenation(b,a);
    }

    bool checkConcatenation(const string& a,const string& b){
        int n = a.size();
        int left = 0, right = n-1;
        while(left<right&&a[left]==b[right]){
            left++;
            right--;
        }
        if(left>=right){
            return true;
        }
        return checkSelfPalidrome(a,left,right) || checkSelfPalidrome(b,left,right);
    }

    bool checkSelfPalidrome(const string& a,int left,int right){
        while(left<right&&a[left]==a[right]){
            left++;
            right--;
        }
        return left>=right;
    }
};
} // P1616

#endif //LEETCODE_SOLUTION_H
