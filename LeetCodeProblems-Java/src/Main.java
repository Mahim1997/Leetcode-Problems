import java.util.*;
import java.util.stream.Collectors;

/**
 * @author viper
 */
// CTRL + ALT + L -> Indent

public class Main {


    static void printPermutations(List<Integer> list){

        if(list.size() == 1){
            System.out.println(list.get(0));
            return;
        }

        for(int i=0; i<list.size(); i++){
            // remove
            int temp = list.get(i);
            System.out.print("<" + list.get(i) + ", ");
            list.remove(i);

            // recurse OR backtrack
            printPermutations(new ArrayList<>(list));

            // add back
            list.add(i, temp);
        }
    }

    public static void main(String[] args) {

        Integer[] arr = {1, 2, 3}; // ,3,4,5

        List<Integer> list = Arrays.stream(arr).collect(Collectors.toList());

        printPermutations(list);

    }
}
