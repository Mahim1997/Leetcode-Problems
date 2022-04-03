package googleCodeJam.problem1;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // read inputs
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            int R = sc.nextInt();
            int C = sc.nextInt();

            char[][] chars = getPattern(R, C);
            printPattern(t, chars);

        }
    }

    private static char[][] getPattern(int R, int C) {
        int numRows = 2 * R + 1, numCols = 2 * C + 1;
        char[][] chars = new char[numRows][numCols];

        // normal
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                boolean isRowEven = (r % 2 == 0);
                boolean isColEven = (c % 2 == 0);

                if (isRowEven && isColEven) {
                    chars[r][c] = '+';
                } else if (isRowEven && !isColEven) {
                    chars[r][c] = '-';
                } else if (!isRowEven && isColEven) {
                    chars[r][c] = '|';
                } else {
                    chars[r][c] = '.';
                }
            }
        }

        // post processing
        chars[0][0] = '.';
        chars[0][1] = '.';
        chars[1][0] = '.';
        chars[1][1] = '.';

        return chars;
    }

    private static void printPattern(int t, char[][] chars) {
        System.out.println("Case #" + t + ":");

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                System.out.print(chars[i][j]);
            }

            System.out.println("");
        }
    }
}
