package adt;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import static adt.TreeDrawer.invertTree;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        this.val = x;
        this.left = null;
        this.right = null;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }

    public static TreeNode buildBinaryTree(String str) {
        if (str == null || str.length() == 0) return null;
        String[] splitted = str.split(",");

        Integer[] source = new Integer[splitted.length];

        for (int i = 0; i < splitted.length; i++) {
            String curS = splitted[i].trim();
            if (curS.equals("#")) {
                source[i] = null;
            } else {
                source[i] = Integer.parseInt(curS);
            }
        }

        return buildBinaryTree(source);
    }

    private static TreeNode buildBinaryTree(Integer[] source) {
        if (source == null || source.length == 0) return null;

        TreeNode root = new TreeNode(source[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                TreeNode cur = queue.poll();
                TreeNode left = null;

                if (2 * i + 1 < source.length && source[2 * i + 1] != null) {
                    left = new TreeNode(source[2 * i + 1]);
                    queue.offer(left);
                }
                cur.left = left;

                TreeNode right = null;
                if (2 * i + 2 < source.length && source[2 * i + 2] != null) {
                    right = new TreeNode(source[2 * i + 2]);
                    queue.offer(right);
                }
                cur.right = right;
                i++;
            }

        }
        return root;
    }


    public static TreeNode buildBinaryTreeWithPreInOrder(int[] preOrder, int[] inOrder) {
        HashMap<Integer, Integer> inOrderMap = new HashMap<>();

        for (int i = 0; i < inOrder.length; i++) {
            inOrderMap.put(inOrder[i], i);
        }

        return helper(0, 0, inOrder.length - 1, preOrder, inOrderMap);
    }

    private static TreeNode helper(int preStart, int inOrderStart, int inOrderEnd, int[] preOrder, HashMap<Integer, Integer> inOrderMap) {
        if (preStart > preOrder.length - 1 || inOrderStart > inOrderEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preOrder[preStart]);

        int rootIndex = inOrderMap.get(root.val);

        root.left = helper(preStart + 1, inOrderStart, rootIndex - 1, preOrder, inOrderMap);
        root.right = helper(preStart + rootIndex - inOrderStart + 1, rootIndex + 1, inOrderEnd, preOrder, inOrderMap);

        return root;
    }

    public static TreeNode buildBinaryTreeWithPostInorder(int[] postOrder, int[] inOrder) {
        HashMap<Integer, Integer> inOrderMap = new HashMap<>();

        for (int i = 0; i < inOrder.length; i++) {
            inOrderMap.put(inOrder[i], i);
        }

        return helper(0, inOrder.length - 1, postOrder, 0, postOrder.length - 1, inOrderMap);

    }

    private static TreeNode helper(int inOrderStart, int inOrderEnd, int[] postOrder, int postOrderStart, int postOrderEnd, HashMap<Integer, Integer> inOrderMap) {
        if (postOrderStart > postOrderEnd || inOrderStart > inOrderEnd) return null;

        TreeNode root = new TreeNode(postOrder[postOrderEnd]);
        int index = inOrderMap.get(postOrder[postOrderEnd]);

        root.left = helper(inOrderStart, index - 1, postOrder, postOrderStart, postOrderStart + index - inOrderStart - 1, inOrderMap);
        root.right = helper(index + 1, inOrderEnd, postOrder, postOrderStart + index - inOrderStart, postOrderEnd - 1, inOrderMap);

        return root;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val &&
                left.equals(treeNode.left) &&
                right.equals(treeNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right);
    }

    public static void main(String[] args) {
        //three ways to build and draw a binary tree

        {
            System.out.println("build a binary by String like leetcode.com, # as null pointer");
            String s = "1,2,3,#,4,#,#,5";
            TreeNode root = TreeNode.buildBinaryTree(s);
            TreeDrawer.draw(root);

            System.out.println("after invert");

            TreeNode newRoot = invertTree(root);
            TreeDrawer.draw(newRoot);
        }

        {
            System.out.println("build a binary tree by Integer array");
            Integer[] source = {1, 2, 3, null, 4, null, null, 5};
            TreeDrawer.draw(TreeNode.buildBinaryTree(source));
        }


        {
            System.out.println("Traditional way to build a binary tree");
            TreeNode node1 = new TreeNode(1);
            TreeNode node2 = new TreeNode(2);
            TreeNode node3 = new TreeNode(3);
            TreeNode node4 = new TreeNode(4);
            TreeNode node5 = new TreeNode(5);

            node1.left = node2;
            node1.right = node3;
            node2.right = node4;
            node4.left = node5;

            TreeNode root = node1;

            //visualize the tree
            TreeDrawer.draw(root);

        }

    }

}





