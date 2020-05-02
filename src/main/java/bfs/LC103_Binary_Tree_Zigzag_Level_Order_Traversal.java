package bfs;

import adt.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC103_Binary_Tree_Zigzag_Level_Order_Traversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isEvenLev = true;

        while (!queue.isEmpty()) {
            List<Integer> curLev = new ArrayList<>();
            int size = queue.size();

            while (size-- > 0) {
                TreeNode cur = queue.poll();


                if (isEvenLev) {
                    curLev.add(cur.val);
                } else {
                    curLev.add(0, cur.val);
                }

                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);


            }

            isEvenLev = !isEvenLev;
            res.add(curLev);
        }

        return res;
    }
}
