#include "global.h"

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

namespace P1796 {
    class Solution {
    public:
        int secondHighest(string s) {
            int first = -1, second = -1;
            for (auto c: s) {
                if (isdigit(c)) {
                    int num = c - '0';
                    if (num > first) {
                        second = first;
                        first = num;
                    } else if (num < first && num > second) {
                        second = num;
                    }
                }
            }
            return second;
        }
    };
}

namespace P1774{
    class Solution {
    public:
        int closestCost(vector<int>& baseCosts, vector<int>& toppingCosts, int target) {
            int n = baseCosts.size(), m = toppingCosts.size(), statusLimit = (int)pow(3,m);
            set<int> toppingCostSet;
            for(int status = 0;status<statusLimit;status++){
                auto select = to3str(status,m);
                //cout << "select = " << select << endl;
                int cur_cost = 0;
                for(int i = 0;i<select.size();i++){
                    int choose = select[i]-'0';
                    cur_cost += choose*toppingCosts[i];
                }
                //cout<< "cur_cost = " << cur_cost << endl;
                toppingCostSet.insert(cur_cost);
            }
            // for(int cost:toppingCostSet){
            //     cout << cost << endl;
            //}
            int minDiff = INT32_MAX, totalCost = INT32_MAX;
            for(auto baseCost:baseCosts){
                for(int toppingCost:toppingCostSet){
                    auto cur_cost = baseCost + toppingCost;
                    if(abs(cur_cost-target)<minDiff
                       ||(abs(cur_cost-target)==minDiff&&cur_cost<totalCost)){
                        minDiff = abs(cur_cost-target);
                        totalCost = cur_cost;
                    }
                }
            }
            return totalCost;
        }

        string to3str(int status,int m){
            string str;
            while(status){
                str+=(status%3)+'0';
                status/=3;
            }
            while(str.size()<m){
                str+='0';
            }
            reverse(str.begin(),str.end());
            return str;
        }
    };
}

namespace P1687{
class Solution {
public:
    int boxDelivering(vector<vector<int>>& boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.size();
        vector<int> p(n+1),w(n+1),neg(n+1);
        vector<long long> W(n+1);
        for (int i = 1; i <= n; ++i) {
            p[i] = boxes[i - 1][0];
            w[i] = boxes[i - 1][1];
            if (i > 1) {
                neg[i] = neg[i - 1] + (p[i - 1] != p[i]);
            }
            W[i] = W[i - 1] + w[i];
        }

        deque<int> opt = {0};
        vector<int> f(n + 1), g(n + 1);

        for (int i = 1; i <= n; ++i) {
            while (i - opt.front() > maxBoxes || W[i] - W[opt.front()] > maxWeight) {
                opt.pop_front();
            }

            f[i] = g[opt.front()] + neg[i] + 2;

            if (i != n) {
                g[i] = f[i] - neg[i + 1];
                while (!opt.empty() && g[i] <= g[opt.back()]) {
                    opt.pop_back();
                }
                opt.push_back(i);
            }
        }
        return f[n];
    }
};
}

namespace P1805{
class Solution {
public:
    int numDifferentIntegers(string word) {
        unordered_set<string> s;
        int n = word.size(),p1 = 0,p2;
        while(true){
            while (p1 < n && !isdigit(word[p1])) {
                p1++;
            }
            if (p1 == n) {
                break;
            }
            p2 = p1;
            while (p2 < n && isdigit(word[p2])) {
                p2++;
            }
            while (p2 - p1 > 1 && word[p1] == '0') { // 去除前导 0
                p1++;
            }
            s.insert(word.substr(p1, p2 - p1));
            p1 = p2;
        }
        return s.size();
    }
};
}

