package googlefoobar.problem2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        int height = 5;
        int[] q = {19,14,28,31};
        int[] ans = Solution.solution(height, q);
        System.out.println("height = " + height + ", ans = " + convert(ans));
    }

    static String convert(int[] arr) {
        return Arrays.stream(arr).boxed().map(String::valueOf).collect(Collectors.joining(","));
    }

    private static int getParent(int rootVal, int val, int height) {
        // edge case
        if (val >= rootVal) {
            return -1;
        }

        int idx = rootVal;
        int n = height;
        
        do{
            int nextGap = (1 << (n - 1)) - 1; // Math.pow(2, (n-1)) - 1
            int rightChild = idx - 1; // right child
            int leftChild = rightChild - nextGap; // left child
            
            // check if exactly equal
            if((leftChild == val) || (rightChild == val))
                return idx;
            
            // either go left OR go right
            if(val < leftChild){ // go left
                idx = leftChild;
            }
            else{   // go right
                idx = rightChild;
            }
            // decrement n
            n--;
        }while(n >= 0);
        
        return 0;
    }

    public static int[] solution(int h, int[] q) {
        // h: height of tree, [] q: queries
        int[] ans = new int[q.length];
        int idx = 0;
        int rootVal = (1 << h) - 1; //(int) Math.pow(2, h) - 1;

        // Caching results due to repeating value
        Map<Integer, Integer> cache = new HashMap<>();
        for (int val : q) {
            int parent;
            if (cache.containsKey(val)) {
                parent = cache.get(val);
            } else {
                parent = getParent(rootVal, val, h);
                cache.put(val, parent);
            }
            ans[idx++] = parent;
        }

        return ans;
    }
}
