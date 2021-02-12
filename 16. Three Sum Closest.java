// Two pointers technique
public int threeSumClosest(int[] nums, int target) {
        // No assumptions on length.
        Arrays.sort(nums); // Sort the array.
        int []indices = new int[3]; // stores the indices for answer.
        indices[0] = 0; indices[1] = 1; indices[2] = 2;
        
        int sum_best = nums[0] + nums[1] + nums[2];
        int diff_best = Math.abs(sum_best - target); // best difference so far
        
        for(int i=0; i<(nums.length-2); i++){ // loop by leaving last 2 elements
            int left_ptr = i+1; // one idx after 'i'
            int right_ptr = nums.length-1; // last idx
            
            while(left_ptr < right_ptr){ // loop until left pointer and right pointers meet
                int sum_curr = nums[i] + nums[left_ptr] + nums[right_ptr];
                int diff_curr = Math.abs(sum_curr - target); // current difference
                
                
                if(diff_curr < diff_best){ // update for improvement
                    // System.out.println("Updating, diff_curr = " + diff_curr + " , diff_best = " + diff_best + " , i = " + i + " , left_p = " + left_ptr + " right_p = " + right_ptr + " , sum_curr = " + sum_curr + " , sum_best = " + sum_best);
                    indices[0] = i; indices[1] = left_ptr; indices[2] = right_ptr;
                    diff_best = diff_curr;
                    sum_best = sum_curr;
                }
                
                if(sum_curr > target){ // decrement the ending pointer
                    right_ptr --;
                }
                else{ // increment the beginning pointer
                    left_ptr ++; 
                }
            }
        }
        return (nums[indices[0]] + nums[indices[1]] + nums[indices[2]]);
        
    }




// Naive Three Loops Solution
class Solution {
    private int getSum(int n1, int n2, int n3){
        return (n1 + n2 + n3);
    }
    public int threeSumClosest(int[] nums, int target) {
        int max_diff = 2147483647, sum = 0, diff = 0;
        int firstVal, secondVal, thirdVal;
        int idx1=0, idx2=0, idx3=0;
        
        for(int first=0; first<(nums.length - 2); first++){
            firstVal = nums[first];
            for(int second=first+1; second<(nums.length - 1); second++){
                secondVal = nums[second];
                for(int third=second+1; third<nums.length; third++){
                    thirdVal = nums[third];
                    sum = getSum(firstVal, secondVal, thirdVal);
                    diff = sum - target;
                    diff = (diff < 0) ? -diff : diff;
                    
                    if(diff < max_diff){
                        idx1 = first;
                        idx2 = second;
                        idx3 = third;
                        max_diff = diff; // update max_diff
                    }
                }
            }
        }
        
        return getSum(nums[idx1], nums[idx2], nums[idx3]);
    }
}