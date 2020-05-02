package bfs;

import java.util.*;

/**
 * @author coryjia@gmail.com
 * @date 4/2/20 6:24 PM
 */
public class LC417PacificAtlanticWaterFlow {
    private static final int[][] DIR = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}}; //UP,DOWN,LEFT,RIGHT

    public static List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();

        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return res;
        }

        int row = matrix.length, col = matrix[0].length;
        boolean[][] pacific = new boolean[row][col];
        boolean[][] atlantic = new boolean[row][col];

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < row; i++) {
            //left
            queue.add(i * col + 0);
            pacific[i][0] = true;
        }

        for (int j = 1; j < col; j++) {
            //top
            queue.add(0 * col + j);
            pacific[0][j] = true;
        }

        bfs(res, queue, pacific, atlantic, matrix);

        for (int i = 0; i < row; i++) {
            //right
            queue.offer(i * col + col - 1);
            atlantic[i][col - 1] = true;
        }

        for (int j = 0; j < col - 1; j++) {
            //bottom
            queue.add((row - 1) * col + j);
            atlantic[row - 1][j] = true;
        }

        bfs(res, queue, atlantic, pacific, matrix);
        return res;
    }

    private static void bfs(List<List<Integer>> res, Queue<Integer> queue, boolean[][] self, boolean[][] other, int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int curI = cur / col;
            int curJ = cur % col;

            if (other[curI][curJ]) {
                res.add(Arrays.asList(curI, curJ));
            }

            for (int[] dir : DIR) {
                int i = curI + dir[0];
                int j = curJ + dir[1];

                if (i >= 0 && i < row && j >= 0 && j < col && matrix[curI][curJ] <= matrix[i][j] && !self[i][j]) {
                    queue.offer(i * col + j);
                    self[i][j] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};

        List<List<Integer>> res = pacificAtlantic(matrix);

        System.out.println(res);
    }
}
