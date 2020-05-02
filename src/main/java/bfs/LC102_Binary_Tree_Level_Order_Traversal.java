package bfs;

import adt.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC102_Binary_Tree_Level_Order_Traversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curLev = new ArrayList<>();

            while (size-- > 0) {
                TreeNode cur = queue.poll();
                curLev.add(cur.val);

                if (cur.left != null) {
                    queue.offer(cur.left);
                }

                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }

            res.add(curLev);
        }
        return res;
    }

    //by dfs
    public List<List<Integer>> levelOrderByDfs(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        dfs(root, res, 0);
        return res;
    }

    private void dfs(TreeNode root, List<List<Integer>> res, int level) {
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }

        res.get(level).add(root.val);
        if (root.left != null) {
            dfs(root.left, res, level + 1);
        }

        if (root.right != null) {
            dfs(root.right, res, level + 1);
        }
    }
}
