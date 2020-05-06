package bfs;

import java.util.*;

public class LC310_Minimum_Height_Trees {
    private class TreeNode{
        public Set<Integer> neighbors;
        public int label;

        public TreeNode(int label) {
            this.neighbors = new HashSet<>();
            this.label = label;
        }

        public void addNei(Integer label) {
            neighbors.add(label);
        }

        public void removeNei(Integer label) {
            neighbors.remove(label);
        }

        public boolean isLeaf() {
            //if the node is a leaf node, the only neighbor is it's parent
            return neighbors.size() == 1;
        }
    }
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        if (n == 1){
            return Arrays.asList(0);
        }

        int nodePool = n;

        TreeNode[] graph = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new TreeNode(i);
        }

        for (int[] edge : edges) {  //O(E)
            graph[edge[0]].addNei(edge[1]);
            graph[edge[1]].addNei(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (graph[i].isLeaf()) {
                queue.offer(i);
                nodePool--;
            }
        }

        while (nodePool > 0) { //O(V + E)
            int size = queue.size();

            while (size-- > 0) {
                int cur = queue.poll();

                for (Integer nei : graph[cur].neighbors) {
                    graph[nei].removeNei(cur);

                    if (graph[nei].isLeaf()){
                        queue.add(nei);
                        nodePool--;
                    }
                }
            }
        }

        return new ArrayList<>(queue);
    }
}
