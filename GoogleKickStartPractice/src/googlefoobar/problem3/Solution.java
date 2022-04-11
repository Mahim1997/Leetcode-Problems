package googlefoobar.problem3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    private static String getNextIteration(String number, int b, int k) {
        char[] chars = number.toCharArray();
        Arrays.sort(chars);

        StringBuilder bld = new StringBuilder();
        bld.append(chars);

        String smallerNumberStr = bld.toString();
        int smallerNum = Integer.parseInt(smallerNumberStr, b);

        String largerNumberStr = bld.reverse().toString();
        int largerNum = Integer.parseInt(largerNumberStr, b);

        int diff = largerNum - smallerNum;

        String diffStr = Integer.toString(diff, b);

//        System.out.println("largerNumStr = " + largerNumberStr
//        + ", smallerNumStr = " + smallerNumberStr + ", diffStr = " + diffStr);
        bld = new StringBuilder();
        for (int i = diffStr.length(); i < k; i++) {
            bld.append('0');
        }
        bld.append(diffStr);

        return bld.toString();
    }

    public static int solution(String number, int b) {
        //Your code here
        int k = number.length();

        int numIterations = 0;
        Map<String, Integer> mapPosition = new HashMap<>();

        String nextItr = number;
        while (true) {
            nextItr = getNextIteration(nextItr, b, k);
            if (mapPosition.containsKey(nextItr) == true) {
                break;
            }

            // if '0' is found, return 1
            if (Integer.parseInt(nextItr) == 0) {
                return 1;
            }

            mapPosition.put(nextItr, numIterations);
            numIterations++;
        }

        int lastPos = mapPosition.get(nextItr);
        int ans = numIterations - lastPos;

        return ans;
    }

//    public static void main(String[] args) {
//        String number = "1211";
//        int b = 10;
//
//        int ans = solution(number, b);
//        System.out.println("number = " + number + ", b = " + b + ", ans = " + ans);
//    }
}
