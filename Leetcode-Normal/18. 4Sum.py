class Solution:
    def twoSumSorted(self, nums: List[int], target: int) -> List[List[int]]:
        left = 0 ## left ptr
        right = len(nums) - 1 ## right ptr
        ans = [] ## list of answers.
        while left < right: ## keep looping until left and right meet the same number
            if left != 0: ## check for duplicates
                if nums[left - 1] == nums[left]:
                    left += 1
                    continue
            addition = nums[left] + nums[right]
            if addition < target: ## move the left ptr
                left += 1
            elif addition > target: ## move the right ptr
                right -= 1
            elif addition == target:
                ans.append([nums[left], nums[right]]) ## append to the list
                left += 1 ## increment to get to the next num.
            
            # print(f"left = {left}, right = {right}")
        
        return ans
    
    def threeSum(self, nums: List[int], target) -> List[List[int]]:
        if len(nums) <= 2: ## anythin less than 2 size.
            return None
        # target_outside
        ## sort the nums
        # nums.sort()
        # print(f"sorted nums = {nums}")
        ans = [] # new list.
        for i in range(0, len(nums) - 2): ## upto less than last 2 elements.
            if (i>0) and (nums[i-1] == nums[i]): continue
            a = nums[i]
            target_inside = target - a
            _2lists = self.twoSumSorted(nums[i+1:], target=target_inside) ## skip this idx
            # print(f"For i = {i}, a = {a}, _2lists = {_2lists}")

            for el in _2lists:
                el.append(a)
                ans.append(el)
        
        return ans
    
    def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
        if len(nums) <= 3: return None
        
        ## Sort the nums
        nums.sort()
        ans = []
        
        for i in range(0, len(nums) - 3): ## outer most loop
            if (i > 0) and (nums[i - 1] == nums[i]): continue
            ## Just run the 3SumSorted
            a = nums[i]
            target_inside = target - a
            _3sumList = self.threeSum(nums[i+1:], target=target_inside)
            
            for el in _3sumList:
                el.append(a)
                ans.append(el)
                
#             for j in range(i+1, len(nums) - 2): ## inner loop
#                 if(j > i) and (nums[j - 1] == nums[j]) continue
                
        return ans