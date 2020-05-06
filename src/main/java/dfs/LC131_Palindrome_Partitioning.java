package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author coryjia@gmail.com
 * @date 5/6/20 2:57 PM
 */
public class LC131_Palindrome_Partitioning {
    public List<List<String>> partition(String s){
        List<List<String>> res = new ArrayList<>();

        if (s == null || s.length() == 0) {
            return res;
        }

        dfs(res, s, 0, new ArrayList<>());
        return res;
    }

    private void dfs(List<List<String>> res, String s, int idx, List<String> path) {
        if (idx == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        int len = s.length();

        for (int i = idx; i < len; i++) {
            if(isPalindrome(s, idx, i)){
                path.add(s.substring(idx, i + 1));
                dfs(res, s, i + 1, path);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while(start <= end){
            if(s.charAt(start++) != s.charAt(end--)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aab";

        System.out.println(new LC131_Palindrome_Partitioning().partition(s));
    }
}
