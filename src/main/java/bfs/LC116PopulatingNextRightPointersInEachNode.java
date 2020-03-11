package bfs;

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}

public class LC116PopulatingNextRightPointersInEachNode {
    public Node connect(Node root) {
        if(root == null) return null;

        Node cur = root;
        Node head = null;
        Node pre = null;

        while(cur != null){
            while(cur != null){
                if(cur.left != null){
                    if(head == null){
                        head = cur.left;
                    }else{
                        pre.next = cur.left;
                    }
                    pre = cur.left;
                }

                if(cur.right != null){
                    if(head != null){
                        pre.next = cur.right;
                    }

                    pre = cur.right;
                }

                cur = cur.next;
            }

            cur = head;
            head = null;
            pre = null;
        }

        return root;
    }
}
