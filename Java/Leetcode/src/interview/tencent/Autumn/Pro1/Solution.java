package interview.tencent.Autumn.Pro1;

import java.util.*;

class TreeNode {
     int val = 0;
     TreeNode left = null;
     TreeNode right = null;
     public TreeNode(int val) {
         this.val = val;
     }
}

public class Solution {
    int count;
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 返回节点权值1个数比0的个数多一的路径数
     * @param root TreeNode类 权值为0和1的二叉树根节点
     * @return int整型
     */
    public int pathNumber (TreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        // write code here
        count = 0;
        dfs(root,0,0);
        return count;
    }

    private void dfs(TreeNode cur,int oneCount,int zeroCount) {
        if (cur.val == 0) {
            zeroCount++;
        } else {
            oneCount++;
        }
        if(Objects.isNull(cur.left) && Objects.isNull(cur.right)) {
            // 左右节点都为空引用，为叶子结点
            if (oneCount == zeroCount+1) {
                count++;
            }
        }
        if (Objects.nonNull(cur.left)) {
            dfs(cur.left, oneCount, zeroCount);
        }
        if (Objects.nonNull(cur.right)) {
            dfs(cur.right, oneCount, zeroCount);
        }
    }

//    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(0);
//        root.left.left = new TreeNode(1);
//        root.left.right = new TreeNode(0);
//        root.right = new TreeNode(0);
//        root.right.right = new TreeNode(1);
//        Solution solution = new Solution();
//        int ans = solution.pathNumber(root);
//        System.out.println(ans);
//    }
}