class Solution:
    def permutation(self, nums, ans, start, end):
        if start == end: # append to answer.
            ans.append(list(nums))
        
        for i in range(start, end+1):
            ## Swap
            nums[i], nums[start] = nums[start], nums[i]
            
            ## Recurse
            self.permutation(nums, ans, start+1, end)
            
            ## Back swap
            nums[i], nums[start] = nums[start], nums[i]
    
    
    def permute(self, nums: List[int]) -> List[List[int]]:
        ans = []
        nums.sort()
        
        self.permutation(nums, ans, 0, len(nums)-1)
        
        
        return ans