namespace P1775{
class Solution {
public:
    int minOperations(vector<int>& nums1, vector<int>& nums2) {
        if(6*nums1.size()<nums2.size()||6*nums2.size()<nums1.size()){
            return -1;
        }
        int sum1 = accumulate(nums1.begin(),nums1.end(),0);
        int sum2 = accumulate(nums2.begin(),nums2.end(),0);
        if(sum1<sum2){
            swap(nums1,nums2);
            swap(sum1,sum2);
        }
        int n1 = nums1.size(),n2 = nums2.size();
        int diff = sum1-sum2;
        if(diff==0) return 0;

        sort(nums1.begin(),nums1.end());
        sort(nums2.begin(),nums2.end());
        int i = n1-1,j = 0,ans = 0;
        while(i >= 0 || j < n2){
            int d1 = 0,d2 = 0;
            if(i >= 0) d1 = nums1[i] - 1;
            if(j < n2) d2 = 6 - nums2[j];
            int maxd = max(d1,d2);
            if(maxd == 0) return -1;
            if(maxd >= diff) return ans + 1;
            if(d1 == maxd){
                i --;
            }else{
                j ++;
            }
            diff -= maxd;
            ans ++;
        }
        return -1;
    }
};
}

namespace P1812{
class Solution {
public:
    bool squareIsWhite(string coordinates) {
        char c1 = coordinates[0], c2 = coordinates[1];
        int i = c1-'a'+c2-'1';
        return i%2;
    }
};
}

namespace P1780{
class Solution {
public:
    bool checkPowersOfThree(int n) {
        set<int> used;
        while(n>=1){
            cout << n << endl;
            int cur = 1;
            while(cur*3<=n){
                cur *= 3;
            }
            if(!used.count(cur)){
                used.insert(cur);
                n -= cur;
            }else{
                return false;
            }
        }
        return n==0;
    }
};
}

namespace P233{
class Solution {
public:
    int countDigitOne(int n) {
        long long mulk = 1;
        int ans = 0;
        for (int k = 0; n >= mulk; ++k) {
            ans += (n / (mulk * 10)) * mulk + min(max(n % (mulk * 10) - mulk + 1, 0LL), mulk);
            mulk *= 10;
        }
        return ans;
    }
};
}

namespace P1047{
class Solution {
public:
    string removeDuplicates(string s) {
        string stk;
        for (char ch : s) {
            if (!stk.empty() && stk.back() == ch) {
                stk.pop_back();
            } else {
                stk.push_back(ch);
            }
        }
        return stk;
    }
};
}

namespace P221{
class Solution {
public:
    int maximalSquare(vector<vector<char>>& matrix) {
        if (matrix.size() == 0 || matrix[0].size() == 0) {
            return 0;
        }
        int maxSize = 0;
        int n = matrix.size(),m = matrix[0].size();
        for(int i = 0;i<matrix.size();i++){
            for(int j = 0;j<matrix[i].size();j++){
                if(matrix[i][j]=='1'){
                    //cout<< i <<"  "<< j <<endl;
                    int size = 1;
                    while(size<min(n-i,m-j)&& isSquare(i,j,matrix,size)){
                        size++;
                    }
                    maxSize = max(maxSize,size*size);
                }
            }
        }
        return maxSize;
    }

    bool isSquare(int x,int y,vector<vector<char>>& matrix,int size){
        //判断行
        for(int j = y;j<=y+size;j++){
            if(matrix[x+size][j]=='0'){
                return false;
            }
        }
        //判断列
        for(int i = x;i<=x+size;i++){
            if(matrix[i][y+size]=='0'){
                return false;
            }
        }
        return true;
    }
};
}

namespace P1691{
class Solution {
public:
    int maxHeight(vector<vector<int>>& cuboids) {
        int n = cuboids.size();
        for(auto& cuboid:cuboids){
            sort(cuboid.begin(),cuboid.end());
        }
        sort(cuboids.begin(),cuboids.end(),[](const auto& a,const auto& b){
           return a[0]+a[1]+a[2] < b[0]+b[1]+b[2];
        });
        int ans = 0;
        vector<int> dp(n);
        for(int i = 0;i<n;i++){
            dp[i] = cuboids[i][2];
            for(int j = 0;j<i;j++){
                if(cuboids[j][0]<=cuboids[i][0]&&
                cuboids[j][1]<=cuboids[j][1]&&
                cuboids[j][2]<=cuboids[j][2]){
                    dp[i] = max(dp[i],cuboids[i][2]+dp[j]);
                }
            }
            ans = max(ans,dp[i]);
        }
        return ans;
    }
};
}

