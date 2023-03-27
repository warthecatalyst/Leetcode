package interview.tencent.Pro3;

import java.util.*;

// A : 100 2 3 1 5 6
// B: 0 1 2 0 2 1
// 构造C: 6 2 4 1 5 3 结果98
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static class Order implements Comparable<Order>{
        int index;
        int val;
        Order(int index,int val){
            this.index = index;
            this.val = val;
        }

        @Override
        public int compareTo(Order o) {
            return Integer.compare(val,o.val);
        }
    }
    static int N;
    static Order[] orders;  //保存A
    static int[] B; //保存b
    public static void main(String[] args) {
        N = scanner.nextInt();
        orders = new Order[N];
        B = new int[N];
        for(int i = 0;i<N;i++){
            int val = scanner.nextInt();
            orders[i] = new Order(i,val);
        }
        Arrays.sort(orders);
        for(int i=0;i<N;i++){
            B[i] = scanner.nextInt();
        }
        int[] dp = new int[N];
        int cnt0 = 0,cnt1 = 0;
        for(int i = N-1;i>=0;i--){
            if(B[i]==2){
                dp[i] = cnt0+cnt1;
            } else if (B[i] == 1) {
                dp[i] = cnt0;
                cnt1++;
            }else{
                cnt0++;
            }
        }
        //System.out.println(Arrays.toString(dp));
        int[] val2indexes = new int[N+1];
        long ans = 0;
        Arrays.fill(val2indexes,-1);
        for(Order order:orders){
            int idx = order.index;
            int minVal = 1+dp[idx]; //至少这么高
            while(minVal<N&&val2indexes[minVal]!=-1){
                minVal++;
            }
            int cnt = 0;
            for(int i=1;i<minVal&&dp[i]>0;i++){
                if(val2indexes[i]!=-1&&val2indexes[i]<idx){ //左边已经有多少个比当前的更小的了,说明右边还要多多少个
                    cnt++;
                }
            }
            //System.out.println("cnt = "+cnt);
            minVal += cnt;
            //System.out.println("minVal = "+minVal);
            while(minVal<N&&val2indexes[minVal]!=-1){  //如果当前被别人占了就继续加
                minVal++;
            }
            val2indexes[minVal] = idx;
            //System.out.println(Arrays.toString(val2indexes));
            ans += Math.abs(order.val-minVal);
        }
//        int[] result = new int[N];
//        for(int i = 1;i<=N;i++){
//            result[val2indexes[i]] = i;
//        }
//        System.out.println(Arrays.toString(result));
        System.out.println(ans);
    }
}
