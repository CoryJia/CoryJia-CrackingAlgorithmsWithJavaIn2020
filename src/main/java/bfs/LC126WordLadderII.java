package bfs;

import java.util.*;

public class LC126WordLadderII {
    public List<List<String>> findLaddersByOneDirection(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();

        Set<String> dict = new HashSet<>(wordList);

        Queue<String> queue = new LinkedList<>();
        Map<String, List<String>> graph = new HashMap<>();

        queue.offer(beginWord);
        boolean findEndWord = false;

        while (!queue.isEmpty()) {
            Set<String> visitedThisLev = new HashSet<>();
            int size = queue.size();

            while (size-- > 0) {
                String cur = queue.poll();
                char[] chars = cur.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    char temp = chars[i];

                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;

                        String str = String.valueOf(chars);

                        if (ch != temp && dict.contains(str)) {
                            if (str.equalsIgnoreCase(endWord)) {
                                findEndWord = true;
                            }

                            if (!visitedThisLev.contains(str)) {
                                List<String> one = new ArrayList<>();
                                one.add(str);
                                graph.put(str, one);
                                queue.offer(str);
                                visitedThisLev.add(str);
                            }else {
                                List<String> one = graph.get(str);
                                one.add(str);
                                graph.put(str, one);
                            }
                        }
                    }

                    chars[i] = temp;
                }

                dict.removeAll(visitedThisLev);

                if (findEndWord) {
                    List<String> one = new LinkedList<>();
                    one.add(endWord);
                    search(res, one, endWord, beginWord, graph);

                    return res;
                }
            }
        }
        return res;
    }

    private void search(List<List<String>> res, List<String> one, String cur, String end, Map<String, List<String>> graph) {
        if (cur.equals(end)) {
            List<String> copy = new LinkedList<>(one);
            res.add(copy);
            return;
        }

        List<String> next = graph.get(cur);

        for (String n : next) {
            one.add(0, n);
            search(res, one, n, end, graph);
            one.remove(0);
        }
    }


    //two directions
    public List<List<String>> findLaddersByTwoDirection(String beginWord, String endWord, List<String> wordList) {
        return null;
    }
}