namespace P1827{
class Solution {
public:
    int minOperations(vector<int>& nums) {
        int ans = 0;
        for(int i = 0;i<nums.size()-1;i++){
            if(nums[i+1]<=nums[i]){
                ans += nums[i]+1-nums[i+1];
                nums[i+1] = nums[i]+1;
            }
        }
        return ans;
    }
};
}

namespace weekly_contest323_1{
class Solution {
public:
    int deleteGreatestValue(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int del = 0, ans = 0;
        while(del<m*n){
            int max_ele = 0;
            for(auto& line:grid){
                auto idx = max_element(line.begin(),line.end());
                if(*idx>max_ele){
                    max_ele = *idx;
                }
                line.erase(idx);
                del++;
            }
            ans += max_ele;
        }
        return ans;
    }
};
}

namespace weekly_contest323_2{
class Solution {
public:
    int longestSquareStreak(vector<int>& nums) {
        sort(nums.begin(),nums.end());
        int n = nums.size();
        vector<int> dp(n,1);
        for(int i = 1;i<n;i++){
            auto idx = std::lower_bound(nums.begin(), nums.end(), (int)sqrt(nums[i]));
            long val = *idx;
            if(val*val==(long)nums[i]){
                dp[i] = dp[idx-nums.begin()]+1;
            }
        }
        int ans = *max_element(dp.begin(),dp.end());
        if(ans < 2){
            return -1;
        }
        return ans;
    }
};
}

namespace weekly_contest323_3{
    class Allocator {
    public:
        vector<int> a;
        int x = 0;
        Allocator(int n) {
            a.resize(n);
            x = n;
        }

        int allocate(int size, int mID) {
            int cnt = 0;
            for (int i = 0; i < x;i++)
            {
                if(a[i])
                    cnt = 0;
                else
                    cnt++;
                if(cnt==size)
                {
                    for (int j = i; j > i - cnt;j--)
                    {
                        a[j] = mID;
                    }
                    return i - cnt + 1;
                }
            }
            return -1;
        }

        int free(int mID) {
            int ans = 0;
            for (int i = 0; i < x;i++)
            {
                if(a[i]==mID)ans ++, a[i] = 0;
            }
            return ans;
        }
    };
}

namespace weekly_contest323_4{

}

namespace P1781{
class Solution {
public:
    int beautySum(string s) {
        int res = 0;
        for(int i = 0;i<s.size();i++){
            vector<int> cnt(26);
            int maxFreq = 0;
            for (int j = i; j < s.size(); j++) {
                cnt[s[j] - 'a']++;
                maxFreq = max(maxFreq, cnt[s[j] - 'a']);
                int minFreq = s.size();
                for (int k = 0; k < 26; k++) {
                    if (cnt[k] > 0) {
                        minFreq = min(minFreq, cnt[k]);
                    }
                }
                res += maxFreq - minFreq;
            }
        }
        return res;
    }
};
}

namespace P146{
class LRUCache {
    struct DLinkedNode{
        int key,value;
        DLinkedNode* prev;
        DLinkedNode* next;
        DLinkedNode(): key(0), value(0), prev(nullptr), next(nullptr) {}
        DLinkedNode(int _key, int _value): key(_key), value(_value), prev(nullptr), next(nullptr) {}
    };

    unordered_map<int,DLinkedNode*> cache;
    DLinkedNode* head;
    DLinkedNode* tail;
    int size;
    int capacity;
public:
    LRUCache(int capacity): capacity(capacity),size(0) {
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head->next = tail;
        tail->next = head;
    }

    int get(int key) {
        if(!cache.count(key)){
            return -1;
        }
        DLinkedNode* node = cache.at(key);
        move_to_head(node);
        return node->value;
    }

    void put(int key, int value) {
        if(cache.count(key)){   //已经存在
            DLinkedNode* node = cache.at(key);
            node->value = value;
            move_to_head(node);
        }else{
            auto* node = new DLinkedNode(key,value);
            cache[key] = node;
            add_to_head(node);
            ++size;
            if(size>capacity){
                auto deleteTail = delete_tail();
                cache.erase(deleteTail->key);
                delete deleteTail;
                size--;
            }
        }
    }

