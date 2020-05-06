package dfs;

import adt.MyArray;

/**
 * @author coryjia@gmail.com
 * @date 5/5/20 11:12 PM
 */
public class LC10_Regular_Expression_Matching {
    public boolean isMatchNoPruning(String s, String p) {
        if (s == null || p == null) return false;
        return dfs(s, 0, p, 0, s.length(), p.length());
    }

    private boolean dfs(String s, int idxS, String p, int idxP, int lenS, int lenP) {
        if (idxP == lenP) {
            return idxS == lenS;
        } else if (idxP == lenP - 1 || p.charAt(idxP + 1) != '*') {
            if (idxS < lenS && (p.charAt(idxP) == '.') || p.charAt(idxP) == s.charAt(idxS)) {
                return dfs(s, idxS + 1, p, idxP + 1, lenS, lenP);
            } else {
                return false;
            }
        } else { // '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
            int i = idxS - 1; // -1 means we check from matching empty "" string


            // i < idsS means ""
            while (i < lenS && (i < idxS || p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(i))) {
                if (dfs(s, i + 1, p, idxP + 2, lenS, lenP)) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    public boolean isMatchWithPruning(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int lenS = s.length(), lenP = p.length();

        // boundary is lenS + 1, lenP + 1, because we want to check matching begin with "" empty string
        Boolean[][] mem = new Boolean[lenS + 1][lenP + 1];

        return dfs(s, 0, p, 0, mem, lenS, lenP);

    }

    private boolean dfs(String s, int idxS, String p, int idxP, Boolean[][] mem, int lenS, int lenP) {
        if (mem[idxS][idxP] != null) {
            return mem[idxS][idxP];
        }

        if (idxP == lenP) {
            return idxS == lenS;
        } else if (idxP == lenP - 1 || p.charAt(idxP + 1) != '*') {
            if (idxS < lenS && (p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(idxS))) {
                mem[idxS][idxP] = dfs(s, idxS + 1, p, idxP + 1, mem, lenS, lenP);
            } else {
                mem[idxS][idxP] = false;
                return false;
            }
        } else { // '*' branch
            int i = idxS - 1;

            while (i < lenS && (i < idxS || p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(idxS))) {
                if (dfs(s, i + 1, p, idxP + 2, mem, lenS, lenP)) {
                    mem[idxS][idxP] = true;
                    return true;
                }
                i++;
            }
            mem[idxS][idxP] = false;
        }
        return mem[idxS][idxP];
    }

    public boolean isMatchWithDP(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int lenS = s.length(), lenP = p.length();

        if (lenP == 0) return lenS == 0;

        boolean[][] dp = new boolean[lenS + 1][lenP + 1];
        dp[lenS][lenP] = true;

        int k = lenP - 2;

        while (k >= 0) {
            if (p.charAt(k + 1) == '*') {
                dp[lenS][k] = true;
            } else {
                break;
            }
            k -= 2;
        }

        MyArray.displayMatrix(dp);
        for (int i = lenS - 1; i >= 0; i--) {
            for (int j = lenP - 1; j >= 0; j--) {
                if (p.charAt(j) == '*') {
                    continue;
                }

                if (j + 1 >= lenP || p.charAt(j + 1) != '*') {
                    if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                        dp[i][j] = dp[i + 1][j + 1];
                    } else {
                        dp[i][j] = false;
                    }
                } else {
                    int idx = i - 1;
                    dp[i][j] = false;

                    while (idx < lenS && (idx < i || p.charAt(j) == '.' || p.charAt(j) == s.charAt(idx))) {
                        if (dp[idx + 1][j + 2]) { // start from dp[i][j + 2]
                            dp[i][j] = true;
                            break;
                        }
                        idx++;
                    }
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b"; // '*' could be '.'

//        System.out.println(new LC10_Regular_Expression_Matching().isMatchNoPruning(s, p));
//        System.out.println(new LC10_Regular_Expression_Matching().isMatchWithPruning(s, p));
        System.out.println(new LC10_Regular_Expression_Matching().isMatchWithDP(s, p));
    }
}
