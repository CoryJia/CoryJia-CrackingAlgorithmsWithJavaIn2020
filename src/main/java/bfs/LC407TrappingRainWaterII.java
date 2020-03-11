package bfs;

import java.util.PriorityQueue;

public class LC407TrappingRainWaterII {
    private class Cell implements Comparable<Cell> {
        int i, j, height;

        public Cell(int i, int j, int height) {
            this.i = i;
            this.j = j;
            this.height = height;
        }

        @Override
        public int compareTo(Cell that) {
            return this.height - that.height;
        }
    }

    private static final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int trapRainWater(int[][] heightMap) { // O(m * n * lg(m+n))
        if (heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) {
            return 0;
        }

        int row = heightMap.length, col = heightMap[0].length;
        PriorityQueue<Cell> heap = new PriorityQueue<>();
        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            heap.offer(new Cell(i, 0, heightMap[i][0]));
            visited[i][0] = true;

            heap.offer(new Cell(i, col - 1, heightMap[i][col - 1]));
            visited[i][col - 1] = true;
        }

        for (int j = 0; j < col; j++) {
            heap.offer(new Cell(0, j, heightMap[0][j]));
            visited[0][j] = true;

            heap.offer(new Cell(row - 1, j, heightMap[row - 1][j]));
            visited[row - 1][j] = true;
        }

        int res = 0;

        while (!heap.isEmpty()) {
            Cell cur = heap.poll();

            for (int[] dir : DIR) {
                int i = cur.i + dir[0];
                int j = cur.j + dir[1];

                if (i >= 0 && i < row && j >= 0 && j < col && !visited[i][j]) {
                    visited[i][j] = true;

                    res += Math.max(0, cur.height - heightMap[i][j]);

                    heap.offer(new Cell(i, j, Math.max(cur.height, heightMap[i][j])));
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] heightMap = {{1, 4, 3, 1, 3, 2}, {3, 2, 1, 3, 2, 4}, {2, 3, 3, 2, 3, 1}};

        int res = new LC407TrappingRainWaterII().trapRainWater(heightMap);
        System.out.println(res);
    }
}