    void add_to_head(DLinkedNode* node){
        node->prev = head;
        node->next = head->next;
        head->next->prev = node;
        head->next = node;
    }

    void delete_node(DLinkedNode* node){
        node->prev->next = node->next;
        node->next->prev = node->prev;
    }

    DLinkedNode* delete_tail(){
        auto node = tail->prev;
        delete_node(node);
        return node;
    }

    void move_to_head(DLinkedNode* node){
        delete_node(node);
        add_to_head(node);
    }
};

class Test{
public:
    //从nums中组合出小于n的最大的数
    // 1<=n<=10^9, 1<=nums.size()<=10;
    int getMinBelow(int n,vector<int>& nums){
        sort(nums.begin(),nums.end());
        if(nums[0]>=n){
            return -1;
        }
        if(nums.size()==1&&nums[0]==0){
            return 0;
        }
        string n_str = to_string(n);
        int len = n_str.size();
        //总共有len位,查看len位中组合出的最小的数能不能满足
        int min_len = 0;
        for(int i = 0;i<len;i++){
            if(i==0&&nums[0]==0){
                min_len = nums[1];
            }else{
                min_len = min_len*10+nums[0];
            }
        }
        if(min_len >= n){
            int max_len_1 = 0;
            for(int i = 0;i<len-1;i++){
                max_len_1 = max_len_1*10+nums.back();
            }
            return max_len_1;
        }
        //通过DFS递归查找len位
        string ans;
        dfs(n_str,nums,0,ans,true);
        return stoi(ans);
    }

    //
    bool dfs(const string& n_str,const vector<int>& nums,int index,string &ans, bool flag){
        int len = n_str.size();
        cout << "index = " << index << ",ans = " << ans << endl;
        if(index >= len){
            return true;
        }
        int num = n_str[index]-'0';
        if(index == len-1){
            if(flag){
                //找出小于num的
                int cur = -1;
                for(int candi:nums){
                    if(candi<num){
                        cur = candi;
                    }
                }
                if(cur == -1){
                    return false;
                }
                ans += (cur+'0');
                return true;
            }else{
                ans += ('0'+nums.back());
                return true;
            }
        }else{
            if(flag){
                //找出小于等于num的
                int cur = -1;
                for(int candi:nums){
                    if(candi<=num){
                        cur = candi;
                    }
                }
                if(cur == -1){
                    return false;
                }
                ans += (cur+'0');
                bool success = dfs(n_str,nums,index+1,ans,true);
                if(!success){
                    ans.pop_back();
                    //找出比num小的
                    cur = -1;
                    for(int candi:nums){
                        if(candi<num){
                            cur = candi;
                        }
                    }
                    if(cur==-1){
                        return false;
                    }
                    ans += (cur+'0');
                    return dfs(n_str,nums,index+1,ans,false);
                }
                return true;
            }else{
                ans += ('0'+nums.back());
                return dfs(n_str,nums,index+1,ans,false);
            }
        }
    }
};

double binarySearch(const vector<double>& vec,double standard_num){
    int left = 0, right = vec.size()-1;
    while(left<right){
        int mid = (left+right)>>1;
        cout<<"mid = " << mid << endl;
        if(vec[mid]<standard_num){
            left = mid+1;
        }else{
            right = mid;
        }
    }
    cout << "left = " << left << endl;
    if(left==0){
        cout << "l1" << endl;
        return vec[left];
    }
    if(abs(vec[left-1]-standard_num)<=abs(vec[left]-standard_num)){
        cout << "l1" << endl;
        return vec[left-1];
    }else{
        cout << "l1" << endl;
        return vec[left];
    }
}

int main_in(){
    double standard_num;
    vector<double> vec;
    cin>>standard_num;
    int N;
    cin>>N;
    for(int i = 0;i<N;i++){
        double tmp;
        cin>>tmp;
        vec.push_back(tmp);
    }
    for(auto num:vec){
        cout << num << ";;";
    }
    cout <<endl;
    auto ans = binarySearch(vec,standard_num);
    cout << "ans = " << ans;
    return 0;
}
}

