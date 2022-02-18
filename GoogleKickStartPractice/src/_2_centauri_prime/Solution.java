package _2_centauri_prime;

import java.util.*;

public class Solution {

    static Set<Character> vowels;

    private static boolean hasNoRuler(char c) {
        return (c == 'y') || (c == 'Y');
    }

    private static String getRuler(String kingdom) {
        int strlen = kingdom.length();
        char lastCharSmaller = kingdom.charAt(strlen - 1);

        // TODO: implement this method to determine the ruler name, given the kingdom.
        String ruler;
        if (hasNoRuler(lastCharSmaller) == true) {
            ruler = "nobody";
        } else if (Solution.vowels.contains(lastCharSmaller) == true) {
            ruler = "Alice";
        } else {
            ruler = "Bob";
        }

        return ruler;
    }

    public static void main(String[] args) {
        // initialize vowels only once.
        List<Character> listVowels = List.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        Solution.vowels = new HashSet<>(listVowels);

        try ( Scanner in = new Scanner(System.in)) {
            int T = in.nextInt();

            for (int t = 1; t <= T; ++t) {
                String kingdom = in.next();
                System.out.println(
                        "Case #" + t + ": " + kingdom + " is ruled by " + getRuler(kingdom) + ".");
            }
        }
    }
}
