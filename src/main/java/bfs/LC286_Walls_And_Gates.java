package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class LC286_Walls_And_Gates {
    private final int INF = Integer.MAX_VALUE;
    private final int[][] DIR = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}}; //UP,DOWN,LEFT,RIGHT

    public void wallsAndGates(int[][] rooms){
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0){
            return;
        }

        int row = rooms.length, col = rooms[0].length;

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0){
                    //store all the gates into the queue
                    queue.offer(i * col + j);
                }
            }
        }

        int minLen = 1;

        //bfs
        while (!queue.isEmpty()){
            int size = queue.size();

            while (size-- > 0){
                int cur = queue.poll();
                int curI = cur / col;
                int curJ = cur % col;

                for (int[] dir : DIR) {
                    int i = curI + dir[0];
                    int j = curJ + dir[1];

                    if(i >= 0 && i < row && j >= 0 && j < col && rooms[i][j] == INF){
                        rooms[i][j] = minLen;
                        queue.offer(i * col + j);
                    }
                }
            }
            minLen++;
        }
    }

}
