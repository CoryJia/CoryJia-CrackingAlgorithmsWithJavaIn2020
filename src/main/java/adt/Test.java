package adt;

import java.util.*;

public class Test {

    static class Node {
        int val;
        int frequency;

        public Node(int val, int frequency) {
            this.val = val;
            this.frequency = frequency;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", frequency=" + frequency +
                    '}';
        }
    }


    public static void main(String[] args) {

        System.out.print("a" + "\n");

        System.out.print("b\n");

        System.out.print("c" + "\t" + "d");

        System.out.println("e");

        System.out.println(1 + "\t" + 2);
        System.out.println(100 + "\t" + 20);

        TreeSet<Node> treeSet = new TreeSet<>((a, b) -> {

            if (a.frequency != b.frequency) {
                return a.frequency - b.frequency;
            } else {
                return -1;
            }
        });

        treeSet.add(new Node(1, 1));
        treeSet.add(new Node(2, 1));
        treeSet.add(new Node(3, 3));

        System.out.println(treeSet);

    }
    @org.junit.Test
    public void test(){

        TreeNode root = TreeGenerator.deserialize("5,1,7,2,4, 6, 8");
        TreeDrawer.draw(root);

        boolean res = validBST(root, null);
        System.out.println(res);

        Map<Integer, Integer> map = new HashMap<>();

         map.entrySet().iterator().next();
         map.keySet().iterator().next();
         map.values().iterator().next();
        System.out.println("hello");

        int[] arr = new int[10];
        for (int n : arr) {

        }

        for (int i = 0; i < arr.length; i++) {

        }

        List<Interval> list = new ArrayList<>();

    }


    public boolean validBST(TreeNode root, TreeNode pre){
        if(root == null) return true;

        if (!validBST(root.left, pre)) return false;
        if(pre != null && pre.val >= root.val) return false;
        pre = root;
        System.out.println(pre);
        System.out.println(pre.val);
        return validBST(root.right, pre);
    }

    @org.junit.Test
    public void test1(){
        TreeNode root = TreeGenerator.deserialize("5,1,7,2,4, 6, 8");
        TreeDrawer.draw(root);

        TreeNode[] pre = {null};
        boolean res = validBST1(root, pre);
        System.out.println(res);
    }

    public boolean validBST1(TreeNode root, TreeNode[] pre){
        if(root == null) return true;

        if (!validBST1(root.left, pre)) return false;
        if(pre != null && pre[0].val >= root.val) return false;
        pre[0] = root;
        System.out.println(pre);
        System.out.println(pre[0].val);
        return validBST1(root.right, pre);
    }


}
