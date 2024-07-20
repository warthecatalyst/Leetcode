#include "p3096.h"

int main()
{
    using p3096::Solution;
    auto solu = new Solution();
    vector<int> possible = {1, 1, 1, 1, 1};
    auto ans = solu->minimumLevels(possible);
    cout << ans << endl;
    return 0;
}