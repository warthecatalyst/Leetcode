package Pro1599;

import java.util.*;
public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int n = customers.length;
        int ans = -1, maxProfit = 0, totalProfit = 0, operations = 0, customersCnt = 0;
        for(int i = 0; i < n; i++) {
            operations++;
            customersCnt += customers[i];
            int curCustomers = Math.min(customersCnt, 4);
            customersCnt -= curCustomers;
            totalProfit += boardingCost * curCustomers - runningCost;
            if (totalProfit > maxProfit) {
                maxProfit = totalProfit;
                ans = operations;
            }
        }
        if (customersCnt == 0) {
            return ans;
        }
        int profitEachTime = boardingCost * 4 - runningCost;
        if (profitEachTime <= 0) {
            return ans;
        }
        if (customersCnt > 0) {
            int fullTimes = customersCnt / 4;
            totalProfit += profitEachTime * fullTimes;
            operations += fullTimes;
            if (totalProfit > maxProfit) {
                maxProfit = totalProfit;
                ans = operations;
            }
            int remainingCustomers = customersCnt % 4;
            int remainingProfit = boardingCost * remainingCustomers - runningCost;
            totalProfit += remainingProfit;
            if (totalProfit > maxProfit) {
                ans++;
            }
        }
        return ans;
    }
}
