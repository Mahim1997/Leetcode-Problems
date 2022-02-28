class Solution {
    public List<String> summaryRanges(int[] nums) {
        // edge cases
        List<String> list = new ArrayList<>();
        
        if(nums.length == 0)
            return list;
        
        if(nums.length == 1){
            list.add(formArrow(nums[0], nums[0]));
            return list;
        }
        
        
        int left = 0, right = 1;
        while(right < nums.length){
            int successiveDifference = (nums[right]-nums[right - 1]);

            if(successiveDifference == 1)
                right++;
            else{
                list.add(formArrow(nums[left], nums[right - 1]));
                left = right;
                right = left + 1;
            }
        }

        // last number checking
        int rightIdx = Math.min(nums.length-1, right);
        list.add(formArrow(nums[left], nums[rightIdx]));

        
        return list;
    }
    
    // inclusive [a, b]: "a->b" AND [a, a] -> "a"
    private String formArrow(int num1, int num2){
        if(num1 == num2)
            return String.valueOf(num1);
        
        return String.valueOf(num1) 
                + "->" + String.valueOf(num2);
    }
    
}