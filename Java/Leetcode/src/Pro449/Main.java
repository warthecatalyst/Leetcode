package Pro449;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode treeNode = new TreeNode(100);
        treeNode.left = new TreeNode(50);
        treeNode.right = new TreeNode(150);
        String str = codec.serialize(null);
        System.out.println(str);
        TreeNode treeNode1 = codec.deserialize(str);
        System.out.println(treeNode1.val);
        System.out.println(treeNode1.left.val);
        System.out.println(treeNode1.right.val);
    }
}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder(dfs_node(root));
        if (isEmptyString(stringBuilder.toString())) {
            return "";
        }
        return stringBuilder.substring(1,stringBuilder.length()-1);
    }

    private String dfs_node(TreeNode node) {
        if(Objects.isNull(node)) {
            return "";
        }
        return "_" + node.val + "_" + dfs_node(node.left) + dfs_node(node.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(isEmptyString(data)) {
            return null;
        }
        String[] strings = data.split("__");
        return dfs_str(strings);
    }

    private TreeNode dfs_str(String[] datas) {
        if (Objects.isNull(datas) || datas.length == 0) {
            return null;
        }
        TreeNode curr = new TreeNode(Integer.parseInt(datas[0]));
        int index = -1;
        for(int i = 1; i < datas.length;i++) {
            if(Integer.parseInt(datas[i]) > Integer.parseInt(datas[0])) {
                index = i;
                break;
            }
        }
        if(index == -1) {
            curr.left = dfs_str(Arrays.copyOfRange(datas, 1, datas.length));
            curr.right = dfs_str(null);
        } else {
            curr.left = dfs_str(Arrays.copyOfRange(datas, 1, index));
            curr.right = dfs_str(Arrays.copyOfRange(datas, index, datas.length));
        }
        return curr;
    }

    private boolean isEmptyString(String str) {
        return Objects.isNull(str) ||str.isEmpty();
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
