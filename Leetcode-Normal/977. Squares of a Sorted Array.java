class Solution {
    public int[] sortedSquares(int[] nums) {
        // return sortedSquaresUsingNaiveMethod(nums);
        // return sortedSquaresUsingDoublePointers(nums);
        return doublePointersReverse(nums);
    }
    
    private int[] doublePointersReverse(int[] nums){
        int[] ans = new int[nums.length];
        
        int idx = ans.length - 1;
        int left = 0, right = nums.length - 1;
        int squareLeft, squareRight;
        while(left <= right){
            squareLeft = nums[left]*nums[left];
            squareRight = nums[right]*nums[right];
            if(squareLeft >= squareRight){ // put the bigger in the end
                ans[idx] = squareLeft;
                left++;
            }
            else{
                ans[idx] = squareRight;
                right--;
            }
            idx--; // go backwards
            
        }
        
        return ans;
    }

    private int getIndexSmallestNumberLinear(int[] nums){
        for(int i=1; i<nums.length; i++){
            if((nums[i-1] < 0) && (nums[i] >= 0)){
                // return i;
                if((nums[i-1]*nums[i-1]) < (nums[i]*nums[i])){
                    return i-1;
                }else{
                    return i;
                }
            }
        }
        
        // No sign reversal.
        if(nums[0] >=0){ // all are positive
            return 0;
        }else{           // all are negative
            return nums.length - 1;
        }
    }
    
    public int[] sortedSquaresUsingDoublePointers(int[] nums){
        int[] ans = new int[nums.length];
        
        if(nums.length == 1){
            ans[0] = nums[0]*nums[0];
            return ans;
        }
        
        // Find index of smallest number.
        int idx = getIndexSmallestNumberLinear(nums);
        int left = idx, right = idx+1;
        
        if(idx == nums.length - 1){ // 
            left = idx - 1;
            right = idx;
        }else if(idx == 0){
            left = idx;
            right = idx + 1;
        }
        
        
        int result = 0; // result array index
        
        // Just like merge step of merge sort.
        int squareRight, squareLeft;
        while((left >= 0) && (right < nums.length)){
            squareRight = (nums[right]*nums[right]);
            squareLeft = (nums[left]*nums[left]);
            if(squareLeft <= squareRight){ // left element will be inserted
                ans[result] = squareLeft;
                left--;
            }else{ // right element will be inserted
                ans[result] = squareRight;
                right++;
            }
            result++;
        }
        // System.out.println("Afterwards, left = " + left + ", right = " + right + ", result = " + result);
        // remaining elements.
        while(left >= 0){
            ans[result++] = nums[left]*nums[left];
            left--;
        }
        while(right < nums.length){
            ans[result++] = nums[right]*nums[right];
            right++;
        }
        
        
        return ans;
    }
    
    private void printArray(int[] arr){
        for(int x: arr){
            System.out.print(x + " ");
        }
        System.out.println("");
    }
    
    public int[] sortedSquaresUsingNaiveMethod(int[] nums){
        int[] ans = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            ans[i] = nums[i] * nums[i];
        }
        Arrays.sort(ans);
        return ans;
    }
}