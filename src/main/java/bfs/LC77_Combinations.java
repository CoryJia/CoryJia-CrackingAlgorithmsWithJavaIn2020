package bfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coryjia@gmail.com
 * @date 5/2/20 11:21 AM
 */
public class LC77_Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<Integer>(), n, 1, 0, k);
        return res;
    }

    private void dfs(List<List<Integer>> res, ArrayList<Integer> path, int n, int curVal, int curSize, int k) {
        if(curSize == k){
            res.add(new ArrayList<>(path));
            return;
        }

        if(curSize > k || curVal > n) return;

        //not add curVal
        dfs(res, path, n, curVal + 1, curSize, k);

        //add curVal
        path.add(curVal);
        dfs(res, path, n, curVal + 1, curSize + 1, k);
        path.remove(path.size() - 1);
    }
}
