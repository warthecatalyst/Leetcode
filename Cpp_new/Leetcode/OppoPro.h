//
// Created by 18317 on 2023/7/7.
//

#ifndef LEETCODE_OPPOPRO_H
#define LEETCODE_OPPOPRO_H
#include "global.h"

class OppoProblems{
    struct Pro1{
        bool checkMap(const unordered_map<char,int>& unorderedMap){
            for(const auto &it:unorderedMap){
                if(it.second%2==1){
                    return false;
                }
            }
            return true;
        }

        int main(){
            int n,k;
            cin >> n >> k;
            string str;
            cin >> str;
            if(k%2==1){
                cout << 0 << endl;
                return 0;
            }
            //创造一个长度为k的滑动窗口
            unordered_map<char,int> unorderedMap;
            for(int i = 0;i<k;i++){
                unorderedMap[str[i]]++;
            }
            int res = 0;
            for(int i = 0;i<n-k;i++){
                if(checkMap(unorderedMap)){
                    res++;
                }
                unorderedMap[str[i]]--;
                unorderedMap[str[i+k]]++;
            }
            res += checkMap(unorderedMap) ? 1:0;
            cout << res << endl;
            return 0;
        }

    };

    struct Pro3{
        bool checkMap(const unordered_map<int,int>& unorderedMap,int bar){
            for(const auto& it:unorderedMap){
                if(it.second >= bar){
                    return true;
                }
            }
            return false;
        }

        int main(){
            int n,k;
            cin >> n >> k;
            vector<int> nums(n);
            for(int i = 0;i<n;i++){
                cin >> nums[i];
            }
            unordered_map<int,int> unorderedMap;
            int bar = k/2;
            if(k%2 == 1){
                bar++;
            }
            for(int i = 0;i < k;i++){
                unorderedMap[nums[i]]++;
            }
            int cnt = 0;
            for(int i = 0;i<n-k;i++){
                if(checkMap(unorderedMap,bar)){
                    cnt++;
                }
                unorderedMap[nums[i]]--;
                unorderedMap[nums[i+k]]++;
            }
            cnt += checkMap(unorderedMap,bar) ? 1 : 0;
            cout << cnt <<endl;
            return 0;
        }
    };
};


#endif //LEETCODE_OPPOPRO_H
