package adt;

import java.util.HashMap;
import java.util.Map;

/*
 * LRUCache (Least Recently Used) Cache - (Key, Value) pair bounded by capacity.
 * Once reach the capacity it will evict the word which is least recently used.
 * case1: check whether the new url is inside LRU cache or not with O(1)
 * case2: if the url already exists, update the older of the hit url as new one(move to the new side), move based on timestamp return node's value
 * case3: if firstly hit the url:
 *          case a: if LRU cache is not full, insert the new node to the new side
 *          case b: otherwise, delete the oldest node and insert the new node to the new side
 * Java provide LinkedHashMap as LRUCache
 */



public class LRUCache<K, V> {
    private int capacity;
    private int size;
    private Map<K, DDL<K, V>> map;
    private DDL dummyHead;
    private DDL dummyTail;
    private static final int DEFAULT_CAPACITY = 10;

    public LRUCache(){
        new LRUCache(DEFAULT_CAPACITY);
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        map = new HashMap<>();
        dummyHead = new DDL(null,null);
        dummyTail = new DDL(null,null);
        dummyHead.next = dummyTail;
        dummyTail.pre = dummyHead;
    }

    public V get(K key) {
        DDL node = map.get(key);

        if(node == null){
            return null;
        }

        removeNode(node);
        setHead(node);

        return (V) node.value;
    }

    public void put(K key, V value) {
        DDL node = map.get(key);

        if (node != null){ //update
            node.value = value;
            removeNode(node);
            setHead(node);
        }else {
            if (size == capacity){
                DDL last = dummyTail.pre;
                removeNode(last);
                map.remove(last.key);
            }

            DDL newNode = new DDL(key, value);
            setHead(newNode);
            map.put(key, newNode);
        }
    }

    // Do not need implement this method when interview
    public V remove(K key) {
        DDL node = map.get(key);

        if (node != null){
            removeNode(node);
            map.remove(node.key);
            return (V) node.value;
        }

        return null;
    }

    private void setHead(DDL node) {
        size++;

        DDL oldNode = dummyHead.next;

        node.next = oldNode;
        oldNode.pre = node;

        dummyHead.next = node;
        node.pre = dummyHead;
    }

    private void removeNode(DDL node) {
        size--;

        node.pre.next = node.next;
        node.next.pre = node.pre;

        node.next = null;
        node.pre = null;
    }
    private class DDL<K, V> {
        K key;
        V value;
        DDL pre;
        DDL next;

        public DDL(K key, V value) {
            this.key = key;
            this.value = value;
            this.pre = null;
            this.next = null;
        }
    }

//    private class DDL<K, V> {
//        private Node<K, V> head;
//        private Node<K, V> tail;
//
//        public DDL() {
//            head = null;
//            tail = null;
//        }
//
//        public void remove(Node<K, V> n) {
//            if (n.pre != null) {
//                n.pre.next = n.next;
//            } else { // remove head
//                head = n.next;
//            }
//
//            if (n.next != null) {
//                n.next.pre = n.pre;
//            } else { //remove tail
//                tail = n.pre;
//            }
//        }
//
//        public void setHead(Node<K, V> n) {
//            if (head == null) {
//                tail = n;
//            } else {
//                n.next = head;
//                head.pre = n;
//            }
//            head = n;
//        }
//    }
}

//public class LRUCache<K, V> {
//    private int capacity;
//    private Map<K, Node<K, V>> map;
//    private DoubleLinkedList<K, V> doubleLinkedList;
//    private static final int DEFAULT_CAPACITY = 10;
//
//    public LRUCache(){
//        new LRUCache(DEFAULT_CAPACITY);
//    }
//
//    public LRUCache(int capacity) {
//        this.capacity = capacity;
//        map = new HashMap<>();
//        doubleLinkedList = new DoubleLinkedList<>();
//    }
//
//    public V get(K key) {
//        if (map.containsKey(key)) {
//            Node<K, V> n = map.get(key);
//            doubleLinkedList.remove(n);
//            doubleLinkedList.setHead(n);
//            return n.value;
//        }
//
//        return null;
//    }
//
//    public void put(K key, V value) {
//        if (map.containsKey(key)) {
//            Node<K, V> old = map.get(key);
//            old.value = value;
//            doubleLinkedList.remove(old);
//            doubleLinkedList.setHead(old);
//        } else {
//            Node<K, V> n = new Node<>(key, value);
//            if (map.size() >= capacity) {
//                map.remove(doubleLinkedList.tail.key);
//                doubleLinkedList.remove(doubleLinkedList.tail);
//                doubleLinkedList.setHead(n);
//            } else {
//                doubleLinkedList.setHead(n);
//            }
//            map.put(key, n);
//        }
//    }
//
//    //Need not implement this method when interview
//    public V remove(K key) {
//        if (map.containsKey(key)) {
//            Node<K, V> n = map.remove(key);
//            doubleLinkedList.remove(n);
//            return n.value;
//        }
//        return null;
//    }
//
//    private class Node<K, V> {
//        K key;
//        V value;
//        Node pre;
//        Node next;
//
//        public Node(K key, V value) {
//            this.key = key;
//            this.value = value;
//            this.pre = null;
//            this.next = null;
//        }
//    }
//
//    private class DoubleLinkedList<K, V> {
//        private Node<K, V> head;
//        private Node<K, V> tail;
//
//        public DoubleLinkedList() {
//            head = null;
//            tail = null;
//        }
//
//        public void remove(Node<K, V> n) {
//            if (n.pre != null) {
//                n.pre.next = n.next;
//            } else { // remove head
//                head = n.next;
//            }
//
//            if (n.next != null) {
//                n.next.pre = n.pre;
//            } else { //remove tail
//                tail = n.pre;
//            }
//        }
//
//        public void setHead(Node<K, V> n) {
//            if (head == null) {
//                tail = n;
//            } else {
//                n.next = head;
//                head.pre = n;
//            }
//            head = n;
//        }
//    }
//}
