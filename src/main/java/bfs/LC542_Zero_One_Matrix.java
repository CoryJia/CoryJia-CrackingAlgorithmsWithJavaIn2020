package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author coryjia@gmail.com
 * @date 5/2/20 9:39 AM
 */
public class LC542_Zero_One_Matrix {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length  == 0) {
            return  null;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        int res[][] = new int[row][col];
        Queue<Integer> queue = new LinkedList<>();
        int  dis = 1;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(matrix[i][j] == 0){
                    queue.offer(i * col + j);
                }
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int cur = queue.poll();
                for (int[] dir : DIRECTIONS) {
                    int i = cur / col + dir[0];
                    int j = cur % col + dir[1];

                    if(i >= 0 && i < row && j >= 0 && j < col && matrix[i][j] == 1 && res[i][j] ==  0){
                        res[i][j]  =dis;
                        queue.offer(i * col + j);
                    }
                }
            }
            dis++;
        }
        return res;
    }
}
