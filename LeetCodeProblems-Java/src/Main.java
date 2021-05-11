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
        String s = "mbaocd";
        s.toCharArray().sort(

        );
        System.out.println(s);

    }
}
