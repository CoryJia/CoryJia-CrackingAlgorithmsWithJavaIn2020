package bfs;

import java.util.*;

/**
 * @author coryjia@gmail.com
 * @date 5/2/20 9:58 AM
 */
public class LC752_Open_The_Lock {
    public int openLock(String[] deadends, String target) {
        Set<String> deadSet = new HashSet<>();

        for (String dd : deadends) {
            if(dd.equals("0000")){
                return -1;
            }
            deadSet.add(dd);
        }
        String start = "0000";
        Set<String> visited = new HashSet<>();
        Queue<char[]> queue = new LinkedList<>();
        queue.add(start.toCharArray());
        visited.add(start);
        int minLen = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- >0){
                char[] cur = queue.poll();

                List<char[]> nexts = convert(cur, deadSet,visited);

                for(char[] next : nexts){
                    String nextStr = String.valueOf(next);
                    if(nextStr.equals(target)){
                        return minLen + 1;
                    }
                    queue.offer(next);
                    visited.add(nextStr);
                }
            }
            minLen++;
        }
        return -1;
    }

    private List<char[]> convert(char[] cur, Set<String> deadSet, Set<String> visited) {
        List<char[]> res = new ArrayList<>();

        for (int i = 0; i < cur.length; i++) {
            char temp = cur[i];

            if(temp == '9'){
                cur[i] = '0';
            }else {
                cur[i] = (char) (temp + 1);
            }

            String str = String.valueOf(cur);

            if(!deadSet.contains(str) && !visited.contains(str)){
                res.add(cur.clone());
            }

            if(temp == '0'){
                cur[i] = '9';
            }else {
                cur[i] = (char)(temp - 1);
            }

            str = String.valueOf(cur);

            if(!deadSet.contains(str) && !visited.contains(str)){
                res.add(cur.clone());
            }

            cur[i] = temp;
        }
        return res;
    }
}
