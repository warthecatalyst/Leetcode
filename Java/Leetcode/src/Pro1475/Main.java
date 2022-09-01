package Pro1475;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        for(int i = 0;i<n;i++){
            int pricesI = prices[i];
            for(int j = i+1;j<n;j++){
                if(prices[j]<=pricesI){
                    prices[i] = pricesI-prices[j];
                    break;
                }
            }
        }
        return prices;
    }
}
