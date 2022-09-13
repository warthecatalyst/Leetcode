package Pro670;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.maximumSwap(98368);
        System.out.println(ans);
    }
}

class Solution {
    public int maximumSwap(int num) {
        String numstr = String.valueOf(num);
        int len = numstr.length();
        for(int i = 0;i<len;i++){
            char max = numstr.charAt(i);
            int maxIndex = -1;
            //System.out.println("cur char = "+max);
            for(int j = i+1;j<len;j++){
                if(numstr.charAt(j)>max||maxIndex!=-1&&numstr.charAt(j)==max){
                    max = numstr.charAt(j);
                    //System.out.println("greater char = "+max);
                    maxIndex = j;
                }
            }
            if(maxIndex!=-1){
                StringBuilder sb = new StringBuilder(numstr);
                char ch = sb.charAt(i);
                sb.setCharAt(i,max);
                sb.setCharAt(maxIndex,ch);
                return Integer.parseInt(sb.toString());
            }
        }
        return num;
    }
}
