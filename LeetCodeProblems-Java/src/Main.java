import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author viper
 */
public class Main {

    static void printArary(int []arr){
        System.out.println(
            Arrays.stream(arr)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","))
        );
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,7,8};
        printArary(arr);
    }
}
