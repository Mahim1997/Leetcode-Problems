package googlefoobar.problem1;

public class Solution {

    private static final String[] codes = {
        "100000", //a",
        "110000", //b",
        "100100", //c",
        "100110", //d",
        "100010", //e",
        "110100", //f",
        "110110", //g",
        "110010", //h",
        "010100", //i",
        "010110", //j",
        "101000", //k",
        "111000", //l",
        "101100", //m",
        "101110", //n",
        "101010", //o",
        "111100", //p",
        "111110", //q",
        "111010", //r",
        "011100", //s",
        "011110", //t",
        "101001", //u",
        "111001", //v",
        "010111", //w",
        "101101", //x",
        "101111", //y",
        "101011", //z"
    };

    private static String getMappingOfCharacter(char c) {
        if (c == ' ') // space character
        {
            return "000000";
        }

        int ascii = c - 'a';
        if ((ascii >= 0) && (ascii <= 26)) // is lower case
        {
            return Solution.codes[ascii];
        }

        // is upper case
        int asciiCapital = c - 'A';
        return "000001" + Solution.codes[asciiCapital];
    }

    public static String solution(String s) {
        // add as map
        StringBuilder bld = new StringBuilder();

        for (char c : s.toCharArray()) {
            bld.append(Solution.getMappingOfCharacter(c));
        }

        return bld.toString();
    }

//    public static void main(String[] args) {
//        String s = "The Quick";
//        System.out.println(Solution.solution(s));
//    }
}
