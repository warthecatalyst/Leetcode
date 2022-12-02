#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <algorithm>
#include <memory>
#include <vector>
#include <string>
#include <stack>
#include <deque>
#include <queue>
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

namespace P1790{
class Solution {
public:
    bool areAlmostEqual(string s1, string s2) {
        if(s1==s2){
            return true;
        }
        int cnt = 0,len = s1.length();
        int first = -1,second = -1;
        for(int i = 0;i<len;i++){
            if(s1[i]!=s2[i]){
                cnt++;
                if(first==-1){
                    first = i;
                }else if(second == -1){
                    second = i;
                }
            }
        }
        return cnt==2&&(s1[first]==s2[second]&&s2[first]==s1[second]);
    }
};
}

namespace P769{
class Solution {
public:
    int maxChunksToSorted(vector<int>& arr) {
        int m = 0, res = 0;
        for (int i = 0; i < arr.size(); i++) {
            m = max(m, arr[i]);
            if (m == i) {
                res++;
            }
        }
        return res;
    }
};
}

namespace P862{
class Solution {
public:
    int shortestSubarray(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> preSum(n+1);
        for(int i = 0;i<n;i++){
            preSum[i+1] = preSum[i]+nums[i];
        }
        int res = n+1;
        deque<int> qu;
        for (int i = 0; i <= n; i++) {
            long curSum = preSum[i];
            while (!qu.empty() && curSum - preSum[qu.front()] >= k) {
                res = min(res, i - qu.front());
                qu.pop_front();
            }
            while (!qu.empty() && preSum[qu.back()] >= curSum) {
                qu.pop_back();
            }
            qu.push_back(i);
        }
        return res < n + 1 ? res : -1;

    }
};
}

namespace P940{
class Solution {
    static const int MOD = 1e9+7;
public:
    int distinctSubseqII(string s) {
        vector<int> last(26,-1);
        int n = s.size();
        vector<int> f(n,1);
        for(int i = 0; i < n;i++){
            for(int j = 0;j < 26;j++){
                if(last[j]!=-1){
                    f[i] = (f[i]+f[last[j]])%MOD;
                }
            }
            last[s[i]-'a'] = i;
        }
        int ans = 0;
        for(int i = 0;i<26;i++){
            if(last[i]!=-1){
                ans = (ans + f[last[i]]) % MOD;
            }
        }
        return ans;
    }
};
}

namespace P1441{
class Solution {
    string push = "Push";
    string pop = "Pop";
public:
    vector<string> buildArray(vector<int>& target, int n) {
        int ed = target.back();
        vector<string> ans;
        int pt = 0;
        for(int i = 1;i <= ed;i++){
            if(i==target[pt]){
                ans.push_back(push);
                pt++;
            }else{
                ans.push_back(push);
                ans.push_back(pop);
            }
        }
        return ans;
    }
};
}

namespace P886{
class Solution {
    //经典染色法问题
public:
    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        vector<int> color(n+1,0);
        vector<vector<int>> g(n+1);
        for(auto &p:dislikes){
            g[p[0]].push_back(p[1]);
            g[p[1]].push_back(p[0]);
        }
        for(int i = 1;i<=n;i++){
            if(color[i]==0){
                queue<int> qu;
                color[i] = 1;
                qu.push(i);
                while(!qu.empty()){
                    auto t = qu.front();
                    qu.pop();
                    for(auto &next:g[t]){
                        if(color[next]>0&&color[next] == color[t]){
                            return false;
                        }
                        if (color[next] == 0) {
                            color[next] = 3 ^ color[t];
                            qu.push(next);
                        }
                    }
                }
            }
        }
        return true;
    }
};
}

