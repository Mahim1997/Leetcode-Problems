import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {

    public static void printArray(int []arr){
        System.out.println(Arrays.stream(arr).
                mapToObj(String::valueOf).
                collect(Collectors.joining(", ")));
    }

    public static void printSingleList(List<Integer> list) {
        System.out.println(list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
    }
    public static void printMultipleList(List<List<Integer>> ans){
        ans.forEach(list -> System.out.println(list.stream()
                        .map(String::valueOf) // x -> String.valueOf(x)
                        .collect(Collectors.joining(", "))));
    }


    private static String getKeysWithSpecifiedValue(Map<Integer, Integer> map, int val){
        return map.keySet()
                .stream()
                .filter(t -> {return map.get(t) == val;})
                .map(x -> String.valueOf(x))
                .collect(Collectors.joining(", "));
    }
    public static void printMap(Map<Integer, Integer> map, int left_partition, int right_partition){
        System.out.print("LEFT:  ");
        System.out.println(getKeysWithSpecifiedValue(map, left_partition));

        System.out.print("RIGHT: ");
        System.out.println(getKeysWithSpecifiedValue(map, right_partition));
    }

}
