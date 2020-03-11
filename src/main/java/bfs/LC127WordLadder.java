package bfs;

import java.util.*;

public class LC127WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null) {
            return -1;
        }

        Set<String> dict = new HashSet<>(wordList);

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int minDis = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                String cur = queue.poll();
                char[] chars = cur.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    char temp = chars[i];

                    for (char ch = 'a'; ch <= 'z' ; ch++) {
                        chars[i] = ch;

                        String str = String.valueOf(chars);

                        if(ch != temp && dict.contains(str)){
                            if (str.equals(endWord)) return minDis + 1;

                            queue.offer(str);
                            dict.remove(str);
                        }
                    }
                    chars[i] = temp;
                }
            }
            minDis++;
        }

        return 0;
    }
}
