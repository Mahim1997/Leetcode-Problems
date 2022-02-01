class Solution {
    
    private int[] twoSumBestDifference(int[] nums, int target, 
                                     int currentVal, int start){
        // return the bestDiff, for which max closest is possible 
        // wrt target & currentVal
        int bestSum = 0;
        int bestDiff = Integer.MAX_VALUE;
        
        int left = start;
        int right = nums.length - 1;
        while(left < right){
            int sum = currentVal + nums[left] + nums[right];
            int diff = Math.abs(sum - target);
            if(diff < bestDiff){
                bestDiff = diff;
                bestSum = sum;
            }
            // advance pointers.
            if(sum == target){
                break;
            }
            else if(sum < target){
                left++;
            }
            else if(sum > target){
                right--;
            }
        }
        return new int[]{bestSum, bestDiff};
    }
    
    public int threeSumClosest(int[] nums, int target) {
        int maxClosest = nums[0] + nums[1] + nums[2];
        
        if(nums.length == 3){
            return maxClosest;
        }
        
        // sort the array
        Arrays.sort(nums);
        
        // for(int x: nums){System.out.print(x + " ");}
        // System.out.println("");
        
        
        int bestDiff = Integer.MAX_VALUE;
        int bestSum = 0;
        
        for(int start=0; start<(nums.length-2); start++){
            int currentVal = nums[start];
            int[] ans = twoSumBestDifference(nums, target, currentVal, start+1);
            
            int currentSum = ans[0];
            int currentDiff = ans[1];
        
            // System.out.println("start = " + start + ", currentSum = " 
            //                    + currentSum + ", currentDiff = " + currentDiff);
            
            if(currentDiff < bestDiff){
                bestSum = currentSum;
                bestDiff = currentDiff;
            }
        }
        
        return bestSum;
    }
}