namespace P1832{
class Solution {
public:
    bool checkIfPangram(string sentence) {
        vector<bool> show_up(26, false);
        for(char ch:sentence){
            show_up[ch-'a'] = true;
        }
        for(auto bl:show_up){
            if(!bl){
                return false;
            }
        }
        return true;
    }
};
}

namespace P1753{
class Solution {
public:
    int maximumScore(int a, int b, int c) {
        if(a>=c){
            swap(a,c);
        }
        if(b>=c){
            swap(b,c);
        }
        int ans = 0;
        while(a+b>c){
            ans++;
            a--;
            b--;
        }
        ans += min(a+b,c);
        return ans;
    }
};
}

namespace P1799{
class Solution {
public:
    int maxScore(vector<int>& nums) {
        int m = nums.size();
        vector<int> dp(1<<m,0);
        vector<vector<int>> gcd_tmp(m,vector<int>(m));
        for (int i = 0; i < m; ++i) {
            for (int j = i + 1; j < m; ++j) {
                gcd_tmp[i][j] = gcd(nums[i], nums[j]);
            }
        }
        int all = 1 << m;
        for(int s = 1;s < all;++s){
            int t = __builtin_popcount(s);
            if (t & 1) {
                continue;
            }
            for (int i = 0; i < m; ++i) {
                if ((s >> i) & 1) {
                    for (int j = i + 1; j < m; ++j) {
                        if ((s >> j) & 1) {
                            dp[s] = max(dp[s], dp[s ^ (1 << i) ^ (1 << j)] + t / 2 * gcd_tmp[i][j]);
                        }
                    }
                }
            }
        }
        return dp[all - 1];
    }
};
}

namespace P1945{
class Solution {
public:
    int getLucky(string s, int k) {
        string num = "";
        for(char c:s){
            num += to_string(c-'a'+1);
        }
        int res = 0;
        bool is_first = true;
        while(k-->0){
            if(is_first){
                res = getAllSum(num);
                is_first = false;
            }else {
                res = getAllSum(to_string(res));
            }
        }
        return res;
    }

    int getAllSum(const string& n){
        int res = 0;
        for(char c:n){
            res += c-'0';
        }
        return res;
    }
};
}

namespace P1785{
class Solution {
public:
    int minElements(vector<int>& nums, int limit, int goal) {
        long sum = 0;
        for(const int& num:nums){
            sum += num;
        }
        long diff = abs(goal-sum);
        return ceil((double)diff/(double)limit);
    }
};
}

namespace P1801{
class Solution {
private:
    const int MOD = 1000000007;
public:
    int getNumberOfBacklogOrders(vector<vector<int>>& orders) {
        priority_queue<pair<int,int>,vector<pair<int,int>>,less<>> buyOrders;
        priority_queue<pair<int,int>,vector<pair<int,int>>,greater<>> sellOrders;
        for(auto &&order:orders){
            int price = order[0], amount = order[1], type = order[2];
            if(type==0){    //购买订单
                while (amount > 0 && !sellOrders.empty() && sellOrders.top().first <= price) {
                    pair<int,int> sellOrder = sellOrders.top();
                    sellOrders.pop();
                    int sellAmount = min(amount, sellOrder.second);
                    amount -= sellAmount;
                    sellOrder.second -= sellAmount;
                    if (sellOrder.second > 0) {
                        sellOrders.push(sellOrder);
                    }
                }
                if (amount > 0) {
                    buyOrders.emplace(price, amount);
                }
            }else{  //销售订单
                while(amount > 0 && !buyOrders.empty() && buyOrders.top().first >= price){
                    pair<int,int> buyOrder = buyOrders.top();
                    buyOrders.pop();
                    int buyAmount = min(amount,buyOrder.second);
                    amount -= buyAmount;
                    buyOrder.second -= buyAmount;
                    if(buyOrder.second > 0){
                        buyOrders.push(buyOrder);
                    }
                }
                if(amount > 0){
                    sellOrders.emplace(price,amount);
                }
            }
        }
        int total = 0;
        while(!buyOrders.empty()){
            total = (total+buyOrders.top().second)%MOD;
            buyOrders.pop();
        }
        while(!sellOrders.empty()){
            total = (total+sellOrders.top().second)%MOD;
            sellOrders.pop();
        }
        return total;
    }
};
}

