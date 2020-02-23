package adt;

import java.io.*;
import java.util.*;

public class IO {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String args[]) throws Exception {
        // read the string filename
        String filename;
//        filename = scan.nextLine();
        filename = "/Users/coryjia/Desktop/Desktop - Jianbo’s iMac/IdeaProjects/CrackingAlgorithms2019/src/crackinginterviews/apple/test_cases_d7qct4f9ec0/input001.txt";
        File file = new File(filename);

        Scanner sc = new Scanner(file);
        Set<String> set = new HashSet();

        Queue<String> buffer = new LinkedList<>();
        while (sc.hasNextLine()) {
            String str = sc.nextLine();

            if (buffer.size() == 100) {
                doSomething(buffer);
            }
        }

        doSomething(buffer);

        writeIntoFile(filename, set);

    }

    private static void writeIntoFile(String filename, Set<String> set) throws IOException {
        //        int index =filename.lastIndexOf("input");
        int index = filename.lastIndexOf("hosts");

        String filePath = filename.substring(0, index);
        System.out.println(filePath);
        String fileTxtName = filename.substring(index);

        System.out.println(fileTxtName);

        String outPath = filePath + "gifs_" + fileTxtName;
        System.out.println(outPath);
        File newFile = new File(outPath);
        BufferedWriter output = new BufferedWriter(new FileWriter(newFile));

        StringBuilder sb = new StringBuilder();
        for (
                String gifName : set) {
            System.out.println(gifName);
            sb.append(gifName).append("\n");
        }
        try {
            output.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doSomething(Queue<String> buffer) {
    }
}