namespace P902{
class Solution {
public:
    int atMostNGivenDigitSet(vector<string>& digits, int n) {
        string s = to_string(n);
        int m = digits.size(), k = s.size();
        vector<int> bits;
        bool isLimit = true;
        for (int i = 0; i < k; i++) {
            if (!isLimit) {
                bits.emplace_back(m - 1);
            } else {
                int selectIndex = -1;
                for (int j = 0; j < m; j++) {
                    if (digits[j][0] <= s[i]) {
                        selectIndex = j;
                    } else {
                        break;
                    }
                }
                if (selectIndex >= 0) {
                    bits.emplace_back(selectIndex);
                    if (digits[selectIndex][0] < s[i]) {
                        isLimit = false;
                    }
                } else {
                    int len = bits.size();
                    while (!bits.empty() && bits.back() == 0) {
                        bits.pop_back();
                    }
                    if (!bits.empty()) {
                        bits.back() -= 1;
                    } else {
                        len--;
                    }
                    while ((int)bits.size() <= len) {
                        bits.emplace_back(m - 1);
                    }
                    isLimit = false;
                }
            }
        }
        int ans = 0;
        for (int bit : bits) {
            ans = ans * m + (bit + 1);
        }
        return ans;
    }
};

class zijie{
public:
    //约定nums.size()>0, n >= 0
    int maxIntLEN(vector<int> nums, int n){      //从nums中组合出小于等于n的最大数
        if(nums.size()==1&&nums[0]==0){
            return 0;
        }
        sort(nums.begin(),nums.end());
        string n_str = to_string(n);
        cout << n_str << endl;
        string res;
        int w_cnt = n_str.size();
        //先判断w_cnt位数中最小的是不是大于n
        for(int i = 0;i<w_cnt;i++){
            if(i==0&&nums[0]==0){
                res.push_back(nums[1]+'0');
            }else{
                res.push_back(nums[0]+'0');
            }
        }
        if(stoi(res)>n){
            res.clear();
            for(int i = 0;i<w_cnt-1;i++){
                res.push_back(nums.back()+'0');
            }
            if(res.empty()){
                return 0;
            }
            return stoi(res);
        }
        res.clear();
        dfs(nums,n_str,res,0, true);
        return stoi(res);
    }

    //to be sure it's enough
    bool dfs(const vector<int>& nums,const string& n_str,string &res,int i,bool greedy){
        cout<< "i = " << i << ", res = " << res << endl;
        if(i==n_str.size()-1){
            if(greedy){
                int j = nums.size()-1;
                for(;j>=0;j--){
                    int num = nums[j];
                    if(num+'0'<=n_str[i]){
                        break;
                    }
                }
                if(j>=0){
                    res.push_back(nums[j]+'0');
                    return true;
                }else{
                    return false;
                }
            }else{
                res.push_back(nums.back()+'0');
                return true;
            }
        }
        //greedy
        if(greedy){
            int j = nums.size()-1;
            for(;j>=0;j--){
                int num = nums[j];
                if(num+'0'<=n_str[i]){
                    break;
                }
            }
            if(j>=0){
                res.push_back(nums[j]+'0');
                if(dfs(nums,n_str,res,i+1, true)) return true;
                else{
                    res.pop_back();
                    for(;j>=0;j--){
                        int num = nums[j];
                        if(num+'0'<n_str[i]){
                            break;
                        }
                    }
                    if(j>=0){
                        res.push_back(nums[j]+'0');
                        return dfs(nums,n_str,res, i+1, false);
                    }else{
                        return false;
                    }
                }
            }else{
                return false;
            }
        }else{
            res.push_back(nums.back()+'0');
            dfs(nums,n_str,res,i+1,false);
            return true;
        }
    }
};
}

namespace P1700{
class Solution {
public:
    int countStudents(vector<int>& students, vector<int>& sandwiches) {
        int s1 = accumulate(students.begin(), students.end(), 0);
        int s0 = students.size() - s1;
        for (int i = 0; i < sandwiches.size(); i++) {
            if (sandwiches[i] == 0 && s0 > 0) {
                s0--;
            } else if (sandwiches[i] == 1 && s1 > 0) {
                s1--;
            } else {
                break;
            }
        }
        return s0 + s1;
    }
};
}

namespace P779{
class Solution {
public:
    int kthGrammar(int n, int k) {
        if(k==0){
            return 0;
        }
        if(k > (1 << (n - 2))){
            return 1 ^ kthGrammar(n-1,k-(1<<(n-2)));
        }
        return kthGrammar(n-1,k);
    }
};
}

