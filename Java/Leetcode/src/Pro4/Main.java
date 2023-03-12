package Pro4;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        double res = solution.findMedianSortedArrays(new int[]{1,3},new int[]{2});
        System.out.println(res);
    }
}

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[] numsAll = new int[n+m];
        int i = 0,j = 0;
        double res = 0;
        while(i<=n&&j<=m){
            if(i==n&&j==m){
                break;
            }
            if(i==n){
                numsAll[i+j] = nums2[j];
                j++;
            }else if(j==m){
                numsAll[i+1] = nums1[i];
                i++;
            } else{
                if(nums1[i]>=nums2[j]){
                    numsAll[i+j] = nums2[j];
                    j++;
                }else{
                    numsAll[i+j] = nums1[i];
                    i++;
                }
            }
        }
        //System.out.println(Arrays.toString(numsAll));
        int k = i+j;
        if(k%2==0){
            res = numsAll[k/2-1]+numsAll[k/2];
            res /= 2;
        }else{
            res = numsAll[k/2];
        }
        return res;
    }
}