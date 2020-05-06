package dfs.LC10;

/**
 * @author coryjia@gmail.com
 * @date 5/5/20 11:12 PM
 */
public class LC_Regular_Expression_Matching {
    public boolean isMatchNoPruning(String s, String p ){
        if (s == null || p == null) return false;
        return dfs(s, 0, p, 0, s.length(), p.length());
    }

    private boolean dfs(String s, int idxS, String p, int idxP, int lenS, int lenP) {
        if(idxP == lenP ){
            return idxS == lenS;
        }else if(idxP == lenP - 1 || p.charAt(idxP + 1) != '*'){
            if(idxS < lenS && (p.charAt(idxP) == '.') || p.charAt(idxP) == s.charAt(idxS)){
                return dfs(s, idxS + 1, p, idxP+ 1, lenS, lenP);
            }else {
                return false;
            }
        }else { // '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
            int i = idxS - 1;

            while(i < lenS && (i < idxS || p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(i))){
                if(dfs(s, i + 1, p, idxP + 2,lenS, lenP)){
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "ab";
        String p = ".*"; // '*' could be '.'

        System.out.println(new LC_Regular_Expression_Matching().isMatchNoPruning(s, p));
    }
}
