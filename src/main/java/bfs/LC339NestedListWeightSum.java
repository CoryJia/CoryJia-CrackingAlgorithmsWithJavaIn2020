package bfs;

import bfs.nestedinteger.NestedInteger;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC339NestedListWeightSum {
    public int depthSum(List<NestedInteger> nestedList) {
        int sum = 0, level = 1;

        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList);

        while (!queue.isEmpty()) {
            int levelSum = 0, size = queue.size();

            while (size-- > 0) {
                NestedInteger cur = queue.poll();

                if (cur.isInteger()) {
                    levelSum += cur.getInteger();
                }else {
                    queue.addAll((Collection<? extends NestedInteger>) cur.getList());
                }
            }

            sum += levelSum * level++;
        }

        return sum;
    }
}
