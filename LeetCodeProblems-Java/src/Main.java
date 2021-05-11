import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author viper
 */
public class Main {

    public static int fib(int n){
        if(n <= 1)
            return n;

        int f_nMinus2 = 0; // f_0 = 0
        int f_nMinus1 = 1; // f_1 = 1
        int f_n = 2;

        for(int i=2; i<=n; i++){
            f_n = f_nMinus1 + f_nMinus2;
            f_nMinus2 = f_nMinus1;
            f_nMinus1 = f_n;
        }

        return f_n;
    }

    public static void main(String[] args) {
        int low = 0;
        int high = 30;

        for(int i=low; i<=high; i++){
            String s = "if (n == " + i + ") { return " + fib(i) + ";}";
            System.out.println(s);
        }

    }
}
