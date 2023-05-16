package Pro831;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public String maskPII(String s) {
        if(s.contains("@")){
            String[] strings = s.split("@");
            String name = strings[0], domain  = strings[1];
            StringBuilder res = new StringBuilder();
            res.append(Character.toLowerCase(name.charAt(0))).append("*****").append(Character.toLowerCase(name.charAt(name.length()-1)));
            res.append("@");
            for(char c:domain.toCharArray()){
                if(Character.isUpperCase(c)){
                    res.append(Character.toLowerCase(c));
                }else{
                    res.append(c);
                }
            }
            return res.toString();
        }else{
            StringBuilder digits = new StringBuilder();
            for(char c:s.toCharArray()){
                if(Character.isDigit(c)){
                    digits.append(c);
                }
            }
            StringBuilder res = new StringBuilder();
            switch (digits.length()){
                case 10:{
                    for(int i = 0;i<10;i++){
                        if(i==3||i==6){
                            res.append('-');
                        }
                        res.append(digits.charAt(i));
                    }
                    break;
                }
                case 11:{
                    res.append('+');
                    for(int i = 0;i<11;i++){
                        if(i==1||i==4||i==7){
                            res.append('-');
                        }
                        res.append(digits.charAt(i));
                    }
                    break;
                }
                case 12:{
                    res.append('+');
                    for(int i = 0;i<11;i++){
                        if(i==2||i==5||i==8){
                            res.append('-');
                        }
                        res.append(digits.charAt(i));
                    }
                    break;
                }
                case 13:{
                    res.append('+');
                    for(int i = 0;i<11;i++){
                        if(i==3||i==6||i==9){
                            res.append('-');
                        }
                        res.append(digits.charAt(i));
                    }
                    break;
                }
            }
            return res.toString();
        }
    }
}
