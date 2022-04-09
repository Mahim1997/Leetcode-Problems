package googleCodeJam.problem1;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // read inputs
//        int T = sc.nextInt();
        int T = Integer.parseInt(sc.nextLine().trim());
        int t = 1;
//        while(sc.hasNextLine()){
        for (t = 1; t <= T; t++) {
            String s = sc.nextLine();
//            System.out.println("<INPUT>: s = <" + s + ">");
            String ans = getAnswer(s);
            System.out.println("Case #" + t + ": " + ans);

            if (t == T) {
                break;
            }
//            t++;
        }
    }

    private static int numUniqueCharacters(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }

        // Also handles 1 length
        return set.size();
    }

    private static String getAnswer(String s) {
        // check all same chars
        if (numUniqueCharacters(s) == 1) {
            return s;
        }

        // normal logic
        int len = s.length();

        StringBuilder bld = new StringBuilder();
        for (int i = 0; i < len; i++) {
            // check for substring [i, len), whether same chars
            String substr = s.substring(i, len);
            if (numUniqueCharacters(substr) == 1) {
                bld.append(substr);
                return bld.toString();
            }

            char c = s.charAt(i);
            // last char, only once
            if (i == (len - 1)) {
                bld.append(c);
                break;
            }

            char nextChar = s.charAt(i + 1);
            int j = i + 1;
            while(nextChar == c){
                j++;
                nextChar = s.charAt(j);
            }
            
            if (c > nextChar) {
                // just once
                bld.append(c);
            } else {
                // twice
                bld.append(c).append(c);
            }
        }

        return bld.toString();
    }
}
