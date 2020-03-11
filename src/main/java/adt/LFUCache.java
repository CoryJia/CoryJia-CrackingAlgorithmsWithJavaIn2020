package adt;
/*
 * @author: Cory Jia
 * @email: coryjia@gmail.com
 * @date: 2018-11-30 08:50
 */

/*
 * LFUCache(Least Frequently Used) Cache - (key,value) pair bounded by capacity. Once reach the capacity it will evict
 * the word which is least frequently used. If there are multiple items being same least frequencies, then evict the
 * word which is least recently being used.
 */


import java.util.*;

public class LFUCache<K, V> {
    private int capacity;
    private Map<K, Node<K, V>> map;
    //    private TreeMap<Integer, LinkedList<Node>> treeMap;
    private TreeSet<Node> treeSet;
    //    private DoubleLinkedList<K, V> doubleLinkedList;
    private static final int DEFAULTCAPACITY = 4;

    public LFUCache() {
        this(DEFAULTCAPACITY);
    }

    public LFUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        treeSet = new TreeSet<>((a, b) -> {
            if (a.frequency != b.frequency) {
                return a.frequency - b.frequency;
            } else {
                return -1;
            }
        });
//        treeMap = new TreeMap<>();
//        doubleLinkedList = new DoubleLinkedList<>();
    }

    public V get(K key) {
        Node<K, V> node = map.get(key);

        if (node != null) {
            update(node, null);

            return node.value;
        }

        return null;
    }

    public void update(Node<K, V> node, V value) {
        node.addFrequency();
        if (value != null) {
            node.setValue(value);
        }

        if (!treeSet.contains(node)){

        }
    }

//    public V remove(K key) {
//        Node<K, V> node = map.remove(key);
//
//        if (node != null) {
//            int frequency = counts.get(key);
//            frequencies.get(frequency).remove(node);
//            removeIfListEmpty(frequency);
//            return node.value;
//        }
//
//        return null;
//    }


    public void put(K key, V value) {
        Node<K, V> node = map.get(key);

        if (node != null) {
            update(node, value);
        } else {
            node = new Node(key, value);
            updateTreeSet(node);
            map.put(key, node);
        }

    }

    public void updateTreeSet(Node<K, V> node) {

        treeSet.add(node);

        if (treeSet.size() >= capacity) {
            treeSet.remove(treeSet.first());
        }
    }


    public void printTreeMap() {
        System.out.println("Nodes in TreeSet");
        System.out.println(treeSet);
        System.out.println();
    }

    public void printMap() {
        System.out.println("Nodes in HashMap");
        System.out.println(map);
        System.out.println();
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);

        Object res = lfuCache.get("1");
        System.out.println(res);

        lfuCache.put("1", "1");
//        lfuCache.printMap();
//        lfuCache.printTreeMap();

        lfuCache.put("2", "2");
//        lfuCache.printMap();
//        lfuCache.printTreeMap();

        lfuCache.put("3", "3");
//        lfuCache.printMap();
//        lfuCache.printTreeMap();


        res = lfuCache.get("1");
        System.out.println(res);
        lfuCache.printMap();
        lfuCache.printTreeMap();
//        lfuCache.put("3", "3");
//        lfuCache.printMap();
//        lfuCache.printTreeMap();
//
//        lfuCache.put("4", "4");
//        lfuCache.printMap();
//        lfuCache.printTreeMap();

//        res = lfuCache.get("first");
//        System.out.println(res);
//        lfuCache.printMap();
//        lfuCache.printSet();
//
//
//
//
//        res = lfuCache.get("first");
//        System.out.println(res);
//        lfuCache.printMap();
//        lfuCache.printSet();
    }
}

class Node<K, V> {
    public K key;
    public V value;
    public int frequency;


    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.frequency = 1;
    }

    public int getFrequency() {
        return frequency;
    }

    public void addFrequency() {
        ++this.frequency;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void removeValue() {
        this.value = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value=" + value +
                ", frequency=" + frequency +
                '}';
    }
}

class DoubleLinkedList<K, V> {
    public Node<K, V> head;
    public Node<K, V> tail;
    private int size;

    public DoubleLinkedList() {
        this.size = 0;
        head = null;
        tail = null;
    }


//    public void remove(Node<K, V> n) {
//        if (n.pre != null) {
//            n.pre.next = n.next;
//        } else {
//            head = n.next;
//        }
//
//        if (n.next != null) {
//            n.next.pre = n.pre;
//        } else {
//            tail = n.pre;
//        }
//
//        size--;
//    }
//
//    public void setHead(Node<K, V> n) {
//        if (head == null) {
//            tail = n;
//        } else {
//            n.next = head;
//            head.pre = n;
//        }
//
//        head = n;
//        size++;
//    }

    public int getSize() {
        return size;
    }

}

