package DataStructure.NodeQueue;

class Node<T>{
    T data;
    Node<T> next;
    public Node(T data){
        this.data = data;
    }
}

public class NodeQueue {
    Node<Integer> first;
    Node<Integer> cur;

    public NodeQueue add(Integer integer){
        cur.next = new Node<>(integer);
        cur = cur.next;
        return this;
    }

    public Integer pop(){
        int ret = first.next.data;
        first.next = first.next.next;
        return ret;
    }
}
