package Pro809;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.expressiveWords("dddiiiinnssssssoooo"
                ,new String[]{"dinnssoo","ddinso","ddiinnso","ddiinnssoo","ddiinso","dinsoo","ddiinsso","dinssoo","dinso"});
        System.out.println(res);
    }
}

class Solution {
    public int expressiveWords(String s, String[] words) {
        int res = 0;
        for(String word:words){
            if(canBeExpanded(word,s)){
                res++;
            }
        }
        return res;
    }

    public boolean canBeExpanded(String src,String dst){
        //System.out.println("src = "+src+", dst = "+dst);
        if(src.length()>dst.length()) return false;
        if(src.length()==dst.length()) return src.equals(dst);
        int i = 0,j = 0;
        while(i<src.length()&&j<dst.length()) {
            if(src.charAt(i)!=dst.charAt(j)){
                return false;
            }
            char ch = src.charAt(i);
            int cnt_src = 0;
            while(i< src.length()&&src.charAt(i)==ch){
                cnt_src++;
                i++;
            }
            int cnt_dst = 0;
            while(j<dst.length()&&dst.charAt(j)==ch){
                cnt_dst++;
                j++;
            }
            if(cnt_src>cnt_dst){
                return false;
            }
            if(cnt_src!=cnt_dst&&cnt_dst<3){
                return false;
            }
        }
        return i == src.length() && j == dst.length();
    }
}
