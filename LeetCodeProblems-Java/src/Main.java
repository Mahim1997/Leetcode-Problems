import java.util.*;
import java.util.stream.Collectors;

/**
 * @author viper
 */
// CTRL + ALT + L -> Indent

public class Main {
    static void printCombinations(List<Integer> list){

        Integer[] nums = {3,2,3,1,2,4,5,5,6};
        int k = 4;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

//        new int[]{1, 2, 3};

        Arrays.stream(nums)
                .flatMap(x -> (minHeap.size() > k) ? (minHeap.poll(); minHeap.add(x);) : minHeap.add(x));
    }

    public static void main(String[] args) {

        Integer[] arr = {1,2,3,4,5};

        List<Integer> list = Arrays.stream(arr).collect(Collectors.toList());

        printCombinations(list);

    }
}
