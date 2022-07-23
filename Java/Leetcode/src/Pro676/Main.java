package Pro676;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

    }
}

class MagicDictionary {
    private Set<String> stringSet;
    public MagicDictionary() {
        stringSet = new HashSet<>();
    }

    public void buildDict(String[] dictionary) {
        stringSet.addAll(Arrays.asList(dictionary));
    }

    public boolean search(String searchWord) {
        for(String str:stringSet){
            if(canReplace(str,searchWord)){
                return true;
            }
        }
        return false;
    }

    private boolean canReplace(String str1,String str2){
        if(str1.length()!=str2.length()){
            return false;
        }
        int cnt = 0;
        for(int i = 0;i<str1.length();i++){
            char c1 = str1.charAt(i),c2 = str2.charAt(i);
            if(c1!=c2){
                cnt++;
                if(cnt>2){
                    return false;
                }
            }
        }
        return cnt==1;
    }
}

