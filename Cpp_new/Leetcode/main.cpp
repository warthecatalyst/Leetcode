#include <iostream>
#include <typeinfo>
#include "p2865.h"


int main(){
    using p2865::Solution;
    auto solution = new Solution();
    vector<int> val = {6,5,3,9,2,7};
    solution->maximumSumOfHeights(val);
}