namespace P2042{
class Solution {
public:
    bool areNumbersAscending(string s) {
        vector<int> vec;
        for(int i = 0;i<s.size();i++){
            if(isdigit(s[i])){
                string tmp;
                while(isdigit(s[i])){
                    tmp += s[i++];
                }
                vec.push_back(stoi(tmp));
            }
        }
        for(int i = 0;i<vec.size()-1;i++){
            if(vec[i]>=vec[i+1]){
                return false;
            }
        }
        return true;
    }
};
}

namespace P1802{
class Solution {
public:
    int maxValue(int n, int index, int maxSum) {
        int left = 1, right = maxSum;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (valid(mid, n, index, maxSum)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    bool valid(int mid, int n, int index, int maxSum) {
        int left = index;
        int right = n - index - 1;
        return mid + cal(mid, left) + cal(mid, right) <= maxSum;
    }

    long cal(int big, int length) {
        if (length + 1 < big) {
            int small = big - length;
            return (long) (big - 1 + small) * length / 2;
        } else {
            int ones = length - (big - 1);
            return (long) big * (big - 1) / 2 + ones;
        }
    }
};
}

namespace P1803{
class Solution {
    struct Trie{
        array<Trie*,2> son{nullptr, nullptr}; //0为左子树，1为右子树
        int sum;
        Trie(): sum(0){}
    };

    Trie* root = nullptr;
    static constexpr int HIGH_BIT = 14;
public:
    int countPairs(vector<int>& nums, int low, int high) {
        return f(nums, high) - f(nums, low - 1);
    }

    void add(int num) {
        Trie* cur = root;
        for (int k = HIGH_BIT; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (cur->son[bit] == nullptr) {
                cur->son[bit] = new Trie();
            }
            cur = cur->son[bit];
            cur->sum++;
        }
    }

    int get(int num, int x) {
        Trie* cur = root;
        int sum = 0;
        for (int k = HIGH_BIT; k >= 0; k--) {
            int r = (num >> k) & 1;
            if ((x >> k) & 1) {
                if (cur->son[r] != nullptr) {
                    sum += cur->son[r]->sum;
                }
                if (cur->son[r ^ 1] == nullptr) {
                    return sum;
                }
                cur = cur->son[r ^ 1];
            } else {
                if (cur->son[r] == nullptr) {
                    return sum;
                }
                cur = cur->son[r];
            }
        }
        sum += cur->sum;
        return sum;
    }

    int f(vector<int>& nums, int x) {
        root = new Trie();
        int res = 0;
        for (int i = 1; i < nums.size(); i++) {
            add(nums[i - 1]);
            res += get(nums[i], x);
        }
        return res;
    }
};
}

class AuthenticationManager {
    int ttl;
    unordered_map<string,int> token_expire_time;
public:
    AuthenticationManager(int timeToLive) :ttl(timeToLive) {}

    void generate(string tokenId, int currentTime) {
        token_expire_time[tokenId] = currentTime+ttl;
    }

    void renew(string tokenId, int currentTime) {
        clear_expire_tokens(currentTime);
        if(token_expire_time.count(tokenId)){
            token_expire_time[tokenId] = currentTime+ttl;
        }
    }

