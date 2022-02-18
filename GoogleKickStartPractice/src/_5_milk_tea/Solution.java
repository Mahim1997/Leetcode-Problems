package _5_milk_tea;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCases = in.nextInt();
        for (int testCase = 1; testCase <= testCases; testCase++) {
            int numberOfFriends = in.nextInt();
            int numberOfForibbenTeas = in.nextInt();
            int optionsOffered = in.nextInt();
            int[][] friendsOrders = new int[numberOfFriends][optionsOffered];
            for (int friend = 0; friend < numberOfFriends; friend++) {
                String currentFriendOrder = in.next().trim();
                for (int option = 0; option < optionsOffered; option++) {
                    friendsOrders[friend][option]
                            = Character.getNumericValue(currentFriendOrder.charAt(option));
                }
            }
            int[][] forbiddenOrders = new int[numberOfForibbenTeas][optionsOffered];
            for (int forbiddenIndex = 0; forbiddenIndex < numberOfForibbenTeas; forbiddenIndex++) {
                String currentForbiddenTeaOrder = in.next().trim();
                for (int option = 0; option < optionsOffered; option++) {
                    forbiddenOrders[forbiddenIndex][option]
                            = Character.getNumericValue(currentForbiddenTeaOrder.charAt(option));
                }
            }
            int ans = findSmallestNumberOfComplaints(friendsOrders, forbiddenOrders);
            System.out.println("Case #" + testCase + ": " + ans);
        }
    }

    static void printDoubleArr(int[][] _2D) {
        for (int[] arr : _2D) {
            for (int x : arr) {
                System.out.print(x + " ");
            }
            System.out.println("");
        }
    }

    private static Set<String> convertArrToStringSet(int[][] arr2D) {
        Set<String> set = new HashSet<>();

        for (int[] arr : arr2D) {
            String str = Arrays.stream(arr).boxed()
                    .map(String::valueOf)
                    .collect(Collectors.joining(""));
            set.add(str);
        }

        return set;
    }

    static int findSmallestNumberOfComplaints(
            int[][] friendsOrders,
            int[][] forbiddenOrders) {
        // TODO: implement this method to find the smallest number of complaints given that Shakti will
        // only be getting 1 type of tea for his friends.
        int smallestNumberOfComplaints = -1;

        int numFriends = friendsOrders.length;
        int numBits = friendsOrders[0].length;

        Set<String> forbidden = convertArrToStringSet(forbiddenOrders);

        int[] agreements = new int[numBits];
        Arrays.fill(agreements, 0);

        int[] majorities = new int[numBits];
        // get these arrays' values
        getAgreementsAndMajority(agreements, majorities, friendsOrders);

        BitHolder[] bitHolders = new BitHolder[numBits];
        for (int i = 0; i < numBits; i++) {
            bitHolders[i] = new BitHolder(i, agreements[i], majorities[i]);
        }
        /*
        Arrays.stream(arr).boxed()
                    .map(String::valueOf)
                    .collect(Collectors.joining(""));
         */
        // Sort wrt agreements, ascending order
        Arrays.sort(bitHolders, (x1, x2) -> (x1.agreement - x2.agreement));

        // Now, edit each majority by flipping them
        for (int numFlipBits = 0; numFlipBits < numBits; numFlipBits++) {
            
        }

        return smallestNumberOfComplaints;
    }

    static String getConvertedString(BitHolder[] bitHolders) {
        // sort by indices
        Arrays.sort(bitHolders, (x1, x2) -> (x1.idx - x2.idx));
        
        return Arrays.stream(bitHolders)
                    .map(x -> x.bitVal)
                    .map(String::valueOf)
                    .collect(Collectors.joining(""));
    }

    static class BitHolder {

        public int idx;
        public int agreement;
        public int majority;
        public int bitVal;

        public BitHolder(int i, int a, int m) {
            this.idx = i;
            this.agreement = a;
            this.majority = m;

            // initially
            this.bitVal = this.majority;
        }

        @Override
        public String toString() {
            return "idx = " + idx + ", agreement = " + agreement + ", majority = " + majority;
        }
    }

    private static void getAgreementsAndMajority(
            int[] agreements,
            int[] majority,
            int[][] friendsOrders) {
        int numBits = agreements.length;
        int numFriends = friendsOrders.length;

        // per bit
        for (int b = 0; b < numBits; b++) {
            int ones = 0, zeros = 0;
            // per friend
            for (int i = 0; i < numFriends; i++) {
                int order = friendsOrders[i][b];
                if (order == 0) {
                    zeros++;
                } else if (order == 1) {
                    ones++;
                }
            }
            majority[b] = (ones >= zeros) ? 1 : 0;
            for (int i = 0; i < numFriends; i++) {
                int order = friendsOrders[i][b];
                if (order == majority[b]) {
                    agreements[b]++;
                }
            }
        }
    }
}
