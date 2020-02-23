package adt;

public class MyLinkedList<E> {
    private ListNode head;
    private ListNode tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addHead(E e) {
        ListNode newHead = new ListNode(e);
        size++;

        if (head == null) {
            tail = newHead;
        } else {
            newHead.next = head;
            head.prev = newHead;
        }
        head = newHead;

    }

    public void addTail(E e) {
        ListNode newTail = new ListNode(e);
        size++;
        if (tail == null) {
            head = newTail;
            tail = newTail;
        } else {
            tail.next = newTail;
            newTail.prev = tail;
            tail = newTail;
        }
    }

    public void remove(E e){

    }

    public E get(int index) {
        if (head == null) throw new NullPointerException();
        if (index < 0 || index >= size) throw new IllegalArgumentException();

        ListNode cur = head;

        while (index > 0) {
            cur = cur.next;
            index--;
        }

        return (E) cur.val;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public ListNode getHead() {
        return head;
    }

    public ListNode getTail() {
        return tail;
    }
}