namespace P210{
class Solution {
public:
    vector<int> findOrder(int numCourses, vector<vector<int>>& prerequisites) {
        vector<vector<int>> g(numCourses,vector<int>());
        vector<vector<int>> anti_g(numCourses,vector<int>());
        vector<int> degrees(numCourses,0);
        for(auto &pre:prerequisites){
            g[pre[1]].push_back(pre[0]);
            anti_g[pre[0]].push_back(pre[1]);
            degrees[pre[0]]++;
        }

        queue<int> qu;
        vector<bool> vis(numCourses,false);
        for(int i = 0;i<numCourses;i++){
            if(degrees[i]==0){
                qu.push(i);
                vis[i] = true;
            }
        }

        vector<int> res;
        while(!qu.empty()){
            int t = qu.front();
            qu.pop();
            res.push_back(t);
            for(auto& next:g[t]){
                if(vis[next]||!pre_set(anti_g,next,vis)) continue;
                qu.push(next);
                vis[next] = true;
            }
        }
        if(res.size()<numCourses) return {};
        return res;
    }

    bool pre_set(vector<vector<int>>& anti_g,int next,vector<bool>& visited){
        for(int pre:anti_g[next]){
            if(!visited[pre]){
                return false;
            }
        }
        return true;
    }

//    bool checkCircle(vector<vector<int>> &graph,vector<int>& degrees){
//        set<int> starts;
//        for(int i = 0;i<degrees.size();i++){
//            if(degrees[i]==0){
//                starts.insert(i);
//            }
//        }
//        for(int start:starts){
//            set<int> visited;
//            if(!dfs(graph,start,visited)){
//                return true;
//            }
//            visited.clear();
//        }
//        return false;
//    }
//
//    bool dfs(vector<vector<int>> &graph,int cur,set<int>& visited){
//        if(visited.count(cur)) return false;
//        visited.insert(cur);
//        for(int next:graph[cur]){
//            if(!dfs(graph,next,visited)) return false;
//        }
//        return true;
//    }
};
}

namespace P764{
class Solution {
public:
    int orderOfLargestPlusSign(int n, vector<vector<int>>& mines) {
        vector<vector<int>> dp(n, vector<int>(n, n));
        unordered_set<int> banned;
        for (auto &&vec : mines) {
            banned.emplace(vec[0] * n + vec[1]);
        }
        int ans = 0;
        for(int i = 0;i<n;i++){
            int count = 0;
            for(int j = 0;j<n;j++){ //left
                if(banned.count(i*n+j)){
                    count = 0;
                }else{
                    count++;
                }
                dp[i][j] = std::min(dp[i][j],count);
            }
            count = 0;
            for(int j = n-1;j>=0;j--){  //right
                if(banned.count(i*n+j)){
                    count = 0;
                }else{
                    count++;
                }
                dp[i][j] = std::min(dp[i][j],count);
            }
        }

        for(int j = 0;j<n;j++) {
            int count = 0;
            //up
            for (int i = 0; i < n; i++) {
                if (banned.count(i * n + j)) {
                    count = 0;
                } else {
                    count++;
                }
                dp[i][j] = std::min(dp[i][j], count);
            }
            count = 0;
            //down
            for (int i = n - 1; i >= 0; i--) {
                if (banned.count(i * n + j)) {
                    count = 0;
                } else {
                    count++;
                }
                dp[i][j] = std::min(dp[i][j], count);
                ans = max(ans, dp[j][i]);
            }
        }
        return ans;
    }
};
}

namespace P805{
class Solution {
public:
    bool splitArraySameAverage(vector<int>& nums) {
        int n = nums.size(), m = n/2;
        if(n==1)
            return false;

        int sum = accumulate(nums.begin(), nums.end(), 0);
        for (int &x : nums) {
            x = x * n - sum;
        }

        unordered_set<int> left;
        for (int i = 1; i < (1 << m); i++) {
            int tot = 0;
            for (int j = 0; j < m; j++) {
                if (i & (1 << j)) {
                    tot += nums[j];
                }
            }
            if (tot == 0) {
                return true;
            }
            left.emplace(tot);
        }

        int rsum = accumulate(nums.begin() + m, nums.end(), 0);
        for (int i = 1; i < (1 << (n - m)); i++) {
            int tot = 0;
            for (int j = m; j < n; j++) {
                if (i & (1 << (j - m))) {
                    tot += nums[j];
                }
            }
            if (tot == 0 || (rsum != tot && left.count(-tot))) {
                return true;
            }
        }
        return false;
    }
};
}

