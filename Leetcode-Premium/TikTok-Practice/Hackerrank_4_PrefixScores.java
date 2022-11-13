import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'getPrefixScores' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    private static long MOD = 1000000000 + 7;

    public static List<Integer> getPrefixScores(List<Integer> list) {
    // Write your code here
        int n = list.size();
        Integer[] ans = new Integer[n];
        Arrays.fill(ans, 0);
        
        int maxSoFar = 0;
        long sumSoFar = 0;
        
        // Subarray with first element only
        maxSoFar = list.get(0);
        
        sumSoFar = (long) (maxSoFar%MOD *2);
        sumSoFar %= MOD;
        ans[0] = (int) sumSoFar;
        
        long lastElementSoFar = (long) ans[0];
        
        // maxSoFar only for the starting element
        // Next subarrays
        for(int prefixSize = 1; prefixSize < n; prefixSize++) {
            int newElement = list.get(prefixSize);
            if(maxSoFar >= newElement) {
                // just new element will be added
                lastElementSoFar = (lastElementSoFar%MOD + newElement%MOD) % MOD;
                
                long sumSoFarCurrent = (long) (ans[prefixSize - 1]%MOD + lastElementSoFar%MOD) % MOD;
                ans[prefixSize] = (int) sumSoFarCurrent;
            }
            else {
                // need to do "diff" related stuffs
                int diff = newElement - maxSoFar;
                maxSoFar = newElement;
                
                long sumSoFarCurrent = (long) (prefixSize%MOD * diff%MOD);
                sumSoFarCurrent %= MOD;
                
                sumSoFarCurrent += (long) (ans[prefixSize - 1]%MOD);
                sumSoFarCurrent %= MOD;
                
                // Need to add last element as well
                lastElementSoFar = (lastElementSoFar%MOD + diff%MOD + newElement%MOD) % MOD;
                
                sumSoFarCurrent += lastElementSoFar%MOD;
                sumSoFarCurrent %= MOD;
                
                // System.out.println("For prefixSize: " + prefixSize + ", ans: " + Arrays.toString(ans) + ", diff: " + diff + ", sumSoFarCurrent: " + sumSoFarCurrent);

                ans[prefixSize] = (int) sumSoFarCurrent;
            }
        }
        
        List<Integer> ansList = Arrays.asList(ans);
        return ansList;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, arrCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.getPrefixScores(arr);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
