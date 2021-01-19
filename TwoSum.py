"""
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Output: Because nums[0] + nums[1] == 9, we return [0, 1].

"""

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        map = {} # Key: number, Value: Index
        for i in range(len(nums)):
            num = nums[i]
            
            difference = target - num
            if difference in map:
                if map[difference] != i: # Can't be the same element
                    return list([i, map[difference]])
            
            map[num] = i ## Update later