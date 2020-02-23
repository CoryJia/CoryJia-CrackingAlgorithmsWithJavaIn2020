package adt;

import basicalgo.s2sortingalgorithm.SortingAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MyArray {
    static Random random = new Random(System.currentTimeMillis());

    public static int[] generateRandomUnsortedArray(int length, int bound) {
        int[] arr = new int[length];


        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt() % bound;
        }

        while (arr.length != 1 && isSorted(arr)) {
            shuffle(arr);
        }

        return arr;
    }

    public static int[] generateRandomSortedArray(int length, int bound) {
        int[] arr = generateRandomUnsortedArray(length, bound);

        SortingAlgorithm.quickSort(arr);

        return arr;

    }

    public static int[] generateRandomPositiveSortedArray(int length, int bound) {
        int[] arr = generateRandomPositiveSortedArray(length, bound);

        SortingAlgorithm.quickSort(arr);

        return arr;

    }

    public static int[] generateRandomPositiveUnsortedArray(int length, int bound) {
        int[] arr = new int[length];


        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(100);
        }

        while (arr.length != 1 && isSorted(arr)) {
            shuffle(arr);
        }
        return arr;
    }

    private static void shuffle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int randIndex = random.nextInt(i + 1);

            swap(arr, randIndex, i);
        }
    }


    // Check if an array is sorted
    public static boolean isSorted(int[] arr) {
        if (arr == null || arr.length <= 1) return true;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }

    //Swap two elements in an array
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static <E> void displayMatrix(E[][] matrix) {
        System.out.println("[");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 0) System.out.print("  ");
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }
        System.out.println("]");
        System.out.println();
    }

    public static void displayMatrix(int[][] matrix) {
        System.out.println("[");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 0) System.out.print("  \t");
                System.out.print(matrix[i][j] + "\t");
            }

            System.out.println();
        }
        System.out.println("]");
        System.out.println();
    }

    public static void displayMatrix(char[][] matrix) {
        System.out.println("[");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 0) System.out.print("  ");
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }

        int spaces = matrix[0].length * 2 + 1;

        for (int i = 0; i <= spaces; i++) {
            System.out.print(" ");
        }
        System.out.println("]");
        System.out.println();
    }

    public static void displayMatrix(double[][] matrix) {
        System.out.println("[");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 0) System.out.print("  ");
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }
        System.out.println("]");
        System.out.println();
    }

    public static void displayMatrix(boolean[][] matrix) {
        System.out.println("[");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 0) System.out.print("  ");
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }
        int spaces = matrix[0].length * 2;

        for (int i = 0; i <= spaces; i++) {
            System.out.print(" ");
        }
        System.out.println("]");
        System.out.println();
    }

    public static int[][] parser(String s) {
        if (s == null || s.length() == 0) return null;

        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();

        int col = 0;
        boolean first = true;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c != '[' && c != ',' && c != ']' && c != ' '){
                sb.append(c);
            }

            if (c == ',' || c == ']'){
                if (sb.length() != 0){
                    int cur = Integer.parseInt(sb.toString());
                    list.add(cur);
                    sb = new StringBuilder();
                }
            }

            if (first && c == ',' && s.charAt(i - 1) == ']' || first && c == ']' && s.charAt(i - 1) == ']'){
                col = list.size();
                first = false;
            }

        }

        int row = list.size() / col;

        int[][] rst = new int[row][col];

        int k = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                rst[i][j] = list.get(k++);
            }
        }

        return rst;

    }

    public static void main(String[] args) {
//        int[] rst = generateRandomSortedArray(1, 100);
//        System.out.println(Arrays.toString(rst));


        displayMatrix(parser("[[1]]"));
//        displayMatrix(parser("   [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]"));

       int[] res =  MyArray.generateRandomUnsortedArray(7, 10);

        System.out.println(Arrays.toString(res));

        res = Arrays.copyOf(res, res.length / 2);
        System.out.println(Arrays.toString(res));

    }
}
