package _1_practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T;
        int numChildren;
        int numCandyBags;
        List<Integer> candies = new ArrayList<>();
        
        while(sc.hasNext()){
            T = sc.nextInt();
            for(int t=1; t<=T; t++){
                numCandyBags = sc.nextInt();
                numChildren = sc.nextInt();
                
                candies = new ArrayList<>();
                for(int i=0; i<numCandyBags; i++){
                    candies.add(sc.nextInt());
                }
                int ans = getRemainingCandies(numChildren, numCandyBags, candies);
                System.out.println("Case #" + t + ": " + ans);
            }
        }
        
        
    }

    private static int getRemainingCandies(int numChildren, int numCandyBags, List<Integer> candies) {
        int sum = candies.stream().collect(Collectors.summingInt(Integer::intValue));
//        System.out.println("numChildren = " + numChildren + ", numCandyBags = " 
//                + numCandyBags + ", candies = " + candies);
        return sum%numChildren;
    }
}
