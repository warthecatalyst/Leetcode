package Pro1096;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public List<String> braceExpansionII(String expression) {
        Deque<Character> op = new ArrayDeque<>();
        List<Set<String>> stk = new ArrayList<>();

        for(int i = 0;i<expression.length();i++){
            if(expression.charAt(i)==','){
                //不断地弹出栈顶的运算符，直到栈为空或栈顶不为乘号
                while(!op.isEmpty()&&op.peek()=='*'){
                    ope(op,stk);
                }
                op.push('+');
            }else if(expression.charAt(i)=='{'){
                //首先判断是否需要添加乘号,再将{入栈
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLetter(expression.charAt(i - 1)))) {
                    op.push('*');
                }
                op.push('{');
            }else if(expression.charAt(i) == '}'){
                while(!op.isEmpty()&&op.peek()!='{'){
                    ope(op,stk);
                }
                op.pop();
            }else{
                // 首先判断是否需要添加乘号，再将新构造的集合添加到集合栈中
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLetter(expression.charAt(i - 1)))) {
                    op.push('*');
                }
                StringBuilder sb = new StringBuilder();
                sb.append(expression.charAt(i));
                stk.add(new TreeSet<>() {{
                    add(sb.toString());
                }});
            }
        }
        while(!op.isEmpty()){
            ope(op, stk);
        }
        return new ArrayList<>(stk.get(stk.size()-1));
    }


    public void ope(Deque<Character> operation,List<Set<String>> stk){
        int left = stk.size()-2, right = stk.size()-1;
        if(operation.peek()=='+'){
            stk.get(left).addAll(stk.get(right));
        }else{
            Set<String> temp = new TreeSet<>();
            for(String leftStr:stk.get(left)){
                for(String rightStr:stk.get(right)){
                    temp.add(leftStr+rightStr);
                }
            }
            stk.set(left,temp);
        }
        operation.pop();
        stk.remove(stk.size()-1);
    }
}
