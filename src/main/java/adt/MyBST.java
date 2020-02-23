package adt;

import java.util.*;

public class MyBST<E extends Comparable<E>> {
    private MyTreeNode<E> root;
    private int size;

    // construct an empty BST
    public MyBST() {
    }

    // construct a BST from other root
    public MyBST(MyBST bst) {
        this.root = bst.root;
    }

    // get the root of BST
    public MyTreeNode getRoot() {
        return root;
    }

    // get the numbers of MyTreeNode in BST
    public int size() {
        return size;
    }

    // return true if BST is empty, otherwise return false
    public boolean isEmpty() {
        return size == 0;
    }

    // get the height of BST
    public int height() {
        return height(root);
    }

    private int height(MyTreeNode x) {
        if (x == null) {
            return 0;
        }
        return Math.max(height(x.left), height(x.right)) + 1;
    }

    // check the BST is balanced, optimize to O(n)
    private boolean isBalanced() { return isBalancedHelper(root) != -1; }

    //if not balanced return -1
    private int isBalancedHelper(MyTreeNode root) {
        if (root == null) return 0;

        int heightLeft = isBalancedHelper(root.left);
        int heightRight = isBalancedHelper(root.right);

        //Corner case: heightLeft = heightRight = -1
        return (heightLeft == -1 || heightRight == -1 || Math.abs(heightLeft - heightRight) > 1) ? -1 : Math.max(heightLeft, heightRight) + 1;
    }

    //optimize to above due to the poor efficiency
//    private boolean isBalanced(MyTreeNode<E> x) {
//        if (x == null) {
//            return true;
//        }
//        return (Math.abs(height(x.left) - height(x.right)) <= 1) && isBalanced(x.left) && isBalanced(x.right);
//    }

    // search a node with specific value, return true if find, otherwise return false
    public boolean find(E target) {
        if (isEmpty()) {
            throw new NoSuchElementException("Tree is empty");
        }

        if (target == null) {
            throw new IllegalArgumentException("Target should not be null");
        }
        return find(root, target);
    }

    private boolean find(MyTreeNode<E> x, E target) {
        if (x == null) {
            return false;
        }

        int cmp = target.compareTo(x.e);

        if (cmp < 0) return find(x.left, target);
        else if (cmp > 0) return find(x.right, target);
        else {
            return true;
        }
    }

    // get the value of smallest node
    public E min() {
        return min(root);
    }

