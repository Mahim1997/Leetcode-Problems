class Solution {
    public int lastStoneWeightII(int[] stones) {
        /*  Partition array into two subarrays S1, S2
            Need to maximize S2 - S1 (best case, sum/2)
            DP(0/1 KS style) from upto sum/2
        */
        int n = stones.length, sumTotal = 0;
        for(int st: stones){sumTotal += st;}
        
        // edge case
        if(n == 1){return stones[0];}
        
        // DP style
        int halfSum = sumTotal/2;
        boolean[] prev = new boolean[sumTotal + 1];
        prev[0] = true; // 0 is reachable
        
        
        int maxSumPossible = Integer.MIN_VALUE;
        // per stone
        for(int st: stones){
            // create a copy array
            boolean[] temp = prev.clone();
            temp[st] = true; // this stone has a true value
            // per sum
            for(int sm=st; sm<=halfSum; sm++){
                if(prev[sm - st] == true){ // only use once
                    temp[sm] = true;
                    
                    maxSumPossible = Math.max(maxSumPossible, sm);
                    // System.out.println("sm = " + sm + ", maxSumPossible = " + maxSumPossible + ", st = " + st);
                }
                if((maxSumPossible*2) == sumTotal){
                    return 0; // best case reached
                }
            }
            // copy back temp <---> prev
            prev = temp;
        }
        return (sumTotal - maxSumPossible*2);
    }
}