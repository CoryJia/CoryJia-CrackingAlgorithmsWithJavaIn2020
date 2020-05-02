package bfs;

import bfs.nestedinteger.NestedInteger;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC364_Nested_List_Weight_Sum_II {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int sum = 0, levelSum = 0;

        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList);

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                NestedInteger cur = queue.poll();

                if (cur.isInteger()) {
                    levelSum += cur.getInteger();
                }else {
                    queue.addAll((Collection<? extends NestedInteger>) cur.getList());
                }
            }
            sum += levelSum;
        }

        return sum;
    }
}
