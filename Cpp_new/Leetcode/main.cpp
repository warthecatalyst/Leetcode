#include <vector>
#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

int n,m;
vector<int> a;


int main() {
    cin >> n >> m;
    a.resize(n);
    vector<int> oddIndex;
    vector<int> evenIndex;
    for (int i = 0; i < n;i++) {
        cin >> a[i];
        if(a[i] % 2 == 0) {
            evenIndex.push_back(i);
        } else {
            oddIndex.push_back(i);
        }
    }
    bool is_all = false;
    for(int loop = 0; loop < m;loop++) {
        int t, x;
        cin >> t >> x;
        if (is_all) {
            if (t % 2 != a[0] % 2) {
                continue;
            }
            std::transform(a.begin(), a.end(), a.begin(), [x](int &n) {
                return n + x;
            });
        } else {
            if (t == 1) {
                for(int odIndex : oddIndex) {
                    a[odIndex] += x;
                }
                //奇数变成偶数
                if (x%2 == 1) {
                    evenIndex.insert(evenIndex.end(), oddIndex.begin(),oddIndex.end());
                    oddIndex.clear();
                    is_all = true;
                }
            } else {
                for(int evIndex : evenIndex) {
                    a[evIndex] += x;
                }
                // 偶数变成奇数
                if (x % 2 == 1) {
                    oddIndex.insert(oddIndex.end(), evenIndex.begin(), evenIndex.end());
                    evenIndex.clear();
                    is_all = true;
                }
            }
        }
    }
    for (int val : a) {
        cout << val << " ";
    }
}