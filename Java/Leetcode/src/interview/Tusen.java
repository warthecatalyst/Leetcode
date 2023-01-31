package interview;


import java.util.*;

//图森未来面试题
public class Tusen {
    public static void main(String[] args) {
        Tusen tusen = new Tusen();
        int[] arr = new int[]{1,15,7,9,2,5,10};
        long ans = tusen.findMaxUpdate(arr,3);
        System.out.println(ans);
    }

    //给定n个任务，每个任务的执行时间都为1小时，但现在由于机械出现了故障，
    //导致所有的任务延后了k个小时执行，costs[i]表示原定于第i个小时执行的任务延后一小时的损失。
    //现在，需要重新规划任务流程，规定现在的执行时间不能早于原定的执行时间。
    //返回最小损失
    public int findMinCost(int n,int k,int[] costs){
        List<int[]> costsWithIndex = new ArrayList<>();
        for(int i = 0;i<n;i++){
            costsWithIndex.add(new int[]{costs[i],i});
        }
        costsWithIndex.sort(Comparator.comparingInt(o -> o[0]));
        int[][] newCostArr = new int[n][2];
        for(int i = 0;i<costsWithIndex.size();i++){
            int beginTime = Math.max(costsWithIndex.get(i)[1],i+k);
            while(newCostArr[beginTime]!=null){
                beginTime++;
            }
            newCostArr[beginTime] = costsWithIndex.get(i);
        }
        int res = 0;
        for(int i = 0;i<newCostArr.length;i++){
            res += (i+k-newCostArr[i][1])*newCostArr[i][0];
        }
        return res;
    }

    // 给定一个数组arr，和一个长度k，我们可以将数组分隔为多个长度最大为k的子数组，
    // 在完成分隔后，子数组内所有值都会变为子数组中元素的最大值。
    // 返回最好的分隔方法下，数组元素之和
    public long findMaxUpdate(int[] arr,int k){
        int n = arr.length;
        long[] value = new long[n];
        for(int end = 0;end<n;end++){
            int start = Math.max(end-k+1,0);
            int maxVal = arr[start];
            for(int i = start+1;i<=end;i++){
                maxVal = Math.max(maxVal,arr[i]);
            }
            for(int i = start;i<=end;i++){
                value[end] += maxVal-arr[i];
            }
        }
//        for(long val:value){
//            System.out.print(val+" ");
//        }
//        System.out.println();
        long dp[] = new long[n];
        for(int i = 0;i<n;i++){
            if(i<k){
                dp[i] = value[i];
            }else{
                dp[i] = dp[i-k]+value[i];
                for(int j = i-k+1;j<i;j++){
                    int maxVal = arr[j+1];
                    for(int l = j+1;l<=i;l++){
                        maxVal = Math.max(maxVal,arr[l]);
                    }
                    long addVal = 0;
                    for(int l = j+1;l<=i;l++){
                        addVal += maxVal-arr[l];
                    }
                    //System.out.println("j = "+j+", dp[j] + addVal = "+(dp[j]+addVal));
                    dp[i] = Math.max(dp[i],dp[j]+addVal);
                }
            }
        }
        for(long val:dp){
            System.out.print(val+" ");
        }
        System.out.println();
        long sum = Arrays.stream(arr).sum();
        return dp[n-1]+sum;
    }
}
