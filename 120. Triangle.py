class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        if len(triangle) == 1:
            return min(triangle[0])
        
        for i in range(1, len(triangle)):
            ## get previous row
            row_prev = triangle[i-1]
            
            row_current = triangle[i]
            ## column traversal of current row
            for col_itr in range(len(row_current)):
                if col_itr == 0: # first column do nothing
                    row_current[col_itr] += row_prev[col_itr]
                elif col_itr == (len(row_current) - 1): # second column do nothing
                    row_current[col_itr] += row_prev[col_itr - 1]
                else: # min of top and diagonal
                    row_current[col_itr] += min(
                        row_prev[col_itr - 1],
                        row_prev[col_itr]
                    )
        
        # print(triangle)
        ## return the minimum of final row.
        return min(triangle[len(triangle)-1])