    int countUnexpiredTokens(int currentTime) {
        clear_expire_tokens(currentTime);
        return token_expire_time.size();
    }
private:
    void clear_expire_tokens(int currentTime){
        vector<string> expireTokens;
        for(auto it = token_expire_time.begin();it != token_expire_time.end();it++){
            if(it->second<=currentTime){
                expireTokens.push_back(it->first);
            }
        }
        for(const auto &str:expireTokens){
            token_expire_time.erase(str);
        }
    }
};

namespace P1223{
class Solution {
public:
    static constexpr int mod = 1E9 + 7;
    int dieSimulator(int n, vector<int>& rollMax) {
        vector d(n + 1, vector(6, vector<int>(16)));
        for (int j = 0; j < 6; j++) {
            d[1][j][1] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 1; k <= rollMax[j]; k++) {
                    for (int p = 0; p < 6; p++) {
                        if (p != j) {
                            d[i][p][1] = (d[i][p][1] + d[i - 1][j][k]) % mod;
                        } else if (k + 1 <= rollMax[j]) {
                            d[i][p][k + 1] = (d[i][p][k + 1] + d[i - 1][j][k]) % mod;
                        }
                    }
                }
            }
        }

        int res = 0;
        for (int j = 0; j < 6; j++) {
            for (int k = 1; k <= rollMax[j]; k++) {
                res = (res + d[n][j][k]) % mod;
            }
        }
        return res;
    }
};
}

namespace palibaba1{
class Solution{
public:
    vector<vector<int>> directions = {{-1,0},{1,0},{0,-1},{0,1}};

    struct step{
        vector<pair<int,int>> path;
        int st;

        step(){st = 0;}
        step(int i, int j){
            path.emplace_back(i,j);
            st = 1;
        }
    };

    vector<pair<int,int>> getPath(vector<string> &matrix,const string& str){
        int n = matrix.size(), m = matrix[0].size();
        queue<step> qu;
        for(int i = 0;i<matrix.size();i++){
            for(int j = 0;j<matrix[i].size();j++){
                if(matrix[i][j]==str[0]){
                    qu.emplace(i,j);
                }
            }
        }
        while(!qu.empty()){
            auto& cur = qu.front();
            qu.pop();
            if(cur.st == str.size()){
                return cur.path;
            }
            const auto& cur_location = cur.path.back();
            int curx = cur_location.first, cury = cur_location.second;
            char next = str[cur.st];
            //找周围
            for(int i = 0;i<4;i++){
                int nextx = curx+directions[i][0],nexty = cury+directions[i][1];
                if(nextx>=0&&nextx<n&&nexty>=0&&nexty<m&&matrix[nextx][nexty]==next){
                    cur.path.emplace_back(nextx,nexty);
                    cur.st++;
                    qu.push(cur);
                }
            }
        }
        return vector<pair<int,int>>();
    }
};

class ChessMatch{
public:
    typedef vector<pair<int,int>> Operator;
    Operator operators;
    vector<vector<int>> keyBoard;

    ChessMatch(){
        keyBoard = vector<vector<int>>(19,vector<int>(19,0));
    }

    bool playChess(int x,int y,int type){
        //type == 1表示下白棋,type == 2表示下黑棋
        if(type == 1){
            if(keyBoard[x][y]==0){
                keyBoard[x][y] = 1;
                clearBoard();
                operators.emplace_back(x,y);
                return true;
            }
        }else if(type == 2){
            if(keyBoard[x][y]==2){
                keyBoard[x][y] = 2;
                clearBoard();
                operators.emplace_back(x,y);
                return true;
            }
        }
        return false;
    }

    pair<int,int> record(int step){
        if(step < operators.size()){
            return operators[step];
        }
        return {-1,-1};
    }

    vector<vector<int>> getBoardByN(int n){
        for(int i = 0;i<n;i++){
            const auto& curOper = operators[i];
            if(i%2==0){
                playChess(curOper.first,curOper.second,1);
            }else{
                playChess(curOper.first,curOper.second,2);
            }
        }
    }
private:
    //处理围棋棋盘，死掉的棋子变为0
    void clearBoard(){

    }
};
}

namespace ali_operating_system{
    size_t getOrder(vector<int> nums, int target){
        size_t left = 0, right = nums.size();
        while(left < right){
            auto mid = (left+right)>>1;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return right;
    }
}


int main() {
    vector<int> vec = {1,10,15,20,30,40,50};
    vector<int> testings = {0,15,20,30,35,45,60};
    for(int test:testings){
        cout << test <<"," << ali_operating_system::getOrder(vec,test) << endl;
    }
}
