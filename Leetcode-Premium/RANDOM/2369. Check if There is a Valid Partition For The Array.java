enum State {
    FALSE,
    UNASSIGNED,
    TRUE
}

class Solution {    
    private State[] getInitializedCache(int n) {
        State[] cache = new State[n + 1];
        for(int i=0; i<=n; i++) {
            cache[i] = State.UNASSIGNED;
        }
        return cache;
    } 
    
    private boolean getBoolean(State state) {
        return (state == State.TRUE);
    }
    
    private State getState(boolean res) {
        if(res == true)
            return State.TRUE;
        else
            return State.FALSE;
    }
    
    // API
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        State[] cache = getInitializedCache(n);
        State ans = dfs(nums, cache, 0);
        return getBoolean(ans);
    }
    
    
    private boolean isValidTwoNumbers(int[] nums, int idx) {
        return (nums[idx] == nums[idx + 1]);
    }
    
    private boolean isValidThreeNumbersEqual(int[] nums, int idx) {
        return (
            (nums[idx] == nums[idx + 1]) &&
            (nums[idx + 1] == nums[idx + 2])
        );
    }
    
    private boolean isValidThreeNumbersIncreasing(int[] nums, int idx) {
        // Should increase by 1
        return (
            ((nums[idx + 1] - nums[idx]) == 1) &&
            ((nums[idx + 2] - nums[idx + 1]) == 1)
        );
    }
    
    private boolean isValidThreeNumbers(int[] nums, int idx) {
        return (
            isValidThreeNumbersEqual(nums, idx) ||
            isValidThreeNumbersIncreasing(nums, idx)
        );
    }
    
    private boolean isValid(int[] nums, int idxFirst, int idxLast) {
        // Edge cases
        if((idxFirst >= nums.length) || (idxLast >= nums.length))
            return false;
        
        int diff = idxLast - idxFirst;
        if(diff == 1) {
            // two numbers
            return isValidTwoNumbers(nums, idxFirst);
        }
        else {
            // three numbers
            return isValidThreeNumbers(nums, idxFirst);
        }
    }
    
    private State AND(State state1, State state2) {
        if((state1 == State.UNASSIGNED) || (state2 == State.UNASSIGNED))
            return State.UNASSIGNED;
        
        if((state1 == State.TRUE) && (state2 == State.TRUE))
            return State.TRUE;
        
        return State.FALSE;
    }
    
    private State OR(State state1, State state2) {
        if((state1 == State.UNASSIGNED) || (state2 == State.UNASSIGNED))
            return State.UNASSIGNED;
        
        if((state1 == State.FALSE) && (state2 == State.FALSE))
            return State.FALSE;
        
        return State.TRUE;
    }
    
    private State dfs(int[] nums, State[] cache, int startIdx) {
        // Base cases
        if(startIdx > nums.length)
            return State.FALSE;
        
        if(startIdx == nums.length)
            return State.TRUE;
    
        // Cache check
        if(cache[startIdx] != State.UNASSIGNED)
            return cache[startIdx];
        
        // Recursive
        State twoState = AND(
            getState(isValid(nums, startIdx, startIdx + 1)), 
            dfs(nums, cache, startIdx + 2)
        );
        
        State threeState = AND(
            getState(isValid(nums, startIdx, startIdx + 2)),
            dfs(nums, cache, startIdx + 3)
        );
        
        State ans = OR(twoState, threeState);
        
        // Update Cache
        cache[startIdx] = ans;
        
        // Return
        return ans;
    }
}

