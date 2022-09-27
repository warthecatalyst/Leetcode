#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <algorithm>
#include <memory>
#include <vector>
#include <string>
#include <stack>
#include <deque>
#include <set>
#include <unordered_set>
#include <map>
#include <unordered_map>
#include <random>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class AandN{
private:
    int findBiggest(char c,const vector<int>& A){
        for(int i = 0;i<A.size();i++){
            if(i==A.size()-1||(A[i]<=c-'0'&&A[i+1]>c-'0')){
                return i;
            }
        }
        return 0;
    }

    bool dfs(const string& n_str,int index,const vector<int>&A,bool flag,string& ans){
        cout<<"index = "<<index<<endl;
        cout<<"ans = "<<ans<<endl;
        if(!flag){
            if(index == n_str.size()-1){
                if(A[0]>=n_str[index]-'0'){
                    return false;
                }
                int cur = findBiggest(n_str[index],A);
                if(A[cur]==n_str[index]-'0'){
                    cur--;
                }
                ans.push_back(A[cur]+'0');
                return true;
            }else{
                int cur = findBiggest(n_str[index],A);
                if(cur==0&&A[cur]>n_str[index]-'0'){
                    return false;
                }
                if(A[cur]==n_str[index]-'0'){
                    ans.push_back(A[cur]+'0');
                    if(!dfs(n_str,index+1,A,false,ans)){
                        ans.pop_back();
                        if(cur==0){
                            return false;
                        }
                        cur--;
                        ans.push_back(A[cur]+'0');
                        return dfs(n_str,index+1,A,true,ans);
                    }
                    return true;
                }else{
                    return dfs(n_str,index+1,A, true,ans);
                }
            }
        }else{
            if(index==n_str.size()-1) {
                ans.push_back(A[A.size() - 1]+'0');
                return true;
            }
            ans.push_back(A[A.size() - 1]+'0');
            return dfs(n_str,index+1,A,true,ans);
        }
    }

public:
    void main(){
        int n,num;
        vector<int> A;
        cin >> n;
        string n_str = to_string(n);
        while(cin >> num&&num!=-1){
            A.push_back(num);
        }
        if(A.size()==1&&A[0]==0){
            cout<<"0"<<endl;
            return;
        }
        sort(A.begin(),A.end());
        int smallest = A[0]==0?A[1]:A[0];
        int tmp = n_str.size();
        while(--tmp){
            smallest = smallest*10+A[0];
        }
        cout<<smallest<<endl;
        if(smallest>=n){
            int biggest = A[A.size()-1];
            tmp = n_str.size()-1;
            while(--tmp){
                biggest = biggest*10+A[A.size()-1];
            }
            cout<<biggest<<endl;
        }else{
            //贪心找最大，如果不行则回溯
            string ans;
            dfs(n_str,0,A,false,ans);
            cout<<ans<<endl;
        }
    }
};

namespace P710{
    class Solution {
        unordered_map<int, int> b2w;
        int bound;

    public:
        Solution(int n, vector<int> &blacklist) {
            int m = blacklist.size();
            bound = n - m;
            unordered_set<int> black;
            for (int b: blacklist) {
                if (b >= bound) {
                    black.emplace(b);
                }
            }

            int w = bound;
            for (int b: blacklist) {
                if (b < bound) {
                    while (black.count(w)) {
                        ++w;
                    }
                    b2w[b] = w++;
                }
            }
        }

        int pick() {
            int x = rand() % bound;
            return b2w.count(x) ? b2w[x] : x;
        }
    };
}

namespace P522{
    class Solution {
    private:
        bool isSubsequent(const string& a,const string& b){
            int pt_a = 0,pt_b = 0;
            while(pt_a<a.size()&&pt_b<=b.size()){
                if(a[pt_a]==b[pt_b]){
                    pt_a++;
                }
                pt_b++;
            }
            return pt_a==a.size();
        }
    public:
        int findLUSlength(vector<string>& strs) {
            int n = strs.size();
            int ans = -1;
            for(int i = 0;i<n;i++){
                bool check = true;
                for(int j = 0;j<n;j++){
                    if(i!=j&& isSubsequent(strs[i],strs[j])){
                        check = false;
                        break;
                    }
                }
                if(check){
                    ans = max(ans,(int)strs[i].size());
                }
            }
            return ans;
        }
    };
}

