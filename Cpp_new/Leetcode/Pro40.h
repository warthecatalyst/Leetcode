//
// Created by 18317 on 2023/3/29.
//

#ifndef LEETCODE_PRO40_H
#define LEETCODE_PRO40_H
#include "global.h"

namespace p40{
class Solution {
private:
    vector<pair<int,int>> freq;
    vector<vector<int>> ans;
    vector<int> sequence;

public:
    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        sort(candidates.begin(),candidates.end());
        for(int candidate:candidates){
            if(!freq.empty()&&candidate==freq.back().first){
                freq.back().second++;
            }else{
                freq.emplace_back(candidate,1);
            }
        }
        dfs(0,target);
        return ans;
    }

    void dfs(int pos,int rest){
        if (rest == 0) {
            ans.push_back(sequence);
            return;
        }
        if (pos == freq.size() || rest < freq[pos].first) {
            return;
        }

        dfs(pos + 1, rest);

        int most = min(rest / freq[pos].first, freq[pos].second);
        for (int i = 1; i <= most; ++i) {
            sequence.push_back(freq[pos].first);
            dfs(pos + 1, rest - i * freq[pos].first);
        }
        for (int i = 1; i <= most; ++i) {
            sequence.pop_back();
        }
    }
};
}

#endif //LEETCODE_PRO40_H
