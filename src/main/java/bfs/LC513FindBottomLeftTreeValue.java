package bfs;

import adt.TreeNode;

import java.security.cert.TrustAnchor;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author coryjia@gmail.com
 * @date 5/1/20 7:12 PM
 */
public class LC513FindBottomLeftTreeValue {
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode res = null;

        while (!queue.isEmpty()){
            int size = queue.size();
            TreeNode firstNode = null;

            while (size-- > 0){
                TreeNode cur = queue.poll();
                if (firstNode == null) {
                    firstNode = cur;
                }

                if (cur.left != null) {
                    queue.offer(cur.left);
                }

                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
            res = firstNode;
        }

        return res.val;
    }
}
