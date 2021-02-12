## Naive Solution
##  17. Letter Combinations of a Phone Number
class Solution:
    def getCombinationsTwo(self, list1, list2):
        dictionary = {}
        all_combinations = []
        for each_permutation in itertools.permutations(list1, len(list2)):
            list_perm_list2 = list(zip(each_permutation, list2))
            for l in list_perm_list2:
                s = l[0] + l[1]
                if(s not in dictionary):
                    dictionary[s] = 1
                    all_combinations.append(s)
        
        return all_combinations # all combinations of two lists.

    def getListOfChars(self, c):
        if(c == '2'):
            return ['a','b','c']
        if(c == '3'):
            return ['d', 'e', 'f']
        if(c == '4'):
            return ['g', 'h', 'i']
        if(c == '5'):
            return ['j', 'k', 'l']
        if(c == '6'):
            return ['m', 'n', 'o']
        if(c == '7'):
            return ['p', 'q', 'r', 's']
        if(c == '8'):
            return ['t', 'u', 'v']
        if(c == '9'):
            return ['w', 'x', 'y', 'z']
        
    def letterCombinations(self, digits: str) -> List[str]:
        # for(c in digits):
        if(len(digits) == 0): return None
        
        list_digits = [c for c in digits]
        
        cuml_combinations = self.getListOfChars(list_digits[0])
        for i in range(1, len(list_digits)):
            currList = self.getListOfChars(list_digits[i])
                            ## update cumulative list.
            cuml_combinations = self.getCombinationsTwo(cuml_combinations, currList) 
            
        
        return cuml_combinations
            
            
            