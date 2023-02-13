//
// Created by 18317 on 2023/2/13.
//

#ifndef LEETCODE_PRO1138_H
#define LEETCODE_PRO1138_H
#include "global.h"

namespace p1138{
class Solution {
public:
    string alphabetBoardPath(string target) {
        string res;
        char cur = 'a';
        for(const char& des:target){
            if('z' == des){
                int nx = (cur-'a')/5;       //cur在第几行
                int ny = (cur-'a')%5;       //cur在第几列
                res.append(ny,'L');
                res.append(5-nx,'D');
            }else{
                int nx = (cur-'a')/5;   //cur在第几行
                int ny = (cur-'a')%5;   //cur在第几列
                int dx = (des-'a')/5;   //des在第几行
                int dy = (des-'a')%5;   //des在第几列
                if(dx>=nx){ //向下
                    res.append(dx-nx,'D');
                }else{  //向上
                    res.append(nx-dx,'U');
                }
                if(dy>=ny){ //向右
                    res.append(dy-ny,'R');
                }else{  //向左
                    res.append(ny-dy,'L');
                }
            }
            res.push_back('!');
            cur = des;
        }
        return res;
    }
};
}

#endif //LEETCODE_PRO1138_H
