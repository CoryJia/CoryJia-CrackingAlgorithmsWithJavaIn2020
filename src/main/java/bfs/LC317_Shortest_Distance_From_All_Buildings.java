package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class LC317_Shortest_Distance_From_All_Buildings {
    private static final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }

        int row = grid.length;
        int col = grid[0].length;

        int[][] cost = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1){
                    bfs(grid, cost, i, j);
                }
            }
        }

        int minDis = Integer.MAX_VALUE;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0 && cost[i][j] != 0){
                    minDis = Math.min(cost[i][j], minDis);
                }
            }
        }

        return minDis == Integer.MAX_VALUE ? -1 : 0;
    }

    private void bfs(int[][] grid, int[][] cost, int i, int j) {
        int row = grid.length;
        int col = grid[0].length;

        boolean[][] visited = new boolean[row][col];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i * col + j);

        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int cur = queue.poll();
                int curI = cur / col;
                int curJ = cur % col;

                cost[curI][curJ] += distance;

                for (int[] dir : DIR) {
                    int ii = curI + dir[0];
                    int jj = curJ + dir[1];

                    if (ii >= 0 && ii < row && jj >= 0 && jj < col && grid[ii][jj] == 0 && !visited[ii][jj]) {
                        queue.offer(ii * col + jj);
                        visited[ii][jj] = true;
                    }
                }
            }
            distance++;
        }

        for (int ii = 0; ii < row; ii++) {
            for (int jj = 0; jj < col; jj++) {
                if (grid[ii][jj] == 0 && !visited[ii][jj]) {
                    grid[ii][jj] = 2;
                }
            }
        }

    }
}
