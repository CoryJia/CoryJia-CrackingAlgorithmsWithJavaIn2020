package adt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyTreeDrawer {

    public static <E extends Comparable<?>> void draw(MyTreeNode<E> root) {
        int maxLevel = MyTreeDrawer.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <E extends Comparable<?>> void printNodeInternal(List<MyTreeNode<E>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || MyTreeDrawer.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        MyTreeDrawer.printWhitespaces(firstSpaces);

        List<MyTreeNode<E>> newNodes = new ArrayList<>();
        for (MyTreeNode<E> node : nodes) {
            if (node != null) {
                System.out.print(node.e);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            MyTreeDrawer.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                MyTreeDrawer.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    MyTreeDrawer.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    MyTreeDrawer.printWhitespaces(1);

                MyTreeDrawer.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    MyTreeDrawer.printWhitespaces(1);

                MyTreeDrawer.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <Item extends Comparable<?>> int maxLevel(MyTreeNode<Item> node) {
        if (node == null)
            return 0;

        return Math.max(MyTreeDrawer.maxLevel(node.left), MyTreeDrawer.maxLevel(node.right)) + 1;
    }

    private static <Item> boolean isAllElementsNull(java.util.List<Item> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }
        return true;
    }
}
