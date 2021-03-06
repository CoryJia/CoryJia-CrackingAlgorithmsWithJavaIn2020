package adt;

public class ListNode<K, V extends Comparable<?>> {
    public K val;
    public V v;   // To contain the key, e pair.
    public ListNode<K, V> next;
    public ListNode<K, V> prev;
    public ListNode<K, V> tail;

    boolean isVisited;

//    public int e;
//    public ListNode next;
//    public ListNode prev;
//    public ListNode tail;

//    public ListNode(int x) {
//        e = x;
//        next = null;
//        prev = null;
//        tail = this;
//
//        isVisited = false;
//    }


    public ListNode() {
        this(null);
//        e = null;
//        next = null;
//        prev = null;
//        tail = null;
    }

    public ListNode(K k) {
        this.val = k;
        next = null;
        prev = null;
        tail = null;

        isVisited = false;
    }

    public ListNode(K k, V v) {
        this.val = k;
        this.v = v;
        next = null;
        prev = null;
        tail = null;

        isVisited = false;
    }

    public static void print(ListNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }

        while (head != null) {
            if (head.next != null)
            System.out.print(head.val + "->");
            else System.out.println(head.val);
            head = head.next;
        }
        System.out.println();
    }

    public static ListNode generateLinkedList(String input) {

        ListNode[] nodes = new ListNode[input.length() + 1];

        for (int i = 0; i < input.length(); i++) {
            nodes[i] = new ListNode(input.charAt(i));
        }

        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i].next = nodes[i + 1];

        }

        return nodes[0];
    }

    public static ListNode generateLinkedList(int input) {
        if (input == 0) return null;

        ListNode[] nodes = new ListNode[input + 1];

        for (int i = 1; i <= input; i++) {
            nodes[i - 1] =  new ListNode(i);
        }

        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i].next = nodes[i + 1];

        }

        return nodes[0];
    }

    public static ListNode tail(ListNode head) {
        if (head == null) return null;

        ListNode curr = head;

        while (curr.next != null) {
            curr = curr.next;
        }

        return curr;
    }

    public static int length(ListNode head) {
//        int len = 0;
//        ListNode cur = head;
//        while (cur != null) {
//            cur = cur.next;
//            ++len;
//        }
//        return len;

        if (head == null) return 0;

        return 1 + length(head.next);
    }


    public static ListNode generateLinkedList(int start, int end) {
        if (end - start == 0) return null;

        ListNode[] nodes = new ListNode[Math.abs(end - start) + 2];


        if (start < end) {
            for (int i = start; i <= end; i++) {
                nodes[i - start] = new ListNode(i);
            }
        } else {
            for (int i = start; i >= end; i--) {
                nodes[start - i] = new ListNode(i);
            }
        }

        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }

        return nodes[0];
    }

    public static ListNode deepCopy(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode first = dummy;
        ListNode cur = head;

        while (cur != null) {
            first.next = cur;
            first = cur;
            cur = cur.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = generateLinkedList("123");
        print(l1);

        System.out.println(length(l1));
        System.out.println("The e of tail node is:");
        System.out.println(tail(l1).val);

        ListNode l2 = generateLinkedList(3);
        print(l2);
    }

}