    private E min(MyTreeNode<E> x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x.e;
        }
        return min(x.left);
    }

    // remove the smallest node
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Tree is empty, nothing can be deleted");
        }
        size--;
        root = deleteMin(root);
    }


    private MyTreeNode<E> deleteMin(MyTreeNode<E> x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    // get the value of the largest node
    public E max() {
        return max(root);
    }

    private E max(MyTreeNode<E> x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x.e;
        }
        return max(x.right);
    }


    // remove the largest node
    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Tree is empty, nothing can be deleted");
        }
        size--;
        root = deleteMax(root);
    }

    private MyTreeNode deleteMax(MyTreeNode x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        return x;
    }

    public ArrayList<E> inOrderTraverse() {
        ArrayList<E> rst = new ArrayList<E>();
        inOrderTraverse(root, rst);
        return rst;
    }

    private void inOrderTraverse(MyTreeNode<E> x, ArrayList<E> rst) {
        if (x == null) {
            return;
        }
        inOrderTraverse(x.left, rst);
        rst.add(x.e);
        inOrderTraverse(x.right, rst);
    }

    // travers BST by level

    public ArrayList<ArrayList<E>> levelTraverse() {
        ArrayList<ArrayList<E>> rst = new ArrayList<>();
        levelTraverse(root, rst);
        return rst;
    }

    private void levelTraverse(MyTreeNode<E> root, ArrayList<ArrayList<E>> rst) {
        Queue<MyTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            ArrayList<E> levelRst = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                MyTreeNode node = queue.poll();
                levelRst.add((E) node.e);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

            }
            rst.add(levelRst);
        }

    }

    // insert a new node
    public void insert(E key) {
        if (key == null) {
            throw new IllegalArgumentException("Can not insert a null node");
        }

        root = insert(root, key);
        size++;
        assert check();
    }

    private MyTreeNode insert(MyTreeNode<E> x, E key) {

        if (x == null) {
            return new MyTreeNode<>(key);
        }

        int cmp = key.compareTo(x.e);

        if (cmp < 0) x.left = insert(x.left, key);
        if (cmp >= 0) x.right = insert(x.right, key);

        return x;
    }

    // delete a node
    public void delete(E dltKey) {
        if (dltKey == null) {
            throw new IllegalArgumentException("Delete key is null, can not delete a null node");
        }

        if (!find(dltKey)) {
            throw new NoSuchElementException("No node value equals delete key");
        }

        if (isEmpty()) {
            throw new NoSuchElementException("Tree is empty, nothing can be deleted");
        }
        root = delete(root, dltKey);
        size--;
        assert check();
    }

    private MyTreeNode<E> delete(MyTreeNode<E> x, E dltKey) {

        if (x == null) {
            return null;
        }

        int cmp = dltKey.compareTo(x.e);

        if (cmp < 0) x.left = delete(x.left, dltKey);
        else if (cmp > 0) x.right = delete(x.right, dltKey);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            // set root value randomly as max of left tree or min of right tree
            Random rd = new Random(System.currentTimeMillis());
            boolean flag = rd.nextBoolean();

            if (flag) {
                E rightMin = min(x.right);
                x.right = deleteMin(x.right);
                x.e = rightMin;

            } else {
                E leftMax = max(x.left);
                x.left = deleteMax(x.left);
                x.e = leftMax;
            }
        }

        return x;
    }

    // check integrity of BST data structure
    private boolean check() {
        if (!isBST()) {
            throw new IllegalArgumentException("Not in symmetric order");
        }
        return isBST();
    }

    private boolean isBST() {
        return isBST(root);
    }

    private boolean isBST(MyTreeNode<E> x) {
        if (x == null) {
            return true;
        }

        if (x.left != null) {
            E leftMax = max(x.left);
            int cmp = leftMax.compareTo(x.e);
            if (cmp >= 0) return false;
        }
        if (x.right != null) {
            E rightMin = min(x.right);
            int cmp = rightMin.compareTo(x.e);
            if (cmp <= 0) return false;
        }
        return isBST(x.left) && isBST(x.right);
    }


   public int find_longest_path(MyTreeNode root, int len) {
        len = helper(root);
        return len;
    }

    private int helper(MyTreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(helper(root.left), helper(root.right)) + 1;
    }


    private static void test(MyBST bst) {
        MyTreeDrawer.draw(bst.getRoot());
        System.out.println("Numbers of nodes= " + bst.size);
        System.out.println("Height = " + bst.height());
        if (bst.isBalanced()) {
            System.out.println("The tree is balanced ");
        } else {
            System.out.println("The tree is NOT balanced ");
        }
        System.out.println("Maximum value= " + bst.max());
        System.out.println("Minimum value= " + bst.min());
        System.out.println("In-order traverse result= " + bst.inOrderTraverse());
        System.out.println("Level traverse result= " + bst.levelTraverse());
        System.out.println();

    }

    public static void main(String[] args) {
        MyBST bst = new MyBST();

      int[] values = {10, 6, 3, 9, 1, 12, 8, 4, 11, 14, 16};
//        int[] values = {14, 23, 7 ,10, 33, 56, 80, 66, 70};

        for (int v : values) {
            bst.insert(v);
        }

        test(bst);
        int path = bst.find_longest_path(bst.root, 0);
        System.out.println(path);

        bst.delete(3);
        bst.delete(4);
        bst.delete(1);
        bst.delete(12);
        test(bst);



    }


}
