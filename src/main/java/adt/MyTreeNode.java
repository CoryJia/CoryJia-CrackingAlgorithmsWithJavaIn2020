package adt;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class MyTreeNode<E extends Comparable<?>> {
    public E e;
    public MyTreeNode<E> left;
    public MyTreeNode<E> right;
    public MyTreeNode<E> next;

    public MyTreeNode(E e) {
        this.e = e;
        left = null;
        right = null;
    }

//    public int e;
//    public MyTreeNode left;
//    public MyTreeNode right;
//
//    public MyTreeNode(int x){
//        this.e = x;
//    }

    public static MyTreeNode buildBinaryTree(Integer[] source) {
        if (source == null || source.length == 0) return null;

        MyTreeNode root = new MyTreeNode(source[0]);
        Queue<MyTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 0;

        while ( !queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                MyTreeNode cur = queue.poll();
                int temp = (int) cur.e;
                MyTreeNode left = null;

                if (2 * i + 1 < source.length && source[2 * i + 1] != null) {
                    left = new MyTreeNode(source[2 * i + 1]);
                    queue.offer(left);
                }
                cur.left = left;

                MyTreeNode right = null;
                if (2 * i + 2 < source.length && source[2 * i + 2] != null) {
                    right = new MyTreeNode(source[2 * i + 2]);
                    queue.offer(right);
                }
                cur.right = right;
                i++;
            }

        }
        return root;
    }


    public static MyTreeNode buildBinaryTree(Character[] source) {
        if (source == null || source.length == 0) return null;

        MyTreeNode root = new MyTreeNode(source[0]);
        Queue<MyTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                MyTreeNode cur = queue.poll();
                MyTreeNode left = null;
                if (2 * i + 1 < source.length && source[2 * i + 1] != null) {
                    left = new MyTreeNode(source[2 * i + 1]);
                    queue.offer(left);
                }
                cur.left = left;

                MyTreeNode right = null;
                if (2 * i + 2 < source.length  && source[2 * i + 2] != null) {
                    right = new MyTreeNode(source[2 * i + 2]);
                    queue.offer(right);
                }
                cur.right = right;
                i++;
            }

        }
        return root;
    }

    public static MyTreeNode buildBinaryTreeWithPreInOrder(int[] preOrder, int[] inOrder) {
        HashMap<Integer, Integer> inOrderMap = new HashMap<>();

        for (int i = 0; i < inOrder.length; i++) {
            inOrderMap.put(inOrder[i],i);
        }

        return helper(0, 0, inOrder.length - 1, preOrder, inOrderMap);
    }

    private static MyTreeNode helper(int preStart, int inOrderStart, int inOrderEnd, int[] preOrder, HashMap<Integer, Integer> inOrderMap) {
        if (preStart > preOrder.length - 1 || inOrderStart > inOrderEnd) {
            return null;
        }

        MyTreeNode root = new MyTreeNode(preOrder[preStart]);

        int rootIndex = inOrderMap.get(root.e);

        root.left = helper(preStart + 1, inOrderStart, rootIndex - 1, preOrder, inOrderMap);
        root.right = helper(preStart + rootIndex - inOrderStart + 1, rootIndex + 1, inOrderEnd, preOrder, inOrderMap);

        return root;
    }

    public static MyTreeNode buildBinaryTreeWithPostInorder(int[] postOrder, int[] inOrder) {
        HashMap<Integer, Integer> inOrderMap = new HashMap<>();

        for (int i = 0; i < inOrder.length; i++) {
            inOrderMap.put(inOrder[i],i);
        }

        return helper(0, inOrder.length - 1, postOrder, 0, postOrder.length - 1, inOrderMap);

    }

    private static MyTreeNode helper(int inOrderStart, int inOrderEnd, int[] postOrder, int postOrderStart, int postOrderEnd, HashMap<Integer, Integer> inOrderMap) {
        if (postOrderStart > postOrderEnd || inOrderStart > inOrderEnd) return null;

        MyTreeNode root = new MyTreeNode(postOrder[postOrderEnd]);
        int index = inOrderMap.get(postOrder[postOrderEnd]);

        root.left = helper(inOrderStart, index - 1, postOrder, postOrderStart, postOrderStart + index - inOrderStart - 1, inOrderMap);
        root.right = helper(index + 1, inOrderEnd, postOrder, postOrderStart + index - inOrderStart, postOrderEnd - 1, inOrderMap);

        return root;

    }

    public static void main(String[] args) {
        MyTreeNode node1 = new MyTreeNode(1);
        MyTreeNode node2 = new MyTreeNode(2);
        MyTreeNode node3 = new MyTreeNode(3);
        MyTreeNode node4 = new MyTreeNode(4);
        MyTreeNode node5 = new MyTreeNode(5);


        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node4.left = node5;

        MyTreeNode root = node1;

        MyTreeDrawer.draw(root);

        Integer[] source = {1,2,null, null, 3};
        MyTreeDrawer.draw(MyTreeNode.buildBinaryTree(source));
        Character[] input = {'a','b',null,null,'c'};

        root = MyTreeNode.buildBinaryTree(input);
        MyTreeDrawer.draw(root);
    }

}
