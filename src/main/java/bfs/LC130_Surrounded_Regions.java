package bfs;

import adt.MyArray;

import java.util.LinkedList;
import java.util.Queue;

public class LC130_Surrounded_Regions {

    private static final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }

        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || j == 0 || i == row - 1 || j == col - 1) {
                    if (board[i][j] == 'O') {
                        visited[i][j] = true;
                        queue.offer(i * col + j);
                        board[i][j] = 'Y';
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int[] dir : DIR) {
                int i = cur / col + dir[0];
                int j = cur % col + dir[1];

                if (i >= 0 && i < row && j >= 0 && j < col && board[i][j] == 'O' && !visited[i][j]){
                    visited[i][j] = true;
                    queue.offer(i * col + j);
                    board[i][j] = 'Y';
                }

            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                } else if (board[i][j] == 'Y') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};

        MyArray.displayMatrix(board);

        solve(board);

        MyArray.displayMatrix(board);

    }
}
