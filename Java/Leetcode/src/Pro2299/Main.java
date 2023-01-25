package Pro2299;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public boolean strongPasswordCheckerII(String password) {
        if(password == null || password.length()<8){
            return false;
        }
        Set<Character> set = new HashSet<>();
        for(char c:"!@#$%^&*()-+".toCharArray()){
            set.add(c);
        }
        boolean hasLower = false, hasUpper = false, hasNum = false, hasSpecial = false;
        for(int i = 0;i<password.length();i++){
            if(i!=password.length()-1&&password.charAt(i+1)==password.charAt(i)){
                return false;
            }
            char ch = password.charAt(i);
            if(Character.isDigit(ch)){
                hasNum = true;
            }
            if(Character.isLowerCase(ch)){
                hasLower = true;
            }
            if(Character.isUpperCase(ch)){
                hasUpper = true;
            }
            if(set.contains(ch)){
                hasSpecial = true;
            }
        }
        return hasLower&&hasUpper&&hasNum&&hasSpecial;
    }
}
