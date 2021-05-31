import java.util.*;
import java.util.stream.Collectors;

/**
 * @author viper
 */
// CTRL + ALT + L -> Indent

public class Main {
    static void analyze(int[][] matrix) {
        Map<Integer, List<Integer[]>> map = new HashMap<>();

        for (int i = 0; i < matrix.length; i++) {
            int x = matrix[i][0];
            map.putIfAbsent(x, new ArrayList<>());
            Integer[] arr = {matrix[i][0], matrix[i][1]};
            map.get(x).add(arr);
        }

        for (int key : map.keySet()) {
            System.out.println(key + ": " +
                    map.get(key).stream().map(x -> "(" + new String(String.valueOf(x[0]) + "," + String.valueOf(x[1])) + ")")
                            .collect(Collectors.joining(", "))

            );
        }
    }

    public static void main(String[] args) {

        int[] arr = {4, 4, 0, 0};

        int cnt_above_zero = (int) Arrays
                .stream(arr)
                .filter(x -> x > 0)
                .count();
        System.out.println("cnt_above_zero = " + cnt_above_zero);

        // Print array
//        System.out.println(Arrays.
//                stream(arr).
//                mapToObj(String::valueOf).
//                collect(Collectors.joining(", ")));

        // keys are in ordered sorted due to TreeMap
        Map<Integer, Integer> map_equal = new TreeMap<>(Collections.reverseOrder());

        Arrays.stream(arr)
                .filter(x -> x > 0)
                .forEach(x -> map_equal.put(x, map_equal.getOrDefault(x, 0) + 1));

        System.out.println(map_equal);

        // Cumulative counting.
        // 0 <= citations[i] <= 1000
        int prev_key = -1; // -1 will not appear in citations .. can use boolean flag instead.
        for(int key: map_equal.keySet()){
            map_equal.put(key, map_equal.get(key) + map_equal.getOrDefault(prev_key, 0));

            prev_key = key;
        }

        // now will have to return which key (in order) has equal or more value.
        System.out.println(map_equal);

        for(int key: map_equal.keySet()){
            if(map_equal.get(key) >= key){
                System.out.println(key);
                break;
            }
        }

        System.out.println("Finally, returning 0");

    }
}