namespace P416{
    class Solution {
    private:
        bool dfs(vector<int>& nums,set<int>& chosen,int index,int left){
//        cout<<index<<endl;
//        cout<<left<<endl;
//        cout<<"chosen:[";
//        for(int num:chosen){
//            cout<<num<<",";
//        }
//        cout<<"]"<<endl;
            if(left==0){
                return true;
            }
            if(left<0||index>=nums.size()){
                return false;
            }
            chosen.insert(nums[index]);
            if(!dfs(nums,chosen,index+1,left-nums[index])){
                chosen.erase(nums[index]);
                return dfs(nums,chosen,index+1,left);
            }
            return true;
        }
    public:
        bool canPartition(vector<int>& nums) {
            if(nums.size()<2){
                return false;
            }

            int sum = 0;
            int maxNum = *max_element(nums.begin(),nums.end());
            for(int num:nums){
                sum+=num;
            }
            if(sum%2!=0||maxNum>sum>>1){
                return false;
            }
            int n = nums.size(),target = sum>>1;
            vector<int> dp(target+1, false);
            dp[0] = true;
            for(int i = 0;i<n;i++){
                int num = nums[i];
                for(int j = target;j>=num;j--){
                    dp[j] |= dp[j-num];
                }
            }
            return dp[target];
        }
    };
}

namespace P497{
    class Solution {
    public:
        Solution(vector<vector<int>>& rects) {
            this->rects = rects;
            cnt = 0;
            arr.push_back(cnt);
            for(int i = 0;i<rects.size();i++){
                auto& rect = rects[i];
                int area = (rect[2]-rect[0]+1)*(rect[3]-rect[1]+1);
                cnt+=area;
                arr.push_back(cnt);
            }
        }

        vector<int> pick() {
            auto picknum = randomDevice()%cnt;
            cout<<picknum<<endl;
            auto rectnum = upper_bound(arr.begin(),arr.end(),picknum);
            cout<<rectnum-arr.begin()<<endl;
            auto rect = rects[rectnum-arr.begin()-1];
            int x = rect[0]+(randomDevice()%(rect[2]-rect[0]+1)), y = rect[1]+(randomDevice()%(rect[3]-rect[1]+1));
            return {x,y};
        }
    private:
        vector<int> arr;
        vector<vector<int>> rects;
        std::random_device randomDevice;
        int cnt;
    };
}

namespace P324{
    class Solution {
    public:
        void wiggleSort(vector<int>& nums) {
            vector<int> sortedNum(nums);
            sort(sortedNum.begin(),sortedNum.end());
            int mid = nums.size()%2==0?nums.size()/2-1:nums.size()/2;
            for(int i = 0;i<nums.size();i++){
                if(i%2==0){
                    nums[i] = sortedNum[mid-i/2];
                }else{
                    nums[i] = sortedNum[nums.size()-1-i/2];
                }
            }
        }
    };
}

namespace P515{
    class Solution {
    private:
        void dfs(TreeNode* cur,int depth,vector<int>& ans){
            if(cur== nullptr){
                return;
            }
            if(depth>ans.size()){
                ans.push_back(cur->val);
            }else if(cur->val>ans[depth-1]){
                ans[depth-1] = cur->val;
            }
            dfs(cur->left,depth+1,ans);
            dfs(cur->right,depth+1,ans);
        }
    public:
        vector<int> largestValues(TreeNode* root) {
            vector<int> ans;
            dfs(root,1,ans);
            return ans;
        }
    };
}

namespace Week311_1{
    class Solution{

    };
}

namespace Week311_2{
    class Solution {
    public:
        int longestContinuousSubstring(string s) {
            int length = s.size();
            int max = 0;
            for(int i = 0;i<length;i++) {
                int j = i + 1;
                while (j < length && (s[j] == s[j - 1] + 1)) {
                    j++;
                }
                max = (j - i > max) ? (j - i) : max;
            }
            return max;
        }
    };
}

namespace Week311_3{
    struct TreeNode {
        int val;
        TreeNode *left;
        TreeNode *right;
        TreeNode() : val(0), left(nullptr), right(nullptr) {}
        TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
        TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
    };

