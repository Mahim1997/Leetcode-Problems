class Solution {
    public int minCost(String colors, int[] neededTime) {
        // Sliding window to maintain for same adjacent colors
        // Cumulative sum
        // Max
        // Subtract the max from the cumulative sum (remaining all will have `minimum` times in total)
        // Repeat until all
        
        int n = colors.length();
        
        int left = 0, right = 0;
        int total = 0;
        
        while((left < n) && (right < n)) {
            int maxWindow = neededTime[left];
            int cumlSumWindow = 0;
 
            while(colors.charAt(left) == colors.charAt(right)) {
                int time = neededTime[right];
                cumlSumWindow += time;
                maxWindow = Math.max(maxWindow, time);
                
                right++;
                if(right >= n)
                    break;
            }
            
            total += (cumlSumWindow - maxWindow);
            
            left = right;
        }
        
        
        return total;
    }
}