namespace P1710{
class Solution {
public:
    int maximumUnits(vector<vector<int>>& boxTypes, int truckSize) {
        sort(boxTypes.begin(),boxTypes.end(),[](const auto& a, const auto& b){
            return a[1]>b[1];
        });
        int ans = 0;
        for(int i = 0;i<boxTypes.size();i++){
            const auto &boxType = boxTypes[i];
            if(truckSize<=0){
                break;
            }
            int cur_pick = boxType[0]<truckSize?boxType[0]:truckSize;
            ans += boxType[1] * cur_pick;
            truckSize-=cur_pick;
        }
        return ans;
    }
};
}

namespace P775{
class Solution {
public:
    bool isIdealPermutation(vector<int>& nums) {
        for(int i = 0;i<nums.size();i++){
            if(nums[i]<i-1||nums[i]>i+1){
                return false;
            }
        }
        return true;
    }
};
}

namespace P792{
class Solution {
public:
    int numMatchingSubseq(string s, vector<string>& words) {
        vector<vector<int>> pos(26);
        for(int i = 0;i<s.size();i++){
            pos[s[i]-'a'].push_back(i);
        }

        int res = words.size();
        for(auto &word:words){
            if(word.size()>s.size()){
                --res;
                continue;
            }
            int p = -1;
            for(char c:word){
                auto &ps = pos[c-'a'];
                auto it = upper_bound(ps.begin(),ps.end(),p);
                if(it==ps.end()){
                    --res;
                    break;
                }
                p = *it;
            }
        }
        return res;
    }
};
}

namespace P1732{
class Solution {
public:
    int largestAltitude(vector<int>& gain) {
        int max_height = INT32_MIN;
        int cur = 0;
        for(int num:gain){
            cur += num;
            if(cur>max_height)
                max_height = cur;
        }
        return max_height;
    }
};
}

namespace P799{
class Solution {
public:
    double champagneTower(int poured, int query_row, int query_glass) {
        vector<double> row = {(double)poured};
        for(int i = 1;i<=query_row;i++){
            vector<double> next_row(i+1,0.0);
            for(int j = 0;j<row.size();j++){
                double volume = row[j];
                if(volume>1){
                    next_row[j] += (volume-1)/2;
                    next_row[j+1] = (volume-1)/2;
                }
            }
            row = next_row;
        }
        return min(1.0,row[query_glass]);
    }
};
}

namespace P808{
class Solution {
public:
    double soupServings(int n) {
        n = ceil((double) n / 25);
        if (n >= 179) {
            return 1.0;
        }
        vector<vector<double>> dp(n + 1, vector<double>(n + 1));
        dp[0][0] = 0.5;
        for (int i = 1; i <= n; i++) {
            dp[0][i] = 1.0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[max(0, i - 4)][j] + dp[max(0, i - 3)][max(0, j - 1)] +
                            dp[max(0, i - 2)][max(0, j - 2)] + dp[max(0, i - 1)][max(0, j - 3)]) / 4.0;
            }
        }
        return dp[n][n];
    }
};
}

namespace P882{
class Solution {
public:
    inline int encode(int u,int v,int n){
        return u*n+v;
    }

