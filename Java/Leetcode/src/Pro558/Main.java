package Pro558;

public class Main {
    public static void main(String[] args) {

    }
}

class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};

class Solution {
    public Node intersect(Node quadTree1, Node quadTree2) {
        if(quadTree1.isLeaf){
            if(quadTree1.val){
                Node node = new Node();
                node.val = true;
                node.isLeaf = true;
                return node;
            }
            return new Node(quadTree2.val,quadTree2.isLeaf,quadTree2.topLeft,quadTree2.topRight,quadTree2.bottomLeft,quadTree2.bottomRight);
        }
        if(quadTree2.isLeaf){
            return intersect(quadTree2, quadTree1);
        }
        Node o1 = intersect(quadTree1.topLeft,quadTree2.topLeft);
        Node o2 = intersect(quadTree1.topRight,quadTree2.topRight);
        Node o3 = intersect(quadTree1.bottomLeft,quadTree2.bottomLeft);
        Node o4 = intersect(quadTree1.bottomRight,quadTree2.bottomRight);
        if(o1.isLeaf&&o2.isLeaf&&o3.isLeaf&o4.isLeaf&&o1.val==o2.val&&o1.val==o3.val&&o1.val==o4.val){
            Node node = new Node();
            node.val = o1.val;
            node.isLeaf = true;
            return node;
        }
        return new Node(false,false,o1,o2,o3,o4);
    }
}