    class Solution {
        using ll = long long;
    public:
        TreeNode* reverseOddLevels(TreeNode* root) {
            vector<int> vec;
            deque<TreeNode*> que;
            que.push_back(root);
            while(!que.empty()){
                auto cur = que.front();
                vec.push_back(cur->val);
                if(cur->left!= nullptr&&cur->right!= nullptr){
                    que.push_back(cur->left);
                    que.push_back(cur->right);
                }
                que.pop_front();
            }

            deque<pair<TreeNode*,int>> que1;
            que1.emplace_back(root,0);
            ll curIndex = -1;
            while(!que1.empty()){
                auto cur = que1.front();
                int curl = cur.second;
                if(curl%2==0){
                    if(curIndex==-1){
                        curIndex = pow(2,curl+2)-2;
                    }
                    cout<<"curIndex = "<<curIndex<<endl;
                    if(curIndex<vec.size()){
                        cur.first->left->val = vec[curIndex--];
                        cur.first->right->val = vec[curIndex--];
                    }
                }else{
                    curIndex = -1;
                }
                if(cur.first->left!= nullptr){
                    que1.emplace_back(cur.first->left,curl+1);
                    que1.emplace_back(cur.first->right,curl+1);
                }
                que1.pop_front();
            }
            cout<<"end"<<endl;
            return root;
        }
    };
}

namespace Week311_4{
    class Solution{
    public:
        struct TrieNode{
            bool isFinished;
            int val;
            TrieNode* next[26];

            TrieNode(){
                isFinished = false;
                val = 0;
                fill(begin(next),end(next), nullptr);
            }
        };

        TrieNode* root;

        void insert(string& word){
            auto node = root;
            for(int i = 0;i<word.size();i++){
                char c = word[i];
                int ind = c-'a';
                if(node->next[ind]== nullptr){
                    node->next[ind] = new TrieNode();
                }
                node->next[ind]->val++;
                node = node->next[ind];
            }
            node->isFinished = true;
        }

        int search(string& word){
            auto node = root;
            int res = 0;
            for(int i = 0;i<word.size();i++){
                char c = word[i];
                int ind = c-'a';
                if(node->next[ind]== nullptr){
                    return res;
                }
                res += node->next[ind]->val;
                node = node->next[ind];
            }
            return res;
        }

        vector<int> sumPrefixScores(vector<string>& words) {
            root = new TrieNode();
            vector<int> res(words.size());
            for(int i = 0;i<words.size();i++){
                insert(words[i]);
            }

            for(int i = 0;i<words.size();i++){
                res[i] = search(words[i]);
            }
            return res;
        }
    };
}

namespace P1636{
    class Solution {
    public:
        vector<int> frequencySort(vector<int>& nums) {
            unordered_map<int,int> frequency;
            for(const int& num:nums){
                frequency[num]++;
            }
            sort(nums.begin(),nums.end(),[&](const int& a,const int& b){
                if(frequency[a]==frequency[b]){
                    return a>b;
                }
                return frequency[a]< frequency[b];
            });
            return nums;
        }
    };
}

namespace P1640{
class Solution {
public:
    bool canFormArray(vector<int>& arr, vector<vector<int>>& pieces) {
        unordered_map<int,int> piecesMap;
        for(int i = 0;i<pieces.size();i++){
            piecesMap[pieces[i][0]] = i;
        }
        for(int i = 0;i<arr.size();){
            if(piecesMap.count(arr[i])==0){
                return false;
            }
            int j = piecesMap[arr[i]], len = pieces[j].size();
            for(int k = 0;k<len;k++){
                if(arr[i+k]!=pieces[j][k]){
                    return false;
                }
            }
            i += len;
        }
        return true;
    }
};
}

namespace mianshi01_02{
class Solution {
public:
    bool CheckPermutation(string s1, string s2) {
        if(s1.size()!=s2.size()){
            return false;
        }
        unordered_map<char,int> s1map;
        unordered_map<char,int> s2map;
        for(char c:s1){
            s1map[c]++;
        }
        for(char c:s2){
            s2map[c]++;
        }
        for(auto iter = s1map.begin();iter!=s1map.end();iter++){
            int ans2 = s2map[iter->first];
            if(ans2!=iter->second){
                return false;
            }
        }
        return true;
    }
};
}

int main() {
    auto solution = new Week311_2::Solution();
    int ans = solution->longestContinuousSubstring("adjp");
    cout<<ans<<endl;
    return 0;
}
