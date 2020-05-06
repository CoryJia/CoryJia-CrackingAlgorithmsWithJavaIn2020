package bfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coryjia@gmail.com
 * @date 5/3/20 10:44 PM
 */
public class LC78_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        res.add(new ArrayList<>());

        for (int n : nums) {
            int size = res.size();

            for (int i = 0; i < size; i++) {
                List<Integer> one = new ArrayList<>(res.get(i));
                one.add(n);
                res.add(one);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LC78_Subsets sol = new LC78_Subsets();
        System.out.println(sol.subsets(new int[]{1, 2, 3}));
    }
}
