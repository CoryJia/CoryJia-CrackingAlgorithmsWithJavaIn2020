package bfs;

import java.util.*;

public class LC126WordLadderIII {

    /**
     * Word Ladder III -> Only output any one of the shortest path
     */

    public List<String> findLadder(String beginWord, String endWord, List<String> wordList){
        List<String> res = new LinkedList<>();

        Set<String> dict = new HashSet<>(wordList);
        dict.remove(beginWord);

        Queue<String> queue = new LinkedList<>();
        Map<String, String> graph = new HashMap<>();

        queue.offer(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                String cur = queue.poll();

                char[] chars = cur.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char temp = chars[i];

                    for (char ch = 'a'; ch <= 'z' ; ch++) {
                        chars[i] = ch;

                        String next = String.valueOf(chars);
                        if (ch != temp && dict.contains(next)){
                            graph.put(next, cur);

                            if (next.equals(endWord)) {
                                String curWord = endWord;

                                while (curWord != null) {
                                    res.add(0, curWord);
                                    curWord = graph.get(curWord);
                                }

                                return res;
                            }

                            queue.offer(next);
                            dict.remove(next);
                        }
                    }

                    chars[i] = temp;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        LC126WordLadderIII solution = new LC126WordLadderIII();

        String beginWord = "tot";
        String endWord = "dog";

        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog", "hop", "tot", "hog");

        List<String> res = solution.findLadder(beginWord, endWord, wordList);
        System.out.println(res);

         beginWord = "hot";
         endWord = "dog";

         wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

         res = solution.findLadder(beginWord, endWord, wordList);
        System.out.println(res);

    }
}