    int reachableNodes(vector<vector<int>>& edges, int maxMoves, int n) {
        vector<vector<pair<int,int>>> adList(n);
        for(auto &edge:edges){
            int u = edge[0], v = edge[1], nodes = edge[2];
            adList[u].emplace_back(v,nodes);
            adList[v].emplace_back(u,nodes);
        }

        unordered_map<int,int> used;
        unordered_set<int> visited;
        int reachableNodes = 0;
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
        pq.push({0,0});
        while (!pq.empty() && pq.top().first <= maxMoves) {
            auto [step, u] = pq.top();
            pq.pop();
            if (visited.count(u)) {
                continue;
            }
            visited.emplace(u);
            reachableNodes++;
            for (auto [v, nodes] : adList[u]) {
                if (nodes + step + 1 <= maxMoves && !visited.count(v)) {
                    pq.emplace(nodes + step + 1, v);
                }
                used[encode(u, v, n)] = min(nodes, maxMoves - step);
            }
        }

        for (auto &edge : edges) {
            int u = edge[0], v = edge[1], nodes = edge[2];
            reachableNodes += min(nodes, used[encode(u, v, n)] + used[encode(v, u, n)]);
        }
        return reachableNodes;
    }
};
}

namespace P1752{
class Solution {
public:
    bool check(vector<int>& nums) {
        int n = nums.size(), flag = false;
        for(int i = 0;i<n-1;i++){
            if(nums[i]>nums[i+1]){
                if(!flag) flag = true;
                else return false;
            }
        }
        return !flag || flag && nums.back()<=nums[0];
    }
};
}

namespace P813{
class Solution {
public:
    double largestSumOfAverages(vector<int>& nums, int k) {
        int n = nums.size();
        vector<double> prefix(n + 1);
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        vector<double> dp(n + 1);
        for (int i = 1; i <= n; i++) {
            dp[i] = prefix[i] / i;
        }
        for (int j = 2; j <= k; j++) {
            for (int i = n; i >= j; i--) {
                for (int x = j - 1; x < i; x++) {
                    dp[i] = max(dp[i], dp[x] + (prefix[i] - prefix[x]) / (i - x));
                }
            }
        }
        return dp[n];
    }
};
}

namespace P1758{
class Solution {
public:
    int minOperations(string s) {
        int cnt = 0;
        for (int i = 0; i < s.size(); i++) {
            char c = s[i];
            if (c != ('0' + i % 2)) {
                cnt++;
            }
        }
        return min(cnt, (int)s.size() - cnt);
    }
};
}

namespace P895{
class FreqStack {
public:
    FreqStack() {
        maxFreq = 0;
    }

    void push(int val) {
        freq[val]++;
        group[freq[val]].push(val);
        maxFreq = max(maxFreq, freq[val]);
    }

    int pop() {
        int val = group[maxFreq].top();
        freq[val]--;
        group[maxFreq].pop();
        if (group[maxFreq].empty()) {
            maxFreq--;
        }
        return val;
    }

private:
    unordered_map<int, int> freq;
    unordered_map<int, stack<int>> group;
    int maxFreq;
};
}

namespace P1779{
class Solution {
public:
    int nearestValidPoint(int x, int y, vector<vector<int>>& points) {
        int idx = -1, min_dis = INT32_MAX;
        for(int i = 0;i<points.size();i++){
            auto& point = points[i];
            if(point[0]==x||point[1]==y){
                if(abs(point[0]-x)+abs(point[1]-y)<min_dis){
                    idx = i;
                    min_dis = abs(point[0]-x)+abs(point[1]-y);
                }
            }
        }
        return idx;
    }
};
}

namespace P1769{
class Solution {
public:
    vector<int> minOperations(string boxes) {
        int left = boxes[0] - '0', right = 0, operations = 0;
        int n = boxes.size();
        for (int i = 1; i < n; i++) {
            if (boxes[i] == '1') {
                right++;
                operations += i;
            }
        }
        vector<int> res(n);
        res[0] = operations;
        for (int i = 1; i < n; i++) {
            operations += left - right;
            if (boxes[i] == '1') {
                left++;
                right--;
            }
            res[i] = operations;
        }
        return res;
    }
};
}

namespace P27{
class Solution {
public:
    int removeElement(vector<int>& nums, int val) {
        for(int i = nums.size()-1;i>=0;i--){
            if(nums[i]==val){
                nums.erase(nums.begin()+i);
            }
        }
        return nums.size();
    }
};
}

int main() {
    auto solution = new P902::zijie();
    vector<int> vec = {4,5,6};
    auto res = solution->maxIntLEN(vec,6443);
    cout<<res<<endl;
}
