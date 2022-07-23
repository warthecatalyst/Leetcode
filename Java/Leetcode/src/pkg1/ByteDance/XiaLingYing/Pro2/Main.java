package pkg1.ByteDance.XiaLingYing.Pro2;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    Map<String,Deque<Integer>> scope = new HashMap<>();
    int start = 0;
    public int evaluate(String expression) {
        return innerEvaluate(expression);
    }

    private int innerEvaluate(String expression){
        if(expression.charAt(start)!='('){
            if(Character.isLowerCase(expression.charAt(start))){
                String var = parseVar(expression);
                return scope.get(var).peek();
            }else{
                return parseInt(expression);
            }
        }
        int ret;
        start++;    //过滤'('
        if(expression.charAt(start)=='l'){  //let表达式
            start+=4;   //过滤let
            List<String> vars = new ArrayList<>();
            while(true){
                if (!Character.isLowerCase(expression.charAt(start))) {
                    ret = innerEvaluate(expression); // let 表达式的最后一个 expr 表达式的值
                    break;
                }
                String var = parseVar(expression);
                if (expression.charAt(start) == ')') {
                    ret = scope.get(var).peek(); // let 表达式的最后一个 expr 表达式的值
                    break;
                }
                vars.add(var);
                start++; // 移除空格
                int e = innerEvaluate(expression);
                scope.putIfAbsent(var, new ArrayDeque<>());
                scope.get(var).push(e);
                start++; // 移除空格
            }
            for(String var:vars){
                scope.get(var).pop();
            }
        }else if(expression.charAt(start)=='a'){    //add表达式
            start+=4;
            int e1 = innerEvaluate(expression);
            start++;
            int e2 = innerEvaluate(expression);
            ret = e1+e2;
        }else{
            start+=5;
            int e1 = innerEvaluate(expression);
            start++;
            int e2 = innerEvaluate(expression);
            ret = e1*e2;
        }
        start++;
        return ret;
    }

    private int parseInt(String expression){
        int n = expression.length();
        int ret = 0, sign = 1;
        if (expression.charAt(start) == '-') {
            sign = -1;
            start++;
        }
        while (start < n && Character.isDigit(expression.charAt(start))) {
            ret = ret * 10 + (expression.charAt(start) - '0');
            start++;
        }
        return sign * ret;
    }

    private String parseVar(String expression){
        int n = expression.length();
        StringBuffer ret = new StringBuffer();
        while (start < n && expression.charAt(start) != ' ' && expression.charAt(start) != ')') {
            ret.append(expression.charAt(start));
            start++;
        }
        return ret.toString();
    }
}