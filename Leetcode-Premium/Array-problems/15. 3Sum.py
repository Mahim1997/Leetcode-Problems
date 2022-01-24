class Solution:
        
    
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        ans = []
        
        if len(nums) <= 2:
            return ans
        
        nums = sorted(nums)
        
        # Nested function for cleaner code
        def twoSum(arr, left, right, target):
            res = []
            l = left
            r = right
            changed = False
            while(l < r):
                if (l != left) and (arr[l] == arr[l - 1]):
                    l += 1
                    continue
                    
                sumCurr = arr[l] +  arr[r]
                if sumCurr == target:
                    res.append([arr[l], arr[r]])
                    # return res
                    l += 1
                    changed = True
                elif sumCurr < target:
                    l += 1
                elif sumCurr > target:
                    r -= 1
                
            if changed == True:
                return res
            else:
                return None
        
        ### End of function
        # print(nums) # debugging
        
        for itr in range(0, len(nums) - 2):
            ## Do not repeat elements.
            if (itr > 0) and (nums[itr] == nums[itr-1]):
                    continue
                    
            new_target = 0 - nums[itr]
            res_two_sum = twoSum(nums, itr+1, len(nums)-1, new_target)
            if res_two_sum is not None:
                for l in res_two_sum:
                    l.append(nums[itr])
                    ans.append(l)
        
        return ans