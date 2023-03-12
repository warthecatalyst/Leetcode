package interview.Point24;

import java.util.*;

public class Main {
    //实现一个能够记录运算结果的24点
    public static void main(String[] args) {
        Soluion soluion = new Soluion();
        Set<String> ans = soluion.get24Points(new int[]{6,6,6,6});
        for(String a:ans){
            System.out.println(a);
        }
    }
}

class Soluion{
    static final int TARGET = 24;
    static final double EPSILON = 1e-6;
    static final int ADD = 0, MUL = 1, SUB = 2,DIV = 3;
    Set<String> ans;

    public Set<String> get24Points(int[] nums){
        ans = new HashSet<>();
        List<Double> list = new ArrayList<>();
        List<StringBuilder> stringBuilders = new ArrayList<>();
        for(int num:nums){
            list.add((double) num);
            stringBuilders.add(new StringBuilder().append((double)num));
        }
        solve(list,stringBuilders);
        return ans;
    }

    public void solve(List<Double> list, List<StringBuilder> stringBuilders){
        if(list.size() == 0){
            return;
        }
        if(list.size() == 1){
            if(Math.abs(list.get(0) - TARGET)<EPSILON){
                //System.out.println(list.get(0));
                //System.out.println(stringBuilders.get(0));
                ans.add(stringBuilders.get(0).toString());
            }
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    List<Double> list2 = new ArrayList<>();
                    List<StringBuilder> sblist2 = new ArrayList<>();
                    for (int k = 0; k < size; k++) {
                        if (k != i && k != j) {
                            list2.add(list.get(k));
                            sblist2.add(stringBuilders.get(k));
                        }
                    }
                    for (int k = 0; k < 4; k++) {
                        if (k < 2 && i > j) {
                            continue;
                        }
                        StringBuilder s2 = new StringBuilder();
                        if (k == ADD) {
                            list2.add(list.get(i) + list.get(j));
                            s2.append('(')
                                    .append(stringBuilders.get(i))
                                    .append('+')
                                    .append(stringBuilders.get(j))
                                    .append(')');
                            sblist2.add(s2);
                        } else if (k == MUL) {
                            list2.add(list.get(i) * list.get(j));
                            s2.append('(')
                                    .append(stringBuilders.get(i))
                                    .append('*')
                                    .append(stringBuilders.get(j))
                                    .append(')');
                            sblist2.add(s2);
                        } else if (k == SUB) {
                            list2.add(list.get(i) - list.get(j));
                            s2.append('(')
                                    .append(stringBuilders.get(i))
                                    .append('-')
                                    .append(stringBuilders.get(j))
                                    .append(')');
                            sblist2.add(s2);
                        } else {
                            if (Math.abs(list.get(j)) < EPSILON) {
                                continue;
                            } else {
                                list2.add(list.get(i) / list.get(j));
                                s2.append('(')
                                        .append(stringBuilders.get(i))
                                        .append('/')
                                        .append(stringBuilders.get(j))
                                        .append(')');
                                sblist2.add(s2);
                            }
                        }
                        solve(list2,sblist2);
                        list2.remove(list2.size()-1);
                        sblist2.remove(sblist2.size()-1);
                    }
                }
            }
        }
    }
}
